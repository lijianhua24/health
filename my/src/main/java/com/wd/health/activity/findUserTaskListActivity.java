package com.wd.health.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.adapter.BonusPollSignAdapter;
import com.wd.health.adapter.UserTaskAdapter;
import com.wd.health.adapter.UserTaskItemAdapter;
import com.wd.health.adapter.UserTaskItemAdapter2;
import com.wd.health.bean.UserTaskListBean;
import com.wd.health.bean.user.FindUserSignBean;
import com.wd.health.bean.user.ReceiveReWardBean;
import com.wd.health.contract.UserTaskContract;
import com.wd.health.presenter.UserTaskListPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 *@describe(描述)：findUserTaskListActivity    我的任务
 *@data（日期）: 2019/12/16
 *@time（时间）: 11:25
 *@author（作者）: 张恩
 **/
public class findUserTaskListActivity extends BaseActivity<UserTaskListPresenter> implements UserTaskContract.IView {

    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.recy_jdt)
    RecyclerView recyJdt;
  /*  @BindView(R.id.my_task)
    RecyclerView myTask;*/
    @BindView(R.id.recy_mrrw)
    RecyclerView recyMrrw;
    @BindView(R.id.recy_ycxrw)
    RecyclerView recyYcxrw;
    private String id;
    private String sessionId;

    @Override
    protected UserTaskListPresenter providePresenter() {
        return new UserTaskListPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_find_user_task_list;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        id = sp.getString("id", "");
        sessionId = sp.getString("sessionId", "");
        mPresenter.UserTaskPresenter(id, sessionId);
        mPresenter.FindUserSignPresenter(id, sessionId);
    }

    @OnClick(R.id.fanhui)
    public void onClick() {
        finish();
    }

    @Override
    public void onUserTaskSuccess(UserTaskListBean bean) {
        if (bean.getStatus().equals("0000")) {
            Toast.makeText(this, "" + bean.getMessage(), Toast.LENGTH_SHORT).show();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(findUserTaskListActivity.this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            recyMrrw.setLayoutManager(linearLayoutManager);
            recyMrrw.setAdapter(new UserTaskAdapter(findUserTaskListActivity.this, bean.getResult()));

            List<UserTaskListBean.ResultBean> result = bean.getResult();
            List<UserTaskListBean.ResultBean> list_yi=new ArrayList<>();
            List<UserTaskListBean.ResultBean> list_er=new ArrayList<>();
            for (int i = 0; i < result.size(); i++) {
                int taskType = result.get(i).getTaskType();
                if (1==taskType){
                    list_yi.add(result.get(i));
                }else  if (2==taskType){
                    list_er.add(result.get(i));
                }
            }
            UserTaskItemAdapter userTaskItemAdapter = new UserTaskItemAdapter(findUserTaskListActivity.this, list_yi);
            recyMrrw.setAdapter(userTaskItemAdapter);

            userTaskItemAdapter.setDoTask(new UserTaskItemAdapter.DoTask() {
                @Override
                public int onclick(int position) {
                    if (position==0){
                        mPresenter.ReceiveReWardPresenter(id,sessionId,"1001");
                        userTaskItemAdapter.notifyDataSetChanged();
                    }
                    return 0;
                }
            });
            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(findUserTaskListActivity.this);
            linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);
            recyYcxrw.setLayoutManager(linearLayoutManager1);
            recyYcxrw.setAdapter(new UserTaskItemAdapter2(findUserTaskListActivity.this,list_er));

        }
    }

    @Override
    public void onFindUserSignSuccess(FindUserSignBean bean) {
        if (bean.getStatus().equals("0000")){
            GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 7);
           //  linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
            recyJdt.setLayoutManager(linearLayoutManager);
            BonusPollSignAdapter bonusPollSignAdapter = new BonusPollSignAdapter(findUserTaskListActivity.this,bean);
            recyJdt.setAdapter(bonusPollSignAdapter);

            bonusPollSignAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onReceiveReWardSuccess(ReceiveReWardBean bean) {
        if (bean.getStatus().equals("0000")){
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            mPresenter.FindUserSignPresenter(id,sessionId);
        }
    }


    @Override
    public void onFailure(Throwable e) {

    }


}
