package com.example.myfirstapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static com.example.myfirstapp.DateDialog1.annDate_DIALOG_ID;

/**
 * Created by HI on 5/2/2017.
 */

public class Add_Page extends Fragment{

    Button but_Clear,but_Submit;
    EditText et_birthDate, et_annDate, et_name, et_phone, et_email, et_telNo;
    long mobile;
    String name,email,birthDate,annDate;
    DatePickerDialog dpd1,dpd2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View returnView = inflater.inflate(R.layout.add_layout, container, false);

        final Calendar c = Calendar.getInstance();
        final int yer = c.get(Calendar.YEAR);
        final int mon = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);

        dpd1 = new DatePickerDialog(getActivity(),birthListener,yer,mon,day);
        dpd1.getDatePicker().setMaxDate((c.getTimeInMillis()));
        dpd2 = new DatePickerDialog(getActivity(),annListener,yer,mon,day);
        dpd2.getDatePicker().setMaxDate((c.getTimeInMillis()));

        et_name      = (EditText) returnView.findViewById(R.id.TF_Name);
        et_phone     = (EditText) returnView.findViewById(R.id.TF_Mobile);
        et_email     = (EditText) returnView.findViewById(R.id.TF_Email);
        et_birthDate = (EditText) returnView.findViewById(R.id.DOB_field);
        et_annDate   = (EditText) returnView.findViewById(R.id.DOA_field);
        et_telNo     = (EditText) returnView.findViewById(R.id.TF_TelNo);
        but_Clear    = (Button) returnView.findViewById(R.id.But_clear);
        but_Submit   = (Button) returnView.findViewById(R.id.But_submit);

        et_name.setFocusable(false);
        et_phone.setFocusable(false);
        et_email.setFocusable(false);
        et_birthDate.setFocusable(false);
        et_annDate.setFocusable(false);
        et_telNo.setFocusable(false);

        et_birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dpd1.show();
                Toast.makeText(getActivity(), "DOB clicked", Toast.LENGTH_SHORT).show();
            }
        });
        et_annDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFocusOff(et_name, et_phone, et_email, et_telNo);

                dpd2.show();
                Toast.makeText(getActivity(), "DOA clicked", Toast.LENGTH_SHORT).show();
            }
        });
        et_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabFocus(et_name);
            }
        });
        et_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                name = et_name.getText().toString();
                if(!hasFocus){

                    if (name.isEmpty()) {
                        et_name.setError("Do not leave this field empty.");
                    } else if (name.length() < 2 || name.length() > 50) {
                        et_name.setError("Length too long or too short");
                    }
                }
            }
        });

        et_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabFocus(et_phone);
            }
        });
        et_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mobile = Long.parseLong(et_phone.getText().toString());
                if (!hasFocus) {

                    if (et_phone.getText().toString().isEmpty()) {
                        et_phone.setError("Do not leave this field empty.");
                    } else if (et_phone.length() != 10) {
                        et_phone.setError("Enter correct mobile no.");
                    }
                }
            }
        });

        et_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabFocus(et_email);
            }
        });
        et_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    email = et_email.getText().toString();
                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        et_email.setError("Please enter correct email address");
                    }
                }
            }
        });
        et_telNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabFocus(et_telNo);
            }
        });

        but_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_name.setText("");
                et_phone.setText("");
                et_email.setText("");
                et_birthDate.setText("");
                et_annDate.setText("");
                et_telNo.setText("");
            }
        });

        but_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                birthDate = et_birthDate.getText().toString();
                annDate   = et_annDate.getText().toString();
                //birth date field validation
                if(birthDate.isEmpty()){
                    et_birthDate.setError("Please enter Date of Birth.");
                }
            }
        });
        return returnView;
    }

    public void grabFocus(TextView tv){
        tv.setFocusable(true);
        tv.setFocusableInTouchMode(true);
        tv.requestFocus();
    }

    /*Following method takes away the focus from fields except DOB_field and DOA_field.
    * created for DOB_field and DOA_field*/
    public void setFocusOff(TextView tv1, TextView tv2, TextView tv3, TextView tv4) {
        tv1.setFocusable(false);
        tv2.setFocusable(false);
        tv3.setFocusable(false);
        tv4.setFocusable(false);
    }

    private DatePickerDialog.OnDateSetListener birthListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            String date = day + "/" + (month + 1) + "/" + year;
            et_birthDate.setText(date);
        }
    };

    private DatePickerDialog.OnDateSetListener annListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            String date = day + "/" + (month + 1) + "/" + year;
            et_annDate.setText(date);
        }
    };
}
