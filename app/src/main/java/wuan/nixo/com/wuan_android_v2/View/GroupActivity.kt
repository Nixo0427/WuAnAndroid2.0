package wuan.nixo.com.wuan_android_v2.View

import android.content.Context
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.google.gson.Gson
import com.zhy.http.okhttp.callback.StringCallback
import kotlinx.android.synthetic.main.activity_group.*
import okhttp3.Call
import wuan.nixo.com.wuan_android_v2.Adapter.GroupAdapter
import wuan.nixo.com.wuan_android_v2.Common.Api.FINDALLGROUPINFO
import wuan.nixo.com.wuan_android_v2.Common.Api.GROUP
import wuan.nixo.com.wuan_android_v2.Ext.SharedExt
import wuan.nixo.com.wuan_android_v2.Ext.pref
import wuan.nixo.com.wuan_android_v2.MainActivity
import wuan.nixo.com.wuan_android_v2.Model.GroupModel
import wuan.nixo.com.wuan_android_v2.R
import wuan.nixo.com.wuan_android_v2.utils.ToastUtils
import wuan.nixo.com.wuan_android_v2.utils.http.MyOkHttpUtils
import wuan.nixo.com.wuan_android_v2.utils.view.BaseActivity
import java.lang.Exception
import kotlin.reflect.KProperty

class GroupActivity : BaseActivity(){



    override fun getLayoutId(): Int {
        return R.layout.activity_group
    }
    override fun initView() {
        findAllGroup()
    }


    fun findAllGroup(){
        var map : Map<String , String> = HashMap()
        MyOkHttpUtils.postJson().json(map).url(FINDALLGROUPINFO).build().execute(object : StringCallback() {
            override fun onResponse(response: String?, id: Int) {
                Log.i("Nixo","-------分组列表Response--------$response")
                var bean = Gson().fromJson(response,GroupModel::class.java)
                rv_group.layoutManager = StaggeredGridLayoutManager(2,LinearLayout.VERTICAL)
                var mGadapter = GroupAdapter(this@GroupActivity,group)
                mGadapter.setDataList(bean.groups)
                rv_group.adapter = mGadapter
                mGadapter.notifyDataSetChanged()
            }

            override fun onError(call: Call?, e: Exception?, id: Int) {
                ToastUtils.newToastCenter(this@GroupActivity,"网络连接失败")
            }

        })
    }
    val group = object : GroupAdapter.chooseGroup {
        override fun onChoose(bean: GroupModel.GroupsBean) {
            var map = HashMap<String, String>()

            var userId by SharedExt<String>(this@GroupActivity,"userId","1")
            map.put("userId", "${bean.id}")
            map.put("groupId", userId)
            MyOkHttpUtils.postJson().json(map).url(GROUP).build().execute(object : StringCallback() {
                override fun onResponse(response: String?, id: Int) {
                    ToastUtils.newToastCenter(this@GroupActivity,"分组选择成功")
                   startActivity(MainActivity::class.java)
                }

                override fun onError(call: Call?, e: Exception?, id: Int) {
                    ToastUtils.newToastCenter(this@GroupActivity,"网络连接失败")
                }
            })
        }


    }

}
