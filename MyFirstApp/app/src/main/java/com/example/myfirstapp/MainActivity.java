package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by Prathamesh 1 on 09-05-2017.
 */

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab1,fab2,miniFab1,miniFab2,miniFab3;
    ArrayList<String> customersList;
    ListView listView;
//    ArrayAdapter<String> adapter;
    Context ctx = this;
    ListViewAdapter adapter;
    Animation miniFabAnim;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        listView = (ListView) findViewById(R.id.listView);
        customersList = new ArrayList<>();
        customersList.add("Sanjay");
        customersList.add("Prayag");
        customersList.add("Krishna");
        customersList.add("Suresh");
        customersList.add("Aaditya");
        customersList.add("Chandan");

//        adapter = new ListViewAdapter(this,R.layout.list_item,customersList);
        listView.setAdapter(adapter);

        miniFab1 = (FloatingActionButton) findViewById(R.id.miniFab1);
        miniFab2 = (FloatingActionButton) findViewById(R.id.miniFab2);
        miniFab3 = (FloatingActionButton) findViewById(R.id.miniFab3);

        miniFab1.setVisibility(View.INVISIBLE);
        miniFab2.setVisibility(View.INVISIBLE);
        miniFab3.setVisibility(View.INVISIBLE);

        miniFab1.setEnabled(false);
        miniFab2.setEnabled(false);
        miniFab3.setEnabled(false);

        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation fab1Anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
                fab1.startAnimation(fab1Anim);
                fab1Anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) { }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        startActivity(new Intent(MainActivity.this,Add_Page1.class));
                        overridePendingTransition(R.anim.bottom_to_top,R.anim.slide_out_right);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) { }
                });
            }
        });


        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miniFabAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mini_fab1_anim);
                miniFab1.startAnimation(miniFabAnim);
                miniFabAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mini_fab2_anim);
                miniFab2.startAnimation(miniFabAnim);
                miniFabAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mini_fab3_anim);
                miniFab3.startAnimation(miniFabAnim);
                miniFab1.setVisibility(View.VISIBLE);
                miniFab2.setVisibility(View.VISIBLE);
                miniFab3.setVisibility(View.VISIBLE);
                miniFab1.setEnabled(true);
                miniFab2.setEnabled(true);
                miniFab3.setEnabled(true);
            }
        });


    }

}
