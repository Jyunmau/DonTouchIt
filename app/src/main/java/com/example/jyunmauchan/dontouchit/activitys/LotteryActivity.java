package com.example.jyunmauchan.dontouchit.activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jyunmauchan.dontouchit.R;
import com.example.jyunmauchan.dontouchit.lotterys.Cancel;
import com.example.jyunmauchan.dontouchit.lotterys.gou;
import com.example.jyunmauchan.dontouchit.lotterys.high;
import com.example.jyunmauchan.dontouchit.lotterys.low;
import com.example.jyunmauchan.dontouchit.lotterys.pie;

import java.util.Random;


public class LotteryActivity extends AppCompatActivity {

    Button cancel;
    Button together;
    int min=1;
    int max=100;
    final gou g=new gou();
    final pie p=new pie();
    final high h=new high();
    final low l=new low();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery);
        cancel=findViewById(R.id.cancel);
        final android.widget.ImageView gou=findViewById(R.id.gou);
        final android.widget.ImageView pie=findViewById(R.id.pie);
        final android.widget.ImageView high=findViewById(R.id.high);
        final android.widget.ImageView low=findViewById(R.id.low);
        final TextView tvg=findViewById(R.id.goucount);
        final TextView tvh=findViewById(R.id.highcount);
        final TextView tvl=findViewById(R.id.lowcount);
        final TextView tvp=findViewById(R.id.piecount);
        if(g.getTotal()==0){
            gou.setImageAlpha(50);
        }
        if(p.getTotal()==0){
            pie.setImageAlpha(50);
        }
        if(h.getTotal()==0){
            high.setImageAlpha(50);
        }
        if(l.getTotal()==0){
            low.setImageAlpha(50);
        }
        tvg.setText("x"+g.getTotal());
        tvl.setText("x"+l.getTotal());
        tvh.setText("x"+h.getTotal());
        tvp.setText("x"+p.getTotal());
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int num=random.nextInt(max) % (max - min + 1) + min;
                if(num>=1&&num<=10)clickg(g,tvg);
                else if(num>=11&&num<=45)clickh(h,tvh);
                else if(num>=46&&num<=80)clickl(l,tvl);
                else if(num>=81&&num<=100)clickp(p,tvp);
                else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(LotteryActivity.this)
                            .setTitle("提示")
                            .setIcon(R.drawable.ic_launcher_background)
                            .setMessage("错误");
                    builder.setPositiveButton("确认",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            refresh();
                        }})
                            .create()
                            .show();
                }
            }
        });
        together=findViewById(R.id.tog);
        together.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Tog()){
                    AlertDialog.Builder builder=new AlertDialog.Builder(LotteryActivity.this)
                            .setTitle("提示")
                            .setIcon(R.drawable.ic_launcher_background)
                            .setMessage("合并成功");
                    builder.setPositiveButton("确认",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            refresh();
                        }})
                            .create()
                            .show();
                }
                else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(LotteryActivity.this)
                            .setTitle("提示")
                            .setIcon(R.drawable.ic_launcher_background)
                            .setMessage("卡片不足");
                    builder.setPositiveButton("确认",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }})
                            .create()
                            .show();
                }
            }

        });

    }
    public boolean Tog(){
        if(g.getTotal()>0&&p.getTotal()>0&&h.getTotal()>0&&l.getTotal()>0) {
            g.deleteTotal();
            h.deleteTotal();
            l.deleteTotal();
            p.deleteTotal();
            return true;
        }
        else
            return false;
    }
    public void clickg(gou g,TextView tv){
        g.addTotal();
        Cancel c=new Cancel();
        AlertDialog.Builder builder=new AlertDialog.Builder(LotteryActivity.this)
                .setTitle("提示")
                .setIcon(R.drawable.ic_launcher_background)
                .setMessage("抽奖成功");
        builder.setPositiveButton("确认",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                refresh();
            }})
                .create()
                .show();
        tv.setText("x"+g.getTotal());
    }
    public void clickp(pie p,TextView tv){
        p.addTotal();
        Cancel c=new Cancel();
        AlertDialog.Builder builder=new AlertDialog.Builder(LotteryActivity.this)
                .setTitle("提示")
                .setIcon(R.drawable.ic_launcher_background)
                .setMessage("抽奖成功");
        builder.setPositiveButton("确认",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                refresh();
            }})
                .create()
                .show();
        tv.setText("x"+p.getTotal());
    }
    public void clickh(high h,TextView tv){
        h.addTotal();
        Cancel c=new Cancel();
        AlertDialog.Builder builder=new AlertDialog.Builder(LotteryActivity.this)
                .setTitle("提示")
                .setIcon(R.drawable.ic_launcher_background)
                .setMessage("抽奖成功");
        builder.setPositiveButton("确认",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                refresh();
            }})
                .create()
                .show();
        tv.setText("x"+h.getTotal());
    }
    public void clickl(low l,TextView tv){
        l.addTotal();
        Cancel c=new Cancel();
        AlertDialog.Builder builder=new AlertDialog.Builder(LotteryActivity.this)
                .setTitle("提示")
                .setIcon(R.drawable.ic_launcher_background)
                .setMessage("抽奖成功");
        builder.setPositiveButton("确认",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                refresh();
            }})
                .create()
                .show();
        tv.setText("x"+l.getTotal());
    }
    public void refresh() {
        finish();
        Intent intent = new Intent(LotteryActivity.this, LotteryActivity.class);
        startActivity(intent);
    }


}
