//package com.risakokato.zerosupport.main;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.widget.ListView;
//
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import com.google.android.material.navigation.NavigationView;
//import com.risakokato.zerosupport.R;
//import com.risakokato.zerosupport.activity.HelpActivity;
//import com.risakokato.zerosupport.activity.SettingActivity;
//import com.risakokato.zerosupport.activity.ShareActivity;
//import com.risakokato.zerosupport.list.ListActivity;
//import com.risakokato.zerosupport.model.Belongings;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//import io.realm.Realm;
//import io.realm.RealmResults;
//
//public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//
//    public Realm realm;
//
//    public ListView listView;
//
//    public Date date;
//
//    public String today;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        realm = Realm.getDefaultInstance();
//
//        listView = (ListView) findViewById(R.id.mainList);
//
//        date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d", Locale.JAPANESE);
//        today = sdf.format(date);
//    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_list) {
//            Intent intent = new Intent(MainActivity.this, ListActivity.class);
//            startActivity(intent);
//
//        } else if (id == R.id.nav_manage) {
//            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
//            startActivity(intent);
//
//        } else if (id == R.id.nav_help) {
//            Intent intent = new Intent(MainActivity.this, HelpActivity.class);
//            startActivity(intent);
//
//        } else if (id == R.id.nav_share) {
//            Intent intent = new Intent(MainActivity.this, ShareActivity.class);
//            startActivity(intent);
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    public void setTodayList() {
//        RealmResults<Belongings> results = realm.where(Belongings.class).equalTo("date", today).findAll();
//        List<Belongings> items = realm.copyFromRealm(results);
//        MainAdapter adapter = new MainAdapter(this, R.layout.layout_item_today, items);
//
//        listView.setAdapter(adapter);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        setTodayList();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        realm.close();
//    }
//}
