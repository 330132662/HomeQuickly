package com.example.taobaoo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * xin聚合新闻适配器  ,每次初始化都要
 * Created by Jeff on 2016/8/17.
 */
public class NewsRecyAdp extends RecyclerView.Adapter<NewsRecyAdp.NewsHolder> {
    List<NewsBean.ResultBean.DataBean> newsList; // 数据最内层model，具体的新闻内容
    private Context context;
    private String getUrl;
/*
    public void setOnRecyItemClick(OnRecyItemClick onRecyItemClick) {
        this.onRecyItemClick = onRecyItemClick;
    }

    OnRecyItemClick onRecyItemClick;*/

    public NewsRecyAdp(List<NewsBean.ResultBean.DataBean> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }


    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(context).inflate(R.layout.newsitem, null));
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        int scale = AppUtil.getDpFromScreenHeight((Activity) context, 20);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, scale);
        LinearLayout.LayoutParams paramsImg = new LinearLayout.LayoutParams(scale, scale);
        holder.news_pic.setLayoutParams(paramsImg);
        holder.news_pic1.setLayoutParams(paramsImg);
        holder.news_pic2.setLayoutParams(paramsImg);
        holder.itemView.setLayoutParams(params);
        NewsBean.ResultBean.DataBean bean = newsList.get(position);// 当前新闻，共10字段
        bean.getRealtype();//8、二级分类，做成tablayout标签
        holder.news_title.setText(bean.getTitle().trim());//1标题
        holder.news_date.setText(bean.getDate().trim());//2日期
        holder.news_author.setText("·" + bean.getAuthor_name() + "·");//3作者
        holder.news_type.setText("·" + bean.getType().trim());//4一级分类
//        ImageLoader.getInstance().displayImage(bean.getThumbnail_pic_s(), holder.news_pic);//5图1
//        setImgByGlide(context, bean.getThumbnail_pic_s02(), holder.news_pic1);//6图2
//        ImageUtils.setImgByGlide(context, bean.getThumbnail_pic_s03(), holder.news_pic2);//7图3
        Glide.get(context).with(context).load(bean.getThumbnail_pic_s()).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.news_pic);
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder {
        private ImageView news_pic, news_pic1, news_pic2;
        private TextView news_title, news_date, news_text, news_type, news_author;

        public NewsHolder(View itemView) {
            super(itemView);
            news_pic = (ImageView) itemView.findViewById(R.id.news_pic);
            news_pic1 = (ImageView) itemView.findViewById(R.id.news_pic1);
            news_pic2 = (ImageView) itemView.findViewById(R.id.news_pic2);
            news_title = (TextView) itemView.findViewById(R.id.news_title);
            news_date = (TextView) itemView.findViewById(R.id.news_date);
//            news_text = (TextView) itemView.findViewById(R.id.news_text);
            news_type = (TextView) itemView.findViewById(R.id.news_type);
            news_author = (TextView) itemView.findViewById(R.id.news_author);
        }
    }

}
