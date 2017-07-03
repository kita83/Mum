package com.app.strkita.mum;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    private static Constellation[] constellations;
    private EditText editBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        editBirthday = (EditText) findViewById(R.id.editBirthday);
        editBirthday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                TextView editConstellation = (TextView)findViewById(R.id.editConstellation);
                editConstellation.setText(getConstellation(editBirthday.getText().toString()));
            }
        });

        editBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialogFragment datePickerDialog = new DatePickerDialogFragment();
                datePickerDialog.show(getSupportFragmentManager(), "datePicker");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * 日付から該当する星座を判定する
     * @param birthday
     * @return constellation
     */
    private String getConstellation(String birthday) {

        // 星座リストの初期化
        initConstellations();

        try {
            Date dateBirthday = format.parse(birthday);

            for (Constellation c : constellations) {
                if (c.between(dateBirthday)) {
                    return c.getName();
                }
            }
        } catch (ParseException e) {

        }

        return null;
    }

    /**
     * 星座リストを初期化する
     */
    private static void initConstellations() {
        constellations = new Constellation[12];
        int i = 0;
        constellations[i++] = new Constellation("Aquarius", "1/20", "2/18");
        constellations[i++] = new Constellation("Pisces", "2/19", "3/20");
        constellations[i++] = new Constellation("Aries", "3/21", "4/19");
        constellations[i++] = new Constellation("Taurus", "4/20", "5/20");
        constellations[i++] = new Constellation("Gemini", "5/21", "6/21");
        constellations[i++] = new Constellation("Cancer", "6/22", "7/22");
        constellations[i++] = new Constellation("Leo", "7/23", "8/22");
        constellations[i++] = new Constellation("Virgo", "8/23", "9/22");
        constellations[i++] = new Constellation("Libra", "9/23", "10/23");
        constellations[i++] = new Constellation("Scorpio", "10/24", "11/22");
        constellations[i++] = new Constellation("Sagittarius", "11/23", "12/21");
        constellations[i++] = new Constellation("Capricorn", "12/22", "1/19");
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
        getMenuInflater().inflate(R.menu.main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
