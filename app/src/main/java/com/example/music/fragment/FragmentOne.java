package com.example.music.fragment;


import static android.net.wifi.WifiConfiguration.Status.strings;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.music.Adapter.MusicAdapter;
import com.example.music.R;
import com.example.music.app.MusicPlayerApplication;
import com.example.music.database.MyDatabaseHelper;
import com.example.music.service.PlayMusicService;
import com.example.music.type.Music;
import com.example.music.util.CommonUtils;
import com.example.music.util.Consts;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, SeekBar.OnSeekBarChangeListener {

    private ListView listView;
    private TextView tvMusicInfo;
    private TextView tvMusicCurrentPosition;
    private TextView tvMusicDuration;
    private SeekBar skMusicProgress;
    private ImageButton ibPrevious;
    private ImageButton ibPlay;
    private ImageButton ibNext;
    private SearchView searchView;

    private List<Music> musics;
    private MusicAdapter adapter;

    private InnerReceiver receiver;

    MyDatabaseHelper myDatabaseHelper;
//   private
//   ArrayList<Music> arrayList = new ArrayList<Music>();
    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_one, container, false);

        myDatabaseHelper=new MyDatabaseHelper(getContext());


        searchView=view.findViewById(R.id.search);
        listView = view.findViewById(R.id.lv_music_list);
        tvMusicInfo = view.findViewById(R.id.tv_music_info);
        tvMusicCurrentPosition = view.findViewById(R.id.tv_music_current_position);
        tvMusicDuration = view.findViewById(R.id.tv_music_duration);
        skMusicProgress = view.findViewById(R.id.sk_progress);
        ibPrevious = view.findViewById(R.id.ib_previous);
        ibPlay = view.findViewById(R.id.ib_play);
        ibNext = view.findViewById(R.id.ib_next);
//        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(getContext());
//
//       myDatabaseHelper.imgadd(img,"某某");


//        initView();
        //获取数据
        MusicPlayerApplication application = (MusicPlayerApplication) getActivity().getApplication();
        musics = application.getMusics();
        adapter = new MusicAdapter(getContext(),musics);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Getdata(position);
            }
        });
        setListeners();

        listView.setTextFilterEnabled(true);
//        设置该SearchView默认是否自动缩小为图标
        searchView.setIconifiedByDefault(false);
//        设置该SearchView显示搜索图标
        searchView.setSubmitButtonEnabled(true);
//        设置该SearchView内默认显示的搜索文字
        searchView.setQueryHint("查找");
//        为SearchView组件设置事件的监听器
//         listView.setAdapter(new ArrayAdapter<Music>(getContext(),R.layout.item_music,arrayList));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //            单击搜索按钮时激发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
//                实际应用中应该在该方法内执行实际查询
//                此处仅使用Toast显示用户输入的查询内容
                Toast.makeText(getContext(),"您选择的是："+query,
                        Toast.LENGTH_SHORT).show();
                boolean data=false;
                for (Music music:musics){
                    if (music.getTitle().equals(query)){
                        data=true;
                        listView.setFilterText(String.valueOf(music));
                    }
                    else {
                        data=false;
                    }
                }
                return false;
            }
            //            用户输入时激发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
//                如果newText不是长度为0的字符串
                if (TextUtils.isEmpty(newText)){
//                    清除ListView的过滤
                    listView.clearTextFilter();
                }else {
//                    使用用户输入的内容对ListView的列表项进行过滤
                    listView.setFilterText(newText);
                }
                return true;
            }
        });

        //激活Service
        Intent intent = new Intent(getContext(), PlayMusicService.class);
        getActivity().startService(intent);

        //注册广播接收者
        receiver = new InnerReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Consts.SET_PLAY_STATE);
        filter.addAction(Consts.SET_PAUSE_STATE);
        filter.addAction(Consts.UPDATE_PROGRESS);
       getActivity().registerReceiver(receiver, filter);
        return view;
    }


    private void setListeners() {
        ibPlay.setOnClickListener(this);
        ibPrevious.setOnClickListener(this);
        ibNext.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        skMusicProgress.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_play:
                //发送广播
                getActivity().sendBroadcast(new Intent(Consts.PLAY_OR_PAUSE));
                break;
            case R.id.ib_previous:
                //发送广播
                getActivity().sendBroadcast(new Intent(Consts.PREVIOUS));
                break;
            case R.id.ib_next:
                //发送广播
                getActivity().sendBroadcast(new Intent(Consts.NEXT));
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setAction(Consts.PLAY_APPOINT);
        intent.putExtra("position", position);
        getActivity().sendBroadcast(intent);
    }

    //进度条拖拽
    //标记是否正在标记进度条
    private boolean isTrackingTouch;

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        isTrackingTouch = true;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        isTrackingTouch = false;
        //当停止拖拽时发出广播，使service实现快进
        Intent intent = new Intent(Consts.SEEK_T0);
        intent.putExtra(Consts.EXTRA_SEEK_TO_PERCENT, skMusicProgress.getProgress());
        getActivity().sendBroadcast(intent);
    }

    private class InnerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Consts.SET_PLAY_STATE.equals(action)) {
                ibPlay.setImageResource(android.R.drawable.ic_media_pause);
                //获取正在播放的歌曲的索引
                int currentMusicIndex = intent.getIntExtra(Consts.EXTRA_CURRENT_MUSIC_INDEX, 0);
                //设置显示当前正在播放的歌曲的标题和时
                tvMusicInfo.setText(musics.get(currentMusicIndex).getTitle());
                tvMusicDuration.setText(CommonUtils.getFormattedTime(intent.getIntExtra(Consts.EXTRA_DURATION, 0)));
            } else if (Consts.SET_PAUSE_STATE.equals(action)) {
                ibPlay.setImageResource(android.R.drawable.ic_media_play);
            } else if (Consts.UPDATE_PROGRESS.equals(action)) {
                int currentPosition = intent.getIntExtra(Consts.EXTRA_CURRENT_POSITION, 0);
                int percent = intent.getIntExtra(Consts.EXTRA_PERCENT, 0);
                //更新控件
                tvMusicCurrentPosition.setText(CommonUtils.getFormattedTime(currentPosition));
                //仅当没有在拖拽时更新进度条
                if (!isTrackingTouch) {
                    skMusicProgress.setProgress(percent);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        //注销广播接收者
         getActivity().unregisterReceiver(receiver);
        super.onDestroy();
    }

    public  void Getdata( int position){
         String musictitle = null;
         Music music = null;
         String data = null;
         switch (position){
             case  0:
                 if (data == null){
                     musictitle=music.getTitle();
                     data = data + 1;
                 }
                 else {
                     data=data+1;
                 }
                 myDatabaseHelper.music(musictitle,data);
                 break;
         }

    }

}
