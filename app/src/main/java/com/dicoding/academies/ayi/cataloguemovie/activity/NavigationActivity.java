package com.dicoding.academies.ayi.cataloguemovie.activity;




import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dicoding.academies.ayi.cataloguemovie.R;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null ){
            NowPlayingFragment currentFragment = new NowPlayingFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.content_navigation, currentFragment, NowPlayingFragment.class.getSimpleName())
                    .commit();
            getSupportActionBar().setTitle(getResources().getString(R.string.now_playing));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Bundle bundle = new Bundle();

        if (id == R.id.nav_camera) {
            NowPlayingFragment currentFragment = new NowPlayingFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.content_navigation, currentFragment, NowPlayingFragment.class.getSimpleName())
                    .commit();
            getSupportActionBar().setTitle(getResources().getString(R.string.now_playing));
        } else if (id == R.id.nav_gallery) {
            UpComingFragment upComingFragment = new UpComingFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.content_navigation, upComingFragment, UpComingFragment.class.getSimpleName())
                    .commit();
            getSupportActionBar().setTitle(getResources().getString(R.string.up_coming));
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_favorite){
            FavoriteFragment favoriteFragment = new FavoriteFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_navigation, favoriteFragment, NowPlayingFragment.class.getSimpleName())
                    .commit();
            getSupportActionBar().setTitle(getResources().getString(R.string.favorite));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
