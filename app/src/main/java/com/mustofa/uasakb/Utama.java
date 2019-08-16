/** 15-08-2019 10116593-Mustofa Kamil-IF13  */
package com.mustofa.uasakb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;


public class Utama extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.logout);
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        NavigationView navigationView = findViewById(R.id.nav_view);
        mDrawerLayout.addDrawerListener(drawerToggle);
        setupNavDrawer(navigationView);

    }

    private ActionBarDrawerToggle setupDrawerToggle(){
        return new ActionBarDrawerToggle(
                this,
                mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupNavDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new Home();
                        break;
                    case R.id.nav_profile:
                        fragment = new Profile();
                        break;
                    case R.id.kontak:
                        fragment = new Kontak();
                        break;
                    case R.id.daftar:
                        fragment = new Daftar();
                        break;
                    case R.id.logout:
                        Preferences.clearLoggedInUser(getBaseContext());
                        startActivity(new Intent(getBaseContext(), LoginActivity.class));
                        finish();
                    default:
                        fragment = new Home();
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                item.setChecked(true);
                setTitle(item.getTitle());
                mDrawerLayout.closeDrawers();

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
}