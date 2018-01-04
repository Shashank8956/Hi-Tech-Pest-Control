package com.hitechpestcontrol.bills;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class EditDialog extends DialogFragment implements View.OnClickListener{
    Button okBtn, cancelBtn;
    EditText tvDiaName;
    EditText tvDiaTreat;
    EditText tvDiaAmt;
    EditText tvDiaDate;
    EditText tvDiaMob;
    EditText tvDiaBill;
    EditText tvDiaTrav;
    EditText tvDiaChem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.list_dialog, null);
        okBtn = (Button)view.findViewById(R.id.EditOk);
        cancelBtn = (Button)view.findViewById(R.id.EditCancel);

        cancelBtn.setOnClickListener(this);
        okBtn.setOnClickListener(this);

        tvDiaName = (EditText)view.findViewById(R.id.EditName);
        tvDiaTreat = (EditText)view.findViewById(R.id.EditTreat);
        tvDiaAmt = (EditText)view.findViewById(R.id.EditAmt);
        tvDiaDate = (EditText)view.findViewById(R.id.EditDate);
        tvDiaMob = (EditText)view.findViewById(R.id.EditContact);
        tvDiaBill = (EditText)view.findViewById(R.id.EditBill);
        tvDiaTrav = (EditText)view.findViewById(R.id.EditTrav);
        tvDiaChem = (EditText)view.findViewById(R.id.EditChem);

        Bundle mArgs = getArguments();
        String myValue = mArgs.getString("_NAME");
        Log.d("Edit Dialog fragment: ", myValue);
        tvDiaName.setText(myValue);

        myValue = Integer.toString(mArgs.getInt("_BILL"));
        tvDiaBill.setText(myValue);

        myValue = mArgs.getString("_DATE");
        tvDiaDate.setText(myValue);

        myValue = mArgs.getString("_TREATMENT");
        tvDiaTreat.setText(myValue);

        myValue = mArgs.getString("_CONTACT");
        tvDiaMob.setText(myValue);

        myValue = Integer.toString(mArgs.getInt("_AMOUNT"));
        tvDiaAmt.setText(myValue);

        myValue = Integer.toString(mArgs.getInt("_CHEMICAL"));
        tvDiaChem.setText(myValue);

        myValue = Integer.toString(mArgs.getInt("_TRAVEL"));
        tvDiaTrav.setText(myValue);

        getDialog().setCanceledOnTouchOutside(true);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //getDialog().setTitle("Details");
        setCancelable(false);
        return view;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.EditCancel) {
            getDialog().dismiss();
        } else if (v.getId() == R.id.EditOk) {
            getDialog().dismiss();
        }
    }
}
