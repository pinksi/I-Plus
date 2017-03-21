package com.example.ubuntu.eyetest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent i = new Intent(this, NewActivity1.class);
        Button b = (Button) findViewById(R.id.b1);          //this button is for visual acuity test
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(i);

            }
        });

        final Intent in = new Intent(this, ColorBlindness.class);
        Button bu = (Button)findViewById(R.id.b2);        //button for colorblindness test
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in);
                Toast toast = Toast.makeText(getApplicationContext(), "Keep ur phone at least one meter away!!!!!!!!       " +
                                "Type the number that you saw in  picture..",
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        final Intent ini = new Intent(this, Astigmatism.class);
        Button but = (Button)findViewById(R.id.b3);        //button for colorblindness test
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ini);
                Toast toast = Toast.makeText(getApplicationContext(),"Observe the picture closely!!!!!!!!",
                       Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        final Intent init = new Intent(this, Maps.class);
        Button butt = (Button)findViewById(R.id.b4);        //button for maps
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(init);
                //Toast toast = Toast.makeText(getApplicationContext(), "Keep ur phone at least one meter away!!!!!!!!",
                //      Toast.LENGTH_LONG);
                //toast.setGravity(Gravity.CENTER, 0, 0);
                //toast.show();
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
