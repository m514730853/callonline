package com.netviom.callonline.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CompanyAddressListFragment extends BaseAddressListFragment {

    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setGroupType("100");
        return super.onCreateView(inflater,container,savedInstanceState);
    }
}
