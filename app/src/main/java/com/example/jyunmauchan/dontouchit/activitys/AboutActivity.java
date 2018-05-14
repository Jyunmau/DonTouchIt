package com.example.jyunmauchan.dontouchit.activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.jyunmauchan.dontouchit.R;

public class AboutActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout; // 包含滑动菜单的控件布局

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar); // toolbar显示条
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);  // 滑动菜单中的布局
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);  // 显示导航按钮
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);   // 用图片替代导航按钮
        }

        navView.setCheckedItem(R.id.nav_About);

        // 点击了滑动菜单的选项后收起滑动菜单
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_About:
                        intent = new Intent(AboutActivity.this, AboutActivity.class);
                        startActivities(new Intent[]{intent});
                        break;
                    case R.id.nav_Home:
                        intent = new Intent(AboutActivity.this, MainActivity.class);
                        startActivities(new Intent[]{intent});
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

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
