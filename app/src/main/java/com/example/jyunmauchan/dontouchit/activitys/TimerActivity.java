package com.example.jyunmauchan.dontouchit.activitys;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jyunmauchan.dontouchit.R;

public class TimerActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        pref = PreferenceManager.getDefaultSharedPreferences(this); // 定义存储变量
    }
}
