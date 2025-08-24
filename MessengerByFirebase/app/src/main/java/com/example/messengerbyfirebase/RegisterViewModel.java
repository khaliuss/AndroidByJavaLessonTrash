package com.example.messengerbyfirebase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterViewModel extends AndroidViewModel {
    private FirebaseAuth mAuth;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private MutableLiveData<String> error = new MutableLiveData<>();
    private MutableLiveData<FirebaseUser> fbUser = new MutableLiveData<>();

    public LiveData<String> getError() {
        return error;
    }

    public LiveData<FirebaseUser> getFbUser() {
        return fbUser;
    }

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");


        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null){
                    fbUser.setValue(firebaseAuth.getCurrentUser());
                }
            }
        });

    }

    void register(
            String email,
            String password,
            String name,
            String lastName,
            int age
    ) {

        if ((email != null && !email.isEmpty()) || (password != null && !password.isEmpty())) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser fairebaseUser = authResult.getUser();
                            if (fairebaseUser == null){
                                return;
                            }
                            User user = new User(
                                    fairebaseUser.getUid(),
                                    name,
                                    lastName,
                                    age,
                                    false
                                    );
                            myRef.child(user.getId()).setValue(user);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            error.setValue(e.getMessage());

                        }
                    });
        } else {
            error.setValue("Please fill the fields");
        }


    }
}
