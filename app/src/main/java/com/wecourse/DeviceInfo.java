package com.wecourse;

import android.os.Build;

/**
 * Created by ddany on 2016/3/27.
 */
public class DeviceInfo{
    //不可在任何地方修改值
    public final static int DEVICE_SDK_VERSION = Build.VERSION.SDK_INT;
    public final static String DEVICE_VERSION = Build.MODEL;

    public static void init() {
    }

}
