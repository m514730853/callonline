package com.netviom.callonline.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.netviom.callonline.R;

public class AddressListHomeFragment extends BaseFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    private PhoneAddressListFragment phoneAddressListFragment;
    private PersonalAddressListFragment personalAddressListFragment;
    private CompanyAddressListFragment companyAddressListFragment;
    private CustomerAddressListFragment customerAddressListFragment;
    private FragmentManager manager;
    private Button btnLocal;
    private Button btnPersenal;
    private Button btncompany;
    private Button btncustomer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adress_list_home, container, false);
        initTop(view, "ͨѶ¼", false, "");
        manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        phoneAddressListFragment = new PhoneAddressListFragment();
        transaction.add(R.id.address_list_fragment, phoneAddressListFragment);
        transaction.commit();
        btnLocal = (Button) view.findViewById(R.id.btn_local);
        btnPersenal = (Button) view.findViewById(R.id.btn_personal);
        btncompany = (Button) view.findViewById(R.id.btn_company);
        btncustomer = (Button) view.findViewById(R.id.btn_customer);
        btnLocal.setOnClickListener(this);
        btnPersenal.setOnClickListener(this);
        btncompany.setOnClickListener(this);
        btncustomer.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragment(transaction);
        initBackGround();
        switch (view.getId()) {
            case R.id.btn_local:
                btnLocal.setBackgroundResource(R.drawable.menu2_hover);
                if (null == phoneAddressListFragment) {
                    phoneAddressListFragment = new PhoneAddressListFragment();
                    transaction.add(R.id.address_list_fragment, phoneAddressListFragment);
                } else {
                    transaction.show(phoneAddressListFragment);
                }
                break;
            case R.id.btn_personal:
                btnPersenal.setBackgroundResource(R.drawable.menu2_hover);
                if (null == personalAddressListFragment) {
                    personalAddressListFragment = new PersonalAddressListFragment();
                    transaction.add(R.id.address_list_fragment, personalAddressListFragment);
                } else {
                    transaction.show(personalAddressListFragment);
                }
                break;
            case R.id.btn_company:
                btncompany.setBackgroundResource(R.drawable.menu2_hover);
                if (null == companyAddressListFragment) {
                    companyAddressListFragment = new CompanyAddressListFragment();
                    transaction.add(R.id.address_list_fragment, companyAddressListFragment);
                } else {
                    transaction.show(companyAddressListFragment);
                }
                break;
            case R.id.btn_customer:
                btncustomer.setBackgroundResource(R.drawable.menu2_hover);
                if (null == customerAddressListFragment) {
                    customerAddressListFragment = new CustomerAddressListFragment();
                    transaction.add(R.id.address_list_fragment, customerAddressListFragment);
                } else {
                    transaction.show(customerAddressListFragment);
                }
                break;
        }
        transaction.commit();
    }

    public void initBackGround() {
        btnLocal.setBackgroundResource(R.drawable.menu2);
        btnPersenal.setBackgroundResource(R.drawable.menu2);
        btncompany.setBackgroundResource(R.drawable.menu2);
        btncustomer.setBackgroundResource(R.drawable.menu2);
    }

    public void hideFragment(FragmentTransaction transaction) {
        if (null != phoneAddressListFragment) {
            transaction.hide(phoneAddressListFragment);
        }
        if (null != personalAddressListFragment) {
            transaction.hide(personalAddressListFragment);
        }
        if (null != companyAddressListFragment) {
            transaction.hide(companyAddressListFragment);
        }
        if (null != customerAddressListFragment) {
            transaction.hide(customerAddressListFragment);
        }
    }
}
