package com.chipone.bjfu.listviewtest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private Toast mToastToShow = null;
    private ArrayList<String> mArrayList = new ArrayList<>();
    ArrayAdapter<String> mAdapter = null;

    private String[] data = {
            "REE",
            "TEE",
            "123",
            "abc",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Collections.addAll(mArrayList, data);//prepare data
        mAdapter = new ArrayAdapter<>(
                MainActivity.this, android.R.layout.simple_list_item_1, mArrayList
        );
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ListView listView = (ListView)findViewById(R.id.listView);
                listView.setAdapter(mAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String string = "You clicked the item :"+((TextView)view).getText();
                        if (mToastToShow == null) {
                            mToastToShow = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT);
                        }
                        else {
                            mToastToShow.setText(string);
                        }
                        //测试网上的例程而已。
                        view.setBackgroundColor(Color.LTGRAY);
                        int i = 1;
                        mArrayList.add(string + i++);
                        //如果是直接修改了数据源，那么每次修改之后必须进行一次notifyDataSetChanged
                        //不然等到下一次点击listview的时候就会报错，报错内容为数据非法
                        //修改之后数据就OK了。
                        mAdapter.notifyDataSetChanged();
                        i = 1;
                        for (String tmp : mArrayList){
                            Log.e("mArrayList",tmp+"----"+i++);
                        }
                        //Toast.makeText(getApplicationContext(), "You clicked the item :"+((TextView)view).getText(), Toast.LENGTH_SHORT).show();
                        mToastToShow.show();
                    }
                });

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
