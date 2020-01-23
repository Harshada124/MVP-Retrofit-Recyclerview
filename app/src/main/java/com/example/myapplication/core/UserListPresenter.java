package com.example.myapplication.core;

import android.util.Log;

import com.example.myapplication.model.User;

import java.util.List;

import static com.example.myapplication.core.UserListActivity.TAG;

public class UserListPresenter implements GetUserDataContract.Presenter, GetUserDataContract.Model.OnFinishedListener{

    private GetUserDataContract.View userListView;

    private GetUserDataContract.Model userListModel;

    public UserListPresenter(GetUserDataContract.View userListView) {
        Log.i(TAG, " UserListPresenter : ");

        this.userListView = userListView;
        userListModel = new UserListModel();
    }

    @Override
    public void onFinished(List<User> userArrayList)
    {
        Log.i(TAG, " UserListPresenter onFinished : "+userArrayList.size());

        userListView.setDataToRecyclerView(userArrayList);

        if (userListView != null) {
            userListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t)
    {
        Log.i(TAG, " UserListPresenter onFailure : "+t.getMessage());

        userListView.onResponseFailure(t);
        if (userListView != null) {
            userListView.hideProgress();
        }
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, " UserListPresenter onDestroy : ");

        this.userListView = null;
    }

    @Override
    public void requestDataFromServer() {
        Log.i(TAG, " UserListPresenter requestDataFromServer : ");

        if (userListView != null) {
            userListView.showProgress();
        }
        userListModel.getUserDataList(this);
    }
}
