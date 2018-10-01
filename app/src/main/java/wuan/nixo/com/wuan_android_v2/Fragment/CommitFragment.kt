package wuan.nixo.com.wuan_android_v2.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wuan.nixo.com.wuan_android_v2.Common.StaticClass
import wuan.nixo.com.wuan_android_v2.R
import wuan.nixo.com.wuan_android_v2.utils.CountDownUtil

class CommitFragment :Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_commited, container, false)

//        CountDownUtil(StaticClass.TIMER_TIME,1000,mComitedTimer).start()
        return view
    }
}