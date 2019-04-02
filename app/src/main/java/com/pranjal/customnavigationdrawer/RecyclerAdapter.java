package com.pranjal.customnavigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.formDisplayClassViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<formDisplayClass> formDisplayClassList;

    //getting the context and product list with constructor
    public RecyclerAdapter(Context mCtx, List<formDisplayClass> formDisplayClassList) {
        this.mCtx = mCtx;
        this.formDisplayClassList = formDisplayClassList;
    }

    @Override
    public formDisplayClassViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.formrow, parent, false);
        return new formDisplayClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final formDisplayClassViewHolder holder, int position) {
        //getting the product of the specified position
        formDisplayClass product = formDisplayClassList.get(position);

        //binding the data with the viewholder views
        holder.name.setText(product.getName());
        holder.noOfCopies.setText(String.valueOf(product.getnoOfCopies()));
        holder.custaddr.setText(product.getCustaddr());
        holder.custmob.setText(product.getCustmob());
        holder.custemail.setText(product.getCustemail());
        holder.custnm.setText(product.getCustnm());

    }


    @Override
    public int getItemCount() {
        return formDisplayClassList.size();
    }


    class formDisplayClassViewHolder extends RecyclerView.ViewHolder {

        public TextView name, noOfCopies,custnm,custaddr,custmob,custemail;

        public formDisplayClassViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.post_title);
            noOfCopies = itemView.findViewById(R.id.copies);
            custnm = itemView.findViewById(R.id.custname);
            custaddr = itemView.findViewById(R.id.custaddr);
            custmob= itemView.findViewById(R.id.custmob);
            custemail = itemView.findViewById(R.id.custemail);
        }
    }
}