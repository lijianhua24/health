package com.wd.homemodel.view;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wd.homemodel.R;
import com.wd.homemodel.adapter.DiseaseAdaapter;
import com.wd.homemodel.adapter.DoctorAdaapter;
import com.wd.homemodel.adapter.DrugsAdaapter;
import com.wd.homemodel.adapter.HistoryAdaapter;
import com.wd.homemodel.app.App;
import com.wd.homemodel.bean.HistoryBean;
import com.wd.homemodel.bean.PopularBean;
import com.wd.homemodel.bean.SearchBean;
import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.presenter.SearchPresenter;
import com.wd.homemodel.utils.XCFlowLayout;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SouActivity extends BaseActivity<SearchPresenter> implements HomeContract.SearchContreact.IView {

    @BindView(R.id.home_touxiang)
    SimpleDraweeView homeTouxiang;
    @BindView(R.id.flowlayout)
    XCFlowLayout flowlayout;
    @BindView(R.id.sou_edit)
    EditText souEdit;
    @BindView(R.id.sou_sou)
    TextView souSou;
    @BindView(R.id.sou_recy)
    RecyclerView souRecy;
    @BindView(R.id.linear_history)
    LinearLayout linearHistory;
    @BindView(R.id.sou_doctors)
    RecyclerView souDoctors;
    @BindView(R.id.sou_drug)
    RecyclerView souDrug;
    @BindView(R.id.sou_Illness)
    RecyclerView souIllness;
    @BindView(R.id.sou_sv)
    NestedScrollView souSv;
    @BindView(R.id.sou_no)
    LinearLayout souNo;

    private TextView textView1;
    private ArrayList<HistoryBean> name;
    private HistoryAdaapter historyAdaapter;

    @Override
    protected SearchPresenter providePresenter() {
        return new SearchPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        name = new ArrayList<>();
        for (int i = 0; i < name.size(); i++) {

        }
        mPresenter.getPopularPresenter();
        souRecy.setNestedScrollingEnabled(false);
        souDoctors.setNestedScrollingEnabled(false);
        souDrug.setNestedScrollingEnabled(false);
        souIllness.setNestedScrollingEnabled(false);
        souRecy.setLayoutManager(new LinearLayoutManager(this));
        souDoctors.setLayoutManager(new LinearLayoutManager(this));
        souDrug.setLayoutManager(new LinearLayoutManager(this));
        souIllness.setLayoutManager(new LinearLayoutManager(this));
        historyAdaapter = new HistoryAdaapter(this, name);
        souRecy.setAdapter(historyAdaapter);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_sou;
    }

    @OnClick(R.id.sou_sou)
    public void onViewClicked() {
        String trim = souEdit.getText().toString().trim();
        Toast.makeText(this, ""+trim, Toast.LENGTH_SHORT).show();
        name.add(new HistoryBean(trim));
        Gson gson =new Gson();
        String lishigson = gson.toJson(name);
        SharedPreferences.Editor edit = App.sharedPreferences.edit();
        edit.putString("lishi",lishigson);
        edit.commit();
        String lishi = App.sharedPreferences.getString("lishi", null);
        if (lishi!=null){
            HistoryBean historyBean = gson.fromJson(lishi, HistoryBean.class);
            String name = historyBean.getName();

        }
        mPresenter.getSearchPresenter(trim);
        historyAdaapter = new HistoryAdaapter(this, name);
        souRecy.setAdapter(historyAdaapter);
    }

    @Override
    public void onSearchSuccess(Object data) {
    //搜索成功
        linearHistory.setVisibility(View.GONE);
        souNo.setVisibility(View.GONE);
        SearchBean searchBean = (SearchBean) data;
        List<SearchBean.ResultBean.DiseaseSearchVoListBean> diseaseSearchVoList = searchBean.getResult().getDiseaseSearchVoList();
        List<SearchBean.ResultBean.DoctorSearchVoListBean> doctorSearchVoList = searchBean.getResult().getDoctorSearchVoList();
        List<SearchBean.ResultBean.DrugsSearchVoListBean> drugsSearchVoList = searchBean.getResult().getDrugsSearchVoList();
        if (diseaseSearchVoList.size()!=0 || doctorSearchVoList.size()!=0 || drugsSearchVoList.size()!=0){
            souSv.setVisibility(View.VISIBLE);
            souIllness.setAdapter(new DiseaseAdaapter(this,diseaseSearchVoList));
            souDoctors.setAdapter(new DoctorAdaapter(this,doctorSearchVoList));
            souDrug.setAdapter(new DrugsAdaapter(this,drugsSearchVoList));
        }else {
            linearHistory.setVisibility(View.GONE);
            souSv.setVisibility(View.GONE);
            souNo.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSearchFailure(Throwable e) {

    }

    @Override
    public void onPopularSuccess(Object data) {
        //热门搜索
        PopularBean popularBean = (PopularBean) data;
        List<PopularBean.ResultBean> result = popularBean.getResult();
        Toast.makeText(this, ""+result.get(0).getName(), Toast.LENGTH_SHORT).show();
        flowlayout = (XCFlowLayout) findViewById(R.id.flowlayout);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 10;
        lp.rightMargin = 10;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for (int i = 0; i < result.size(); i++) {
            textView1 = new TextView(this);
            String list = result.get(i).getName();
            textView1.setText(list);
            textView1.setTextColor(Color.BLACK);
            textView1.setTextSize(14);
            textView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));

            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = textView1.getText().toString();
                    SouActivity.this.name.add(new HistoryBean(s));
                    historyAdaapter = new HistoryAdaapter(SouActivity.this, name);
                    souRecy.setAdapter(historyAdaapter);

                    Toast.makeText(SouActivity.this, "" + list, Toast.LENGTH_SHORT).show();
                }
            });
            flowlayout.addView(textView1, lp);
        }
    }

    @Override
    public void onPopularFailure(Throwable e) {

    }

}
