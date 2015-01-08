package com.netviom.callonline.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.netviom.callonline.R;
import com.nvm.common.constant.ParameterDatas;
import com.nvm.common.constant.XmlCmd;
import com.nvm.common.http.Request;
import com.nvm.common.response.vo.Resp;
import com.nvm.common.response.vo.UserinfoResp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UserInfoActivity extends BaseActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

    @Override
    public void initView() {
        setContentView(R.layout.activity_user_info);
        initTop("个人信息",true,"");
        listView = (ListView) findViewById(R.id.user_info_listview);
        initDatas();
    }

    @Override
    public void initListener() {

    }

    public void initDatas()
    {
        Request request = new Request(XmlCmd.userInfo.getValue(),null,null);
        request.setOnCallBackListener(new Request.OnCallback() {
            @Override
            public void onSuccessCallback(List<Resp> datas, int status) {
                initListview(datas);
            }
        });
        request.execute();
    }
    public void initListview(List datas)
    {
        for(Iterator iterator = datas.iterator();iterator.hasNext();)
        {
            UserinfoResp resp = (UserinfoResp) iterator.next();
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put(ParameterDatas.TITLE,"登陆账号："+resp.getAccount());
            list.add(map1);

            Map<String,Object> map2 = new HashMap<String,Object>();
            map2.put(ParameterDatas.TITLE,"手机号码："+resp.getMobile());
            list.add(map2);
            Map<String,Object> map3 = new HashMap<String,Object>();
            map3.put(ParameterDatas.TITLE,"注册时间："+resp.getAdd_date());
            list.add(map3);
            Map<String,Object> map4 = new HashMap<String,Object>();
            String sex ="";
            if(resp.getSex()==1)
            {
                sex="男";

            }
            else if(resp.getSex()==0)
            {
                sex="女";
            }
            map4.put(ParameterDatas.TITLE, "性别：" + sex);
            list.add(map4);
        }
        adapter = new SimpleAdapter(this,list,R.layout.simple_adapter_user_info,new String[]{ParameterDatas.TITLE},new int[]{R.id.item_text});
        listView.setAdapter(adapter);
    }


}
