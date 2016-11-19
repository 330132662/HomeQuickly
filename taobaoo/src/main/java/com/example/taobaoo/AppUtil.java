package com.example.taobaoo;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将遇到的工具类全部集成于此  200行以内的  零散的没什么扩展性的   ，
 */

public class AppUtil {

    /**
     * 集合是否至少有一个元素
     *
     * @param list
     * @return
     */
    public static boolean isListEmpty(List list) {
        if (null != list && list.size() > 0) {
            return false;
        } else {
            return true;
        }

    }
/***********************************************文本工具开始*****************************************************************************/

    /**
     * @author YuYuanDa
     * created by 2016-08-25
     * 判断是否为空
     */
    public static boolean isEmpty(String str) {
        if ((str != null) && (!"null".equals(str)) && (!"".equals(str)) && (str.length() > 0)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkUserName(String userName) {
        boolean flag = false;
        String check = "([a-zA-Z]|[0-9]){4,20}";
        try {
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(userName);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 判断邮箱是否合法
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }

        return flag;
    }

    /**
     * 复制文字到剪切板
     */
    public static void copyTextContent(String content, Context context) {
        if (!isEmpty(content)) {
            ClipboardManager clip = (ClipboardManager) context
                    .getSystemService(Context.CLIPBOARD_SERVICE);
            clip.setText(content.trim());
        }
    }

    /**
     * 从系统剪切板粘贴
     */
    public static String peaseTextContent(Context context) {
        ClipboardManager clip = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        return clip.getText().toString().trim();
    }

    /***********************************************文本工具结束*****************************************************************************/
    /***********************************************屏幕工具开始*****************************************************************************/
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float getDensity(Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return scale;
    }

    public static int getColor(Context context, int colorId) {
        return ContextCompat.getColor(context, colorId);
    }

    public static Drawable getDrawable(Context context, int resId) {
        return ContextCompat.getDrawable(context,resId);
    }
    /**
     * 将当前屏幕高度分为100份 传入您所需的百分比
     * 如果需要获取屏幕宽度请传入 100
     *
     * @param context 上下文
     * @param scale   百分比
     * @return int值 =scale/100
     */
    public static int getDpFromScreenHeight(Context context, int scale) {
        WindowManager m = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int newHight = m.getDefaultDisplay().getHeight() / 100 * scale;
        return newHight;
    }

    /**
     * 将当前屏幕宽度分为100份 传入您所需的百分比
     * 如果需要获取屏幕实高际度请传入 100
     *
     * @param context context 上下文
     * @param scale   百分比
     * @return int值 =scale/100
     */
    public static int getDpFromScreenWidth(Context context, int scale) {
        WindowManager m = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int newWidth = m.getDefaultDisplay().getWidth() / 100 * scale;
        return newWidth;
    }
    /************************************************屏幕工具结束*********************************************/
    /************************************************
     * Snack开始
     *********************************************/
    private static Snackbar snackbar;

    /**
     * 函数0   只有左侧文本
     *
     * @param viewInParent 当前界面任一个View子类对象即可  记得初始化！
     * @param leftStr      左侧文本
     */
    public static void showSnackBar(View viewInParent, String leftStr) {

        snackbar = Snackbar.make(viewInParent, leftStr, Snackbar.LENGTH_SHORT);
        KHSnackTheme(snackbar);
        snackbar.show();
    }

    /**
     * 函数1 传入两侧文本 没监听
     * 若要在按右侧按钮做自定义操作，请使用函数2
     *
     * @param viewInParent 当前界面任一个View子类对象即可  记得初始化！
     * @param leftStr      左侧文本
     * @param rightStr     右侧文本
     */
    public static void showSnackBar(View viewInParent, String leftStr, String rightStr) {
        snackbar = Snackbar.make(viewInParent, leftStr, Snackbar.LENGTH_LONG).setAction(rightStr, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                不弄一个 右侧文本不能显示
            }
        });
        KHSnackTheme(snackbar);
        snackbar.show();
    }

    /**
     * 函数2   两侧有文本   右侧按钮请实现监听
     *
     * @param viewInParent  当前界面任一个View子类对象即可  记得初始化！
     * @param leftStr       左侧文本
     * @param rightStr      右侧文本
     * @param rightListener 右侧监听
     */
    public static void showSnackBar(View viewInParent, String leftStr, String rightStr, View.OnClickListener rightListener) {
        snackbar = Snackbar.make(viewInParent, leftStr, Snackbar.LENGTH_LONG).setAction(rightStr, rightListener);
        KHSnackTheme(snackbar);
        snackbar.show();
    }

    /**
     * 工具类内部处理应用snackbar样式
     *
     * @param snackbar
     */
    private static void KHSnackTheme(Snackbar snackbar) {
        Snackbar.SnackbarLayout snacklayout = (Snackbar.SnackbarLayout) snackbar.getView();//snackd布局
        /**
         *  snack背景
         */
//        snacklayout.setBackgroundResource(R.color.deeporange);
//                右侧文本(监听可在外部设置)
//        TextView lefttv = (TextView) snacklayout.findViewById(R.id.snackbar_text);
//        TextView actiontv = (TextView) snacklayout.findViewById(R.id.snackbar_action);
//        actiontv.setTextColor(Color.parseColor("#ffffff"));//此处控制透明度
//        actiontv.setTextSize(16);
//        lefttv.setTextSize(16);
    }
    /************************************************Snack结束*********************************************/
    /************************************************
     * 数值转换开始
     *********************************************/
    public static int strToInt(String str) {
        int num = 0;
        try {
            num = Integer.valueOf(str);
        } catch (Exception e) {
            num = 0;
        }
        return num;
    }

    /**
     * 2016年1月22日16:18:16
     *
     * @param str          将要被转化的值
     * @param defaultValue 抛出异常得到捕获之后，期望返回的默认值
     * @return J_X 重载自己设置默认值
     */
    public static int strToInt(String str, final int defaultValue) {
        int num = 0;
        try {
            num = Integer.valueOf(str);
        } catch (Exception e) {
            num = defaultValue;
        }
        return num;
    }

    public static double strToDouble(String str) {
        double num = 0.00;
        try {
            num = Double.valueOf(str);
        } catch (Exception e) {
            num = 0.00;
        }
        return num;
    }

    public static float strToFloat(String str) {
        float num = 0.00f;
        try {
            num = Float.valueOf(str);
        } catch (Exception e) {
            num = 0.00f;
        }
        return num;
    }

    /**
     * 格式化保留两位小数
     *
     * @param str
     * @return 2016年3月3日16:28:19 JX
     */
    public static String strToDouble2(String str) {
        DecimalFormat df = new DecimalFormat("#0.00");
        double num = 0.00;
        try {
            num = Double.valueOf(str);
        } catch (Exception e) {
            num = 0.00;
        }
        return df.format(num);
    }

}
