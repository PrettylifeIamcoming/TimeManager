package com.prettylifeiamcoming.timemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.fragment.DayFragment;
import com.prettylifeiamcoming.timemanager.fragment.MonthFragment;
import com.prettylifeiamcoming.timemanager.fragment.YearFragment;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTextView;

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private BottomNavigationView mBottomNavigationView;

    private DayFragment mDayFragment = new DayFragment();
    private MonthFragment mMonthFragment = new MonthFragment();
    private YearFragment mYearFragment = new YearFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            SimpleDateFormat sdf;
            Date date = new Date();
            String a;
            switch (item.getItemId()) {
                case R.id.bottom_main_day:
                    mTextView = findViewById(R.id.toolbar_time);
                    sdf = new SimpleDateFormat("yyyy.MM.dd");
                    a = sdf.format(date);
                    mTextView.setText(a);
                    invalidateOptionsMenu();
                    selectedFragment = mDayFragment;
                    break;
                case R.id.bottom_main_month:
                    sdf = new SimpleDateFormat("yyyy.MM");
                    mTextView = findViewById(R.id.toolbar_time);
                    a = sdf.format(date);
                    mTextView.setText(a);
                    invalidateOptionsMenu();
                    selectedFragment = mMonthFragment;
                    break;
                case R.id.bottom_main_year:
                    sdf = new SimpleDateFormat("yyyy");
                    mTextView = findViewById(R.id.toolbar_time);
                    a = sdf.format(date);
                    mTextView.setText(a);
                    invalidateOptionsMenu();
                    selectedFragment = mYearFragment;
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 设置toolbar_day,toolbar_month,toolbar_year
         */
        mToolbar =findViewById(R.id.toolbar_main);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mTextView = findViewById(R.id.toolbar_time);
        SimpleDateFormat sdf;
        Date date = new Date();
        String a;
        sdf = new SimpleDateFormat("yyyy.MM.dd");
        a = sdf.format(date);
        mTextView.setText(a);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        /**
         * DrawerLayout和NavigationView
         */
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setCheckedItem(R.id.nav_menu_task_table);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_menu_task_table:
                        intent = new Intent(MainActivity.this, TaskTableActivity.class);
                        startActivity(intent);
                        test(0);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_menu_schedule_table:
                        intent = new Intent(MainActivity.this, ScheduleTableActivity.class);
                        startActivity(intent);
                        test(1);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_menu_daily_introspection_table:
                        intent = new Intent(MainActivity.this, DailyIntrospectionActivity.class);
                        startActivity(intent);
                        test(2);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_menu_task_completed_table:
                        intent = new Intent(MainActivity.this, CompletedTaskTableActivity.class);
                        startActivity(intent);
                        test(3);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_menu_schedule_completed_table:
                        intent = new Intent(MainActivity.this, CompletedScheduleTableActivity.class);
                        startActivity(intent);
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

        /**
         * 底部导航栏
         */
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mDayFragment).commit();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // 动态设置ToolBar状态
        switch (mBottomNavigationView.getSelectedItemId()) {
            case R.id.bottom_main_day:
                getMenuInflater().inflate(R.menu.toolbar_day, menu);
                Toast.makeText(this,"day",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bottom_main_month:
                getMenuInflater().inflate(R.menu.toolbar_month, menu);
                Toast.makeText(this,"month",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bottom_main_year:
                getMenuInflater().inflate(R.menu.toolbar_year, menu);
                Toast.makeText(this,"year",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    //toolbar菜单栏按钮监控
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.toolbar_day_task:
                Toast.makeText(this, "You clicked Add Task", Toast.LENGTH_SHORT).show();
                intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
                break;
            case R.id.toolbar_day_schedule:
                Toast.makeText(this, "You clicked Add Schedule", Toast.LENGTH_SHORT).show();
                intent = new Intent(MainActivity.this, AddScheduleActivity.class);
                startActivity(intent);
                break;
            case R.id.toolbar_day_add:
                Toast.makeText(this, "You clicked Add Today Task", Toast.LENGTH_SHORT).show();
                intent = new Intent(MainActivity.this, TaskTableActivity.class);
                startActivity(intent);
                break;
            case R.id.toolbar_month_add:
                Toast.makeText(this,"You clicked Add Month Plan",Toast.LENGTH_LONG).show();
                break;
            case R.id.toolbar_month_delete:
                Toast.makeText(this,"You clicked Delete Month Plan",Toast.LENGTH_LONG).show();
                break;
            case R.id.toolbar_year_add:
                Toast.makeText(this,"You clicked Add Year Plan",Toast.LENGTH_LONG).show();
                break;
            case R.id.toolbar_year_delete:
                Toast.makeText(this,"You clicked Delete Year Plan",Toast.LENGTH_LONG).show();
                break;
        }
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
}
