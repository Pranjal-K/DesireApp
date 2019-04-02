package com.pranjal.customnavigationdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class salesRecord extends AppCompatActivity {

    //a list to store all the products
    List<formDisplayClass> formDisplayClassList;

    //the recyclerview
    RecyclerView recyclerView;

    RecyclerAdapter adapter;
    Button mysearch ;
    EditText mytextsearch;
    DatabaseReference dbProducts;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag = getIntent().getIntExtra("flag", 0);
        if(flag==0)
            setContentView(R.layout.activity_sales_record);
        else
            setContentView(R.layout.custrecord);

        mysearch = findViewById(R.id.search);
        mytextsearch = findViewById(R.id.textsearch);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.myRecycleView);
        recyclerView.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //initializing the formDisplayClassList
        formDisplayClassList = new ArrayList<formDisplayClass>();


        if (flag == 0)
        {
            dbProducts = FirebaseDatabase.getInstance().getReference("Forms");

        }
        else
        {
            dbProducts = FirebaseDatabase.getInstance().getReference("PreOrders");
        }
      if(flag==0) {
          mysearch.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String searchtext = mytextsearch.getText().toString();
                  dbProducts.orderByChild("Sold-By").equalTo(searchtext).addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(DataSnapshot dataSnapshot) {

                          if (dataSnapshot.exists()) {
                              formDisplayClassList.clear();

                              for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                                  String name = (String) productSnapshot.child("Sold-By").getValue();
                                  String noOfCopies = "Copies: " + (String) productSnapshot.child("Copy-Count").getValue();
                                  String custnm = (String) productSnapshot.child("FirstName").getValue() + " " + (String) productSnapshot.child("LastName").getValue();
                                  String custaddr = (String) productSnapshot.child("Address").getValue() + " " + (String) productSnapshot.child("City").getValue();
                                  String custmob = (String) productSnapshot.child("Phone-Number").getValue();
                                  String custemail = (String) productSnapshot.child("Email-Address").getValue();
                                  formDisplayClass f = new formDisplayClass(name, noOfCopies, custnm, custaddr, custmob, custemail);
                                  formDisplayClassList.add(f);
                              }
                              adapter = new RecyclerAdapter(salesRecord.this, formDisplayClassList);
                              recyclerView.setAdapter(adapter);

                          }

                      }

                      @Override
                      public void onCancelled(DatabaseError databaseError) {

                      }
                  });

              }

          });
      }

        dbProducts.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    for(DataSnapshot productSnapshot : dataSnapshot.getChildren()){
                        String name = (String) productSnapshot.child("Sold-By").getValue();
                        String noOfCopies = "Copies: " + (String) productSnapshot.child("Copy-Count").getValue();
                        String custnm = (String) productSnapshot.child("FirstName").getValue() + " " + (String) productSnapshot.child("LastName").getValue() ;
                        String custaddr = (String) productSnapshot.child("Address").getValue() + " " + (String) productSnapshot.child("City").getValue() ;
                        String  custmob = (String) productSnapshot.child("Phone-Number").getValue();
                        String  custemail = (String) productSnapshot.child("Email-Address").getValue();
                        formDisplayClass f = new formDisplayClass(name, noOfCopies,custnm,custaddr,custmob,custemail);
                        formDisplayClassList.add(f);
                    }

                    adapter = new RecyclerAdapter(salesRecord.this, formDisplayClassList);
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}