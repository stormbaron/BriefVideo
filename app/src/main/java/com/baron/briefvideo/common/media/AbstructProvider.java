package com.baron.briefvideo.common.media;

import com.baron.briefvideo.bean.Video;

import java.util.List;
/**
 * 本地资源获取（提供）接口
 * @author baron
 *
 */
public interface AbstructProvider {
	public List<Video> getList();
}
