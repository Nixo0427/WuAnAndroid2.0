package wuan.nixo.com.wuan_android_v2.Ext

object SharedSetting{
    var account : String by SharedExt(AppContext,"account","")
    var passWord : String by SharedExt(AppContext,"password","")
}