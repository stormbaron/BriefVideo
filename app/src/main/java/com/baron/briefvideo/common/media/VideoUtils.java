package com.baron.briefvideo.common.media;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;

public class VideoUtils {
/**
 * 视频资源处理
 *
 */

    /**
     * 获取视频缩略图
     * @param videoPath
     * @param width
     * @param height
     * @param kind
     * @return
     */
    public static Bitmap getVideoThumbnail(String videoPath, int width , int height, int kind){    
     Bitmap bitmap = null;    
     bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);    
     bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);    
     return bitmap;    
    } 
}
