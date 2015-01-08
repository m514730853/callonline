package com.netviom.callonline.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.netviom.callonline.R;
import com.netviom.callonline.adapter.GroupAdapter;
import com.netviom.callonline.adapter.bean.GroupAdapterBean;
import com.nvm.common.constant.XmlCmd;
import com.nvm.common.http.Request;
import com.nvm.common.response.vo.ContactsResp;
import com.nvm.common.response.vo.GroupResp;
import com.nvm.common.response.vo.Resp;
import com.nvm.common.util.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tanyong on 2014/12/15.
 */
public class BaseAddressListFragment extends Fragment {
    private ListView listView;
    private GroupAdapter adapter;
    private List<GroupAdapterBean> list = new ArrayList<GroupAdapterBean>();
    private String groupType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_personal_address_list, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        adapter = new GroupAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        initGroup();
        initListener();
        return view;
    }

    public void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GroupAdapterBean bean = list.get(i);
                if (bean.isExpand()) {
                    while ((i+1)<list.size()) {
                        if (list.get(i + 1).isGroup()) {
                            break;
                        } else {
                            list.remove(i + 1);
                        }
                    }
                    list.get(i).setExpand(false);
                    adapter.notifyDataSetChanged();
                    return;
                }
                if (bean.isGroup()) {
                    list.get(i).setExpand(true);
                    List<ContactsResp> childlist = bean.getChildList();
                    if(null!=childlist)
                    {
                        initContactsView(childlist,i);
                        return;
                    }
                    GroupAdapterBean tipbean = new GroupAdapterBean();
                    tipbean.setTips(true);
                    list.add(i + 1, tipbean);
                    list.get(i).setExpand(true);
                    adapter.notifyDataSetChanged();
                    initContacts(bean.getId(), i);
                }
            }
        });
    }

    public void initGroup() {
        Request request = new Request(XmlCmd.group.getValue(), new String[]{"grouptype"}, new String[]{groupType});
        request.setOnCallBackListener(new Request.OnCallback() {
            @Override
            public void onSuccessCallback(List<Resp> datas,int status) {
                initListView(datas);
            }
        });
        request.execute();
    }

    public void initListView(List datas) {
        for (Iterator iterator = datas.iterator(); iterator.hasNext(); ) {
            GroupResp resp = (GroupResp) iterator.next();
            GroupAdapterBean bean = new GroupAdapterBean();
            bean.setId(resp.getGroup_seqid());
            bean.setName(resp.getGroup_name());
            bean.setGroup(true);
            list.add(bean);
        }
        adapter.notifyDataSetChanged();
    }
    public void initContactsView(List datas,int position)
    {
        List<ContactsResp> childList = new ArrayList<ContactsResp>();
        for (Iterator iterator = datas.iterator(); iterator.hasNext(); ) {
            ContactsResp resp = (ContactsResp) iterator.next();
            GroupAdapterBean bean = new GroupAdapterBean();
            bean.setName(resp.getName());
            bean.setPhoneNumber(resp.getUsernumber());
            bean.setId(resp.getContacts_seqid());
            bean.setGroup(false);
            childList.add(resp);
            list.add(position + 1, bean);
        }
        if(null!= list.get(position).getChildList())
        {
            list.get(position).getChildList().clear();
        }
        list.get(position).setChildList(childList);
        adapter.notifyDataSetChanged();
    }
    public void initContacts(String groupid, final int position) {
        Request request = new Request(XmlCmd.contacts.getValue(), new String[]{"groupid"}, new String[]{groupid});
        request.setOnCallBackListener(new Request.OnCallback() {
            @Override
            public void onSuccessCallback(List<Resp> datas,int status) {
                list.remove(position + 1);
                initContactsView(datas,position);
            }
        });
        request.execute();
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }
}
