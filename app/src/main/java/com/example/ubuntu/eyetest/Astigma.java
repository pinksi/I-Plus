package com.example.ubuntu.eyetest;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;


public class Astigma extends ActionBarActivity {
    static int marks = 0;
    static int count1 = 0;
    static int count2 = 0;
    int totalcount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astigma);



        final Intent Astigmatism = new Intent(this,Astigmatism.class);
        Button but1 = (Button)findViewById(R.id.bt1);//correct answer button
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Astigmatism);
                marks = marks + 25;
                count1++;
               /* if (count1 == 4) {
                    showResult();
                    count1 = 0;
                    marks = 0;

                }*/
            }

        });

        Button but2 = (Button)findViewById(R.id.bt2);//wrong answer button
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Astigmatism);
                count2++;
                /*if(count == 4) {
                    showResult();
                    count = 0;
                    marks = 0;
               }*/
            }
        });
        totalcount = count1 + count2;
        if(totalcount == 4){
            showResult();
            count1 = 0;
            count2 = 0;
            marks = 0;
        }


    }

    public void showResult() {
        Intent result = new Intent(this, AstigmaResult.class);
        if (marks == 100) {
            String msg = "Awesome.....your eye has normal vision....:)";
            result.putExtra("message", msg);
            startActivity(result);
        }
        else {
            if (marks < 100) {
                String msg = "Ooops! This test reveals astigmatism..you should consult an eye specialist";
                result.putExtra("message", msg);
                startActivity(result);
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_astigma, menu);
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
