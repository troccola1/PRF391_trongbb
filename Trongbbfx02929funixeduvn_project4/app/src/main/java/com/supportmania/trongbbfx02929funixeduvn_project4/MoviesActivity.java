package com.supportmania.trongbbfx02929funixeduvn_project4;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MoviesActivity extends AppCompatActivity {

    // Declare ActionBar
    private ActionBar mActionBar;
    private boolean mAddLogin;
    // Declare information facebook, google.
    String name, email, id, personName, personEmail, personPhoto, loginType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        Intent intent = getIntent();
        loginType =intent.getStringExtra("loginType");
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        id = intent.getStringExtra("id");

        personPhoto = intent.getStringExtra("personPhoto");
        personName = intent.getStringExtra("personName");
        personEmail = intent.getStringExtra("personEmail");

        setmAddLogin();
        mActionBar = getSupportActionBar();

        // load the store fragment by default
        assert mActionBar != null;
        mActionBar.setTitle("Movies");
        loadFragment(new ListFragment());

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
    }

    private void setmAddLogin() {
        Intent mIntentInfor = getIntent();
        loginType = mIntentInfor.getStringExtra("loginType");
        assert loginType != null;
        if (loginType.contains("facebook")) {
            mAddLogin = true;
        } else if (loginType.contains("google")) {
            mAddLogin = false;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_movies:
                    mActionBar.setTitle("Movies");
                    fragment = new ListFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    mActionBar.setTitle("Profile");
                    fragment = new ProfileFragment();
                    Bundle bundle = new Bundle();

                    bundle.putString("name", name);
                    bundle.putString("email", email);
                    bundle.putString("id", id);

                    bundle.putString("personPhoto", personPhoto);
                    bundle.putString("personName", personName);
                    bundle.putString("personEmail", personEmail);
                    fragment.setArguments(bundle);
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_log_out:
                    Intent intent = new Intent(MoviesActivity.this, MainActivity.class);
                    if (mAddLogin) {
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                    return true;
            }
            return false;
        }
    };

    // TODO: load fragment
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
