package wuan.nixo.com.wuan_android_v2

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_SHIFTING
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.activity_main.*
import wuan.nixo.com.wuan_android_v2.R.mipmap.human
import wuan.nixo.com.wuan_android_v2.R.mipmap.paper
import wuan.nixo.com.wuan_android_v2.utils.ToastUtils
import wuan.nixo.com.wuan_android_v2.utils.view.BaseActivity

class MainActivity : BaseActivity() , BottomNavigationBar.OnTabSelectedListener{





    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        bottom_navigation_bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        bottom_navigation_bar.setMode(BottomNavigationBar.MODE_SHIFTING)

        bottom_navigation_bar
                .addItem(BottomNavigationItem(R.mipmap.frist,"首页"))
                .addItem(BottomNavigationItem(R.mipmap.paper,"周报"))
                .addItem(BottomNavigationItem(R.mipmap.me,"我的"))
                .setFirstSelectedPosition(0)
                .initialise()
        bottom_navigation_bar.hide(true)
        bottom_navigation_bar.setTabSelectedListener(this)
        onTabSelected(0)

    }

    private fun getMainInfo(){

    }

    override fun onTabUnselected(position: Int) {

    }
    override fun onTabReselected(position: Int) {}


    override fun onTabSelected(position: Int) {
        supportFragmentManager.beginTransaction().apply {
            when (position) {
                0 -> {
                    ToastUtils.newToastCenter(this@MainActivity, "首页")
                }
                1 -> {
                    ToastUtils.newToastCenter(this@MainActivity, "周报")
                }
                2 -> {
                    ToastUtils.newToastCenter(this@MainActivity, "我的")
                }
            }
        }.commitAllowingStateLoss()
    }


}
