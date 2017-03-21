package com.example.ubuntu.eyetest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class NewActivity1 extends ActionBarActivity {//select numbers or letters

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity1);
        //show();
        Button b1 = (Button)findViewById(R.id.letter);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewActivity1.this,NewActivity2.class);
                i.putExtra("whatTest", "letter"); // we pass data between activity through this way
                // in the target activity.. we call getIntent().getStringExtra("whatTest") to get this value
                // based on this value.. whether it is "letter" or "number" we can show different problem sets
                startActivity(i);
            }
        });

        Button b2 = (Button)findViewById(R.id.number);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NewActivity1.this,NewActivity2.class);
                in.putExtra("whatTest", "number");
                startActivity(in);
            }
        });

    }
    /*public void show(){
        TextView p = (TextView)findViewById(R.id.t1);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("message")) {
                String ourMessage= extras.getString("message");
//this tag('message') must be the same as the one we'd used earlier
                p.setText(ourMessage);
            }

        }
*/
    //}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_activity1, menu);
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
