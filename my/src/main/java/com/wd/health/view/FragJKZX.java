package com.wd.health.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.health.R;
import com.wd.health.adapter.JKZXAdapter;
import com.wd.health.bean.JKZXBean;
import com.wd.health.contract.ConlltionContract;
import com.wd.health.presenter.CollectionPresenter;
import com.wd.mylibrary.Base.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 19:44
 */
public class FragJKZX extends BaseFragment<CollectionPresenter> implements ConlltionContract.IView {
    @BindView(R.id.xrecy_view_jkzx)
    XRecyclerView xrecyViewJkzx;
    public static final String TAG="FragJKZX";
    int page =1;
    @Override
    protected int provideLayoutId() {
        return R.layout.frag_jkzx;
    }

    @Override
    protected CollectionPresenter providePresenter() {
        return new CollectionPresenter();
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = sp.getString("id", "");
        String sessionId = sp.getString("sessionId", "");
        mPresenter.JKZXPresenter(id,sessionId,page+"","10");
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onJKZXSuccess(JKZXBean bean) {
        Log.d(TAG, "onJKZXSuccess: "+bean.getMessage());
        if (bean.getStatus().equals("0000")){
            Toast.makeText(getActivity(), ""+bean.getMessage(), Toast.LENGTH_SHORT).show();
            List<JKZXBean.ResultBean> result = bean.getResult();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            xrecyViewJkzx.setLayoutManager(linearLayoutManager);
            xrecyViewJkzx.setAdapter(new JKZXAdapter(getActivity(),result));
            xrecyViewJkzx.setLoadingMoreEnabled(true);
            xrecyViewJkzx.setPullRefreshEnabled(true);
            xrecyViewJkzx.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {
                    page=1;
                    xrecyViewJkzx.refreshComplete();
                }

                @Override
                public void onLoadMore() {
                    page++;
                    xrecyViewJkzx.refreshComplete();
                }
            });
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
