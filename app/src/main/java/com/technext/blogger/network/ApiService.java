package com.technext.blogger.network;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiService {
    //1 get initial blog list
    @GET("simple-blog-api/db")
    Call<JsonElement> get_bloglist();

}
