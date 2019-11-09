package com.mixapplications.restrestrofit.ui;

import com.mixapplications.restrestrofit.model.Page;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PageInterface {
    @GET("users")
    public Call<Page> getPage(@Query("page") int pageId);
}
