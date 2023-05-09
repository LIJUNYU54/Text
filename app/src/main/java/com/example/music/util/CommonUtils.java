package com.example.music.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 0.0xiaoyu@gmail.com on 2018/4/24.
 */

public class CommonUtils {
    //由于是循环调用，所以把new相关语句拿出来
    static String defaultPattern = "yyyy-MM-dd HH:mm:ss";
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultPattern);
    static Date date = new Date();

    /**
     * 获取格式化后的时间
     *
     * @param pattern
     * @param timeMillis
     * @return
     */
    //工具类中的方法一般写成静态
    public static String getFormattedTime(String pattern, long timeMillis) {
        if (pattern != null && !"".equals(pattern)) {
            //应用新的pattern
            simpleDateFormat.applyPattern(pattern);
        }
        date.setTime(timeMillis);
        return simpleDateFormat.format(date);
    }

    //方法重载，因为这个案例中只会有这种格式的
    public static String getFormattedTime(long timeMillis) {
        return getFormattedTime("mm:ss", timeMillis);
    }
}
