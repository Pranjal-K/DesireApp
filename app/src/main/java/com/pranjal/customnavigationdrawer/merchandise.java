package com.pranjal.customnavigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link merchandise.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link merchandise#newInstance} factory method to
 * create an instance of this fragment.
 */
public class merchandise extends Fragment {


    public merchandise() {
        // Required empty public constructor
    }


    ImageButton copy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.merchandise, container, false);
        copy = (ImageButton) view.findViewById(R.id.copy);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), preorder.class);
                startActivity(i);
            }
        });

        return view;

    }

}
