package com.pranjal.customnavigationdrawer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class preorder extends AppCompatActivity {

    private FirebaseAuth mauth;
    private Button adminScreen;
    private ProgressDialog loadingBar;

    Button mFirebaseBtn;
    DatabaseReference mDatabase;
    EditText fnamefield, lnamefield, statefield, cityfield, addressfield;
    EditText phonefield, emailfield;
    EditText countfield;

    EditText datefield;
    // int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;

    //Class fragmentClass;
    //public static Fragment fragment;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sendUserToLoginActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preorder);

        loadingBar = new ProgressDialog(this);

        mFirebaseBtn = findViewById(R.id.submit);
        mDatabase = FirebaseDatabase.getInstance().getReference("PreOrders");

        datefield = findViewById(R.id.date);
       /* final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);*/

        fnamefield = findViewById(R.id.fname);
        lnamefield = findViewById(R.id.lname);
        statefield = findViewById(R.id.state);
        cityfield = findViewById(R.id.city);
        addressfield = findViewById(R.id.address);
        phonefield = findViewById(R.id.phone);
        emailfield = findViewById(R.id.email);
        countfield = findViewById(R.id.count);
        //billfield = findViewById(R.id.bill);
        // soldbyfield = findViewById(R.id.soldby);

        mauth = FirebaseAuth.getInstance();

        mFirebaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingBar.show();
                HashMap<String, String> dataMap = new HashMap<String, String>();


                //NAME Fields
                String fnm = fnamefield.getText().toString().trim();
                String lnm = lnamefield.getText().toString().trim();
                dataMap.put("FirstName", fnm);
                dataMap.put("LastName", lnm);

                //Location Fields
                String st = statefield.getText().toString().trim();
                String cty = cityfield.getText().toString().trim();
                String addr = addressfield.getText().toString().trim();
                dataMap.put("State", st);
                dataMap.put("City", cty);
                dataMap.put("Address", addr);

                //Contact Field
                String phone = phonefield.getText().toString().trim();
                String email = emailfield.getText().toString().trim();
                dataMap.put("Phone-Number", phone);
                dataMap.put("Email-Address", email);

                //Order Details
                String cnt = countfield.getText().toString().trim();
                //String tbill = billfield.getText().toString().trim();
                String dt = datefield.getText().toString().trim();
                // String soldby = soldbyfield.getText().toString().trim();
                dataMap.put("Copy-Count", cnt);
                //      dataMap.put("Total-Bill", tbill);
                dataMap.put("Date", dt);
                //dataMap.put("Sold-By", soldby);

                if (TextUtils.isEmpty(fnm)) {
                    loadingBar.dismiss();
                    Toast.makeText(preorder.this, "Please provide your credentials", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(lnm)) {
                    loadingBar.dismiss();
                    Toast.makeText(preorder.this, "Please provide your credentials", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(st)) {
                    loadingBar.dismiss();
                    Toast.makeText(preorder.this, "Please provide your credentials", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(cty)) {
                    loadingBar.dismiss();
                    Toast.makeText(preorder.this, "Please provide your credentials", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(addr)) {
                    loadingBar.dismiss();
                    Toast.makeText(preorder.this, "Please provide your credentials", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(phone)) {
                    loadingBar.dismiss();
                    Toast.makeText(preorder.this, "Please provide your credentials", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(email)) {
                    loadingBar.dismiss();
                    Toast.makeText(preorder.this, "Please provide your credentials", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(cnt)) {
                    loadingBar.dismiss();
                    Toast.makeText(preorder.this, "Please provide your credentials", Toast.LENGTH_SHORT).show();
                } else {
                    mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                loadingBar.dismiss();
                                Toast.makeText(preorder.this, "Form Submitted", Toast.LENGTH_LONG).show();
                            } else {
                                loadingBar.dismiss();
                                Toast.makeText(preorder.this, "Error...", Toast.LENGTH_LONG).show();
                            }
                            Intent intent = new Intent(preorder.this, preorder.class);
                            startActivity(intent);
                        }

                    });
                }
            }
        });

    }

  /*  void showDate(View v)
    {
        showDialog(DIALOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        else
            return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            year_x = year;
            month_x = month + 1;
            day_x = day;
            datefield.setText(year_x + "/" + month_x + "/" + day_x);
        }
    };*/

    private void sendUserToLoginActivity() {
        Intent login = new Intent(this, Main2Activity.class);
        login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(login);
        finish();
    }
}