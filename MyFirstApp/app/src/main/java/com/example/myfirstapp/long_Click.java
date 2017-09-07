package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prathamesh 1 on 16-05-2017.
 */

public class long_Click extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> stringArrayList;
    private ListViewAdapter adapter;
    FloatingActionButton fab,fabEdit,fab2,miniFab1,miniFab2,miniFab3;
    private boolean semaphore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.long_click_layout);
        listView = (ListView) findViewById(R.id.list_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabEdit = (FloatingActionButton) findViewById(R.id.fabEdit);
        fabEdit.setVisibility(View.INVISIBLE);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);

        setData();
        adapter = new ListViewAdapter(this, R.layout.list_item, stringArrayList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!semaphore) {
//                    view.setSelected(false);
//                    TextView tv = (TextView)view.findViewById(R.id.textView);
//                    tv.setSelected(false);
//                    listView.setItemChecked(position,false);
                    Animation fabEditAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.drop_down);
                    fabEdit.startAnimation(fabEditAnim);
                    fabEditAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            fabEdit.setVisibility(View.INVISIBLE);
                            fab.setVisibility(View.VISIBLE);
                            Animation fabAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.drop_up);
                            fab.startAnimation(fabAnim);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    semaphore=true;
                }
//                Toast.makeText(long_Click.this, (String)parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(semaphore) {
//                    view.setSelected(true);

//                    listView.setItemChecked(position,true);
                    Animation fabAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.drop_down);
                    fab.startAnimation(fabAnim);
                    fabAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            fab.setVisibility(View.INVISIBLE);
                            fabEdit.setVisibility(View.VISIBLE);
                            Animation fabEditAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.drop_up);
                            fabEdit.startAnimation(fabEditAnim);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    semaphore=false;
                }
                return true;
            }
        });

//        listView.set

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation fabAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
                fab.startAnimation(fabAnim);
                fabAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) { }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        startActivity(new Intent(long_Click.this,Add_Page1.class));
                        overridePendingTransition(R.anim.bottom_to_top,R.anim.slide_out_right);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) { }
                });
            }
        });

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(long_Click.this,"fabEdit Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData() {
        stringArrayList = new ArrayList<>();
        stringArrayList.add("Aaditya Yadav");
        stringArrayList.add("Suresh Vishwakarma");
        stringArrayList.add("Chandan Singh");
        stringArrayList.add("Prathamesh Mestry");
        stringArrayList.add("Aakash shukla");
        stringArrayList.add("Rohan Mehta");
        stringArrayList.add("Aryan");
        stringArrayList.add("Harish G");
        stringArrayList.add("Priyesh Pawar");
        stringArrayList.add("Narendra Choudhary");
        stringArrayList.add("Asif Shaikh");
        stringArrayList.add("Aalim Daar");
        stringArrayList.add("Penn & Teller");
        stringArrayList.add("Ellen Degeneres");
    }

}
