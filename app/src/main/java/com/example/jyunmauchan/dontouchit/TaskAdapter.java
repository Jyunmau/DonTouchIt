package com.example.jyunmauchan.dontouchit;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/*
 * 多张任务卡片列表视图适配器类，定义列表及卡片控件相关属性
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private Context mContext;
    private List<Task> mTaskList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        ImageView taskImage;
        TextView taskName;
        TextView taskNum;
        TextView taskCount;

        public ViewHolder(View view) {
            super(view);
            cardview = (CardView) view;
            taskImage = (ImageView) view.findViewById(R.id.task_image);
            taskName = (TextView) view.findViewById(R.id.task_name);
            taskNum = (TextView) view.findViewById(R.id.task_num);
            taskCount = (TextView) view.findViewById(R.id.task_count);
        }
    }

    public TaskAdapter(List<Task> taskList) {
        mTaskList = taskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = mTaskList.get(position);
        holder.taskName.setText(task.getName());
        holder.taskNum.setText(Integer.toString(task.getNum()));
        holder.taskCount.setText(Integer.toString(task.getCount()));
        Glide.with(mContext).load(task.getImageId()).into(holder.taskImage);
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }
}
