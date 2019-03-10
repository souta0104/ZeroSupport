package com.risakokato.zerosupport.new_content;

import android.app.DatePickerDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.risakokato.zerosupport.dialog.DatePickDialog;
import com.risakokato.zerosupport.model.Belongings;
import com.risakokato.zerosupport.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

public class NewContentActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener {

    TextView textView;
    String dateText;
    String dateText2;

    public EditText belongingsEdit;

    public Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_content);


        realm = Realm.getDefaultInstance();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d", Locale.JAPANESE);
        dateText = sdf.format(date);

        Date date1 = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        dateText2 = sdf1.format(date1);

        textView = findViewById(R.id.date);
        textView.setText(dateText);

        belongingsEdit = findViewById(R.id.belongingsEdit);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        dateText = String.format(Locale.JAPANESE, "%d-%d-%d", year, monthOfYear + 1, dayOfMonth);
        textView.setText(dateText);

    }

    public void dateSet(View v) {
        DialogFragment newFragment = new DatePickDialog();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }
    public void addBelongings(View view) {
        String belongins = belongingsEdit.getText().toString();

        String date = dateText;

        boolean check = false;

        String updateDate = dateText2;

        save(belongins, date, check,updateDate);

        finish();
    }

    public void save(final String belongings, final String date, final boolean check, final String updateDate) {

        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {

                Belongings belo = realm.createObject(Belongings.class);
                belo.date = date;
                belo.belongings = belongings;
                belo.check = check;
                belo.updateDate = updateDate;

            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.close();
    }
}
