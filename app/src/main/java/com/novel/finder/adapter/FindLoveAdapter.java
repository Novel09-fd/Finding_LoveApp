package com.novel.finder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.novel.finder.R;
import com.novel.finder.entity.UserModel;

import java.util.ArrayList;

public class FindLoveAdapter extends BaseAdapter {
    private ArrayList<UserModel> list;
    private Context context;

    public FindLoveAdapter(ArrayList<UserModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int i) {

        return list.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_find_love,viewGroup,false);
            ImageView image = view.findViewById(R.id.img_findLove);
            ((TextView)view.findViewById(R.id.txt_name)).setText(list.get(i).getName());
            ((TextView)view.findViewById(R.id.txt_age)).setText(list.get(i).getAge());
            Glide.with(context).load("http://e997-61-94-124-28.ngrok.io/user/image/"+list.get(i).getPhoto()).into(image);
        }

        return view;
    }
}
