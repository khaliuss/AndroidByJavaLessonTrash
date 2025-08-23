package com.example.messengerbyfirebase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotViewModel extends AndroidViewModel {

    private FirebaseAuth mAuth;
    private MutableLiveData<String> error = new MutableLiveData<>();
    private MutableLiveData<Boolean> success = new MutableLiveData<>();

    public ForgotViewModel(@NonNull Application application) {
        super(application);
        mAuth = FirebaseAuth.getInstance();

    }

    public LiveData<String> getError() {
        return error;
    }

    public LiveData<Boolean> isSuccess() {
        return success;
    }

    void resetPass(String email) {
        if (email != null) {
            mAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            success.setValue(true);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            error.setValue(e.getMessage());
                        }
                    });
        }

    }
}
