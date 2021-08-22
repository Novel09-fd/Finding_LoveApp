package com.novel.finder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;
import com.google.gson.Gson;
import com.novel.finder.adapter.FindLoveAdapter;
import com.novel.finder.entity.UserModel;
import com.novel.finder.service.ApiClient;
import com.novel.finder.service.ApiInterface;
import com.novel.finder.utility.JwtUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindLove extends AppCompatActivity {

    private SwipeDeck swipeDeck;
    UserModel userSex;
    ApiInterface apiInterface;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_love_swipe);


        swipeDeck = findViewById(R.id.swipe_deck);
        token = "Bearer "+getIntent().getStringExtra("token");

        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        try{
            Gson gson = new Gson();
            userSex = gson.fromJson(JwtUtil.getBodyDecode(getIntent().getStringExtra("token")),UserModel.class);
//            JwtUtil.getBodyDecode(getIntent().getStringExtra("token"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(userSex.getUsersex().equalsIgnoreCase("male")){
            callFemaleList();
            Toast.makeText(FindLove.this,"male",Toast.LENGTH_LONG).show();
        }else{
            callMaleList();
        }

        swipeActivity();

    }


    private void callFemaleList() {
        Call<ArrayList<UserModel>> call = apiInterface.getUserByUsersex(token,"female");
        call.enqueue(new Callback<ArrayList<UserModel>>() {
            @Override
            public void onResponse(Call<ArrayList<UserModel>> call, Response<ArrayList<UserModel>> response) {
                FindLoveAdapter findLoveAdapter = new FindLoveAdapter(response.body(), FindLove.this);
                swipeDeck.setAdapter(findLoveAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<UserModel>> call, Throwable t) {
                System.out.println("Test");
                System.out.println(t);
            }
        });
    }

    private void callMaleList() {
        Call<ArrayList<UserModel>> call = apiInterface.getUserByUsersex(token,"male");
        call.enqueue(new Callback<ArrayList<UserModel>>() {
            @Override
            public void onResponse(Call<ArrayList<UserModel>> call, Response<ArrayList<UserModel>> response) {
                FindLoveAdapter jodohPilihAdapter = new FindLoveAdapter(response.body(), FindLove.this);
                swipeDeck.setAdapter(jodohPilihAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<UserModel>> call, Throwable t) {
                System.out.println("Test");
                System.out.println(t);
            }
        });
    }


    private  void swipeActivity() {
        swipeDeck.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                Toast.makeText(FindLove.this, "Card Swiped Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardSwipedRight(int position) {
                Toast.makeText(FindLove.this, "Card Swiped Right", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void cardsDepleted() {
                Toast.makeText(FindLove.this, "No more choices", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {

            }
        });
    }


}