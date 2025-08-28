package com.example.messengerbyfirebase;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private FirebaseAuth mAuth;

    private MutableLiveData<FirebaseUser> user = new MutableLiveData<>();

    private MutableLiveData<List<User>> users = new MutableLiveData<>();
    private FirebaseDatabase database;
    private DatabaseReference myRef;



    public MainViewModel() {

        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    user.setValue(firebaseAuth.getCurrentUser());
            }
        });

        database = FirebaseDatabase.getInstance();
        myRef= database.getReference("Users");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser == null){
                    return;
                }
                List<User> usersFromDb = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren() ) {
                    User user = dataSnapshot.getValue(User.class);
                    if (!user.getId().equals(currentUser.getUid())){
                        usersFromDb.add(user);
                    }
                }
                users.setValue(usersFromDb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void setUserOnline(boolean isOnline){
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser == null){
            return;
        }
        myRef.child(firebaseUser.getUid()).child("online")
                .setValue(isOnline);
    }


    public LiveData<FirebaseUser> getUser() {
        return user;
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public void signOut(){
        setUserOnline(false);
        mAuth.signOut();
    }
}
