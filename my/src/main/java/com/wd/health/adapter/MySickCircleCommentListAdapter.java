package com.wd.health.adapter;


/*
 *@auther:张恩
 *@Date: 2019/12/21
 *@Time:16:10
 *@Description:评论列表适配器
 **/

import android.content.Context;

import com.wd.health.R;
import com.wd.health.bean.user.MySickCircleCommentListBean;
import com.zd.baserecyadapterlib.base.BaseRecyclerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MySickCircleCommentListAdapter extends BaseRecyclerAdapter<MySickCircleCommentListBean.ResultBean.OtherSickCircleCommentListBean> {
    public MySickCircleCommentListAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.info_list_item;
    }

    @Override
    public void bindViewDataPosition(BaseViewHolder baseViewHolder, MySickCircleCommentListBean.ResultBean.OtherSickCircleCommentListBean otherSickCircleCommentListBean, int i) {
        super.bindViewDataPosition(baseViewHolder, otherSickCircleCommentListBean, i);
        baseViewHolder.setPic(R.id.info_list_headPic,otherSickCircleCommentListBean.getHeadPic());
        baseViewHolder.setText(R.id.info_list_nickName,otherSickCircleCommentListBean.getNickName());
        baseViewHolder.setText(R.id.info_list_content,otherSickCircleCommentListBean.getContent());
        baseViewHolder.setText(R.id.info_list_supportNum,otherSickCircleCommentListBean.getSupportNum()+"");
        baseViewHolder.setText(R.id.info_list_opposeNum,otherSickCircleCommentListBean.getOpinion()+"");
        Date date = new Date(otherSickCircleCommentListBean.getCommentTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        baseViewHolder.setText(R.id.info_list_commentTime,simpleDateFormat.format(date));
    }

    @Override
    public void bindViewData(BaseViewHolder baseViewHolder, MySickCircleCommentListBean.ResultBean.OtherSickCircleCommentListBean otherSickCircleCommentListBean) {

    }
}
