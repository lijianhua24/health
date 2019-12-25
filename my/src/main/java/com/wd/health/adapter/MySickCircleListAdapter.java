package com.wd.health.adapter;


/*
 *@auther:张恩
 *@Date: 2019/12/21
 *@Time:11:28
 *@Description:我的病友圈列表适配器
 **/

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.wd.health.R;
import com.wd.health.bean.user.MySickCircleListBean;
import com.zd.baserecyadapterlib.base.BaseRecyclerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MySickCircleListAdapter extends BaseRecyclerAdapter<MySickCircleListBean.ResultBean> {
    public MySickCircleListAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.mysickcircle_item;
    }

    @Override
    public void bindViewDataPosition(BaseViewHolder baseViewHolder, MySickCircleListBean.ResultBean resultBean, int i) {
        super.bindViewDataPosition(baseViewHolder, resultBean, i);
        baseViewHolder.setText(R.id.mysickcircleitem_name,resultBean.getTitle());
        baseViewHolder.setText(R.id.mysickcircleitem_text,resultBean.getDetail());
        Date date = new Date(resultBean.getReleaseTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        baseViewHolder.setText(R.id.mysickcircleitem_tiem,simpleDateFormat.format(date));
        TextView textView = (TextView) baseViewHolder.get(R.id.mysickcircleitem_pinglun);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOnClick.onitem(resultBean.getSickCircleId());
            }
        });
    }

    @Override
    public void bindViewData(BaseViewHolder baseViewHolder, MySickCircleListBean.ResultBean resultBean) {

    }
    //接口回调
    ItemOnClick itemOnClick;
    public void setItemOnClick(ItemOnClick itemOnClick){
        this.itemOnClick = itemOnClick;
    }
    public interface ItemOnClick{
        void onitem(int p);
    }
}
