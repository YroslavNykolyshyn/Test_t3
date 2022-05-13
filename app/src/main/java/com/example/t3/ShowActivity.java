package com.example.t3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.annotations.Nullable;

public class ShowActivity extends AppCompatActivity {
    private TextView tvName,tvDesc;
    private ImageView tvUrl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);
        init();
        getIntentMain();
    }
    private void init(){
        tvName = findViewById(R.id.tvName);
        tvDesc = findViewById(R.id.tvDesc);
        tvUrl = findViewById(R.id.tvUrl);
    }
    private void getIntentMain(){
        Intent intent = getIntent();
        if (intent != null){
            tvName.setText(intent.getStringExtra(Constant.ITEM_NAME));
            tvDesc.setText(intent.getStringExtra(Constant.ITEM_DESC));
            tvUrl.setImageURI(Uri.parse(intent.getStringExtra(Constant.ITEM_URL)));
        }
    }
}