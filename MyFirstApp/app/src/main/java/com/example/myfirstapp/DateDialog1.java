package com.example.myfirstapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by HI on 5/2/2017.
 */
public class DateDialog1 extends AppCompatActivity implements View.OnClickListener {
    static final int birthDate_DIALOG_ID = 0;
    static final int annDate_DIALOG_ID = 1;
    private int datePickerID;

    Button but_Clear,but_Submit;
    EditText et_birthDate, et_annDate, et_name, et_phone, et_email, et_telNo;
    long mobile;
    String name,email,birthDate,annDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

        et_name      = (EditText) findViewById(R.id.TF_Name);
        et_phone     = (EditText) findViewById(R.id.TF_Mobile);
        et_email     = (EditText) findViewById(R.id.TF_Email);
        et_birthDate = (EditText) findViewById(R.id.DOB_field);
        et_annDate   = (EditText) findViewById(R.id.DOA_field);
        et_telNo     = (EditText) findViewById(R.id.TF_TelNo);
        but_Clear    = (Button) findViewById(R.id.But_clear);
        but_Submit   = (Button) findViewById(R.id.But_submit);

        et_name.setFocusable(false);
        et_phone.setFocusable(false);
        et_email.setFocusable(false);
        et_birthDate.setFocusable(false);
        et_annDate.setFocusable(false);
        et_telNo.setFocusable(false);

        et_birthDate.setOnClickListener(this);
        et_annDate.setOnClickListener(this);
        et_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabFocus(et_name);
            }
        });

        et_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabFocus(et_phone);
            }
        });

        et_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabFocus(et_email);
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
                name      = et_name.getText().toString();
                mobile    = Long.parseLong(et_phone.getText().toString());
                email     = et_email.getText().toString();
                birthDate = et_birthDate.getText().toString();
                annDate   = et_annDate.getText().toString();
                int mobile1 = et_phone.length();
                //name field validation
                if (name.isEmpty()) {
                    et_name.setError("Do not leave this field empty.");
                } else if (name.length() < 2 || name.length() > 50) {
                    et_name.setError("Length too long or too short");
                }

                //mobile no. validation
                if(et_phone.getText().toString().isEmpty()){
                    et_phone.setError("Do not leave this field empty.");
                }else if(mobile1!=10){
                    et_phone.setError("Enter correct mobile no.");
                }

                //email field validation
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    et_email.setError("Please enter correct email address");
                }

                //birth date field validation
                if(birthDate.isEmpty()){
                    et_birthDate.setError("Please enter Date of Birth.");
                }
            }
        });

    }

    public void grabFocus(TextView tv){
        tv.setFocusable(true);
        tv.setFocusableInTouchMode(true);
        tv.requestFocus();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.DOB_field) {
            datePickerID = R.id.DOB_field;
            setFocusOff(et_name, et_phone, et_email, et_telNo);
            showDialog(0);
        } else if (v.getId() == R.id.DOA_field) {
            datePickerID = R.id.DOA_field;
            setFocusOff(et_name, et_phone, et_email, et_telNo);
            showDialog(1);
        }
    }

    public Dialog onCreateDialog(int id) {
        final Calendar c = Calendar.getInstance();
        final int yer = c.get(Calendar.YEAR);
        final int mon = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog[] dpd = new DatePickerDialog[1];
        if (id == birthDate_DIALOG_ID) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dpd[0] = new DatePickerDialog(DateDialog1.this, dpListener, yer, mon, day);
                    dpd[0].getDatePicker().setMaxDate(c.getTimeInMillis());
                }
            });
            return dpd[0];
        } else if (id == annDate_DIALOG_ID) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dpd[0] = new DatePickerDialog(DateDialog1.this, dpListener, yer, mon, day);
                    dpd[0].getDatePicker().setMaxDate(c.getTimeInMillis());
                }
            });
            return dpd[0];
        } else {
            Toast.makeText(DateDialog1.this, "Error!!!", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    /*Following method takes away the focus from fields except DOB_field and DOA_field.
    * created for DOB_field and DOA_field*/
    public void setFocusOff(TextView tv1, TextView tv2, TextView tv3, TextView tv4) {
        tv1.setFocusable(false);
        tv2.setFocusable(false);
        tv3.setFocusable(false);
        tv4.setFocusable(false);
    }

    private DatePickerDialog.OnDateSetListener dpListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            String date = day + "/" + (month + 1) + "/" + year;
            switch (datePickerID) {
                case R.id.DOB_field:
                    et_birthDate.setText(date);
                    break;
                case R.id.DOA_field:
                    et_annDate.setText(date);
                    break;
                default:
                    Toast.makeText(DateDialog1.this, "Error!!!", Toast.LENGTH_LONG).show();
                    break;
            }

        }
    };
}

