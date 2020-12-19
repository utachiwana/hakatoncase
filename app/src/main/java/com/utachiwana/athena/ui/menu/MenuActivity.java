package com.utachiwana.athena.ui.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.utachiwana.athena.R;
import com.utachiwana.athena.data.Post;
import com.utachiwana.athena.ui.login.LoginFragment;
import com.utachiwana.athena.ui.menu.home.HomeFragment;

import java.util.List;

public class MenuActivity extends FragmentActivity {

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
    }

}