package com.baron.briefvideo.common.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baron.briefvideo.R;
import com.baron.briefvideo.bean.Video;
import com.baron.briefvideo.utils.ImageUtils;
import com.baron.briefvideo.utils.LogUtil;

import java.util.List;

import static com.baron.briefvideo.utils.DateUtils.formatTime;

public class ArrayVideoAdapter extends ArrayAdapter {
    private Context context;
    private List<Video> dataList;
    private int layoutId;

    @SuppressWarnings("unchecked")
    public ArrayVideoAdapter(Context context, int resource, List<Video> objects) {
        super(context, resource, objects);
        this.context = context;
        dataList = objects;
        layoutId = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layoutId, null);
        TextView name = (TextView) convertView.findViewById(R.id.video_title);
        name.setText(dataList.get(position).getTitle());
        ImageView img = (ImageView) convertView.findViewById(R.id.video_img);
        // img.setImageBitmap(VideoProvider.)
        TextView time = (TextView) convertView.findViewById(R.id.video_time);
        TextView size = (TextView) convertView.findViewById(R.id.video_size);
        size.setText(formatSize(dataList.get(position).getSize()) + " MB");
        time.setText("" + formatTime(dataList.get(position).getDuration()));
        LogUtil.warn("ArrayAdapter", "Size:" + dataList.get(position).getSize() + ";Duration:" + dataList.get(position).getDuration());
        LogUtil.warn("ArrayAdapter", "Size:" + formatSize(dataList.get(position).getSize()) + ";Duration:" + dataList.get(position).getDuration());
        if (dataList.get(position).getImagePath() != null) {
            img.setImageBitmap(
                    ImageUtils.getLoacalBitmap(dataList.get(position).getImagePath()));
        } else {

        }
        return convertView;
    }

    public int getCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    public long formatSize(long size) {
        return size / (1024 * 1024);
    }
}
