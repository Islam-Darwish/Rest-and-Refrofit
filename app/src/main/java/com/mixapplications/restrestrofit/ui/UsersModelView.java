package com.mixapplications.restrestrofit.ui;

import androidx.lifecycle.MutableLiveData;

import com.mixapplications.restrestrofit.model.Page;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersModelView {
    public MutableLiveData<Page> pageMutableLiveData = new MutableLiveData<>() ;
    public int totalPages = 1;
    public int nextPage = 1;
    public void loadPage(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PageInterface pageInterface = retrofit.create(PageInterface.class);
        Call<Page> call = pageInterface.getPage(nextPage++);
        call.enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                pageMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                try {
                    throw new Throwable(t);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }
}
