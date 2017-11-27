package com.hitechpestcontrol.bills;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CustAdapter extends RecyclerView.Adapter<CustAdapter.MyViewHolder> {


    private LayoutInflater inflator;
    private static String[] Name= {"Lorem", "Ipsum", "Doler", "Sit"};
    private static String[] Treat= {"Sit", "Doler", "Ipsum", "Lorem"};

    public CustAdapter(Context con)
    {
        inflator=LayoutInflater.from(con);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvName.setText(Name[1]);
        holder.tvTreat.setText(Treat[1]);
    }

    @Override
    public int getItemCount() {
        return 400;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvTreat;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.RowName);
            tvTreat = (TextView) itemView.findViewById(R.id.RowTreat);
        }
    }
}