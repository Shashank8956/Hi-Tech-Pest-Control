package com.hitechpestcontrol.bills;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ListDetailsDialog extends DialogFragment implements View.OnClickListener{
    Button okBtn;
    TextView tvDiaName;
    TextView tvDiaTreat;
    TextView tvDiaAmt;
    TextView tvDiaDate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.list_dialog, null);
        okBtn = (Button)view.findViewById(R.id.DialogOk);
        okBtn.setOnClickListener(this);
        tvDiaName = (TextView)view.findViewById(R.id.DialogName);
        tvDiaTreat = (TextView)view.findViewById(R.id.DialogTreat);
        tvDiaAmt = (TextView)view.findViewById(R.id.DialogAmt);
        tvDiaDate = (TextView)view.findViewById(R.id.DialogDate);

        Bundle mArgs = getArguments();
        String myValue = mArgs.getString("key");
        Log.d("Dialog fragment: ", myValue);
        tvDiaName.setText(myValue);

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
