package wuan.nixo.com.wuan_android_v2.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.activity_register.*
import wuan.nixo.com.wuan_android_v2.R
import wuan.nixo.com.wuan_android_v2.R.id.tv_register
import wuan.nixo.com.wuan_android_v2.utils.StringUtils
import wuan.nixo.com.wuan_android_v2.utils.ToastUtils
import wuan.nixo.com.wuan_android_v2.utils.view.BaseActivity

class RegisterActivity :BaseActivity(), View.OnClickListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initView() {
        tv_register.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0){
            tv_register->{
//                et_username.
            }





        }
    }
}




