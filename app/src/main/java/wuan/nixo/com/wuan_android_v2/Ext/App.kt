package wuan.nixo.com.wuan_android_v2.Ext

import android.app.Application
import android.content.ContextWrapper

private lateinit var INSTANCE:Application

class APP:Application(){

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

}

object AppContext : ContextWrapper(INSTANCE)