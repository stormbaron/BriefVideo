package com.baron.briefvideo.common.media;

import java.util.ArrayList;
import java.util.List;
import android.Manifest;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.baron.briefvideo.bean.Video;
import com.baron.briefvideo.utils.ImageUtils;
/**
 * 本地视频资源获取
 * @author baron
 *
 */
public class VideoProvider implements AbstructProvider {  
    private Context context;  
      
    public VideoProvider(Context context) {  
        this.context = context;  
    }  
      
    @Override  
    public List<Video> getList() {
        List<Video> list = null;
            if (context != null) {
                Cursor cursor = context.getContentResolver().query(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null,
                        null, null);
                if (cursor != null) {
                    list = new ArrayList<Video>();
                    while (cursor.moveToNext()) {
                        int id = cursor.getInt(cursor
                                .getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                        String title = cursor
                                .getString(cursor
                                        .getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                        String album = cursor
                                .getString(cursor
                                        .getColumnIndexOrThrow(MediaStore.Video.Media.ALBUM));
                        String artist = cursor
                                .getString(cursor
                                        .getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST));
                        String displayName = cursor
                                .getString(cursor
                                        .getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                        String mimeType = cursor
                                .getString(cursor
                                        .getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE));
                        String path = cursor
                                .getString(cursor
                                        .getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                        long duration = cursor
                                .getInt(cursor
                                        .getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                        long size = cursor
                                .getLong(cursor
                                        .getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));

                        Bitmap imagebit=VideoUtils.getVideoThumbnail(path,220,200,100);

                        String imagePath= ImageUtils.saveImageToSdk(imagebit, title);

                        Video video = new Video(id, title, album, artist, displayName, mimeType, path, size, duration,imagePath,imagebit);
                        // LogUtil.warn("Video", "id"+id+";title"+title+";album"+album+";artist"+artist+";displayName"+displayName+";mimeType"+mimeType+";path"+path+";size"+size+";duration"+ duration+";imagePath"+imagePath);
                        list.add(video);
                    }
                    cursor.close();
                }
            }
        return list;
    }  
    
}
