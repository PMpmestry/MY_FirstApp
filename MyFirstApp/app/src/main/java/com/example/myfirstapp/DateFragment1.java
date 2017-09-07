package com.example.myfirstapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

/**
 * Created by Prathamesh 1 on 03-05-2017.
 */

public class DateFragment1 extends DialogFragment implements DatePickerDialog.OnDateSetListener {
//    DatePickerDialog.OnDateSetListener ondateSet;
    private int yer, mon, day;

//    public void setCallBack(DatePickerDialog.OnDateSetListener ondate){
//        ondateSet = ondate;
//    }

    public void setArguments(Bundle args){
        super.setArguments(args);
        yer = args.getInt("year");
        mon = args.getInt("month");
        day = args.getInt("day");
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        return new DatePickerDialog(getActivity(),this,yer,mon,day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Add_Page ap = new Add_Page();
        String date = day + "/" + (month + 1) + "/" + year;
        ap.et_birthDate.setText(date);
    }
}
