package com.example.androidportlister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    //This list will keep the list of ports
    private List<String> localPortList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**Called when the user clicks the show open ports button**/
    public void showPorts(View view){
        //First, get the list of open ports
        int portCount=0;
        try {
            File myObj = new File("/proc/net/tcp");
            Scanner myReader = new Scanner(myObj);
            String data, localAddressAndPort, localAddress, localPort ;
            String [] largerTokens, portAndAddressTokens;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                if(portCount>0) {
                    largerTokens = data.split(" ");
                    if(largerTokens.length<6){
                        throw new IllegalArgumentException();
                    }
                    localAddressAndPort = largerTokens[4];
                    portAndAddressTokens = localAddressAndPort.split(":");
                    localPort = portAndAddressTokens[1];
                    localPortList.add(String.valueOf(Integer.parseInt(localPort,16)));
                }
                portCount++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("/proc/net/tcp file cannot be found");
            e.printStackTrace();
        }

        System.out.println(portCount-1);
        for(int i=0; i<localPortList.size();i++){
            System.out.println(localPortList.get(i));
        }

        //Now, start a new activity and send the port list
        Intent intent = new Intent(this, DisplayPortsActivity.class);
        intent.putStringArrayListExtra("portList", (ArrayList<String>) localPortList);
        startActivity(intent);
    }

}