package com.example.messengerbyfirebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private UsersAdapter usersAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            User user = new User("id: " + i,
                    "Name " + i,
                    "LastName " + i,
                    new Random().nextInt(70), new Random().nextBoolean());
            users.add(user);
        }

        usersAdapter.setUsers(users);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        observeViewModel();

    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        usersAdapter = new UsersAdapter();
        recyclerView.setAdapter(usersAdapter);
    }

    public void observeViewModel() {
        viewModel.getUser().observe(MainActivity.this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser == null) {
                    Intent intent = LogInActivity.newIntent(MainActivity.this);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logOut) {
            viewModel.signOut();
        }
        return super.onOptionsItemSelected(item);
    }
}