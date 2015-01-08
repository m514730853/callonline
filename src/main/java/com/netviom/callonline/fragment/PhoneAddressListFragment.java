package com.netviom.callonline.fragment;


import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.netviom.callonline.R;
import com.netviom.callonline.adapter.GroupAdapter;
import com.netviom.callonline.adapter.bean.GroupAdapterBean;

import java.util.ArrayList;
import java.util.List;

public class PhoneAddressListFragment extends Fragment {
    private ListView listView;
    private GroupAdapter adapter;
    private List<GroupAdapterBean> list;
    private final String[] PHONES_PROJECTION = new String[]{
            Phone.DISPLAY_NAME, Phone.NUMBER};
    private final int CONTACTS_NAME = 0;
    private final int PHONE_NUMBER = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address_list, container, false);
        listView = (ListView) view.findViewById(R.id.phone_address_list_listview);
        list = new ArrayList<GroupAdapterBean>();
        adapter = new GroupAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        getLocalPhoneNumber();
        return view;
    }

    public void getLocalPhoneNumber() {
        //获取本地通讯录
        ContentResolver contentResolver = getActivity().getContentResolver();
        Cursor cursor = contentResolver.query(Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
        if (null != cursor) {
            while (cursor.moveToNext()) {
                GroupAdapterBean bean = new GroupAdapterBean();
                bean.setName(cursor.getString(CONTACTS_NAME));
                bean.setPhoneNumber(cursor.getString(PHONE_NUMBER));
                bean.setGroup(false);
                list.add(bean);
            }
        }
        //获取SIM卡通讯录
        Uri uri = Uri.parse("content://icc/adn");
        Cursor simCursorv = contentResolver.query(uri,PHONES_PROJECTION,null,null,null);
        if (null != simCursorv) {
            while (simCursorv.moveToNext()) {
                GroupAdapterBean bean = new GroupAdapterBean();
                bean.setName(simCursorv.getString(CONTACTS_NAME));
                bean.setPhoneNumber(simCursorv.getString(PHONE_NUMBER));
                bean.setGroup(false);
                list.add(bean);
            }
        }
        adapter.notifyDataSetChanged();
    }

}
