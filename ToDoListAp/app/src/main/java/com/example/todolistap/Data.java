package com.example.todolistap;

public class Data {

    private int id;
    private int priority;
    private String text;

    public Data(int id, int priority, String text) {
        this.id = id;
        this.priority = priority;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public String getText() {
        return text;
    }
}
