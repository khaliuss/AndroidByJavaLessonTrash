package com.example.toolsshop;

import androidx.annotation.NonNull;

public class Drill {

    private String title;
    private String description;
    private int icon;

    public Drill(String title, String description, int icon) {
        this.title = title;
        this.description = description;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getIcon() {
        return icon;
    }

    @NonNull
    @Override
    public String toString() {
        return title;

    }
}
