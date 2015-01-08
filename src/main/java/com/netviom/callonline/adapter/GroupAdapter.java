package com.netviom.callonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.netviom.callonline.R;
import com.netviom.callonline.adapter.bean.GroupAdapterBean;
import com.netviom.callonline.adapter.bean.PboxCallLogAdapterBean;
import com.nvm.common.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class GroupAdapter extends BaseAdapter {
	private LayoutInflater listContainer;
	private Context context;
    private final int PADDING=20;
	private class RecentViewHolder {
		ImageView imgLeft;
        TextView texItem;
        TextView texPhoneNumber;
        ImageView imgRight;
	}
	private List<GroupAdapterBean> mAppList = new ArrayList<GroupAdapterBean>();
	public GroupAdapter(Context c, List<GroupAdapterBean> appList) {
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
			convertView = listContainer.inflate(R.layout.group_adapter, null);
			holder = new RecentViewHolder();
			holder.imgLeft = (ImageView) convertView
					.findViewById(R.id.img_left);
            holder.texItem = (TextView) convertView
                    .findViewById(R.id.item_text);
            holder.texPhoneNumber = (TextView) convertView
                    .findViewById(R.id.tex_phone_number);
            holder.imgRight = (ImageView) convertView
                    .findViewById(R.id.img_right);
			convertView.setTag(holder);
		} else {
			holder = (RecentViewHolder) convertView.getTag();
		}
        GroupAdapterBean bean = mAppList.get(position);
        if(bean.isTips())
        {
            holder.texItem.setText("正在加数据，请稍后...");
            holder.imgLeft.setVisibility(View.INVISIBLE);
            holder.imgRight.setVisibility(View.GONE);
            return  convertView;
        }
        holder.texItem.setText(bean.getName());
        if(StringUtils.isEmpty(bean.getPhoneNumber()))
        {
            holder.texPhoneNumber.setVisibility(View.GONE);
        }
        else
        {
            holder.texPhoneNumber.setVisibility(View.VISIBLE);
            holder.texPhoneNumber.setText(bean.getPhoneNumber());
        }
        if(bean.isGroup())
        {
            holder.imgLeft.setVisibility(View.VISIBLE);
            holder.imgRight.setVisibility(View.GONE);
            holder.imgLeft.setPadding(0,0,0,0);
           if(bean.isExpand())
           {
                    holder.imgLeft.setImageResource(R.drawable.group_expand);
           }
            else
               holder.imgLeft.setImageResource(R.drawable.group_nomal);
        }
        else
        {
            holder.imgLeft.setVisibility(View.INVISIBLE);
            holder.imgRight.setVisibility(View.VISIBLE);
            holder.imgLeft.setPadding(PADDING,0,0,0);
        }
		return convertView;
	}
}
