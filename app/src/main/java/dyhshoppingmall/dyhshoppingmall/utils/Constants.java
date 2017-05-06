package dyhshoppingmall.dyhshoppingmall.utils;

/**
 * date:2017/1/25
 * author:易宸锋(dell)
 * function:配置各个页面联网地址
 */
public class Constants {
    //服务器原始地址
    public static String URLBASE = "http://169.254.181.113:8080/DSShop/";

    // 请求Json数据基本URL
    public static final String BASE_URL_JSON = URLBASE + "json/";
    //主页面的路径
    public static String HOME_URL = BASE_URL_JSON + "HOME_URL.json";

    //图片地址 + url就能显示图片
    public static String HOME_URL_IMAGE = URLBASE + "img";
    // 请求图片基本URL
    public static final String BASE_URl_IMAGE = URLBASE + "img";

    //页面的具体数据的id
    public static final String GOODSINFO_URL = BASE_URL_JSON + "GOODSINFO_URL.json";

    //是否回到首页
    public static Boolean isBackHome = false;



}
