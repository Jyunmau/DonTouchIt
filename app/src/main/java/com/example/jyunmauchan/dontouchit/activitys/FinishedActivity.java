package com.example.jyunmauchan.dontouchit.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.jyunmauchan.dontouchit.R;
import com.example.jyunmauchan.dontouchit.Task;
import com.example.jyunmauchan.dontouchit.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

public class FinishedActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout; // 包含滑动菜单的控件布局

    private List<Task> taskList = new ArrayList<>();    // 任务列表的list
    private TaskAdapter taskAdapter;    // 任务列表视图的适配器对象
    private RecyclerView recyclerView;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;    // 用于储存数据的变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar); // toolbar显示条
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout1);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);  // 滑动菜单中的布局
        ActionBar actionBar = getSupportActionBar();
        pref = PreferenceManager.getDefaultSharedPreferences(this); // 定义存储变量

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);  // 显示导航按钮
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);   // 用图片替代导航按钮
        }

        navView.setCheckedItem(R.id.nav_Finished);

        // 点击了滑动菜单的选项后收起滑动菜单
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_About:
                        intent = new Intent(FinishedActivity.this, AboutActivity.class);
                        startActivities(new Intent[]{intent});
                        finish();
                        break;
                    case R.id.nav_Finished:
                        intent = new Intent(FinishedActivity.this, FinishedActivity.class);
                        startActivities(new Intent[]{intent});
                        finish();
                        break;
                    case R.id.nav_Home:
                        intent = new Intent(FinishedActivity.this, MainActivity.class);
                        startActivities(new Intent[]{intent});
                        finish();
                        break;
                    case R.id.nav_Setting:
                        intent = new Intent(FinishedActivity.this, LotteryActivity.class);
                        startActivities(new Intent[]{intent});
                        finish();
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        // 任务列表显示逻辑
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        // 每次重新打开活动时要读取数据重新建任务列表
        int num = pref.getInt("task_num", 0);
        for (int i = 0; i < num; i++) {
            // 判断任务是否完成，若未完成加入显示链表
            if (pref.getInt("counts_" + i, 0) == pref.getInt("nums_" + i, 0))
                taskList.add(new Task(pref.getString("names_" + i, ""), pref.getInt("nums_" + i, 0), R.mipmap.task_finished));
        }
        taskAdapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(taskAdapter);

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
