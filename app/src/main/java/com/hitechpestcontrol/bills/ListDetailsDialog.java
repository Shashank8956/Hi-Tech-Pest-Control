package com.hitechpestcontrol.bills;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ListDetailsDialog extends DialogFragment implements View.OnClickListener{
    Button okBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.list_dialog, null);
        okBtn = (Button)view.findViewById(R.id.DialogOk);
        okBtn.setOnClickListener(this);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().setTitle("Details");
        setCancelable(false);
        return view;
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.DialogOk)
            getDialog().dismiss();
    }
}
