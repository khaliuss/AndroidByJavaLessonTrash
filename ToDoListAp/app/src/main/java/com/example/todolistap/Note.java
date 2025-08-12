package com.example.todolistap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey
    private int id;
    private int priority;
    private String text;

    public Note(int id, int priority, String text) {
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
