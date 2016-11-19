package com.example.taobaoo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends BaseActivity {
    private Banner home_banner;
    ;
    private RecyclerView home_recy, home_recy1, home_recy2;
    private Context mContext;
    private List<NewsBean.ResultBean.DataBean> newsList; // 数据最内层model，具体的新闻内容
    private List<String> urlList;
    private List<String> titleList;
    NewsRecyAdp newsRecyAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this ;
        initView();
        reuNews();
    }

    public void initView() {
        home_banner = fView(R.id.home_banner);//这三行 初始化banner
        home_recy = fView(R.id.home_recy);
        home_recy1 = fView(R.id.home_recy1);
        home_recy2 = fView(R.id.home_recy2);
    }

    /**
     * 请求新闻内容
     */
    private void reuNews() {
        newsList = new ArrayList<>();
        RetrofitUtil.addUrlApi(UrlManager.URLNews, NewsApi.class).
                reqNews("top", Constant.News_KEY).enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Response<NewsBean> response, Retrofit retrofit) {
                NewsBean news = response.body();
                try {
                    newsList = news.getResult().getData();
                } catch (Exception e) {

                }
                initList();
                initList1();
                initList2();
                initBannerUrl();
            }


            @Override
            public void onFailure(Throwable t) {
                showToast("新闻内容出错");
            }
        });
    }


    /**
     * 2*2列表  各个列表显示的item可能不一样 所以可能需要不同的适配器
     */
    private void initList() {
        List<NewsBean.ResultBean.DataBean> list = newsList.subList(0, 4);
        newsRecyAdp = new NewsRecyAdp(list, mContext);
        GridLayoutManager functionGrid = new GridLayoutManager(mContext, 4);
//        home_recy.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));//item分割线
        home_recy.setLayoutManager(functionGrid);
        home_recy.setAdapter(newsRecyAdp);

    }

    /**
     * 3*1
     */
    private void initList1() {
        List<NewsBean.ResultBean.DataBean> list = newsList.subList(4, 8);
        newsRecyAdp = new NewsRecyAdp(list, mContext);
        GridLayoutManager functionGrid = new GridLayoutManager(mContext, 2);
//        home_recy1.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));//item分割线
//        home_recy1.setOnTouchListener((v, event) -> true);// 返回true，让recyclerview和根布局无冲突顺畅滑动
//        newsRecyAdp.setOnRecyItemClick(HomeFragment.this);
        home_recy1.setLayoutManager(functionGrid);
        home_recy1.setAdapter(newsRecyAdp);

    }

    /**
     * 4*1
     */
    private void initList2() {
        List<NewsBean.ResultBean.DataBean> list = newsList.subList(8, 16);
        newsRecyAdp = new NewsRecyAdp(list, mContext);
        GridLayoutManager functionGrid = new GridLayoutManager(mContext, 2);
//        home_recy2.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));//item分割线
//        home_recy2.setOnTouchListener((v, event) -> true);// 返回true，让recyclerview和根布局无冲突顺畅滑动
//        newsRecyAdp.setOnRecyItemClick(HomeFragment.this);
        home_recy2.setLayoutManager(functionGrid);
        home_recy2.setAdapter(newsRecyAdp);
    }

    /**
     * 初始化banner
     */
    private void initBannerUrl() {
        ViewGroup.LayoutParams bannerHeight = new FrameLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, AppUtil.getDpFromScreenHeight(this, 40));
        home_banner.setLayoutParams(bannerHeight);
        home_banner.setImageLoader(new MyImageLoader());
        titleList = new ArrayList<>();
        urlList = new ArrayList<>();
        for (NewsBean.ResultBean.DataBean t : newsList) {
            urlList.add(t.getThumbnail_pic_s03());
            titleList.add(t.getTitle());
        }
        home_banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        home_banner.setImages(urlList);// 加载图片
        home_banner.setBannerTitles(titleList);// 加载标题
        home_banner.start();
        home_banner.startAutoPlay();
    }
}
