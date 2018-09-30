package wuan.nixo.com.wuan_android_v2.utils.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import wuan.nixo.com.wuan_android_v2.utils.HideInputUtils;

public abstract class BaseActivity extends FragmentActivity {



        private AlertDialog mAlertDialog;

        public boolean isSetWindow = false;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
//        fullScreen(this);
            onBeforeSetContentView();
            setContentView(getLayoutId());
            initView();
        }

        @Override
        protected void onResume() {
            super.onResume();
        }

        @Override
        protected void onPause() {
            super.onPause();
        }


        /*********************
         * 子类实现
         *****************************/
        //获取布局文件
        public abstract int getLayoutId();

        //初始化view
        protected abstract void initView();

        /**
         * 设置layout前配置
         *
         */
        public void onBeforeSetContentView() {
            AppManager.getAppManager().addActivity(this);
            // 设置竖屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }
        /**
         * 设置主题
         */
//    private void initTheme() {
//        ChangeModeController.setTheme(this, R.style.DayTheme, R.style.NightTheme);
//    }


        /**
         * 沉浸状态栏（4.4以上系统有效）
         */
        protected void translucentStatusBar() {

//        StatusBarCompat.translucentStatusBar(this);
        }

        /**
         * 通过Class跳转界面
         **/
        public void startActivity(Class<?> cls) {
            startActivity(cls, null);
        }

        /**
         * 通过Class跳转界面
         **/
        public void startActivityForResult(Class<?> cls, int requestCode) {
            startActivityForResult(cls, null, requestCode);
        }

        /**
         * 含有Bundle通过Class跳转界面
         **/
        public void startActivityForResult(Class<?> cls, Bundle bundle,
                                           int requestCode) {
            Intent intent = new Intent();
            intent.setClass(this, cls);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivityForResult(intent, requestCode);
        }

        /**
         * 含有Bundle通过Class跳转界面
         **/
        public void startActivity(Class<?> cls, Bundle bundle) {
            Intent intent = new Intent();
            intent.setClass(this, cls);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivity(intent);
        }


        /**
         * 请求权限
         * <p>
         * 如果权限被拒绝过，则提示用户需要权限
         */
        @RequiresApi(api = Build.VERSION_CODES.M)
        protected void requestPermission(final String permission, String rationale, final int requestCode) {
            if (shouldShowRequestPermissionRationale(permission)) {
                showAlertDialog("权限需求", rationale,
                        new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[]{permission}, requestCode);
                            }
                        }, "确定", null, "取消");
            } else {
                requestPermissions(new String[]{permission}, requestCode);
            }
        }

        /**
         * 显示指定标题和信息的对话框
         *
         * @param title                         - 标题
         * @param message                       - 信息
         * @param onPositiveButtonClickListener - 肯定按钮监听
         * @param positiveText                  - 肯定按钮信息
         * @param onNegativeButtonClickListener - 否定按钮监听
         * @param negativeText                  - 否定按钮信息
         */
        protected void showAlertDialog(@Nullable String title, @Nullable String message,
                                       @Nullable DialogInterface.OnClickListener onPositiveButtonClickListener,
                                       @NonNull String positiveText,
                                       @Nullable DialogInterface.OnClickListener onNegativeButtonClickListener,
                                       @NonNull String negativeText) {
            showAlertDialog(title, message, onPositiveButtonClickListener, positiveText, onNegativeButtonClickListener, negativeText, false);
        }

        /**
         * 显示指定标题和信息的对话框
         *
         * @param title                         - 标题
         * @param message                       - 信息
         * @param onPositiveButtonClickListener - 肯定按钮监听
         * @param positiveText                  - 肯定按钮信息
         * @param onNegativeButtonClickListener - 否定按钮监听
         * @param negativeText                  - 否定按钮信息
         * @param isCancal                      - 点击范围外和back键是否取消
         */
        protected void showAlertDialog(@Nullable String title, @Nullable String message,
                                       @Nullable DialogInterface.OnClickListener onPositiveButtonClickListener,
                                       @NonNull String positiveText,
                                       @Nullable DialogInterface.OnClickListener onNegativeButtonClickListener,
                                       @NonNull String negativeText, boolean isCancal) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setCancelable(isCancal);
            builder.setPositiveButton(positiveText, onPositiveButtonClickListener);
            builder.setNegativeButton(negativeText, onNegativeButtonClickListener);
            mAlertDialog = builder.show();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
        }

//    /**
//     * 设置输入法的显示和隐藏
//     *
//     * @return
//     */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
////            if (ShouldHideInput_util.isShouldHideInput(v, ev)) {
////                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
////                if (imm != null) {
////                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
////                }
////            }
//        }
//        // 必不可少，否则所有的组件都不会有TouchEvent了
//        if (getWindow().superDispatchTouchEvent(ev)) {
//            return true;
//        }
//        return onTouchEvent(ev);
//    }
        /**
         * 设置字体大小不会随着系统更改
         * @return
         */
        @Override
        public Resources getResources() {
            Resources resources = super.getResources();
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
            return resources;
        }
        /**
         * 设置全屏
         *
         * @param activity
         */
        private void fullScreen(Activity activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                    Window window = activity.getWindow();
                    View decorView = window.getDecorView();
                    //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                    int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                    decorView.setSystemUiVisibility(option);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.TRANSPARENT);
                } else {
                    Window window = activity.getWindow();
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                    attributes.flags |= flagTranslucentStatus;
                    window.setAttributes(attributes);
                }
            }
        }
        /**
         * 设置输入法的显示和隐藏
         *
         * @return
         */
        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                if (HideInputUtils.isShouldHideInput(v, ev)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            }
            // 必不可少，否则所有的组件都不会有TouchEvent了
            if (getWindow().superDispatchTouchEvent(ev)) {
                return true;
            }
            return onTouchEvent(ev);
        }

    }


