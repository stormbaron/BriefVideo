package com.baron.briefvideo.moudle;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baron.briefvideo.R;
import com.baron.briefvideo.bean.Video;
import com.baron.briefvideo.common.adapter.ArrayVideoAdapter;
import com.baron.briefvideo.common.db.DBService;
import com.baron.briefvideo.common.thread.IMyRunnable;
import com.baron.briefvideo.common.thread.ThreadPoolUtil;

import java.util.List;

/**
 * MainActivity show video list
 *
 * @author baron
 */
public class MainActivity extends BaseActivity {
    private ListView video_listview;
    List<Video> listVideos;
    ProgressDialog progressDialog;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayVideoAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        getData();
    }

    public void initView() {
        video_listview = (ListView) findViewById(R.id.video_listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在加载视频");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void getData() {
        ThreadPoolUtil.doThread(new IMyRunnable(new IMyRunnable.MyRunnableTask() {
            @Override
            public void doTask() {

                listVideos = DBService.getVideoInfo(MainActivity.this);

                //SettingInfo.videoList=listVideos;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setView();
                        progressDialog.dismiss();
                    }
                });
            }
        }));

        //Log.e("MainActivity", listVideos.get(0).getPath().toString());
        /*
         * Intent intent = new Intent(Intent.ACTION_VIEW);
		 * intent.setDataAndType
		 * (Uri.parse(listVideos.get(0).getPath().toString()), "video/mp4");
		 * startActivity(intent); intent.setDataAndType(uri, "video/mp4");
		 * startActivity(intent);
		 */
    }

    public void getAndRefreshData() {
        ThreadPoolUtil.doThread(new IMyRunnable(new IMyRunnable.MyRunnableTask() {
            @Override
            public void doTask() {

                listVideos = DBService.getVideoInfo_refresh(MainActivity.this);

                //SettingInfo.videoList=listVideos;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //setView();
                        myadapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }));

    }

    public void setView() {

        myadapter = new ArrayVideoAdapter(this,
                R.layout.item_list, listVideos);
        video_listview.setAdapter(myadapter);
        video_listview.setOnItemClickListener(videoItemClickListener);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            //ʵ��ˢ��ʱ��
            @Override
            public void onRefresh() {
                // Toast.makeText(MainActivity.this, "正在加载视频", Toast.LENGTH_LONG).show();
                //do something

                getAndRefreshData();
                //........

            }
        });
    }


    OnItemClickListener videoItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            VideoSurface.actionStart(MainActivity.this, listVideos.get(arg2)
                    .getPath(), listVideos.get(arg2).getTitle());
        }
    };


}
