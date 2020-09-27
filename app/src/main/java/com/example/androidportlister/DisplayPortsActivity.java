package com.example.androidportlister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class DisplayPortsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ports);

        Intent intent = getIntent();

        //I get the port list from the intent, and then create a textView for every port to be able to show them on the screen
        List<String> localPortList = intent.getStringArrayListExtra("portList");

        ListView listViewForPorts = findViewById(R.id.listViewPorts);

        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, localPortList);

        listViewForPorts.setAdapter(veriAdaptoru);


    }
}