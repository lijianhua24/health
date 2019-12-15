package com.wd.homemodel.fragment;

import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.homemodel.R;
import com.wd.homemodel.adapter.MyAdapter;
import com.wd.homemodel.bean.BannerBean;
import com.wd.homemodel.bean.DepartmentBean;
import com.wd.homemodel.bean.InfoSectionBean;
import com.wd.homemodel.bean.SectionBean;
import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.presenter.BannerPresenter;
import com.wd.mylibrary.Base.BaseFragment;
import com.wd.mylibrary.Test.ToastUtils;

import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment<BannerPresenter> implements HomeContract.BnnerContreact.IView {


    @BindView(R.id.home_recy)
    RecyclerView homeRecy;

    private List<BannerBean.ResultBean> bannerlist;
    private List<SectionBean.ResultBean> sectionlist;
    private List<InfoSectionBean.ResultBean> infoSectionList;
    private List<DepartmentBean.ResultBean> departmentlist;

    @Override
    public void onBnnerSuccess(BannerBean data) {
        String message = data.getMessage();
        Log.d("banner", message);
        bannerlist = data.getResult();
        if (bannerlist!=null){
            MyAdapter myAdapter = new MyAdapter(getActivity(), bannerlist, sectionlist, departmentlist, infoSectionList);
            homeRecy.setAdapter(myAdapter);
            myAdapter.onListenter(new MyAdapter.setChage() {
                @Override
                public void getChange(int i) {
                    // ToastUtils.show(i);
                    Toast.makeText(getActivity(), ""+i, Toast.LENGTH_SHORT).show();
                    mPresenter.getInfoSectionPresenter(i+"", 1, 5);
                }
            });
        }
    }

    @Override
    public void onBnnerFailure(Throwable e) {

    }

    @Override
    public void onSectionSuccess(Object data) {
        SectionBean sectionBean = (SectionBean) data;
        Log.d("sectionBean",sectionBean.getMessage());
        sectionlist = sectionBean.getResult();
        if (sectionlist!=null){
            MyAdapter myAdapter = new MyAdapter(getActivity(), bannerlist, sectionlist, departmentlist, infoSectionList);
            homeRecy.setAdapter(myAdapter);
            myAdapter.onListenter(new MyAdapter.setChage() {
                @Override
                public void getChange(int i) {
                   // ToastUtils.show(i);
                    Toast.makeText(getActivity(), ""+i, Toast.LENGTH_SHORT).show();
                    mPresenter.getInfoSectionPresenter(i+"", 1, 5);
                }
            });
        }
    }

    @Override
    public void onSectionFailure(Throwable e) {

    }

    @Override
    public void onDepartmentSuccess(Object data) {
        DepartmentBean departmentBean = (DepartmentBean) data;
        Log.d("departmentBean",departmentBean.getMessage());
        departmentlist = departmentBean.getResult();
        if (departmentlist!=null){
            MyAdapter myAdapter = new MyAdapter(getActivity(), bannerlist, sectionlist, departmentlist, infoSectionList);
            homeRecy.setAdapter(myAdapter);
            myAdapter.onListenter(new MyAdapter.setChage() {
                @Override
                public void getChange(int i) {
                    // ToastUtils.show(i);
                    Toast.makeText(getActivity(), ""+i, Toast.LENGTH_SHORT).show();
                    mPresenter.getInfoSectionPresenter(i+"", 1, 5);
                }
            });
        }
    }

    @Override
    public void onDepartmentFailure(Throwable e) {

    }

    @Override
    public void onInfoSectionSuccess(Object data) {
        InfoSectionBean infoSectionBean = (InfoSectionBean) data;
        Log.d("infoSectionBean",infoSectionBean.getMessage());
        infoSectionList = infoSectionBean.getResult();

        if (infoSectionList!=null){
            MyAdapter myAdapter = new MyAdapter(getActivity(), bannerlist, sectionlist, departmentlist, infoSectionList);
            homeRecy.setAdapter(myAdapter);
            myAdapter.onListenter(new MyAdapter.setChage() {
                @Override
                public void getChange(int i) {
                    // ToastUtils.show(i);
                    Toast.makeText(getActivity(), ""+i, Toast.LENGTH_SHORT).show();
                    mPresenter.getInfoSectionPresenter(i+"", 1, 5);
                }
            });
        }
    }

    @Override
    public void onInfoSectionFailure(Throwable e) {

    }

    @Override
    protected BannerPresenter providePresenter() {
        return new BannerPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mPresenter.getBnnerPresenter();
        //homeSou.getBackground().setAlpha(30);
        mPresenter.getDepartmentPresenter();
        mPresenter.getSectionPresenter();
        mPresenter.getInfoSectionPresenter("1", 1, 5);
        homeRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_home;
    }
}
