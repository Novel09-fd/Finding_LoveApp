package com.novel.finder;

import androidx.appcompat.app.AppCompatActivity;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.novel.finder.entity.UserModel;
import com.novel.finder.service.ApiClient;
import com.novel.finder.service.ApiInterface;

import java.io.File;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {

    EditText et_username ,et_password,et_name,
             et_age,et_phone ;
    Spinner spn_gender;
    Button btn_reg , btn_upload;
    private int requestCode=1;
    String mediaPath;
    ImageView imgPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_username = findViewById(R.id.txt_usernameReg);
        et_password = findViewById(R.id.txt_passwordReg);
        et_age = findViewById(R.id.txt_ageReg);
        et_name = findViewById(R.id.txt_nameReg);
        et_phone = findViewById(R.id.txt_phoneReg);
        btn_upload = findViewById(R.id.btn_upload);
        btn_reg = findViewById(R.id.btn_register);
        spn_gender = findViewById(R.id.spinner_reg);
        imgPhoto = findViewById(R.id.imageView);

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gambar = new Intent(getApplicationContext(), ImageSelectActivity.class);
                gambar.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
                gambar.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
                gambar.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
                startActivityForResult(gambar, 1);
            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
                finish();
            }
        });

    }

    private void submit() {
        UserModel user = new UserModel();
        user.setUsername(et_username.getText().toString());
        user.setName(et_name.getText().toString());
        user.setPassword(et_password.getText().toString());
        user.setPhonenumber(et_phone.getText().toString());
        user.setAge(et_age.getText().toString());
        //spinner
        String itemVal =String.valueOf(spn_gender.getSelectedItem());
        user.setUsersex(itemVal);

        Gson gson = new Gson();
        String json = gson.toJson(user);

        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        File file = new File(mediaPath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody data =RequestBody.create(MediaType.parse("text/plain"),json);
        Call<String> call = apiInterface.userRegister(fileToUpload,data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("=================");
                Toast.makeText(RegisterActivity.this, response.body(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "register unsuccessful", Toast.LENGTH_LONG).show();
                System.out.println(t);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.requestCode == requestCode && resultCode == RESULT_OK) {

            mediaPath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);

            imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
        }
    }



}