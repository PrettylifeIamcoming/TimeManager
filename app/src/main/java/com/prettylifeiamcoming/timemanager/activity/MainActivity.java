package com.prettylifeiamcoming.timemanager.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.prettylifeiamcoming.timemanager.R;

import java.text.SimpleDateFormat;
import java.util.Date;



public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private TextView mTextView;

    private Date date;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_main_day:
                    test(0);
                    return true;
                case R.id.navigation_main_month:
                    test(1);
                    return true;
                case R.id.navigation_main_year:
                    test(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //底部导航栏
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //设置toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mTextView = findViewById(R.id.toolbar_main_time);
        date = new Date();
        String a = sdf.format(date);
        mTextView.setText(a);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        //设置菜单栏
        NavigationView navView = findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_menu_task_table);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_menu_task_table:
                        test(0);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_menu_schedule_table:
                        test(1);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_menu_daily_introspection_table:
                        test(2);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_menu_task_completed_table:
                        test(3);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_menu_schedule_completed_table:
                        test(4);
                        mDrawerLayout.closeDrawers();
                        break;
                    default:
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

    }

    //toolbar菜单填充
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    //测试用
    public void test(int i){
        switch (i) {
            case 0:
                Toast.makeText(this, "You clicked Add Task", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "You clicked Add Schedule", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "You clicked Daily Introspection Table", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "You clicked Task Completed Table", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "You clicked Schedule Competed Table", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    //toolbar菜单栏按钮监控
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.toolbar_main_task:
                Toast.makeText(this, "You clicked Add Task", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_main_schedule:
                Toast.makeText(this, "You clicked Add Schedule", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_main_add:
                Toast.makeText(this, "You clicked Add Today Task", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
