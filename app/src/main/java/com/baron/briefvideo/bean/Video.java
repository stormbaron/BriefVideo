package com.baron.briefvideo.bean;

import java.io.Serializable;

import android.graphics.Bitmap;

public class Video implements Serializable {

    private static final long serialVersionUID = -7920222595800367956L;
    private int id;
    /**
     * 视频名称
     */
    private String title;
    /**
     *
     */
    private String album;
    /**
     * 视频作者
     */
    private String artist;
    /**
     *
     */
    private String displayName;
    /**
     *
     */
    private String mimeType;
    /**
     * 视频路径
     */
    private String path;
    /**
     * 截图保存的路径
     */
    private String imagePath;
    /**
     * 视频大小
     */
    private long size;
    /**
     * 视频播放位置
     */
    private long position;
    /**
     * 视频总的长度
     */
    private long duration;
    /**
     * 截图bitmap
     */
    private Bitmap bitmap;

    // private LoadedImage image;

    /**
     *
     */
    public Video() {
        super();
    }

    /**
     * @param id
     * @param title
     * @param album
     * @param artist
     * @param displayName
     * @param mimeType
     * @param size
     * @param duration
     */
    public Video(int id, String title, String album, String artist,
                 String displayName, String mimeType, String path, long size,
                 long duration, String imagePath, Bitmap bit) {
        super();
        this.id = id;
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.displayName = displayName;
        this.mimeType = mimeType;
        this.path = path;
        this.size = size;
        this.duration = duration;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}