package com.app.strkita.mum;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText editBirthday;
    final String[] fragments = {
            "com.app.strkita.mum.ProfileFragment",
            "com.app.strkita.mum.CalendarFragment",
            "com.app.strkita.mum.DetailFragment",
            "com.app.strkita.mum.SettingFragment"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_profile:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, Fragment.instantiate(MainActivity.this, fragments[0]))
                        .commit();
                break;
            case R.id.nav_calendar:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, Fragment.instantiate(MainActivity.this, fragments[1]))
                        .commit();
                break;
            case R.id.nav_detail:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, Fragment.instantiate(MainActivity.this, fragments[2]))
                        .commit();
                break;
            case R.id.nav_settings:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, Fragment.instantiate(MainActivity.this, fragments[3]))
                        .commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickSetBirthday(View view) {
        DatePickerDialogFragment datePickerDialog = new DatePickerDialogFragment();
        datePickerDialog.show(getSupportFragmentManager(), "datePicker");
    }
}
