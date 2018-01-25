package com.nova.bedshow.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.nova.bedshow.R;


/**
 * Created by Administrator on 2017/12/11.
 */

public class CustomProgressDlg extends Dialog {

    Context context;
    private boolean cancel;
    ImageView progress_dialog;
    CircularProgressDrawable circularProgressDrawable;
    public CustomProgressDlg(Context context, int theme, boolean cancel) {
        super(context, theme);
        this.context = context;
        this.cancel = cancel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);
        setCanceledOnTouchOutside(false);// 显示进度条的时候 点击无效
        setCancelable(cancel);
        progress_dialog = (ImageView) findViewById(R.id.progress_dialog);
        circularProgressDrawable = new CircularProgressDrawable(context
                .getResources().getColor(R.color.theme_n), 5);
        progress_dialog.setImageDrawable(circularProgressDrawable);
        circularProgressDrawable.start();
    }
}
