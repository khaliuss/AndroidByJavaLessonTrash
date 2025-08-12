package com.example.todolistap;

import java.util.ArrayList;
import java.util.Random;

public class DataBase {

    private ArrayList<Note> notes = new ArrayList<>();

    private static DataBase instance= null;
    public static DataBase getInstance(){
        if (instance == null){
            instance = new DataBase();
        }
        return instance;
    }


    public void add(Note note){
        notes.add(note);
    }

    public void remove(int id){
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            if (note.getId() == id){
                notes.remove(note);
            }
        }
    }

    public ArrayList<Note> getNotes() {

        return new ArrayList<>(notes);
    }
}
