package com.risakokato.zerosupport.start;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.risakokato.zerosupport.R;
import com.risakokato.zerosupport.main.MainActivity;

public class StartActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO: ここで処理を実行する
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);

                finish();

            }
        }, 1500);
    }
}
