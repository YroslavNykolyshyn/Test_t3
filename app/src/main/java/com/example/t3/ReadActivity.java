package com.example.t3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listItem;
    private List<Item> listTemp;
    private DatabaseReference myDb;
    private  String ITEM_KEY = "Item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        init();
        getDataFromDb();
        setOnClickItem();
    }
    private void init(){
        listView = findViewById(R.id.listView);
        listItem = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listItem);
        listView.setAdapter(adapter);
        myDb = FirebaseDatabase.getInstance().getReference(ITEM_KEY);
    }
    private void getDataFromDb(){
        ValueEventListener valueEventListener= new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (listItem.size() > 0)listItem.clear();
                if (listTemp.size() > 0)listTemp.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    Item item= ds.getValue(Item.class);
                    assert item != null;
                    listItem.add(item.name);
                    listTemp.add(item);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myDb.addValueEventListener(valueEventListener);
    }
    private void setOnClickItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = listTemp.get(position);
                Intent intent = new Intent(ReadActivity.this,ShowActivity.class);
                intent.putExtra(Constant.ITEM_NAME,item.name);
                intent.putExtra(Constant.ITEM_DESC,item.desc);
                intent.putExtra(Constant.ITEM_URL,item.url);
                startActivity(intent);
            }
        });
    }
}