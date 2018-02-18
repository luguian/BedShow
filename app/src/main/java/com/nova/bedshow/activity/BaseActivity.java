package com.nova.bedshow.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nova.bedshow.R;
import com.nova.bedshow.dialog.AlertDialog;
import com.nova.bedshow.dialog.CustomProgressDlg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lu on 2018/1/10.
 */

public abstract class BaseActivity extends CheckPermissionsActivity {

    public static final String packageName = "com.nova.bedshow";
    public static final List<BaseActivity> activityCache = new ArrayList<BaseActivity>();
    protected boolean control = true;

    /**
     * 屏幕的宽度、高度、密度
     */
    public static int mScreenWidth;
    public static int mScreenHeight;
    public static float mDensity;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (control) {
            activityCache.add(this);
        }

    }


    @Override
    protected void onResume(){
        super.onResume();
        initview();
        initdata();
    }
    /**
     * 短暂显示Toast提示(来自res)
     **/
    protected void showShortToast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    protected void showLongToast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    protected void showLongToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    /**
     * 含有Bundle通过Action跳转界面
     **/
    protected void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有标题和内容的对话框
     **/
    public void showAlertDialog(String title, String message) {
        new AlertDialog(this).builder().setTitle(title)
                .setMsg(message).show();
    }

    /**
     * 含有内容、两个按钮的对话框
     **/
    public void showAlertDialog(String message,
                                String positiveText,
                                View.OnClickListener onPositiveClickListener,
                                String negativeText,
                                View.OnClickListener onNegativeClickListener) {
        new AlertDialog(this).builder()
                .setMsg(message)
                .setPositiveButton(positiveText, onPositiveClickListener)
                .setNegativeButton(negativeText, onNegativeClickListener)
                .show();
    }

    /**
     * 含有内容，一个按钮的对话框
     */
    public void showAlertDialog(String message,
                                String positiveText,boolean iscancel,
                                View.OnClickListener onPositiveClickListener
    ) {
        new AlertDialog(this).builder()
                .setMsg(message)
                .setPositiveButton(positiveText, onPositiveClickListener)
                .setCancelable(iscancel)
                .show();
    }
    /**
     * 含有内容、两个按钮的对话框
     **/
    public void showAlertDialog(String message,
                                String positiveText,
                                View.OnClickListener onPositiveClickListener,
                                String negativeText,
                                View.OnClickListener onNegativeClickListener, DialogInterface.OnCancelListener onCancelListener) {
        new AlertDialog(this).builder()
                .setMsg(message)
                .setPositiveButton(positiveText, onPositiveClickListener)
                .setNegativeButton(negativeText, onNegativeClickListener)
                .setOnCancelListener(onCancelListener)
                .show();
    }

    /**
     * 含有标题、内容、图标、两个按钮的对话框
     **/
    public void showAlertDialog(String title, String message,
                                int icon, String positiveText,
                                View.OnClickListener onPositiveClickListener,
                                String negativeText,
                                View.OnClickListener onNegativeClickListener) {
        new AlertDialog(this).builder().setTitle(title)
                .setMsg(message)
                .setPositiveButton(positiveText, onPositiveClickListener)
                .setNegativeButton(negativeText, onNegativeClickListener)
                .show();
    }

    /**
     * 显示自定义Toast提示(来自String)
     **/
    public void showCustomToast(String text) {
        View toastRoot = LayoutInflater.from(this).inflate(
                R.layout.common_toast, null);
        ((TextView) toastRoot.findViewById(R.id.toast_text)).setText(text);
        Toast toast = new Toast(BaseActivity.this);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastRoot);
        toast.show();
    }

    public static <T> T getActivity(Class<T> clazz) {
        for (BaseActivity activity : activityCache) {
            if (clazz.isAssignableFrom(activity.getClass())) {
                T activity2 = (T) activity;
                return activity2;
            }
        }
        return null;
    }

    public static void clearAll() {
        if (null != activityCache && activityCache.size() > 0) {
            Iterator<BaseActivity> itor = activityCache.iterator();
            while (itor.hasNext()) {
                BaseActivity activity = itor.next();
                activity.finish();
                itor.remove();
            }
        }
    }

    public static <T> void clearAllExcept(Class<T> clazz) {
        if (null != activityCache && activityCache.size() > 0) {
            Log.d("baseActivity", "before clear alive:" + activityCache.size());
            Iterator<BaseActivity> itor = activityCache.iterator();
            while (itor.hasNext()) {
                BaseActivity activity = itor.next();
                if (clazz.isAssignableFrom(activity.getClass())) {
                    activity.finish();
                    itor.remove();
                }
            }
            Log.d("baseActivity", "after clear alive:" + activityCache.size());
        }
    }

    public static void clearAllExcept(BaseActivity except) {
        if (null != activityCache && activityCache.size() > 0) {
            Log.d("baseActivity", "before clear alive:" + activityCache.size());
            Iterator<BaseActivity> itor = activityCache.iterator();
            while (itor.hasNext()) {
                BaseActivity activity = itor.next();
                if (activity != except) {
                    activity.finish();
                    itor.remove();
                }
            }
            Log.d("baseActivity", "after clear alive:" + activityCache.size());
        }
    }

    @Override
    protected  void onDestroy(){
        Glide.get(this).clearMemory();
        Glide.get(this).getBitmapPool().clearMemory();
        System.gc();
        super.onDestroy();
        if (control) {
            activityCache.remove(this);
        }
    }

    private CustomProgressDlg dialog = null;

    public void showProgressDialog(Context context) {
        if (null == dialog) {
            dialog = new CustomProgressDlg(context, R.style.DialogStyle, true);
        }
        if (null != dialog && !dialog.isShowing()) {
            try {
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dismissProgressDialog() {
        if (null != dialog && dialog.isShowing()) {
            try {
                dialog.dismiss();
                dialog = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    protected abstract void initview();

    protected abstract void initdata();


}
