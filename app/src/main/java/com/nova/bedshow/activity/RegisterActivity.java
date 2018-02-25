package com.nova.bedshow.activity;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.nova.bedshow.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lu on 2018/2/25.
 */

public class RegisterActivity extends BaseActivity {


    @Bind(R.id.text_protocol)
    TextView text_protocol;






    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        text_protocol.setText("");
        text_protocol.append("我已阅读并同意");
        SpannableStringBuilder sub3 = new SpannableStringBuilder("使用条款和隐私政策");
        sub3.setSpan(atClick(this), 0, sub3.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        text_protocol.append(sub3);
        text_protocol.setMovementMethod(LinkMovementMethod.getInstance());// textview只有加上这句代码才能实现多个点击事件的响应
    }


    @Override
    protected void initview() {

    }

    @Override
    protected void initdata() {

    }



    public static ClickableSpan atClick(final BaseActivity activity) {
        return new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // TODO Auto-generated method stub
                WebViewActivity.startFrom(activity,
                        WebViewActivity.USER_PROTOCOL, "用户协议");
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(activity.getResources().getColor(R.color.theme_n));
                ds.setUnderlineText(false);
            }
        };
    }
}
