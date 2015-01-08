package com.netviom.callonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.netviom.callonline.R;
import com.nvm.common.constant.XmlCmd;
import com.nvm.common.http.Request;
import com.nvm.common.response.vo.Resp;
import com.nvm.common.response.vo.SmscommoninfoResp;
import com.nvm.common.response.vo.SmssignaResp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SmsSignaActivity extends BaseActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_sms_common_info);
        initTop("Ç©Ãû",true,"");
        listView = (ListView) findViewById(R.id.listView2);
        initDatas();
    }

    @Override
    public void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String content = list.get(i);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("content",content);
                intent.putExtras(bundle);
                setResult(RESULT_FIRST_USER,intent);
                finish();
            }
        });

    }
    public void initDatas()
    {
        Request request = new Request(XmlCmd.smsSigna.getValue(),null,null);
        request.setOnCallBackListener(new Request.OnCallback() {
            @Override
            public void onSuccessCallback(List<Resp> datas, int status) {
                for(Iterator iterator = datas.iterator();iterator.hasNext();)
                {
                    SmssignaResp resp = (SmssignaResp) iterator.next();
                    list.add(resp.getSigna_info());
                }
                adapter = new ArrayAdapter<String>(SmsSignaActivity.this,R.layout.textview,list);
                listView.setAdapter(adapter);
            }
        });
        request.execute();
    }
}
