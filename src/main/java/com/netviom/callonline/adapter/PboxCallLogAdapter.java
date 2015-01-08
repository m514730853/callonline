package com.netviom.callonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netviom.callonline.R;
import com.netviom.callonline.adapter.bean.PboxCallLogAdapterBean;
import com.nvm.common.util.LogUtil;
import com.nvm.common.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class PboxCallLogAdapter extends BaseAdapter {
    private LayoutInflater listContainer;
    private Context context;

    private class RecentViewHolder {
        TextView texUsernumber;
        TextView texStartTime;
        TextView texHangUpSms;
        ImageView imgLeft;
        ImageView imgRight;
        LinearLayout linListItem;
    }

    private List<PboxCallLogAdapterBean> mAppList = new ArrayList<PboxCallLogAdapterBean>();

    public PboxCallLogAdapter(Context c, List<PboxCallLogAdapterBean> appList) {
        mAppList = appList;
        context = c;
        listContainer = LayoutInflater.from(c);
    }

    public void clear() {
        if (mAppList != null) {
            mAppList.clear();
        }
    }

    public int getCount() {
        LogUtil.info("mAppList.size():"+mAppList.size());
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
            holder.texUsernumber = (TextView) convertView
                    .findViewById(R.id.tex_user_number);
            holder.texStartTime = (TextView) convertView
                    .findViewById(R.id.tex_start_time);
            holder.texHangUpSms = (TextView) convertView
                    .findViewById(R.id.tex_hang_up_sms);
            holder.imgLeft = (ImageView) convertView.findViewById(R.id.img_left);
            holder.imgRight = (ImageView) convertView.findViewById(R.id.img_right);
            holder.linListItem = (LinearLayout) convertView.findViewById(R.id.lin_list_item);
            convertView.setTag(holder);
        } else {
            holder = (RecentViewHolder) convertView.getTag();
        }
        LogUtil.info("overwrite getview");
        PboxCallLogAdapterBean bean = mAppList.get(position);
        if (bean.getItemType()==PboxCallLogAdapterBean.TIME_ITEM) {
            holder.texUsernumber.setText(bean.getItemTextTime());
            holder.linListItem.setBackgroundDrawable(null);
            holder.imgLeft.setVisibility(View.GONE);
            holder.imgRight.setVisibility(View.GONE);
            holder.texStartTime.setVisibility(View.GONE);
            holder.texHangUpSms.setVisibility(View.GONE);
            return convertView;
        }
        if (bean.getItemType()==PboxCallLogAdapterBean.TIPS_ITEM) {
            holder.texUsernumber.setText("未获取到数据！");
            holder.linListItem.setBackgroundResource(R.drawable.shape_list_bg);
            holder.imgLeft.setVisibility(View.GONE);
            holder.imgRight.setVisibility(View.GONE);
            holder.texStartTime.setVisibility(View.GONE);
            holder.texHangUpSms.setVisibility(View.GONE);
            return convertView;
        }
        if(StringUtils.isEmpty(bean.getName()))
        {
            holder.texUsernumber.setText(bean.getUsernumber());
        }
       else  holder.texUsernumber.setText(bean.getName());
        holder.texStartTime.setText(bean.getStartTime());
        holder.texHangUpSms.setText("通话次数：" + bean.getCalledTimes());
        if (bean.getBackgroundType() == PboxCallLogAdapterBean.ALL) {
            holder.linListItem.setBackgroundResource(R.drawable.shape_list_bg);
        } else if (bean.getBackgroundType() == PboxCallLogAdapterBean.TOP) {
            holder.linListItem.setBackgroundResource(R.drawable.input1);
        } else if (bean.getBackgroundType() == PboxCallLogAdapterBean.BOTTOM) {
            holder.linListItem.setBackgroundResource(R.drawable.input3);
        } else {
            holder.linListItem.setBackgroundResource(R.drawable.input2);
        }
        return convertView;
    }
}
