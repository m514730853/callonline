package com.netviom.callonline.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.netviom.callonline.R;
import com.nvm.common.constant.XmlStatus;
import com.nvm.common.response.vo.User;
import com.nvm.common.services.DatasServices;
import com.nvm.common.sqllite.DataBaseHelper;
import com.nvm.common.util.DbUtil;
import com.nvm.common.util.IntentUtil;

import java.util.List;

//欢迎界面可以初始化一些数据
public class WelcomeActivity extends Activity {
    private DataBaseHelper dbhepler;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        dbhepler = new DataBaseHelper(this, DataBaseHelper.getDbname(), null, DataBaseHelper.getVersion());
        dbhepler.getWritableDatabase();
        db = DataBaseHelper.dbDatabase(this);
        init();
    }

    public void init() {
        login();
    }

    public void login() {
        User user = new User();
        List list = DbUtil.find(user, "loginTime", null, db);
        if (list.size() <=0) {
            IntentUtil.switchIntent(WelcomeActivity.this,LoginActivity.class);
            return;

        }
        User u = (User) list.get(0);
        String username = u.getUsername();
        String passwrod = u.getPassword();
        DatasServices services = new DatasServices();
        services.setDoHandler(new DatasServices.DoHandler() {
            @Override
            public void doNext(List datas, int status) {
                if(status== XmlStatus.HTTP_RESPONSE_SUCCESS)
                {
                    IntentUtil.switchIntent(WelcomeActivity.this,HomeTabActivity.class);
                    return;
                }
                IntentUtil.switchIntent(WelcomeActivity.this,LoginActivity.class);
            }
        });
        services.login(username,passwrod);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        dbhepler.close();
    }
}
