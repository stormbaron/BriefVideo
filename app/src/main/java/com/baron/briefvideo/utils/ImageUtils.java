package com.baron.briefvideo.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
public class ImageUtils {

    /**
     * 图片（Bitmap）保存到本地SDK内，返回保存的路径
     * @param
     * @param videoName
     * @return image_path
     */
    public static String  saveImageToSdk(Bitmap b,String videoName){
        //Bitmap b =  bitmap;
        String name = videoName;
        String fileNmae = Environment.getExternalStorageDirectory().toString()+File.separator+"briefvideo/image/"+name+".jpg";
        File imgFile=new File(fileNmae);

        try {
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                if(!imgFile.getParentFile().exists()){
                    imgFile.getParentFile().mkdirs();
                }
                BufferedOutputStream bos;
                bos = new BufferedOutputStream(new FileOutputStream(imgFile));
                if(bos!=null){
                    b.compress(Bitmap.CompressFormat.JPEG, 80, bos);

                    bos.flush();
                    bos.close();
                }
            }
            else{


            }
        } catch (FileNotFoundException e) {

            //e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return  fileNmae;

    }


    /**
     * 加载本地图片
     * http://bbs.3gstdy.com
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 从服务器取图片
     *http://bbs.3gstdy.com
     * @param url
     * @return
     */
    public static Bitmap getHttpBitmap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            // Log.d(TAG, url);
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setConnectTimeout(0);
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}