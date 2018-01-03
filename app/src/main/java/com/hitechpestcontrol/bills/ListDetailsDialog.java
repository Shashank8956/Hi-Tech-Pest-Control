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


public class ListDetailsDialog extends DialogFragment implements View.OnClickListener{
    Button okBtn;
    TextView tvDiaName;
    TextView tvDiaTreat;
    TextView tvDiaAmt;
    TextView tvDiaDate;
    TextView tvDiaMob;
    TextView tvDiaBill;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.list_dialog, null);
        okBtn = (Button)view.findViewById(R.id.DialogOk);
        okBtn.setOnClickListener(this);
        tvDiaName = (TextView)view.findViewById(R.id.DialogName);
        tvDiaTreat = (TextView)view.findViewById(R.id.DialogTreat);
        tvDiaAmt = (TextView)view.findViewById(R.id.DialogAmt);
        tvDiaDate = (TextView)view.findViewById(R.id.DialogDate);
        tvDiaMob = (TextView)view.findViewById(R.id.DialogMob);
        tvDiaBill = (TextView)view.findViewById(R.id.DialogBill);

        Bundle mArgs = getArguments();
        String myValue = mArgs.getString("_NAME");
        Log.d("Dialog fragment: ", myValue);
        tvDiaName.setText(myValue);

        myValue = Integer.toString(mArgs.getInt("_BILL"));
        tvDiaBill.setText("Bill no: #"+myValue);

        myValue = mArgs.getString("_DATE");
        tvDiaDate.setText("Date: "+myValue);

        myValue = mArgs.getString("_TREATMENT");
        tvDiaTreat.setText("Treat: "+myValue);

        myValue = mArgs.getString("_CONTACT");
        tvDiaMob.setText("Mob: "+myValue);

        myValue = Integer.toString(mArgs.getInt("_AMOUNT"));
        tvDiaAmt.setText("Amt: "+getString(R.string.Rs)+" "+myValue);

        getDialog().setCanceledOnTouchOutside(true);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //getDialog().setTitle("Details");
        setCancelable(false);
        return view;
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.DialogOk)
            getDialog().dismiss();
    }
}
