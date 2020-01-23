package com.example.myapplication.network;

import com.example.myapplication.model.User;
import com.example.myapplication.model.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("users")
    Call<List<User>> getUser();
}
