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


public class HospitalFragment extends Fragment {

    // Khai báo list view(danh sách)
    ListView simpleList;
    // // Khai báo metro list(danh sách)
    ArrayList<Item> hospitalList = new ArrayList<Item>();

    // Return a View component
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hospital, container, false);
        simpleList = view.findViewById(R.id.list_hospital);
        hospitalList.add(new Item(R.drawable.hospital, "Bệnh viện Bạch Mai",
                "78 – Đường Giải Phóng – Phương Mai – Đống Đa – Hà Nội"));
        hospitalList.add(new Item(R.drawable.hospital, "Bệnh Viện Hữu Nghị",
                "Số 1 – Trần Khánh Dư – Quận Hai Bà Trưng – Hà Nội"));
        hospitalList.add(new Item(R.drawable.hospital, "Bệnh Viện E, Hà Nội",
                "89 - Trần Cung - Nghĩa Tân - Cầu Giấy - hà Nội"));
        hospitalList.add(new Item(R.drawable.hospital, "Viện Răng Hàm Mặt",
                "40B - Tràng Thi - Hoàn Kiếm - Hà Nội"));
        hospitalList.add(new Item(R.drawable.hospital, "Bệnh Viện Tai Mũi Họng Trung Ương",
                "78 – Đường Giải Phóng – Quận Đống Đa – Hà Nội"));
        hospitalList.add(new Item(R.drawable.hospital, "Bệnh Viện Mắt Trung Ương",
                "85 - Phố Bà Triệu - Quận Hai Bà Trưng - Hà Nội"));
        // Declare the class MyAdapter object adapter
        MyAdapter adapter = new MyAdapter(getContext(), R.layout.mylist, hospitalList);
        simpleList.setAdapter(adapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Toast.makeText(getContext(), "78 – Đường Giải Phóng – Phương Mai – Đống Đa – Hà Nội",
                            Toast.LENGTH_SHORT).show();
                }
                if (i == 1) {
                    Toast.makeText(getContext(), "Số 1 – Trần Khánh Dư – Quận Hai Bà Trưng – Hà Nội",
                            Toast.LENGTH_SHORT).show();
                }
                if (i == 2) {
                    Toast.makeText(getContext(), "89 - Trần Cung - Nghĩa Tân - Cầu Giấy - hà Nội",
                            Toast.LENGTH_SHORT).show();
                }
                if (i == 3) {
                    Toast.makeText(getContext(), "40B - Tràng Thi - Hoàn Kiếm - Hà Nội",
                            Toast.LENGTH_SHORT).show();
                }
                if (i == 4) {
                    Toast.makeText(getContext(), "78 – Đường Giải Phóng – Quận Đống Đa – Hà Nội",
                            Toast.LENGTH_SHORT).show();
                }
                if (i == 5) {
                    Toast.makeText(getContext(), "85 - Phố Bà Triệu - Quận Hai Bà Trưng - Hà Nội",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
