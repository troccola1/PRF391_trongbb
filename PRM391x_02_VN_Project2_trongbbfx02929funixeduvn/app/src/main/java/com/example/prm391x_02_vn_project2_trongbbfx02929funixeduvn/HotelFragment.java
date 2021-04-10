package com.example.prm391x_02_vn_project2_trongbbfx02929funixeduvn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class HotelFragment extends Fragment {
    // Khai báo list view(danh sách)
    ListView simpleList;
    // Khai báo metro list(danh sách)
    ArrayList<Item> hotellList = new ArrayList<Item>();

    // Return a View component
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotel, container, false);
        simpleList= view.findViewById(R.id.list_hotel);
        hotellList.add(new Item(R.drawable.hotel, "The Queen Hotel and Spa",
                "67 Thuốc Bắc, Hàng Bồ, Hàng Bồ, Quận Hoàn Kiếm, Hà Nội, Việt Nam"));
        hotellList.add(new Item(R.drawable.hotel, "Hanoi Nostalgia Hotel & Spa",
                "13-15 Luong Ngoc Quyen, Hang Buom, Hoan Kiem, Hàng Buồm, Quận Hoàn Kiếm, Hà Nội, Việt Nam"));
        hotellList.add(new Item(R.drawable.hotel, "Church Legend Hotel Hanoi",
                "46 Ấu Triệu, Phường Hàng Trống, Quận Hoàn Kiếm, Hà Nội, Việt Nam"));
        hotellList.add(new Item(R.drawable.hotel, "Little Hanoi Diamond Hotel",
                "11 Bát Đàn, Quận Hoàn Kiếm, Hàng Bồ, Quận Hoàn Kiếm, Hà Nội Việt Nam"));
        hotellList.add(new Item(R.drawable.hotel, "Flamingo Dai Lai Resort",
                "Thôn Ngọc Quang, Xã Ngọc Thanh, Vĩnh Phúc, Phúc Yên, Hà Nội, Việt Nam"));
        hotellList.add(new Item(R.drawable.hotel, "Anman Legend Hotel",
                "27 Hàng Bè, Hàng Bạc, Quận Hoàn Kiếm, Hà Nội, Việt Nam"));

        // Khai báo lớp MyAdapter đối tượng myAdapter
        MyAdapter myAdapter = new MyAdapter(getContext(),R.layout.mylist,hotellList);
        simpleList.setAdapter(myAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Toast.makeText(getContext(), "67 Thuốc Bắc, Hàng Bồ, Hàng Bồ, Quận Hoàn Kiếm, Hà Nội, Việt Nam",
                            Toast.LENGTH_SHORT).show();
                }
                if (i == 1) {
                    Toast.makeText(getContext(), "13-15 Luong Ngoc Quyen, Hang Buom, Hoan Kiem, Hàng Buồm, Quận Hoàn Kiếm, Hà Nội, Việt Nam",
                            Toast.LENGTH_SHORT).show();
                }
                if (i == 2) {
                    Toast.makeText(getContext(), "46 Ấu Triệu, Phường Hàng Trống, Quận Hoàn Kiếm, Hà Nội, Việt Nam",
                            Toast.LENGTH_SHORT).show();
                }


                if (i == 3) {
                    Toast.makeText(getContext(), "11 Bát Đàn, Quận Hoàn Kiếm, Hàng Bồ, Quận Hoàn Kiếm, Hà Nội Việt Nam",
                            Toast.LENGTH_SHORT).show();
                }
                if (i == 4) {
                    Toast.makeText(getContext(), "Thôn Ngọc Quang, Xã Ngọc Thanh, Vĩnh Phúc, Phúc Yên, Hà Nội, Việt Nam",
                            Toast.LENGTH_SHORT).show();
                }
                if (i == 5) {
                    Toast.makeText(getContext(), "27 Hàng Bè, Hàng Bạc, Quận Hoàn Kiếm, Hà Nội, Việt Nam",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}

