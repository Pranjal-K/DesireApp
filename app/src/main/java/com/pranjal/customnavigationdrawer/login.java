package com.pranjal.customnavigationdrawer;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class login extends Fragment {


    public login() {
        // Required empty public constructor
    }

    private FirebaseAuth mauth;
    private Button loginButton;
    private EditText email, password;
    private ProgressDialog loadingBar;
    private DatabaseReference userRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        loadingBar = new ProgressDialog(getActivity());
        mauth = FirebaseAuth.getInstance();
        FirebaseUser currentuser = mauth.getCurrentUser();

        View view = inflater.inflate(R.layout.login, container, false);
        loginButton = (Button) view.findViewById(R.id.login);
        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);


        if (currentuser != null) {
            sendUserToInternScreen();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allowUserToLogin();
            }
        });

        return view;
    }

    private void allowUserToLogin() {

        final String userEmail = email.getText().toString();
        final String userPassword = password.getText().toString();

        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(getActivity(), "Please provide your email id", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(getActivity(), "Please provide your password", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.show();
            mauth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        sendUserToInternScreen();
                    } else {
                        String message = task.getException().getMessage();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }
    }

    private void sendUserToInternScreen() {
        Intent formScreen = new Intent(getActivity(), internPage.class);
        formScreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(formScreen);
    }

}
