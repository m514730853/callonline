package com.netviom.callonline.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.netviom.callonline.R;
import com.netviom.callonline.adapter.PboxResponLogAdapter;
import com.netviom.callonline.adapter.bean.PboxCallLogAdapterBean;
import com.netviom.callonline.adapter.bean.PboxResponLogAdapterBean;
import com.nvm.common.constant.XmlCmd;
import com.nvm.common.http.Request;
import com.nvm.common.response.vo.PboxresponlogResp;
import com.nvm.common.response.vo.Resp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PboxResponLogFragment extends BaseFragment {
    private ListView listView;
    private PboxResponLogAdapter adapter;
    private List<PboxResponLogAdapterBean> list = new ArrayList<PboxResponLogAdapterBean>();
    public PboxResponLogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pbox_respon_log, container, false);
        listView = (ListView) view.findViewById(R.id.pbox_respon_listview);
        initTop(view,"¹Ò»ú¶ÌÐÅ",false,"");
        initDatas();
        return view;
    }
    public  void initDatas()
    {
       Request request = new Request(XmlCmd.pboxResponLog.getValue(),new String[]{"pageno","pagesize"},new String[]{"1","100"});
        request.setOnCallBackListener(new Request.OnCallback() {
            @Override
            public void onSuccessCallback(List<Resp> datas, int status) {

                initListView(datas);
            }
        });
        request.execute();
    }
    public void initListView(List datas)
    {
        for(Iterator iterator = datas.iterator();iterator.hasNext();)
        {
            PboxresponlogResp resp = (PboxresponlogResp) iterator.next();
            PboxResponLogAdapterBean bean = new PboxResponLogAdapterBean();
            bean.setUsernumber(resp.getUsernumber());
            bean.setSmsContent(resp.getSms_content());
            bean.setRespTime(resp.getResp_time());
            list.add(bean);
        }
        adapter = new PboxResponLogAdapter(getActivity(),list);
        listView.setAdapter(adapter);
    }



}
