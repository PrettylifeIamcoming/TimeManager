package com.prettylifeiamcoming.timemanager.dialog;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.EditText;

import java.util.Calendar;

public class SetDateDialog {
    /*
    显示日期对话框
     */
    public static void showDatePickerDialog(Context context, EditText editText) {
        Calendar calendar = Calendar.getInstance();
        new android.app.DatePickerDialog(context, (view, year, monthOfYear, dayOfMonth) -> {
            final String a = year + "." + (monthOfYear + 1) + "." + dayOfMonth;
            // 时间对话框
            new TimePickerDialog(context, (view1, hourOfDay, minute) -> {
                String b;
                if (minute < 10) {
                    b = hourOfDay + ":0" + minute;
                } else {
                    b = hourOfDay + ":" + minute;
                }
                String c = a + "/" + b;
                editText.setText(c);
            }, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), true).show();
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
