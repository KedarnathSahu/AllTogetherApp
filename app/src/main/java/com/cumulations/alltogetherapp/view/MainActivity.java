package com.cumulations.alltogetherapp.view;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cumulations.alltogetherapp.R;
import com.cumulations.alltogetherapp.model.Record;
import com.cumulations.alltogetherapp.repository.UserRepository;
import com.cumulations.alltogetherapp.view.adapter.UserAdapter;
import com.cumulations.alltogetherapp.viewModel.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private UserAdapter userAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userRepository = new UserRepository(this);
        userRepository.getUserList();

        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.refresh);
        userViewModel = new UserViewModel(this);
        callData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void callData() {
        userViewModel.getUsers();
        userViewModel.getUserListliveData().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(@Nullable List<Record> records) {
                userAdapter = new UserAdapter(MainActivity.this, records);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(userAdapter);
            }
        });
    }
}
