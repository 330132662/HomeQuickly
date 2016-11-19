package com.example.taobaoo;

/**
 * 本应用所有的url保存于此
 * Created by LiJianfei on 2016/8/4.
 */
public class UrlManager {
    private final static String Protocol = "http://";// 这个直接加载api前面
    private final static String ProtocolS = "https://";
    private final static String JuHeApi = Protocol + "v.juhe.cn";
    /**
     * 聚合请求天气 http://op.juhe.cn/onebox/weather/query?cityname=青岛&key=c4211ccae4f143cedd5d1ab65721abae
     */
    public final static String URLJuhe = "http://op.juhe.cn";// 两种方式都可以
    public final static String URLNews = JuHeApi + "/toutiao/";// 这次不能把二级页面写在这里……这个"/toutiao并没有显示"
    /**
     * CTM 登录接口
     */
    /**
     * 充值卡  2016年10月14日21:07:18
     */
    /**
     * 微信精选 http://v.juhe.cn/weixin/query
     * 2016年10月14日21:07:14
     */
    /**
     * 去哪儿景点门票查询-xiang详情   2016年11月11日0:05:44
     * http://apis.baidu.com/apistore/qunaerticket/querydetail&apikey=5205c1b31ff52efcdf268f57e7fa28b1
     */
    /** 优酷 -获取相关视频
     * https://openapi.youku.com/v2/videos/by_related.json?client_id=cf11657d03165b2c&video_id=XMjg1MTcyNDQ0
     */

/**  优酷-获取视频分类
 * https://openapi.youku.com/v2/schemas/show/category.json
 */
}
