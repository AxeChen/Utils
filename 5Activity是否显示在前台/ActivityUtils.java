package com.hunanyihong.htk.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;

public class ActivityUtils {
    /**
     * 判断某个activity是否在前台显示
     */
    public static boolean isForeground(Activity activity) {
        return isForeground(activity, activity.getClass().getName());
    }

    /**
     * 判断某个界面是否在前台,返回true，为显示,否则不是
     */
    public static boolean isForeground(Activity context, String className)
    {
        if (context == null || TextUtils.isEmpty(className))
            return false;
        ActivityManager am =(ActivityManager) context . getSystemService (Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am . getRunningTasks (1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list . get (0).topActivity;
            if (className.equals(cpn.getClassName()))
                return true;
        }
        return false;
    }
}
