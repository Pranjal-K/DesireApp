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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class register extends AppCompatActivity {

    private EditText userMail, userPassword;
    private Button register;
    private FirebaseAuth mauth;
    private DatabaseReference userRef;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userMail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);

        mauth = FirebaseAuth.getInstance();
        loading = new ProgressDialog(this);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });

    }

    private void createNewAccount() {

        loading.show();

        String email = userMail.getText().toString();
        String password = userPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please provide your email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please provide your password", Toast.LENGTH_SHORT).show();
        }

        mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    sendUserToAdminPage();
                    Toast.makeText(register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loading.dismiss();
    }

    private void sendUserToAdminPage() {

        Intent adminPage = new Intent(register.this, adminpage.class);
        adminPage.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(adminPage);
        finish();
    }
}
