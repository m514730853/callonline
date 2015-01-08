package com.netviom.callonline.fragment;

import android.app.Fragment;
import android.view.View;

import com.netviom.callonline.R;
import com.netviom.callonline.definedwidget.TopLayout;

/**
 * Created by tanyong on 2014/12/11.
 */
public abstract class BaseFragment  extends Fragment{
    protected TopLayout top;

    public void initTop(View view,String title,boolean showLefe,String rightText)
    {
        top = (TopLayout) view.findViewById(R.id.top);
        top.init(false,title,rightText);
    }
}
