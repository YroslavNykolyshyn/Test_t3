package com.example.t3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText edName,edDescription,edUrl;
    private DatabaseReference myDb;
    private String ITEM_KEY = "Item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    public void onClickSave(View view){
        String id  = myDb.getKey();
        String name = edName.getText().toString();
        String desc = edDescription.getText().toString();
        String url = edUrl.getText().toString();
        Item item = new Item(id,name,desc,url);
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(desc) && !TextUtils.isEmpty(url)){
            myDb.push().setValue(item);
            Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Enter value", Toast.LENGTH_SHORT).show();
        }
    }
   public void onClickRead(View view){
       Intent intent = new Intent(MainActivity.this, ReadActivity.class);
       startActivity(intent);
    }
    public void init(){
        edName = findViewById(R.id.edName);
        edDescription = findViewById(R.id.edDescription);
        edUrl = findViewById(R.id.edUrl);
        myDb = FirebaseDatabase.getInstance().getReference(ITEM_KEY);
    }

}