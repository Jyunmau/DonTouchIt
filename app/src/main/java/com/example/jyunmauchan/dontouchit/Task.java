package com.example.jyunmauchan.dontouchit;

/*
 * 任务卡片的类
 */
public class Task {

    private String name;

    private int imageId;

    public Task(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

}
