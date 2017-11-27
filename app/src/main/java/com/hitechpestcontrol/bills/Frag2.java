package com.hitechpestcontrol.bills;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


public class Frag2 extends Fragment {
    private RecyclerView rcl;
    private CustAdapter ada;

    private String[] names = {"Hotel Nagpur Ashok", "Rahul Delux", "Hotel Rahul", "Rathi Foods", "Dosa Plaza",
            "Hotel Nagpur Ashok", "Rahul Delux", "Hotel Rahul", "Rathi Foods", "Dosa Plaza",
            "Hotel Nagpur Ashok", "Rahul Delux", "Hotel Rahul", "Rathi Foods", "Dosa Plaza",
            "Hotel Nagpur Ashok", "Rahul Delux", "Hotel Rahul", "Rathi Foods", "Dosa Plaza",
            "Hotel Nagpur Ashok", "Rahul Delux", "Hotel Rahul", "Rathi Foods", "Dosa Plaza",};

    private String[] treatment = {"Termite", "Cockroach", "Fumigation", "BedBug", "Fogging",
            "Termite", "Cockroach", "Fumigation", "BedBug", "Fogging",
            "Termite", "Cockroach", "Fumigation", "BedBug", "Fogging",
            "Termite", "Cockroach", "Fumigation", "BedBug", "Fogging",
            "Termite", "Cockroach", "Fumigation", "BedBug", "Fogging"};

    private String[] date = {"02/05/2017", "03/06/2017", "06/06/2017", "19/06/2017", "15/07/2017",
            "02/05/2017", "03/06/2017", "06/06/2017", "19/06/2017", "15/07/2017",
            "02/05/2017", "03/06/2017", "06/06/2017", "19/06/2017", "15/07/2017",
            "02/05/2017", "03/06/2017", "06/06/2017", "19/06/2017", "15/07/2017",
            "02/05/2017", "03/06/2017", "06/06/2017", "19/06/2017", "15/07/2017"};

    private String[] amount = {"15000", "5000", "6000", "3000", "2000",
            "15000", "5000", "6000", "3000", "2000",
            "15000", "5000", "6000", "3000", "2000",
            "15000", "5000", "6000", "3000", "2000"};


    public Frag2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag2_layout, container, false);
        rcl = (RecyclerView) view.findViewById(R.id.RList);
        ada = new CustAdapter(getActivity());
        rcl.setAdapter(ada);
        rcl.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcl.setHasFixedSize(true);
        return view;
        /*View view = inflater.inflate(R.layout.frag2_layout, container, false);
        CustomListAdapter cusAda = new CustomListAdapter(getActivity(), names, treatment, date, amount);
        lv = (ListView) view.findViewById(R.id.lview);
        lv.setAdapter(cusAda);

        return view;*/
    }

}
