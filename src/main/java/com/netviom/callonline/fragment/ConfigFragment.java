package com.netviom.callonline.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.netviom.callonline.R;
import com.netviom.callonline.activity.EditPasswordActivity;
import com.netviom.callonline.activity.SpUserInfoActivity;
import com.netviom.callonline.activity.UserInfoActivity;
import com.netviom.callonline.adapter.ConfigAdapter;
import com.netviom.callonline.adapter.GroupAdapter;
import com.netviom.callonline.adapter.bean.ConfigAdapterBean;
import com.netviom.callonline.listener.DoAction;
import com.nvm.common.response.vo.UserinfoResp;
import com.nvm.common.util.IntentUtil;
import com.nvm.common.util.ListViewUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigFragment extends BaseFragment {
    private ListView listView;
    private ConfigAdapter adapter;
    private List<ConfigAdapterBean> list = new ArrayList<ConfigAdapterBean>();
    public ConfigFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_config, container, false);
        initTop(view,"更多",false,"");
        listView = (ListView) view.findViewById(R.id.config_listview);
        initListView();
        initListener();
        return view;
    }
    public void initListView()
    {
        ConfigAdapterBean bean1 =new ConfigAdapterBean();
        bean1.setName("个人信息");
        bean1.setImgDrawableLeft(R.drawable.icon_username);
        bean1.setDoAction(new DoAction() {
            @Override
            public void doAction() {
                IntentUtil.switchIntent(getActivity(), UserInfoActivity.class);
            }
        });
        list.add(bean1);

        ConfigAdapterBean bean2 =new ConfigAdapterBean();
        bean2.setName("修改密码");
        bean2.setImgDrawableLeft(R.drawable.icon_editpw2);
        bean2.setDoAction(new DoAction() {
            @Override
            public void doAction() {
                IntentUtil.switchIntent(getActivity(), EditPasswordActivity.class);
            }
        });
        list.add(bean2);

        ConfigAdapterBean bean3 =new ConfigAdapterBean();
        bean3.setName("企业信息");
        bean3.setDoAction(new DoAction() {
            @Override
            public void doAction() {
                IntentUtil.switchIntent(getActivity(), SpUserInfoActivity.class);
            }
        });
        list.add(bean3);

        adapter = new ConfigAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        ListViewUtil.setListViewHeightByItem(listView);
    }
    public void initListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ConfigAdapterBean bean = list.get(i);
                bean.getDoAction().doAction();
            }
        });
    }


}
