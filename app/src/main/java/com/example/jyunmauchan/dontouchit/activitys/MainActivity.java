package com.example.jyunmauchan.dontouchit.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;


import com.example.jyunmauchan.dontouchit.R;
import com.example.jyunmauchan.dontouchit.Task;
import com.example.jyunmauchan.dontouchit.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout; // 包含滑动菜单的控件布局

    private List<Task> taskList = new ArrayList<>();    // 任务列表的list
    private TaskAdapter taskAdapter;    // 任务列表视图的适配器对象
    private RecyclerView recyclerView;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;    // 用于储存数据的变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar); // toolbar显示条
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);  // 滑动菜单中的布局
        ActionBar actionBar = getSupportActionBar();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);   // “新建”浮动按钮
        pref = PreferenceManager.getDefaultSharedPreferences(this); // 定义存储变量

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);  // 显示导航按钮
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);   // 用图片替代导航按钮
        }

        navView.setCheckedItem(R.id.nav_Home);

        // 点击了滑动菜单的选项后收起滑动菜单
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_About:
                        intent = new Intent(MainActivity.this, AboutActivity.class);
                        startActivities(new Intent[]{intent});
                        break;
                    case R.id.nav_Home:
                        intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivities(new Intent[]{intent});
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        // 点击新建按钮来新增一个项目
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                startActivityForResult(intent, 1);  // 请求返回数据，跳转到TaskActivity
            }
        });

        // 任务列表显示逻辑
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        // 每次重新打开活动时要读取数据重新建任务列表
        int num = pref.getInt("task_num", 0);
        for (int i = 0; i < num; i++) {
            taskList.add(new Task(pref.getString("names_" + i, ""), pref.getInt("nums_" + i, 0), R.mipmap.cdv_sc2));
        }
        taskAdapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(taskAdapter);

    }

    // 重写该方法来接收数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnData = data.getStringExtra("data_return");
                    int returnInt = data.getIntExtra("int_return", 0);
                    taskList.add(new Task(returnData, returnInt, R.mipmap.cdv_sc2));
                    editor = pref.edit();
                    // 存储列表， 取出编号后加一
                    int num = pref.getInt("task_num", 0);
                    editor.remove("names_" + num);
                    editor.putString("names_" + num, returnData);
                    editor.remove("nums_" + num);
                    editor.putInt("nums_" + num, returnInt);
                    num++;
                    editor.remove("task_num");
                    editor.putInt("task_num", num);
                    editor.apply();
                    // 刷新布局
                    recyclerView.scrollToPosition(taskAdapter.getItemCount() - 1);
                    taskAdapter.notifyDataSetChanged();
                }
        }
    }

    /*
     * 响应toolbar最左侧的图片点击，弹出滑动菜单
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
                default:
        }
        return true;
    }
}
