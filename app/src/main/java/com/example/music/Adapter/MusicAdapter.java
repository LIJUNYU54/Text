package com.example.music.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.music.R;
import com.example.music.activity.register;
import com.example.music.database.MyDatabaseHelper;
import com.example.music.type.Music;

import java.util.List;

public class MusicAdapter extends BaseAdapter<Music>  {
//    TextView title;
//    ImageView coll;
//    MyDatabaseHelper myDatabaseHelper;
    public MusicAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Music music = getData().get(i);
//        coll=view.findViewById(R.id.coll);
//        title=view.findViewById(R.id.tv_music_title);
//        coll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String Coll=title.getText().toString().trim();
//                if (!TextUtils.isEmpty(Coll)){
//                    myDatabaseHelper.Colladd(Coll);
//                }
//
//            }
//        });
        ViewHolder holder;
//         myDatabaseHelper =new MyDatabaseHelper(getContext());
        if (view == null) {
            view = getInflater().inflate(R.layout.item_music, null);
            holder = new ViewHolder();
            holder.title = view.findViewById(R.id.tv_music_title);
            holder.path = view.findViewById(R.id.tv_music_path);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.title.setText(music.getTitle());
        holder.path.setText(music.getPath());

        return view;
    }


    class ViewHolder {
        TextView title;
        TextView path;
    }

}
