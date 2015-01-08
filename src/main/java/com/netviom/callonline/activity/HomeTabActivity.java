package com.netviom.callonline.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;

import com.netviom.callonline.R;
import com.netviom.callonline.fragment.AddressListHomeFragment;
import com.netviom.callonline.fragment.ConfigFragment;
import com.netviom.callonline.fragment.PboxCallLogFragment;
import com.netviom.callonline.fragment.PboxResponLogFragment;
import com.netviom.callonline.fragment.SendSmsFragment;

/**
 * Created by tanyong on 2014/12/11.
 */
public class HomeTabActivity extends BaseActivity implements View.OnClickListener {
    private PboxCallLogFragment pboxCallLogFragment;
    private FragmentManager manager;
    private AddressListHomeFragment addressListHomeFragment;
    private SendSmsFragment sendSmsFragment;
    private PboxResponLogFragment pboxResponLogFragment;
    private ConfigFragment configFragment;
    @Override
    public void initView() {
        setContentView(R.layout.home_tab);
        manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        pboxCallLogFragment = new PboxCallLogFragment();
        transaction.add(R.id.fra_home, pboxCallLogFragment);
        transaction.commit();
    }

    @Override
    public void initListener() {
        findViewById(R.id.btn_call_log).setOnClickListener(this);
        findViewById(R.id.btn_address_list).setOnClickListener(this);
        findViewById(R.id.btn_mass_texting).setOnClickListener(this);
        findViewById(R.id.btn_hang_sms).setOnClickListener(this);
        findViewById(R.id.btn_more).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragment(transaction);
        switch (view.getId()) {
            case R.id.btn_call_log:
                if (null == pboxCallLogFragment) {
                    pboxCallLogFragment = new PboxCallLogFragment();
                    transaction.add(R.id.fra_home, pboxCallLogFragment);
                } else {
                    transaction.show(pboxCallLogFragment);
                }
                break;
            case R.id.btn_address_list:
                if (null == addressListHomeFragment) {
                    addressListHomeFragment = new AddressListHomeFragment();
                    transaction.add(R.id.fra_home, addressListHomeFragment);
                } else {
                    transaction.show(addressListHomeFragment);
                }
                break;
            case R.id.btn_mass_texting:
                if (null == sendSmsFragment) {
                    sendSmsFragment = new SendSmsFragment();
                    transaction.add(R.id.fra_home, sendSmsFragment);
                } else {
                    transaction.show(sendSmsFragment);
                }
                break;
            case R.id.btn_hang_sms:
                if (null == pboxResponLogFragment) {
                    pboxResponLogFragment = new PboxResponLogFragment();
                    transaction.add(R.id.fra_home, pboxResponLogFragment);
                } else {
                    transaction.show(pboxResponLogFragment);
                }
                break;
            case R.id.btn_more:
                if (null == configFragment) {
                    configFragment = new ConfigFragment();
                    transaction.add(R.id.fra_home, configFragment);
                } else {
                    transaction.show(configFragment);
                }
                break;
        }
        transaction.commit();
    }

    public void hideFragment(FragmentTransaction transaction) {
        if (null != pboxCallLogFragment) {
            transaction.hide(pboxCallLogFragment);
        }
        if (null != addressListHomeFragment) {
            transaction.hide(addressListHomeFragment);
        }
        if (null != sendSmsFragment) {
            transaction.hide(sendSmsFragment);

        }
        if (null != pboxResponLogFragment) {
            transaction.hide(pboxResponLogFragment);

        }
        if (null != configFragment) {
            transaction.hide(configFragment);

        }

    }
}
