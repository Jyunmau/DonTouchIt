package com.example.jyunmauchan.dontouchit.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.jyunmauchan.dontouchit.R;

public class TaskActivity extends AppCompatActivity {

    private EditText taskname;  // 输入框对象：任务名字
    private NumberPicker mNumberPicker = null; // 数值选择器
    private int t_num = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar); // toolbar显示条
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.task_fab);   // 确认按钮
        taskname = (EditText) findViewById(R.id.task_in);
        // 获取NumberPicker组件
        mNumberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        // 设置NumberPicker属性
        mNumberPicker.setMinValue(1);
        mNumberPicker.setMaxValue(20);
        mNumberPicker.setValue(5);

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);  // 显示导航按钮
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        // 监听数值选择器改变事件
        mNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Toast.makeText(TaskActivity.this, "选择的是：" + newVal,
                        Toast.LENGTH_SHORT).show();
                t_num = newVal;
            }
        });

        // 按钮点击事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t_name = taskname.getText().toString();
                int t_number = t_num;
                String s = taskname.getText().toString().trim();
                if (!TextUtils.isEmpty(s)){
                    Intent intent = new Intent();
                    intent.putExtra("data_return", t_name);
                    intent.putExtra("num_return", t_number);
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });

    }

}
