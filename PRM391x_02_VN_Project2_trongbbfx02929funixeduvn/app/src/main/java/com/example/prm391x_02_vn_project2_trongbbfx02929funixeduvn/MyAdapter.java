package com.example.prm391x_02_vn_project2_trongbbfx02929funixeduvn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Item> {
    ArrayList<Item> arrayList = new ArrayList<Item>();
    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Item> ojects) {
        super(context, resource, ojects);
        arrayList = ojects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listItem = inflater.inflate(R.layout.mylist, null);

        ImageView imageView = listItem.findViewById(R.id.imageView);
        imageView.setImageResource(arrayList.get(position).getmImgId());

        TextView textViewTitle = listItem.findViewById(R.id.textViewTitle);
        textViewTitle.setText(arrayList.get(position).getmTitle());

        TextView subTextViewTitle = listItem.findViewById(R.id.subTextViewTitle);
        subTextViewTitle.setText(arrayList.get(position).getmSubtitle());

        return listItem;
    }
}