package com.example.jyunmauchan.dontouchit.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.jyunmauchan.dontouchit.R;
import com.example.jyunmauchan.dontouchit.RingProgressBar;


public class TimerActivity extends AppCompatActivity {
    private RingProgressBar progressBar1;
    //  private RingProgressBar progressBar2;
    private int progress = 1;
    private int CountTime=1;//设置倒数几分钟
    private Handler handler = new Handler();
    private String ProjectName="123";
    private int handleNumber=1;
    private int finishedNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_timer);
        String textName="项目名："+ProjectName;
        String textNumber="剩余项目："+handleNumber+"/总共项目："+finishedNumber;
        TextView textViewName=findViewById(R.id.project);
        TextView textViewNumber=findViewById(R.id.remain);
        textViewName.setText(textName);
        textViewNumber.setText(textNumber);

        progressBar1 = (RingProgressBar) findViewById(R.id.progress_bar_1);
        // progressBar2 = (RingProgressBar) findViewById(R.id.progress_bar_2);
        // progressBar2.setProgress(1);
        handler.post(runnable);
        //  progressBar2 = (RingProgressBar) findViewById(R.id.progress_bar_2);
        /*
        progressBar2.setOnProgressListener(new RingProgressBar.OnProgressListener() {

            @Override
            public void progressToComplete() {
                Toast.makeText(MainActivity.this, "complete", Toast.LENGTH_SHORT).show();
            }
        });
        */
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (progress > 100) {
                handler.removeCallbacks(this);
            } else {
                progress++;
                //progressBar2.setProgress(progress);
                progressBar1.setProgress(progress);
                handler.postDelayed(this, CountTime*6*100);
            }
        }
    };
}