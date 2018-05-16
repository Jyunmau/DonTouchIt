package com.example.jyunmauchan.dontouchit;

/*
 * 任务卡片的类
 */
public class Task {

    private String name;

    private int imageId;

    private int num;

    public Task(String name, int num, int imageId) {
        this.name = name;
        this.imageId = imageId;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public int getImageId() {
        return imageId;
    }

}
