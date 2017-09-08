package com.android.common;

import android.os.Environment;

/**
 * Created by kiddo on 17-8-3.
 */

public class AppConstant {
    //public static final String Url = "http://120.25.235.70/Record/";
    public static final String Url = "http://192.168.199.133:8080";

    public final static String SAVE_PATH = Environment.getExternalStorageDirectory() + "/record/";

    /**
     * 本地保存图片的相对位置
     */
    public final static String PHOTO_SAVE_PATH = SAVE_PATH + "photo/";

    /**
     * 压缩图片暂存位置
     */
    public static final String PATH_COMPRESSED_IMAGES = AppConstant.SAVE_PATH + "compressedImages/";
}
