package com.risakokato.zerosupport.Dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.risakokato.zerosupport.Activity.NewContentActivity;

import java.util.Calendar;

/**
 * Created by develop on 2018/08/14.
 */

public class DatePickDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), (NewContentActivity)getActivity(), year, month, day);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

}