package wuan.nixo.com.wuan_android_v2.View

import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.zhy.http.okhttp.callback.StringCallback
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.Call
import wuan.nixo.com.wuan_android_v2.Common.Api.LOGIN
import wuan.nixo.com.wuan_android_v2.Ext.isNull
import wuan.nixo.com.wuan_android_v2.Ext.otherwise
import wuan.nixo.com.wuan_android_v2.Ext.pref
import wuan.nixo.com.wuan_android_v2.Ext.yes

import wuan.nixo.com.wuan_android_v2.MainActivity
import wuan.nixo.com.wuan_android_v2.Model.BaseModel
import wuan.nixo.com.wuan_android_v2.R
import wuan.nixo.com.wuan_android_v2.utils.Preferences
import wuan.nixo.com.wuan_android_v2.utils.StringUtils
import wuan.nixo.com.wuan_android_v2.utils.ToastUtils
import wuan.nixo.com.wuan_android_v2.utils.http.MyOkHttpUtils
import wuan.nixo.com.wuan_android_v2.utils.view.BaseActivity

class LoginActivity : BaseActivity() , View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        tv_login.setOnClickListener(this)
        tv_register.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            tv_login -> {
                val account = et_account.text.toString().trim()
                val password = et_password.text.toString().trim()
                Log.i("Nixo", account + "------>>----" + password)
                println("isNull返回值-------------》"+account.isNull())
                (account.isNull()).yes{
                    ToastUtils.newToastCenter(this,"yes")
                }.otherwise{
                    ToastUtils.newToastCenter(this,"no")
                }
//                (StringUtils.isEmpty(account)).yes {
//                    ToastUtils.newToastCenter(this, "请输入邮箱")
//                }.otherwise {
//                    (StringUtils.isEmpty(password)).yes {
//                        ToastUtils.newToastCenter(this, "请输入密码")
//                    }.otherwise {
//                        (account.contains("@")).yes {
//                            login(account, password)
//                        }.otherwise {
//                            ToastUtils.newToastCenter(this, "请输入正确的邮箱格式")
//                        }
//                    }
//                }
            }
            tv_register -> {
                startActivity(RegisterActivity::class.java)
            }
        }
    }



    fun login(account: String, password: String): Unit {
        var map = HashMap<String, String>()
        map.put("email", account)
        map.put("password", password)
        MyOkHttpUtils.postJson().json(map).url(LOGIN).build().execute(object : StringCallback() {
            override fun onError(call: Call, e: Exception, id: Int) {

            }

            override fun onResponse(response: String, id: Int) {
                val bean = Gson().fromJson<BaseModel>(response,BaseModel::class.java)
                if("500".equals(bean.infoCode)){
                    ToastUtils.newToastCenter(this@LoginActivity,"登录失败")
                }else if("200".equals(bean.infoCode)){
                    var groupId by pref(bean.groupId)
                    var groupName by pref(bean.groupName)
                    var week by pref(bean.currWeek)
                    var userId by pref("$bean.userId")
                    ToastUtils.newToastCenter(this@LoginActivity,"登录成功")
                    startActivity(MainActivity::class.java)
                }else{
                    ToastUtils.newToastCenter(this@LoginActivity,bean.infoText)
                }
            }
        })
    }
}

