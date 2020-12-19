package com.utachiwana.athena.ui.menu;

import com.google.gson.JsonObject;
import com.utachiwana.athena.DataGenerator;
import com.utachiwana.athena.network.NetworkUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuPresenter{

    MenuView mView;

    public MenuPresenter(MenuView mView) {
        this.mView = mView;
    }

    public void loadPosts(Boolean... args) {
        if (args == null || args.length != 3) return;
        mView.showPosts(DataGenerator.getPosts(10));
        //mView.showLoading();
        /*NetworkUtils.getApi().getPosts(args).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()){
                    mView.showPosts(response.body());
                }
                else {
                    mView.showError();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                mView.showError();
            }
        });*/
    }
}
