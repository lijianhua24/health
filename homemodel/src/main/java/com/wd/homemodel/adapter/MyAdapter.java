package com.wd.homemodel.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.androidx.XBanner;
import com.stx.xhb.androidx.entity.SimpleBannerInfo;
import com.wd.homemodel.MainActivity;
import com.wd.homemodel.R;
import com.wd.homemodel.bean.BannerBean;
import com.wd.homemodel.bean.DepartmentBean;
import com.wd.homemodel.bean.InfoSectionBean;
import com.wd.homemodel.bean.SectionBean;

import java.util.AbstractList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    List<BannerBean.ResultBean> bannerlist;
    List<SectionBean.ResultBean> sectionlist;
    List<DepartmentBean.ResultBean> departmentlist;
    List<InfoSectionBean.ResultBean> infoSectionList;

    public MyAdapter(MainActivity mainActivity, List<BannerBean.ResultBean> bannerlist, List<SectionBean.ResultBean> sectionlist, List<DepartmentBean.ResultBean> departmentlist, List<InfoSectionBean.ResultBean> infoSectionList) {
        this.context = mainActivity;
        this.sectionlist = sectionlist;
        this.departmentlist = departmentlist;
        this.infoSectionList = infoSectionList;
        this.bannerlist = bannerlist;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.banner_layout, parent, false);
            return new MyBannerHolder(inflate);
        } else if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.baodian_layout, parent, false);
            return new MyBaoDianHolder(inflate);
        } else if (viewType == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.department_layout, parent, false);
            return new MyDepartmentHolder(inflate);
        } else if (viewType == 3) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.pingce_layout, parent, false);
            return new MyPingCeHolder(inflate);
        } else if (viewType == 4) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.section_layout, parent, false);
            return new MySectionHolder(inflate);
        }else if (viewType == 5) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.infosection_layout, parent, false);
            return new MyInfoSectionHolder(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case 0:
               if (holder instanceof  MyBannerHolder){
                   if (bannerlist != null) {
                       ((MyBannerHolder) holder).bannerBanner.setBannerData(R.layout.simple_image, new AbstractList<SimpleBannerInfo>() {
                           @Override
                           public SimpleBannerInfo get(int index) {
                               return null;
                           }

                           @Override
                           public int size() {
                               return bannerlist.size();
                           }
                       });
                       ((MyBannerHolder) holder).bannerBanner.loadImage(new XBanner.XBannerAdapter() {
                           @Override
                           public void loadBanner(XBanner banner, Object model, View view, int position) {
                               SimpleDraweeView simpel_one = view.findViewById(R.id.simpel_one);
                               Uri parse = Uri.parse(bannerlist.get(position).getImageUrl());
                               simpel_one.setImageURI(parse);
                           }
                       });
                   }
               }
                break;
            case 1:
                if (holder instanceof MyBaoDianHolder){

                }
                break;
            case 2:
                if (holder instanceof MyDepartmentHolder){
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
                    ((MyDepartmentHolder) holder).departent_recy.setLayoutManager(gridLayoutManager);
                    if (departmentlist!=null){
                        ((MyDepartmentHolder) holder).departent_recy.setAdapter(new DepartentAdapter(context,departmentlist));
                    }
                }
                break;
            case 3:

                break;
            case 4:
                if (holder instanceof MySectionHolder){
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                    linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                    ((MySectionHolder) holder).section_recy.setLayoutManager(linearLayoutManager);
                   if (sectionlist!=null){
                       SectionAdapter sectionAdapter = new SectionAdapter(context, sectionlist);
                       ((MySectionHolder) holder).section_recy.setAdapter(sectionAdapter);
                   }
                }
                break;
            case 5:
                if (holder instanceof MyInfoSectionHolder){
                    if (infoSectionList!=null){
                        ((MyInfoSectionHolder) holder).infosection_recy.setLayoutManager(new LinearLayoutManager(context));
                        ((MyInfoSectionHolder) holder).infosection_recy.setAdapter(new InfoSectionAdapter(context,infoSectionList));
                    }
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else if (position == 2) {
            return 2;
        } else if (position == 3) {
            return 3;
        } else if (position == 4) {
            return 4;
        }
        return 0;
    }

    class MyBannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner_banner)
        XBanner bannerBanner;
        public MyBannerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

    class MySectionHolder extends RecyclerView.ViewHolder {

        private final RecyclerView section_recy;

        public MySectionHolder(@NonNull View itemView) {
            super(itemView);
            section_recy = itemView.findViewById(R.id.section_recy);
        }
    }

    class MyDepartmentHolder extends RecyclerView.ViewHolder {

        private final RecyclerView departent_recy;

        public MyDepartmentHolder(@NonNull View itemView) {
            super(itemView);
            departent_recy = itemView.findViewById(R.id.departent_recy);
        }
    }

    class MyInfoSectionHolder extends RecyclerView.ViewHolder {

        private final RecyclerView infosection_recy;

        public MyInfoSectionHolder(@NonNull View itemView) {
            super(itemView);
            infosection_recy = itemView.findViewById(R.id.infosection_recy);
        }
    }

    class MyBaoDianHolder extends RecyclerView.ViewHolder {

        public MyBaoDianHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    class MyPingCeHolder extends RecyclerView.ViewHolder {

        public MyPingCeHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
