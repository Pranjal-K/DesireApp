package com.pranjal.customnavigationdrawer;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paytm.pgsdk.PaytmPGService;


/**
 * A simple {@link Fragment} subclass.
 */
public class paytm extends Fragment {


    public paytm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }

        PaytmPGService Service = PaytmPGService.getProductionService();





        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_paytm, container, false);

    }

}
