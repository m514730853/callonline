package com.netviom.callonline.definedwidget;

import com.netviom.callonline.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.AvoidXfermode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopLayout extends RelativeLayout {
	private Button btnBack;
	private TextView texTitle;
	private Button btnRight;
	private OnTopClickListener listener;
	private ProgressBar topLoading;
	public TopLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.top, this);
		texTitle = (TextView) findViewById(R.id.tex_title);
		btnRight = (Button) findViewById(R.id.btn_right);
        btnBack = (Button) findViewById(R.id.btn_back);
		topLoading = (ProgressBar) findViewById(R.id.top_loding);
	}
	public void init(boolean showLeftImg, String title, String rightText) {
		if (null != title) {
			texTitle.setText(title);
		}
		if (null != rightText) {
			btnRight.setText(rightText);
			btnRight.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(null!=listener)
					{
						listener.rightBtnClickListener();
					}
				}
			});
		}
		if (showLeftImg) {
            btnBack.setVisibility(View.VISIBLE);
            btnBack.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					((Activity)getContext()).finish();
				}
			});
		} else
            btnBack.setVisibility(View.GONE);
	}
    public void setTopProVisible(int visible)
    {
    	this.topLoading.setVisibility(visible);
    }
	public void setOnTopClickListener(OnTopClickListener listener) {
		this.listener = listener;
	}

	public interface OnTopClickListener {
		public abstract void rightBtnClickListener();
	}
}
