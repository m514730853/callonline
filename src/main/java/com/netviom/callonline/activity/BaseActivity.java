package com.netviom.callonline.activity;


import com.netviom.callonline.R;
import com.netviom.callonline.definedwidget.TopLayout;
import com.nvm.common.constant.MyApplication;
import com.nvm.common.response.vo.StaticDatas;
import com.nvm.common.util.ActivityUtil;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {
	protected TopLayout top;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActivityUtil.addActivity(this);
		initView();
		initListener();
	}
	public void showToast(String s)
	{
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityUtil.removeActivity(this);
	}
	public void initTop(String title,boolean showLeft,String rightText){
     if(null==top)
     {
            top= (TopLayout) findViewById(R.id.top);
     }
        top.init(showLeft,title,rightText);
	}
    public StaticDatas getDatas()
    {
        return ((MyApplication)getApplication()).getDatas();
    }
	public abstract void initView();
	public abstract void initListener();
}
