package com.example.messengerbyfirebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final  String EXTRA_CURRENT_USER_ID = "current_user_id";


    private MainViewModel viewModel;
    private UsersAdapter usersAdapter;
    private RecyclerView recyclerView;
    private String currentUserId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);


        usersAdapter.setOnUserClickListener(new UsersAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(User user) {
                Intent intent = ChatActivity.newIntent(MainActivity.this,currentUserId,user.getId());
                startActivity(intent);
            }
        });


        observeViewModel();

    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.setUserOnline(true);

    }

    @Override
    protected void onPause() {
        super.onPause();

        viewModel.setUserOnline(false);
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerViewMessage);
        usersAdapter = new UsersAdapter();
        recyclerView.setAdapter(usersAdapter);
        currentUserId = getIntent().getStringExtra(EXTRA_CURRENT_USER_ID);
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

        viewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                usersAdapter.setUsers(users);
            }
        });
    }

    public static Intent newIntent(Context context,String currentUserId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_CURRENT_USER_ID,currentUserId);
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
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}