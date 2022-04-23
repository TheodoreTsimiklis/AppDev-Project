package com.example.fitnessbud.helpers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.fitnessbud.R;
import com.example.fitnessbud.diary.DiaryFragment;
import com.example.fitnessbud.exercises.ExercisesFragment;
import com.example.fitnessbud.home.ProfileFragment;
import com.example.fitnessbud.loginAndRegister.MainActivity;
import com.example.fitnessbud.progress.ProgressFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class Container extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        replaceFragment(new ProfileFragment());

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                        switch (item.getItemId()) {
                    case R.id.nav_logOut:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(Container.this, MainActivity.class));
                    case R.id.nav_home:
                        replaceFragment(new ProfileFragment());
                        return true;
                    case R.id.nav_diary:
                        replaceFragment(new DiaryFragment());
                        return true;
                    case R.id.nav_exercises:
                        replaceFragment(new ExercisesFragment());
                        return true;
                    case R.id.nav_progress:
                        replaceFragment(new ProgressFragment());
                        return true;
                }
                return false;
            }
        });

    }

    private void replaceFragment(Fragment fragment) {
        String tag = fragment.getClass().getSimpleName();
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();

        Fragment curFrag = getSupportFragmentManager().getPrimaryNavigationFragment();
        Fragment cacheFrag = getSupportFragmentManager().findFragmentByTag(tag);

        if (curFrag != null)
            tr.hide(curFrag);

        if (cacheFrag == null) {
            tr.add(R.id.fragment_container, fragment, tag);
        } else {
            tr.show(cacheFrag);
            fragment = cacheFrag;
        }

        tr.setPrimaryNavigationFragment(fragment);
        tr.commit();
    }
}