package com.wd.health.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.bean.CircleListShowBean;
import com.wd.health.bean.DepartmentListBean;
import com.wd.health.bean.DoTaskBean;
import com.wd.health.bean.KeywordSearchBean;
import com.wd.health.bean.ReleasePatientsBean;
import com.wd.health.bean.UnitDiseaseBean;
import com.wd.health.bean.UploadPatientBean;
import com.wd.health.contract.IContract;
import com.wd.health.presenter.DepartmentListPresenter;
import com.wd.health.view.adapter.KeywordSearchAdapter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.ToastUtils;

import java.util.List;

public class SearchActivity extends BaseActivity<DepartmentListPresenter> implements IContract.iView {
    private ImageView search_back;
    private EditText search_keyword;
    private TextView search_text;
    private RecyclerView search_recyclerView;
    private RelativeLayout patient_relative_serach;
    private KeywordSearchAdapter keywordSearchAdapter;
    private LinearLayout niumeiyou;
    private TextView niuwei;
    private String trim;


    @Override
    protected DepartmentListPresenter providePresenter() {
        return new DepartmentListPresenter();
    }

    @Override
    protected void initData() {
        search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        search_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trim = search_keyword.getText().toString().trim();
                mPresenter.getKeywordSearchPresenter(trim);
            }
        });
    }

    @Override
    protected void initView() {
        search_back = findViewById(R.id.search_back);
        search_keyword = findViewById(R.id.search_keyword);
        search_text = findViewById(R.id.search_text);
        niuwei=findViewById(R.id.niuwei);
        search_recyclerView = findViewById(R.id.search_recyclerView);
        niumeiyou = findViewById(R.id.niumeiyou);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }

    @Override
    public void DepartmentListsuccess(DepartmentListBean departmentListBean) {

    }

    @Override
    public void DepartmentListFailure(Throwable e) {

    }

    @Override
    public void CircleListShowsuccess(CircleListShowBean circleListShowBean) {

    }

    @Override
    public void CircleListShowFailure(Throwable e) {

    }

    @Override
    public void KeywordSearchsuccess(KeywordSearchBean keywordSearchBean) {
        List<KeywordSearchBean.ResultBean> result = keywordSearchBean.getResult();
        if (result.size() == 0) {
            niumeiyou.setVisibility(View.VISIBLE);
            search_recyclerView.setVisibility(View.GONE);
            niuwei.setText("抱歉,没有找到“" + trim + "”的病友圈");
        } else {
            niumeiyou.setVisibility(View.GONE);
            search_recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            search_recyclerView.setLayoutManager(linearLayoutManager);
            keywordSearchAdapter = new KeywordSearchAdapter(this);
            keywordSearchAdapter.addData(result);
            search_recyclerView.setAdapter(keywordSearchAdapter);
            keywordSearchAdapter.onItemClickListener(new KeywordSearchAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position, int id) {

                    Intent intent = new Intent(SearchActivity.this, PatientDetailsActivity.class);
                    intent.putExtra("sickCircleId", id);
                    startActivity(intent);
                }
            });
        }
    }
    @Override
    public void KeywordSearchFailure(Throwable e) {
        ToastUtils.show("没有网");
    }

    @Override
    public void ReleasePatientssuccess(ReleasePatientsBean ReleasePatientsBean) {

    }

    @Override
    public void ReleasePatientsFailure(Throwable e) {

    }

    @Override
    public void UnitDiseasessuccess(UnitDiseaseBean unitDiseaseBean) {

    }

    @Override
    public void UnitDiseaseFailure(Throwable e) {

    }

    @Override
    public void uploadPatientsuccess(UploadPatientBean uploadPatientBean) {


    }

    @Override
    public void uploadPatientFailure(Throwable e) {

    }

    @Override
    public void DoTasksuccess(DoTaskBean doTaskBean) {

    }

    @Override
    public void DoTaskFailure(Throwable e) {

    }
}
