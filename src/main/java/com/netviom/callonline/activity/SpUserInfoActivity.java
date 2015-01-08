package com.netviom.callonline.activity;

import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.netviom.callonline.R;
import com.nvm.common.constant.ParameterDatas;
import com.nvm.common.constant.XmlCmd;
import com.nvm.common.http.Request;
import com.nvm.common.response.vo.Resp;
import com.nvm.common.response.vo.SpuserinfoResp;
import com.nvm.common.response.vo.UserinfoResp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SpUserInfoActivity extends BaseActivity {

    private ListView listView;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

    @Override
    public void initView() {
        setContentView(R.layout.activity_sp_user_info);
        initTop("企业信息",true,"");
        listView = (ListView) findViewById(R.id.sp_user_info_listview);
        initDatas();
    }

    @Override
    public void initListener() {

    }

    public void initDatas()
    {
        Request request = new Request(XmlCmd.spUserInfo.getValue(),null,null);
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
            SpuserinfoResp resp = (SpuserinfoResp) iterator.next();
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put(ParameterDatas.TITLE,"企业名称："+resp.getUsername());
            list.add(map1);

            Map<String,Object> map2 = new HashMap<String,Object>();
            map2.put(ParameterDatas.TITLE,"企业编号："+resp.getSp_seqid());
            list.add(map2);
            Map<String,Object> map3 = new HashMap<String,Object>();
            map3.put(ParameterDatas.TITLE,"注册时间："+resp.getReg_date());
            list.add(map3);
            Map<String,Object> map4 = new HashMap<String,Object>();
            String status ="";
            if(resp.getStatus()==1)
            {
                status="审核通过";

            }
            else
            {
                status="审核未通过";
            }
            map4.put(ParameterDatas.TITLE, "审核状态：" + status);
            list.add(map4);
        }
        adapter = new SimpleAdapter(this,list,R.layout.simple_adapter_user_info,new String[]{ParameterDatas.TITLE},new int[]{R.id.item_text});
        listView.setAdapter(adapter);
    }
}
