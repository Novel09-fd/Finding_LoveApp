package com.novel.finder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.novel.finder.entity.UserModel;
import com.novel.finder.service.ApiClient;
import com.novel.finder.service.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername , edtpassword ;
    Button btnLogin ;
    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.txt_loginUsername);
        edtpassword = findViewById(R.id.txt_loginPassword);
        btnLogin = findViewById(R.id.btn_login);
        txtRegister = findViewById(R.id.txt_register);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel user = new UserModel();
                user.setUsername(edtUsername.getText().toString());
                user.setPassword(edtpassword.getText().toString());
                ApiInterface apiInterface =  ApiClient.getRetrofit().create(ApiInterface.class);
                Call<String> call = apiInterface.userLogin(user);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        System.out.println("suuuuuuuuuuuuuuuuuuuuk");
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("token", response.body());
                        intent.putExtra("name", edtUsername.getText().toString());
                        Toast.makeText(LoginActivity.this,"Login Success", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Login problem!", Toast.LENGTH_SHORT).show();
                        System.out.println("========================");
                    }
                });
            }
        });

    }
}