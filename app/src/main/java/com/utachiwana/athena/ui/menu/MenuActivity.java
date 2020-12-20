package com.utachiwana.athena.ui.menu;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.utachiwana.athena.DataGenerator;
import com.utachiwana.athena.R;
import com.utachiwana.athena.data.Post;
import com.utachiwana.athena.ui.login.LoginFragment;
import com.utachiwana.athena.ui.menu.home.HomeFragment;
import com.utachiwana.athena.ui.menu.profile.ProfileFragment;

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    BottomNavigationView mBottomBar;
    final String TG_HOME = "home", TG_ENTRIES = "entries", TG_PROFILE = "profile";
    HomeFragment home;
    ProfileFragment profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        if (savedInstanceState == null) {
            home = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, home)
                    .commit();
        }
        mBottomBar = findViewById(R.id.bottomBarNavigationView);

        mBottomBar.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_profile:
                    if (profile == null) {
                        profile = new ProfileFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("data", getIntent().getStringExtra("profile"));
                        profile.setArguments(bundle);
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, profile, TG_PROFILE)
                            .commit();

                    break;
                case R.id.menu_home:
                    if (home == null) {
                        home = new HomeFragment();
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, home, TG_HOME)
                            .commit();
                    break;
            }

            return true;
        });
    }

}