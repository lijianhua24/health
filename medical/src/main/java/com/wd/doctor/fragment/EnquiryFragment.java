package com.wd.doctor.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.doctor.R;
import com.wd.doctor.adapter.EnquiryAdapter;
import com.wd.doctor.bean.EnquiryBean;
import com.wd.doctor.bean.SuffererDetailBean;
import com.wd.doctor.contract.EnquiryContract;
import com.wd.doctor.presenter.EnquiryPresenter;
import com.wd.mylibrary.Base.BaseFragment;
import com.wd.mylibrary.Test.Logger;

import java.util.List;

import butterknife.BindView;


public class EnquiryFragment extends BaseFragment<EnquiryPresenter> implements EnquiryContract.iView {

    private static final String TAG = "EnquiryFragment";
    @BindView(R.id.enquiry_rv_suffererOut)
    RecyclerView enquiryRvSuffererOut;
    private int id;

    @Override
    protected EnquiryPresenter providePresenter() {
        return new EnquiryPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mPresenter.getSuffererDetailPresenter(id,1,10);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_enquiry;
    }

    @Override
    public void onEnquirySuccess(EnquiryBean enquiryBean) {

    }

    @Override
    public void onEnquiryFailure(Throwable e) {

    }

    @Override
    public void onSuffererDetailSuccess(SuffererDetailBean suffererDetailBean) {
        Logger.d("EnquiryFragment", "" + suffererDetailBean.getMessage());
        List<SuffererDetailBean.ResultBean> result = suffererDetailBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        enquiryRvSuffererOut.setLayoutManager(linearLayoutManager);

        EnquiryAdapter enquiryAdapter = new EnquiryAdapter(getActivity(),result);
        enquiryRvSuffererOut.setAdapter(enquiryAdapter);
    }

    @Override
    public void onSuffererDetailFailure(Throwable e) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        id = arguments.getInt("id", 0);
    }
}
