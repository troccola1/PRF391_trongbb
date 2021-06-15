package com.supportmania.project4_prm391x_02_vn_fx02929;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.facebook.login.widget.ProfilePictureView;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ProfilePictureView profilePictureView = view.findViewById(R.id.friend_profile_picture);
        TextView txtEmail = view.findViewById(R.id.text_view_email);
        TextView txtName = view.findViewById(R.id.text_view_name);

        ImageView imageView = view.findViewById(R.id.friend_profile_picture_google);
        TextView txtEmailGoogle = view.findViewById(R.id.text_view_email_google);
        TextView txtNameGoogle = view.findViewById(R.id.text_view_name_google);
        Bundle bundle = getArguments();

        try {
            if (bundle != null) {
                txtEmail.setText(bundle.getString("email"));
                txtName.setText(bundle.getString("name"));
                profilePictureView.setProfileId(bundle.getString("id"));

                txtEmailGoogle.setText(bundle.getString("personEmail"));
                txtNameGoogle.setText(bundle.getString("personName"));

                Picasso.with(getContext()).load(Uri.parse(bundle.getString("personPhoto"))).into(imageView);

                if (bundle.getString("id") != null) {
                    view.findViewById(R.id.friend_profile_picture_google).setVisibility(View.GONE);

                }
                if (bundle.getString("personPhoto") != null) {
                    view.findViewById(R.id.friend_profile_picture).setVisibility(View.GONE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}
