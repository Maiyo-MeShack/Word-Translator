package com.interpreter.application;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder>{
    private String[] myDataSet;


    public void setMyDataSet(String[] myDataSet) {
        this.myDataSet = myDataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int x){
        TextView textView = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.mywords,viewGroup,false);
        MyViewHolder view = new MyViewHolder(textView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder view, int x){
        MyViewHolder.textView.setText(myDataSet[x]);
    }

    @Override
    public int getCount() {
        return myDataSet.length;
    }
        public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            public MyViewHolder(@NonNull TextView textView){
                super(textView);
                this.textView = textView;
            }
        }
}
