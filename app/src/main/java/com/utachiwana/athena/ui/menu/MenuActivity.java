package com.utachiwana.athena.ui.menu;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.utachiwana.athena.R;
import com.utachiwana.athena.data.Post;
import com.utachiwana.athena.ui.login.LoginFragment;
import com.utachiwana.athena.ui.menu.home.HomeFragment;
import com.utachiwana.athena.ui.menu.profile.ProfileFragment;

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    BottomNavigationView mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new HomeFragment())
                    .commit();
        }
        mBottomBar = findViewById(R.id.bottomBarNavigationView);

        mBottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_profile:
                        //todo:нужно чистить стек фрагментов и убрать костыль
                        if (getSupportFragmentManager().getBackStackEntryCount() < 1) //костыль
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainer, new ProfileFragment(), "menu_profile")
                                .addToBackStack("menu_profile")
                                .commit();
                        break;
                    case R.id.menu_home:
                        //todo:нужно чистить стек фрагментов
                        /*getSupportFragmentManager().beginTransaction()
                                .add(R.id.fragmentContainer, new HomeFragment())
                                .addToBackStack(null)
                                .commit();*/
                        getSupportFragmentManager().popBackStack();
                        break;
                }

                return true;
            }
        });
    }

}