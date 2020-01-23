package com.example.myapplication.core;

import android.util.Log;

import com.example.myapplication.model.User;
import com.example.myapplication.model.UserList;
import com.example.myapplication.network.ApiClient;
import com.example.myapplication.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.core.UserListActivity.TAG;

public class UserListModel implements GetUserDataContract.Model{

    @Override
    public void getUserDataList(final OnFinishedListener onFinishedListener) {

        ApiInterface apiInterfaceService = ApiClient.getRetrofitInstance().create(ApiInterface.class);

        Call<List<User>> call = apiInterfaceService.getUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.i(TAG," onResponse: "+response.body().size());
                for (int i=0;i<response.body().size();i++) {
                    Log.i(TAG," onResponse: "+response.body().get(i).getLogin());
                }
                onFinishedListener.onFinished(response.body());

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.i(TAG," onFailure: "+t.getMessage());

            }
        });
    }
}
