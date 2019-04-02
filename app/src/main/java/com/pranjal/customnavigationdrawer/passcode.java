package com.pranjal.customnavigationdrawer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class passcode extends AppCompatActivity {

    private DatabaseReference refCode;
    private EditText passcode;
    private Button submit;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);

        loadingBar = new ProgressDialog(this);
        passcode = (EditText) findViewById(R.id.editText);
        submit = (Button) findViewById(R.id.button10);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingBar.show();
                final String code = passcode.getText().toString();
                refCode = FirebaseDatabase.getInstance().getReference("passcode");
                refCode.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String storeCode = dataSnapshot.getValue(String.class);
                        if(storeCode.equals(code))
                        {
                            Intent i = new Intent(passcode.this, adminpage.class);
                            startActivity(i);
                            loadingBar.dismiss();
                        }
                        else
                        {
                            Toast.makeText(passcode.this, "Wrong Passcode", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }

                        refCode.removeEventListener(this);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
