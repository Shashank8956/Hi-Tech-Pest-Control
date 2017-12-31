package com.hitechpestcontrol.bills;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class ConfirmDialog extends DialogFragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.list_dialog, null);

        getDialog().setCanceledOnTouchOutside(true);
        getDialog().setTitle("Delete Record");
        setCancelable(false);
        return view;
    }
    @Override
    public void onClick(View v) {

    }
}
