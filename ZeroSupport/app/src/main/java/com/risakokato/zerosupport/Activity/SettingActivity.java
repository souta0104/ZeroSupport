package com.risakokato.zerosupport.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.risakokato.zerosupport.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Switch switch1= (Switch) findViewById(R.id.switch1);

        switch1.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener(){
                    public void onCheckedChanged(CompoundButton comButton, boolean isChecked){

                        String displayChar;
                        if(isChecked){
                            displayChar = "通知はオンです";
                        }
                        // オフなら
                        else{
                            displayChar = "通知がオフになりました";
                        }
                        Toast toast = Toast.makeText(SettingActivity.this, displayChar, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
        );
    }

    public void timeSetNotification(View view){

    }
}
