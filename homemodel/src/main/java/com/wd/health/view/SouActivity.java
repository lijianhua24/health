package com.wd.health.view;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
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
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.DiseaseAdaapter;
import com.wd.health.adapter.DoctorAdaapter;
import com.wd.health.adapter.DrugsAdaapter;
import com.wd.health.adapter.HistoryAdaapter;
import com.wd.health.app.App;
import com.wd.health.bean.HistoryBean;
import com.wd.health.bean.PopularBean;
import com.wd.health.bean.SearchBean;
import com.wd.health.contract.HomeContract;
import com.wd.health.presenter.SearchPresenter;
import com.wd.health.utils.XCFlowLayout;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SouActivity extends BaseActivity<SearchPresenter> implements HomeContract.SearchContreact.IView {

    @BindView(R2.id.home_touxiang)
    SimpleDraweeView homeTouxiang;
    @BindView(R2.id.flowlayout)
    XCFlowLayout flowlayout;
    @BindView(R2.id.sou_edit)
    EditText souEdit;
    @BindView(R2.id.sou_sou)
    TextView souSou;
    @BindView(R2.id.sou_recy)
    RecyclerView souRecy;
    @BindView(R2.id.linear_history)
    LinearLayout linearHistory;
    @BindView(R2.id.sou_doctors)
    RecyclerView souDoctors;
    @BindView(R2.id.sou_drug)
    RecyclerView souDrug;
    @BindView(R2.id.sou_Illness)
    RecyclerView souIllness;
    @BindView(R2.id.sou_sv)
    NestedScrollView souSv;
    @BindView(R2.id.sou_no)
    LinearLayout souNo;
    @BindView(R2.id.sou_tv_doctors)
    TextView souTvDoctors;
    @BindView(R2.id.sou_tv_drug)
    TextView souTvDrug;
    @BindView(R2.id.sou_tv_Illness)
    TextView souTvIllness;

    private TextView textView1;
    private ArrayList<HistoryBean> beanArrayList;
    private HistoryAdaapter historyAdaapter;
    String lishi;

    private String lishijilu;
    private String[] split;
    private SharedPreferences.Editor edit;

    @Override
    protected SearchPresenter providePresenter() {
        return new SearchPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //souRecy.setNestedScrollingEnabled(false);

        beanArrayList = new ArrayList<>();
        edit = App.sharedPreferences1.edit();
        mPresenter.getPopularPresenter();
        souRecy.setNestedScrollingEnabled(false);
        souDoctors.setNestedScrollingEnabled(false);
        souDrug.setNestedScrollingEnabled(false);
        souIllness.setNestedScrollingEnabled(false);
        souRecy.setLayoutManager(new LinearLayoutManager(this));
        souDoctors.setLayoutManager(new LinearLayoutManager(this));
        souDrug.setLayoutManager(new LinearLayoutManager(this));
        souIllness.setLayoutManager(new LinearLayoutManager(this));
        lishijilu = App.sharedPreferences1.getString("lishijilu", null);
        if (lishijilu != null) {
            lishi = lishijilu;
            split = lishijilu.split(",");
            for (int i = 0; i < split.length; i++) {
                if (split[i] != null) {
                    beanArrayList.add(new HistoryBean(split[i]));
                }
            }
            historyAdaapter = new HistoryAdaapter(this, beanArrayList);
            souRecy.setAdapter(historyAdaapter);
        }
        if (historyAdaapter != null) {
            historyAdaapter.onChange(new HistoryAdaapter.onLisenter() {
                @Override
                public void getlistenter(String name, String id) {
                    if (name != null) {
                        mPresenter.getSearchPresenter(name);
                    }
                    if (id != null) {

                        Integer integer = Integer.valueOf(id);
                        String jilu = null;
                        Log.d("ddddddddddddddd", integer + "");
                        Toast.makeText(SouActivity.this, ""+integer, Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < beanArrayList.size(); i++) {
                            if (i != integer) {
                                if (jilu == null) {
                                    String name1 = beanArrayList.get(i).getName();
                                    jilu = name1 + ",";
                                } else {
                                    String name1 = beanArrayList.get(i).getName();
                                    jilu =  jilu+(name1 + ",");
                                }
                                Log.d("1", jilu);
                                edit.putString("lishijilu", jilu);
                                edit.commit();
                            } else  {
                                Toast.makeText(SouActivity.this, ""+i, Toast.LENGTH_SHORT).show();
                                beanArrayList.remove(i);
                            }


                        }
                        if (beanArrayList.size() == 0) {
                            App.sharedPreferences1.edit().clear().commit();
                        }
                        Toast.makeText(SouActivity.this, "" + jilu, Toast.LENGTH_SHORT).show();
                        historyAdaapter.notifyDataSetChanged();

                    }
                }
            });

        }
    }


    @Override
    protected int provideLayoutId() {
        return R2.layout.activity_sou;
    }

    @OnClick(R2.id.sou_sou)
    public void onViewClicked() {
        String s = souEdit.getText().toString();
        String trim = s.trim();
        if (!trim.isEmpty()) {
            for (int i = 0; i < beanArrayList.size(); i++) {
                if (beanArrayList.get(i).getName() != trim) {
                    beanArrayList.add(new HistoryBean(trim));
                }
            }
            if (lishi == null) {
                lishi = trim + ",";
            } else {
                lishi = (trim + ",") + lishi;
            }
            edit.putString("lishijilu", lishi);
            edit.commit();
            mPresenter.getSearchPresenter(trim);
        } else {
            Toast.makeText(this, "请输入要搜索的内容", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSearchSuccess(Object data) {
        //搜索成功
        linearHistory.setVisibility(View.GONE);
        souNo.setVisibility(View.GONE);
        SearchBean searchBean = (SearchBean) data;
        String message = searchBean.getMessage();
        if (message.contains("搜索成功")) {
            List<SearchBean.ResultBean.DiseaseSearchVoListBean> diseaseSearchVoList = searchBean.getResult().getDiseaseSearchVoList();
            List<SearchBean.ResultBean.DoctorSearchVoListBean> doctorSearchVoList = searchBean.getResult().getDoctorSearchVoList();
            List<SearchBean.ResultBean.DrugsSearchVoListBean> drugsSearchVoList = searchBean.getResult().getDrugsSearchVoList();
            if (diseaseSearchVoList.size() != 0 || doctorSearchVoList.size() != 0 || drugsSearchVoList.size() != 0) {
                souSv.setVisibility(View.VISIBLE);
                souIllness.setAdapter(new DiseaseAdaapter(this, diseaseSearchVoList));
                souDoctors.setAdapter(new DoctorAdaapter(this, doctorSearchVoList));
                souDrug.setAdapter(new DrugsAdaapter(this, drugsSearchVoList));
                if (diseaseSearchVoList.size() == 0) {
                    souIllness.setVisibility(View.GONE);
                    souTvIllness.setVisibility(View.GONE);
                }
                if (doctorSearchVoList.size() == 0) {
                    souDoctors.setVisibility(View.GONE);
                    souTvDoctors.setVisibility(View.GONE);
                }
                if (drugsSearchVoList.size() == 0) {
                    souDrug.setVisibility(View.GONE);
                    souTvDrug.setVisibility(View.GONE);
                }
            } else {
                linearHistory.setVisibility(View.GONE);
                souSv.setVisibility(View.GONE);
                souNo.setVisibility(View.VISIBLE);
            }
        } else {
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
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
                    for (int j = 0; j < beanArrayList.size(); j++) {
                        if (beanArrayList.get(j).getName() != s) {
                            SouActivity.this.beanArrayList.add(new HistoryBean(s));
                        }
                    }
                    historyAdaapter = new HistoryAdaapter(SouActivity.this, beanArrayList);
                    souRecy.setAdapter(historyAdaapter);
                    if (lishi == null) {
                        lishi = list + ",";

                    } else {
                        lishi = (list + ",") + lishi;

                    }
                    edit.putString("lishijilu", lishi);
                    edit.commit();
                    mPresenter.getSearchPresenter(list);
                }
            });
            flowlayout.addView(textView1, lp);
        }
    }

    @Override
    public void onPopularFailure(Throwable e) {

    }


}
