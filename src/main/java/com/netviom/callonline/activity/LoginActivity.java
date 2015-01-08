package com.netviom.callonline.activity;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.netviom.callonline.R;
import com.nvm.common.constant.XmlStatus;
import com.nvm.common.response.vo.User;
import com.nvm.common.services.DatasServices;
import com.nvm.common.sqllite.DataBaseHelper;
import com.nvm.common.util.DateUtil;
import com.nvm.common.util.DbUtil;
import com.nvm.common.util.IntentUtil;


public class LoginActivity extends BaseActivity {
	private EditText ediUsername;
	private EditText ediPassword;
	private Button   btnLogin;
	private CheckBox cheRemPass;
	private SQLiteDatabase db;
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.login);
        initTop("用户登录",false,"");
		ediUsername = (EditText) findViewById(R.id.edi_username);
		ediPassword = (EditText) findViewById(R.id.edi_password);
		cheRemPass = (CheckBox) findViewById(R.id.che_rem_password);
		btnLogin = (Button) findViewById(R.id.btn_login);
		db= DataBaseHelper.dbDatabase(this);
		lastLoginUser();
	}
	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnLogin.setText("正在登陆...");
				top.setTopProVisible(View.VISIBLE);
				final String username = ediUsername.getText().toString();
				final String password = ediPassword.getText().toString();
                DatasServices services = new DatasServices();
                services.setDoHandler(new DatasServices.DoHandler() {
                    @Override
                    public void doNext(List datas,int status) {
                        if(status== XmlStatus.HTTP_RESPONSE_SUCCESS)
                        {
                            loginSuccess(username,password);
                        }
                        btnLogin.setText("登陆");
                        top.setTopProVisible(View.GONE);
                    }
                });
                services.login(username,password);
			}
		});
	}
	//登陆成功后，保存账号和密码在本地数据库
	public void loginSuccess(String username,String password)
	{
		User user = new User();
		user.setUsername(username);
		List<Object> list = DbUtil.find(user, null, null, db);
		if(list.size()>0)
		{
			User u = new User();
			u.setLoginTime(DateUtil.getLongDate());
			DbUtil.update(u, new String[]{"Username"}, new String[]{username}, db);
		}
        else
        {
            user.setPassword(password);
            DbUtil.insert(user,db);
        }
        IntentUtil.switchIntent(LoginActivity.this, HomeTabActivity.class);
	}
	//上次登陆的用户
	public void lastLoginUser()
	{
		User user = new User();
		List<Object> list = DbUtil.find(user, "LoginTime","DESC",db);
		if(list.size()>0)
		{
			User u = (User) list.get(0);
			ediUsername.setText(u.getUsername());
			ediPassword.setText(u.getPassword());
		}
	}
}
