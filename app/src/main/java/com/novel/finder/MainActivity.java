package com.novel.finder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton ibFindLove , ibListLove , ibExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ibFindLove = findViewById(R.id.btn_findLove);
        ibListLove = findViewById(R.id.btn_match);
        ibExit = findViewById(R.id.btn_exit);

        logout();
        findLove();
    }

    private void logout() {
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        ibExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ibExit.startAnimation(animation);
                finish();
            }
        });
    }

    private void findLove() {
        ibFindLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FindLove.class);
                intent.putExtra("token", getIntent().getStringExtra("token"));
                startActivity(intent);
            }
        });

    }


}