package com.netviom.callonline.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.netviom.callonline.R;
import com.nvm.common.constant.XmlCmd;
import com.nvm.common.constant.XmlStatus;
import com.nvm.common.http.Request;
import com.nvm.common.response.vo.Resp;
import com.nvm.common.util.IntentUtil;
import com.nvm.common.util.StringUtils;

import java.util.List;

public class EditPasswordActivity extends BaseActivity {
    private TextView texUsername;
    private EditText ediOldPassword;
    private EditText ediNewPassword;
    private EditText ediReNewPassword;
    private Button btnSubmit;

    @Override
    public void initView() {
        setContentView(R.layout.activity_edit_password);
        initTop("�޸�����",true,"");
        texUsername = (TextView) findViewById(R.id.tex_username);
        texUsername.setText(getDatas().getUsername());
        ediOldPassword = (EditText) findViewById(R.id.edi_old_password);
        ediNewPassword = (EditText) findViewById(R.id.edi_new_password);
        ediReNewPassword = (EditText) findViewById(R.id.edi_re_new_password);
        btnSubmit = (Button) findViewById(R.id.btn_subbmit);
    }

    @Override
    public void initListener() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = texUsername.getText().toString();
                String oldpassword = ediOldPassword.getText().toString();
                String newpassword = ediNewPassword.getText().toString();
                String renewpassword = ediReNewPassword.getText().toString();
                if(StringUtils.isEmpty(oldpassword))
                {
                         showToast("�����벻��Ϊ�գ�");
                    return;
                }
                if(StringUtils.isEmpty(newpassword))
                {
                    showToast("�����벻��Ϊ�գ�");
                    return;
                }
                if(StringUtils.isEmpty(renewpassword))
                {
                    showToast("�ظ����벻��Ϊ�գ�");
                    return;
                }
                if(!newpassword.equals(renewpassword))
                {
                    showToast("���������ظ����벻һ�£�");
                    return;
                }
                update(oldpassword,newpassword);
            }
        });

    }
    public void update(String oldpassword,String newpassword)
    {
        Request request = new Request(XmlCmd.editPassowrd.getValue(),new String[]{"oldpassword","newpassword"},new String[]{oldpassword,newpassword});
        request.setOnCallBackListener(new Request.OnCallback() {
            @Override
            public void onSuccessCallback(List<Resp> datas, int status) {
                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        IntentUtil.switchIntentClearAllActivityCache(EditPasswordActivity.this, LoginActivity.class);
                    }
                };
                new AlertDialog.Builder(EditPasswordActivity.this).setTitle("��ʾ").setMessage("������³ɹ�������Ҫ���µ�¼��").setPositiveButton("ȷ��",listener).show();
            }
        });
        request.execute();
    }

}
