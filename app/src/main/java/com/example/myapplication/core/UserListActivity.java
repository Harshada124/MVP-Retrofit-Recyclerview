package com.example.myapplication.core;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.UserRecyclerAdapter;
import com.example.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity implements GetUserDataContract.View{

    public static final String TAG = "UserList";

    private UserListPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private UserRecyclerAdapter mUserRecyclerAdapter;
    private List<User> usersList;
    private ProgressBar pbLoading;

    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initializeViews();

        mPresenter = new UserListPresenter(this);
        mPresenter.requestDataFromServer();

    }

    private void initializeViews() {

        pbLoading = findViewById(R.id.pb_loading);
        mRecyclerView = findViewById(R.id.user_recyclervw);

        usersList = new ArrayList<>();

        mUserRecyclerAdapter = new UserRecyclerAdapter(usersList,UserListActivity.this);
        linearLayoutManager= new LinearLayoutManager(UserListActivity.this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mUserRecyclerAdapter);

    }

    @Override
    public void showProgress() {
        Log.i(TAG, " UserListActivity showProgress : ");
        pbLoading.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        Log.i(TAG, " UserListActivity hideProgress : ");

        pbLoading.setVisibility(View.GONE);

    }

    @Override
    public void setDataToRecyclerView(List<User> userArrayList) {
        Log.i(TAG, "  UserListActivity setDataToRecyclerView : "+ userArrayList.size());

        usersList.addAll(userArrayList);
        mUserRecyclerAdapter.notifyDataSetChanged();

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.i(TAG, " response failure : "+ throwable.getMessage());
        Toast.makeText(this, TAG+" : "+throwable.getMessage(), Toast.LENGTH_LONG).show();
    }
}
