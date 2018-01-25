package com.nova.bedshow.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * Created by lu on 2018/1/10.
 */

public class StringUtil {
    /**
     * 设置多彩字符串
     *
     * @param textView
     * @param head
     * @param span
     * @param bottom
     * @param color
     */
    public static void setSpanText(TextView textView, String head, String span, String bottom, int color) {
        textView.append(head);
        SpannableString ss = new SpannableString(span);
        ss.setSpan(new ForegroundColorSpan(color), 0, span.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(ss);
        textView.append(bottom);
    }

    public static boolean isBlank(String src) {
        return null == src || "".equals(src.trim());
    }
    public static boolean isNotBlank(String src) {
        return null != src && (!"".equals(src.trim()));
    }
}
