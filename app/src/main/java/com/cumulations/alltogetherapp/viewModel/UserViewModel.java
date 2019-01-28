package com.cumulations.alltogetherapp.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cumulations.alltogetherapp.model.Record;
import com.cumulations.alltogetherapp.repository.UserRepository;

import java.util.List;

public class UserViewModel extends ViewModel {
    private Context context;
    private UserRepository userRepository;
    public LiveData<List<Record>> userListliveData = new MutableLiveData<>();

    public UserViewModel(Context context) {
        this.context = context;
        userRepository = new UserRepository(context);
        userListliveData = userRepository.getListLiveData();
    }

    public void getUserList() {
        userRepository.getUserList();
    }

    public void getUsers() {
        this.context = context;
        userRepository = new UserRepository(context);
        userListliveData = userRepository.getListLiveData();
    }

    public LiveData<List<Record>> getUserListliveData() {
        userListliveData = userRepository.getListLiveData();
        return userListliveData;
    }
}
