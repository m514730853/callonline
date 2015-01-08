package com.netviom.callonline.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.netviom.callonline.R;
import com.netviom.callonline.adapter.GroupAdapter;
import com.netviom.callonline.adapter.bean.GroupAdapterBean;
import com.nvm.common.constant.XmlCmd;
import com.nvm.common.http.Request;
import com.nvm.common.response.vo.GroupResp;
import com.nvm.common.response.vo.Resp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PersonalAddressListFragment extends BaseAddressListFragment {

    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setGroupType("101");
        return super.onCreateView(inflater,container,savedInstanceState);
    }
}
