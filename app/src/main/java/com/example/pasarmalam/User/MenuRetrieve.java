package com.example.pasarmalam.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pasarmalam.Database.FoodMenu;
import com.example.pasarmalam.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuRetrieve extends AppCompatActivity {


            FloatingActionButton nextPage;
            ListView myListView;
            List<FoodMenu> foodMenuList;

            DatabaseReference foodMenuDBref;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_menu_retrieve);


                myListView = findViewById(R.id.myListView);
                nextPage = findViewById(R.id.nextPage);

                nextPage = (FloatingActionButton) findViewById(R.id.nextPage);
                nextPage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), MenuForm.class));
                    }
                });


                foodMenuList = new ArrayList<>();

                foodMenuDBref = FirebaseDatabase.getInstance().getReference("Menu");

                foodMenuDBref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        foodMenuList.clear();

                        for (DataSnapshot foodMenuDatasnap : snapshot.getChildren()){

                            FoodMenu foodMenu = foodMenuDatasnap.getValue(FoodMenu.class);
                            foodMenuList.add(foodMenu);
                        }

                        ListAdapter3 adapter = new ListAdapter3(MenuRetrieve.this,foodMenuList);
                        myListView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
}

