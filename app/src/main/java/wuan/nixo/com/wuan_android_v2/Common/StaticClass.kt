package wuan.nixo.com.wuan_android_v2.Common

/**
 * 项目名：   WuanAndroid
 * 包名 ：    com.example.administrator.wuanandroid.utils
 * 文件名：   StaticClass
 * 创建者:    Nixo
 * 创建时间：  2018/3/16/016-20:58
 * 描述：
 */

object StaticClass {

    //logTAG
    val TAG = "JSY"

    //Shared总Key
    val NAME = "Shared"
    // 午安客户端版本号
    var WUAN_VERSION = 1.0

    //午安URL请求地址
    val WUAN_URL = "http://13.125.249.52:8008"
    //    public static final String WUAN_URL = "http://ericheel.free.ngrok.cc";

    //当前周数Shared
    val WEEK_NUM = "weekNumber"



    //是否提交周报
    val IS_UPDATANEEWS_KEY = "isUpData"
    //    SharedKey
    val USER_ID = "User_key"
    val GROUP_ID = "Group_key"
    //    public static final String  = "Group_key";
    //倒计时一周时间
    val TIMER_TIME : Long = 604800000;
    //是否保存周报判断
    var isSave = false

}
