package com.cumulations.alltogetherapp.remote;

import com.cumulations.alltogetherapp.model.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("aqxlc")
    Call<User> getUserList();
}