package com.pranjal.customnavigationdrawer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class changePasscode extends AppCompatActivity {

    private DatabaseReference refCodeChangePasscode;
    private EditText passcode;
    private Button submitChangePasscode;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_passcode);

        loadingBar = new ProgressDialog(this);
        passcode = (EditText) findViewById(R.id.editText_changePasscode);
        submitChangePasscode = (Button) findViewById(R.id.changePasscode);

        submitChangePasscode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = passcode.getText().toString();
                if (!TextUtils.isEmpty(code)) {
                    loadingBar.show();
                    refCodeChangePasscode = FirebaseDatabase.getInstance().getReference("passcode");

                    refCodeChangePasscode.setValue(code).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                loadingBar.dismiss();
                                Toast.makeText(changePasscode.this, "Change Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(changePasscode.this, adminpage.class);
                                startActivity(i);
                                finish();
                            } else {
                                loadingBar.dismiss();
                                Toast.makeText(changePasscode.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(changePasscode.this, "Please Enter Passcode", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
