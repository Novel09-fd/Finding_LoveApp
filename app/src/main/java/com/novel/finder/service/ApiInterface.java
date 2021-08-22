package com.novel.finder.service;

import com.novel.finder.entity.UserModel;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("/login")
    Call<String> userLogin(@Body UserModel userModel);

    @Multipart
    @POST("/register")
    Call<String> userRegister(@Part MultipartBody.Part file, @Part ("data") RequestBody data);

    @GET("/user/{usersex}")
    Call<ArrayList<UserModel>> getUserByUsersex(@Header("Authorization") String header, @Path("usersex")String gender);
}
