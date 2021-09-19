package com.example.tada;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    Context context;
    ArrayList<String> date=new ArrayList<String>(0);
    ArrayList<String> name=new ArrayList<String>(0);
    ArrayList<String> travel=new ArrayList<String>(0);
    ArrayList<String> lunch=new ArrayList<String>(0);
    ArrayList<String> instrument=new ArrayList<String>(0);
    ArrayList<Boolean> paid=new ArrayList<Boolean>(0);


    //constructor
    public Adapter(Context c,ArrayList<String> d, ArrayList<String> e,ArrayList<String> f, ArrayList<String> g, ArrayList<String> h, ArrayList<Boolean> i){
        context =c ;
        date=d;
        name=e;
        travel=f;
        lunch=g;
        instrument=h;
        paid=i;
    }


    @Override
    public int getCount() {
        return date.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater li=LayoutInflater.from(context);
        View v=li.inflate(R.layout.listview_history,viewGroup,false);

        //initial
        TextView c1=v.findViewById(R.id.tmDate);
        TextView c2=v.findViewById(R.id.tmName);
        TextView c3=v.findViewById(R.id.tmTravel);
        TextView c4=v.findViewById(R.id.tmLunch);
        TextView c5=v.findViewById(R.id.tmInstrument);
        TextView c6=v.findViewById(R.id.tmTotal);
        CheckBox c7=v.findViewById(R.id.tmSwitch);

        //setdata
        c1.setText(date.get(i));
        c2.setText(name.get(i));
        c3.setText(travel.get(i));
        c4.setText(lunch.get(i));
        c5.setText(instrument.get(i));

        int sum=Integer.parseInt(travel.get(i))+Integer.parseInt(lunch.get(i))+Integer.parseInt(instrument.get(i));
        c6.setText(String.valueOf(sum));

        c7.setChecked(paid.get(i));

        return v;
    }
}
