package com.example.jyunmauchan.dontouchit.lotterys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.jyunmauchan.dontouchit.R;
import com.example.jyunmauchan.dontouchit.activitys.LotteryActivity;

public class Cancel extends LotteryActivity {
    public void simple(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this ).setTitle("提示")
                .setIcon(R.drawable.ic_launcher_background)
                .setMessage("抽奖成功");
        builder.setPositiveButton("确认",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }});
        builder .create();
        builder.show();
    }
    public Context getContext(){
        return this;
    }
}
