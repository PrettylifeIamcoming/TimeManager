package com.prettylifeiamcoming.timemanager.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldf.calendar.component.State;
import com.ldf.calendar.interf.IDayRenderer;
import com.ldf.calendar.model.CalendarDate;
import com.ldf.calendar.view.DayView;
import com.prettylifeiamcoming.timemanager.R;

/**
 * Created by ldf on 17/6/27.
 */

public class ThemeDayView extends DayView {
    private TextView dateTv;
    private ImageView marker;
    private View selectedBackground;
    private View todayBackground;
    private final CalendarDate today = new CalendarDate();

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public ThemeDayView(Context context, int layoutResource) {
        super(context, layoutResource);
        dateTv = findViewById(R.id.date);
        marker = findViewById(R.id.maker);
        selectedBackground = findViewById(R.id.selected_background);
        selectedBackground = findViewById(R.id.selected_background);
        todayBackground = findViewById(R.id.today_background);
    }

    @Override
    public void refreshContent() {
        CalendarDate date = day.getDate();
        State state = day.getState();
        if (date != null) {
            if (date.equals(today)) {
                dateTv.setText("今");
                todayBackground.setVisibility(VISIBLE);
            } else {
                dateTv.setText(date.day + "");
                todayBackground.setVisibility(GONE);
            }
        }
        if (state == State.SELECT) {
            selectedBackground.setVisibility(VISIBLE);
        } else {
            selectedBackground.setVisibility(GONE);
        }
        renderSelect(day.getState());
        super.refreshContent();
    }

    private void renderSelect(State state) {
        if (state == State.SELECT) {
            selectedBackground.setVisibility(VISIBLE);
            dateTv.setTextColor(Color.BLACK);
        } else if (state == State.NEXT_MONTH || state == State.PAST_MONTH) {
            selectedBackground.setVisibility(GONE);
            dateTv.setTextColor(Color.parseColor("#d5d5d5"));
        } else {
            selectedBackground.setVisibility(GONE);
            dateTv.setTextColor(Color.parseColor("#111111"));
        }
    }

    @Override
    public IDayRenderer copy() {
        return new ThemeDayView(context, layoutResource);
    }
}
