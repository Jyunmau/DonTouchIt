package com.example.jyunmauchan.dontouchit;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout; // 包含滑动菜单的控件布局

    private List<Task> taskList = new ArrayList<>();    // 任务列表的list
    private TaskAdapter taskAdapter;    // 任务列表视图的适配器对象
    private RecyclerView recyclerView;

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

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);  // 显示导航按钮
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);   // 用图片替代导航按钮
        }

        navView.setCheckedItem(R.id.nav_Home);

        // 点击了滑动菜单的选项后收起滑动菜单
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        // 点击新建按钮来新增一个项目
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.add(new Task("我爱学习", R.mipmap.cdv_sc2));
                recyclerView.scrollToPosition(taskAdapter.getItemCount() - 1);
                taskAdapter.notifyDataSetChanged();
            }
        });

        // 任务列表显示逻辑
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
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
