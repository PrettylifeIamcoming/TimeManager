package com.prettylifeiamcoming.timemanager.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ldf.calendar.Utils;
import com.ldf.calendar.component.CalendarAttr;
import com.ldf.calendar.component.CalendarViewAdapter;
import com.ldf.calendar.interf.IDayRenderer;
import com.ldf.calendar.interf.OnSelectDateListener;
import com.ldf.calendar.model.CalendarDate;
import com.ldf.calendar.view.Calendar;
import com.ldf.calendar.view.MonthPager;
import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.activity.ShowOneDayActivity;
import com.prettylifeiamcoming.timemanager.adapter.ExampleAdapter;
import com.prettylifeiamcoming.timemanager.view.CustomDayView;
import com.prettylifeiamcoming.timemanager.view.ThemeDayView;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class MonthFragment extends Fragment {
    private TextView tvYear;
    private TextView tvMonth;
    private TextView backToday;
    private CoordinatorLayout content;
    private MonthPager monthPager;
    private RecyclerView rvToDoList;
    private TextView scrollSwitch;
    private TextView themeSwitch;
    private TextView nextMonthBtn;
    private TextView lastMonthBtn;

    private ArrayList<Calendar> currentCalendars = new ArrayList<>();
    private CalendarViewAdapter calendarAdapter;
    private IDayRenderer iDayRenderer;
    private OnSelectDateListener onSelectDateListener;
    private static CalendarDate selectDate = new CalendarDate();
    private int mCurrentPage = MonthPager.CURRENT_DAY_INDEX;
    private Context context;
    private CalendarDate currentDate;
    private boolean initiated = false;

    private Handler handler = new Handler();

    private static int lock = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_month, container, false);
        context = getActivity();
        content = view.findViewById(R.id.content);
        monthPager = view.findViewById(R.id.calendar_view);
        //此处强行setViewHeight，毕竟你知道你的日历牌的高度
        monthPager.setViewHeight(Utils.dpi2px(context, 270));
        tvYear = view.findViewById(R.id.show_year_view);
        tvMonth = view.findViewById(R.id.show_month_view);
        backToday = view.findViewById(R.id.back_today_button);
        scrollSwitch = view.findViewById(R.id.scroll_switch);
        themeSwitch = view.findViewById(R.id.theme_switch);
        nextMonthBtn = view.findViewById(R.id.next_month);
        lastMonthBtn = view.findViewById(R.id.last_month);
        rvToDoList = view.findViewById(R.id.list);
        rvToDoList.setHasFixedSize(true);
        //这里用线性显示 类似于listview
        rvToDoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvToDoList.setAdapter(new ExampleAdapter(getActivity()));
        initCurrentDate();
        initCalendarView();
        initToolbarClickListener();

        return view;
    }

    /*
     * 如果你想以周模式启动你的日历，请在onResume是调用
     * Utils.scrollTo(content, rvToDoList, monthPager.getCellHeight(), 200);
     * calendarAdapter.switchToWeek(monthPager.getRowIndex());
     * */
    @Override
    public void onResume() {
        super.onResume();
//        Utils.scrollTo(content, rvToDoList, monthPager.getCellHeight(), 200);
//        calendarAdapter.switchToWeek(monthPager.getRowIndex());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private MyRunnable myRunnable = new MyRunnable();

    class MyRunnable implements Runnable{
        @Override
        public void run() {
            String a = currentDate.getYear() + getResources().getString(R.string.fragment_month_year);
            tvYear.setText(a);
            String b = currentDate.getMonth() + "";
            tvMonth.setText(b);
        }
    }

    private WeekRunnable weekRunnable = new WeekRunnable();     //确保刷新周的时候可以字可以同步

    class WeekRunnable implements Runnable{
        @Override
        public void run() {
            CalendarDate calendarDate  = new CalendarDate();
            String a = calendarDate.getYear() + getResources().getString(R.string.fragment_month_year);
            tvYear.setText(a);
            String b = calendarDate.getMonth() + "";
            tvMonth.setText(b);
        }
    }

    /**
     * 初始化对应功能的listener
     *
     * @return void
     */
    private void initToolbarClickListener() {
        backToday.setOnClickListener(new View.OnClickListener() {   //有效
            @Override
            public void onClick(View view) {
                onClickBackToDayBtn();
            }
        });
        scrollSwitch.setOnClickListener(new View.OnClickListener() {    //无效
            @Override
            public void onClick(View view) {
                calendarAdapter.notifyDataSetChanged();
                calendarAdapter.notifyDataChanged(selectDate);
                if (calendarAdapter.getCalendarType() == CalendarAttr.CalendarType.WEEK) {
                    Utils.scrollTo(content, rvToDoList, monthPager.getViewHeight(), 200);
                    calendarAdapter.switchToMonth();
                    handler.post(weekRunnable);
                } else {
                    Utils.scrollTo(content, rvToDoList, monthPager.getCellHeight(), 200);
                    calendarAdapter.switchToWeek(monthPager.getRowIndex());
                    handler.post(weekRunnable);
                }
            }
        });
        themeSwitch.setOnClickListener(new View.OnClickListener() {     //有效
            @Override
            public void onClick(View view) {
                refreshSelectBackground();
            }
        });
        nextMonthBtn.setOnClickListener(new View.OnClickListener() {    //无效
            @Override
            public void onClick(View view) {
                monthPager.setCurrentItem(monthPager.getCurrentPosition() + 1);
                handler.post(myRunnable);
            }
        });
        lastMonthBtn.setOnClickListener(new View.OnClickListener() {    //无效
            @Override
            public void onClick(View view) {
                monthPager.setCurrentItem(monthPager.getCurrentPosition() - 1);
                handler.post(myRunnable);
            }
        });
    }

    /**
     * 初始化currentDate
     *
     * @return void
     */
    private void initCurrentDate() {
        currentDate = new CalendarDate();
        handler.post(myRunnable);
    }

    /**
     * 初始化CustomDayView，并作为CalendarViewAdapter的参数传入
     */
    private void initCalendarView() {
        initListener();
        iDayRenderer = new CustomDayView(context, R.layout.custom_day);
        calendarAdapter = new CalendarViewAdapter(
                context,
                onSelectDateListener,
                CalendarAttr.WeekArrayType.Monday,
                iDayRenderer);
        calendarAdapter.setOnCalendarTypeChangedListener(new CalendarViewAdapter.OnCalendarTypeChanged() {
            @Override
            public void onCalendarTypeChanged(CalendarAttr.CalendarType type) {
                rvToDoList.scrollToPosition(0);
            }
        });
        initMarkData();
        initMonthPager();
    }

    /**
     * 初始化标记数据，HashMap的形式，可自定义
     * 如果存在异步的话，在使用setMarkData之后调用 calendarAdapter.notifyDataChanged();
     */
    private void initMarkData() {
        HashMap<String, String> markData = new HashMap<>();
        markData.put("2017-8-9", "1");
        markData.put("2017-7-9", "0");
        markData.put("2017-6-9", "1");
        markData.put("2017-6-10", "0");
        calendarAdapter.setMarkData(markData);
    }

    private void initListener() {
        onSelectDateListener = new OnSelectDateListener() {
            @Override
            public void onSelectDate(CalendarDate date) {
                selectDate = date;
                refreshClickDate(date);
                Intent intent = new Intent(getActivity(), ShowOneDayActivity.class);
                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.setTimeInMillis(System.currentTimeMillis());
                cal.set(java.util.Calendar.YEAR, date.year);
                cal.set(java.util.Calendar.MONTH, (date.month - 1));
                cal.set(java.util.Calendar.DAY_OF_MONTH, date.day);
                intent.putExtra("timestamp", cal.getTimeInMillis());
                startActivity(intent);
            }

            @Override
            public void onSelectOtherMonth(int offset) {
                //偏移量 -1表示刷新成上一个月数据 ， 1表示刷新成下一个月数据
                monthPager.selectOtherMonth(offset);
            }
        };
    }


    private void refreshClickDate(CalendarDate date) {
        currentDate = date;
        handler.post(myRunnable);
    }

    /**
     * 初始化monthPager，MonthPager继承自ViewPager
     *
     */
    private void initMonthPager() {
        monthPager.setAdapter(calendarAdapter);
        monthPager.setCurrentItem(MonthPager.CURRENT_DAY_INDEX);
        monthPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                position = (float) Math.sqrt(1 - Math.abs(position));
                page.setAlpha(position);
            }
        });
        monthPager.addOnPageChangeListener(new MonthPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("shibushi", "onPageScrolled: "+position);
                if (lock == 0){
                    calendarAdapter.notifyDataSetChanged();
                    calendarAdapter.notifyDataChanged(selectDate);
                    lock = 1;
                }
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPage = position;
                currentCalendars = calendarAdapter.getPagers();
                if (currentCalendars.get(position % currentCalendars.size()) != null) {
                    CalendarDate date = currentCalendars.get(position % currentCalendars.size()).getSeedDate();
                    currentDate = date;
                    handler.post(myRunnable);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void onClickBackToDayBtn() {
        refreshMonthPager();
    }

    private void refreshMonthPager() {
        CalendarDate today = new CalendarDate();
        calendarAdapter.notifyDataChanged(today);
        handler.post(myRunnable);
    }

    private void refreshSelectBackground() {
        if (iDayRenderer instanceof CustomDayView) {
            iDayRenderer = new ThemeDayView(context, R.layout.custom_day_focus);
            calendarAdapter.setCustomDayRenderer(iDayRenderer);
            calendarAdapter.notifyDataSetChanged();
            calendarAdapter.notifyDataChanged(new CalendarDate());
        } else {
            iDayRenderer = new CustomDayView(context, R.layout.custom_day);
            calendarAdapter.setCustomDayRenderer(iDayRenderer);
            calendarAdapter.notifyDataSetChanged();
            calendarAdapter.notifyDataChanged(new CalendarDate());
        }
    }
}
