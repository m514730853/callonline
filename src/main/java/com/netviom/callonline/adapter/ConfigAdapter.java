package com.netviom.callonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.netviom.callonline.R;
import com.netviom.callonline.adapter.bean.ConfigAdapterBean;
import com.netviom.callonline.adapter.bean.PboxCallLogAdapterBean;

import java.util.ArrayList;
import java.util.List;


public class ConfigAdapter extends BaseAdapter {
	private LayoutInflater listContainer;
	private Context context;
	private class RecentViewHolder {
		TextView texConfigName;
        ImageView imgLeft;
	}
	private List<ConfigAdapterBean> mAppList = new ArrayList<ConfigAdapterBean>();
	public ConfigAdapter(Context c, List<ConfigAdapterBean> appList) {
		mAppList = appList;
		context=c;
		listContainer = LayoutInflater.from(c);
	}

	public void clear() {
		if (mAppList != null) {
			mAppList.clear();
		}
	}

	public int getCount() {
		return mAppList.size();
	}
	@Override
	public Object getItem(int position) {
		return mAppList.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		RecentViewHolder holder;
		if (convertView == null) {
			convertView = listContainer.inflate(R.layout.config_adapter, null);
			holder = new RecentViewHolder();
			holder.texConfigName = (TextView) convertView
					.findViewById(R.id.tex_config_name);
            holder.imgLeft = (ImageView) convertView.findViewById(R.id.img_left);
			convertView.setTag(holder);
		} else {
			holder = (RecentViewHolder) convertView.getTag();
		}
        ConfigAdapterBean bean = mAppList.get(position);
        holder.texConfigName.setText(bean.getName());
        holder.imgLeft.setImageResource(bean.getImgDrawableLeft());
		return convertView;
	}
}
