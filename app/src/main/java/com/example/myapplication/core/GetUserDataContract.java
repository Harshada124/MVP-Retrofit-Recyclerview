package com.example.myapplication.core;

import com.example.myapplication.model.User;

import java.util.List;

public interface GetUserDataContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(List<User> userArrayList);

            void onFailure(Throwable t);
        }

        void getUserDataList(OnFinishedListener onFinishedListener);

    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<User> userArrayList);

        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();

        void requestDataFromServer();

    }

}
