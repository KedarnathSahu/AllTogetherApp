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
import com.cumulations.alltogetherapp.view.adapter.UserAdapter;
import com.cumulations.alltogetherapp.viewModel.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private UserAdapter userAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        userViewModel = new UserViewModel(this);
        userViewModel.getUserList();//api call and get the data

        userAdapter = new UserAdapter(MainActivity.this);
        recyclerView.setAdapter(userAdapter);

        callData();
        swipeRefreshLayout = findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void callData() {
        userViewModel.getUserListliveData().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(@Nullable List<Record> records) {
                userAdapter.updateDataSetChange(records);
            }
        });
    }
}
