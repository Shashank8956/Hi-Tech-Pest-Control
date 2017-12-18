package com.hitechpestcontrol.bills;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity{


    private static final int NUM_PAGES = 3;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private CustAdapter tempAda;
    private SearchView search;
    private ArrayList<Model> tempMod = new ArrayList<Model>();
    private String tabtitles[] = new String[] { "Home", "Log", "Accounts" }, query = null;
    private EditText edate;
    private String tag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_view_pager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Set custom title
        toolbar.setTitle("Hi Tech Pest Control");
        setSupportActionBar(toolbar);

        mPager = (ViewPager)findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),getApplicationContext());
        mPager.setAdapter(mPagerAdapter);

        /*Bundle bundle = new Bundle();
        bundle.putString("params", "My String data");
// set MyFragment Arguments
        Frag1 myObj = new Frag1();
        myObj.setArguments(bundle);*/

        tempMod = new Frag2().getModelList();
        tempAda = new Frag2().getAdapter();
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public void sendMessage(View view)
    {
        Context context = getApplicationContext();
        String msg = "Entry added successfully!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast= Toast.makeText(context, msg, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public String getFragmentTag(){
        return tag;
    }

    public void getAccounts(View view)
    {
        Button btn;

        Intent intent = new Intent(MainActivity.this, Activity2.class);
        startActivity(intent);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private Map<Integer, String> mFragmentTags;
        private FragmentManager mFragmentManager;
        private Context mContext;

        public ScreenSlidePagerAdapter(FragmentManager fm, Context con) {
            super(fm);
            mFragmentManager = fm;
            mFragmentTags = new HashMap<Integer, String>();
            mContext = con;
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return new Frag1();
                case 1: return new Frag2();
                case 2: return new Frag3();
                default: return new Frag1();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int pos) {
            return tabtitles[pos];
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            Object obj = super.instantiateItem(container, position);
            if(obj instanceof Fragment) {
                //Recording the fragment tag here
                Fragment f = (Fragment) obj;
                tag = f.getTag();
                mFragmentTags.put(position, tag);
            }
            return obj;
        }

        public Fragment getFragment(int position){
            String tag = mFragmentTags.get(position);
            if(tag == null)
                return null;
            return mFragmentManager.findFragmentByTag(tag);
        }
    }
}
