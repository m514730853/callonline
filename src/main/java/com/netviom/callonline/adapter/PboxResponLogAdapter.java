package com.netviom.callonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.netviom.callonline.R;
import com.netviom.callonline.adapter.bean.PboxCallLogAdapterBean;
import com.netviom.callonline.adapter.bean.PboxResponLogAdapterBean;

import java.util.ArrayList;
import java.util.List;


public class PboxResponLogAdapter extends BaseAdapter {
	private LayoutInflater listContainer;
	private Context context;
	private class RecentViewHolder {
		TextView texSmsContent;
        TextView texUsernnumber;
        TextView texResponTime;
	}
	private List<PboxResponLogAdapterBean> mAppList = new ArrayList<PboxResponLogAdapterBean>();
	public PboxResponLogAdapter(Context c, List<PboxResponLogAdapterBean> appList) {
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
			convertView = listContainer.inflate(R.layout.pbox_call_log_adapter, null);
			holder = new RecentViewHolder();
			holder.texSmsContent = (TextView) convertView
					.findViewById(R.id.tex_user_number);
            holder.texUsernnumber = (TextView) convertView
                    .findViewById(R.id.tex_start_time);
            holder.texResponTime = (TextView) convertView
                    .findViewById(R.id.tex_hang_up_sms);
			convertView.setTag(holder);
		} else {
			holder = (RecentViewHolder) convertView.getTag();
		}
        PboxResponLogAdapterBean bean = mAppList.get(position);
        holder.texSmsContent.setText(bean.getSmsContent());
        holder.texUsernnumber.setText(bean.getUsernumber());
        holder.texResponTime.setText(bean.getRespTime());
		return convertView;
	}
}
