package com.netviom.callonline.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.netviom.callonline.R;
import com.netviom.callonline.adapter.PboxCallLogAdapter;
import com.netviom.callonline.adapter.bean.PboxCallLogAdapterBean;
import com.nvm.common.constant.XmlCmd;
import com.nvm.common.http.Request;
import com.nvm.common.response.vo.PboxcalllogResp;
import com.nvm.common.response.vo.Resp;
import com.nvm.common.util.DateUtil;
import com.nvm.common.util.LogUtil;
import com.nvm.common.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tanyong on 2014/12/11.
 */
public class PboxCallLogFragment extends BaseFragment {
    private ListView listView;
    private List<PboxCallLogAdapterBean> list = new ArrayList<PboxCallLogAdapterBean>();;
    private PboxCallLogAdapter adapter;
    private String currenttime="";
    private Button btnMore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pbox_call_log, container, false);
        initTop(view,"Í¨»°¼ÇÂ¼",false,"");
        listView = (ListView) view.findViewById(R.id.list_pbox_call_log);
        adapter = new PboxCallLogAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        btnMore = (Button) view.findViewById(R.id.btn_more);
        if(StringUtils.isEmpty(currenttime))
        {
            currenttime = DateUtil.getYYYY_MM_DD();
        }
        initDatas();
        initListener();
        return view;
    }

    public void initDatas() {
        Request request = new Request(XmlCmd.pboxCallLog.getValue(), new String[]{"starttime","endtime"}, new String[]{currenttime,""});
        request.execute();
        request.setOnCallBackListener(new Request.OnCallback() {
            @Override
            public void onSuccessCallback(List<Resp> datas,int status) {
                // TODO Auto-generated method stub
                PboxCallLogAdapterBean bean = new PboxCallLogAdapterBean();
                bean.setItemType(PboxCallLogAdapterBean.TIME_ITEM);
                bean.setItemTextTime(currenttime);
                list.add(bean);
                initListView(datas);
            }
        });
    }
    public void initListener()
    {
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currenttime = DateUtil.addDays(currenttime,-1);
                initDatas();
            }
        });
    }

    public void initListView(List datas) {
        LogUtil.info("list.size():"+list.size());
        if (null==datas||datas.size()<=0) {
            PboxCallLogAdapterBean bean = new PboxCallLogAdapterBean();
            bean.setItemType(PboxCallLogAdapterBean.TIPS_ITEM);
            list.add(bean);
            adapter.notifyDataSetChanged();
            return;
        }
        for(int i=0;i<datas.size();i++)
        {
            PboxcalllogResp resp = (PboxcalllogResp) datas.get(i);
            PboxCallLogAdapterBean bean = new PboxCallLogAdapterBean();
            if(datas.size()<=1)
            {
                bean.setBackgroundType(PboxCallLogAdapterBean.ALL);
            }
            else if(i==0)
            {
                bean.setBackgroundType(PboxCallLogAdapterBean.TOP);
            }
            else if(i==datas.size()-1)
            {
                bean.setBackgroundType(PboxCallLogAdapterBean.BOTTOM);
            }
            bean.setUsernumber(resp.getUsernumber());
            bean.setStartTime(resp.getStart_time());
            bean.setCalledTimes(resp.getCalled_count());
            bean.setName(resp.getName());
           list.add(bean);
        }
        adapter.notifyDataSetChanged();
    }
}
