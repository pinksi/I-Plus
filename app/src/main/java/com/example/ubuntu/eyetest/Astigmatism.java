package com.example.ubuntu.eyetest;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;



public class Astigmatism extends ActionBarActivity {
    private ImageView appImageView;
    private Button appButton;
    private Drawable drawable;
    private Random random;
    private Drawable[] drawables = null; // create a Drawables array that stores location of different images
    private int currentlyShowingQuestion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astigmatism);
        appImageView = (ImageView) findViewById(R.id.aim1);
        appButton = (Button) findViewById(R.id.ab2);//next button

 /*
* Store the location of images inside the array
*/
        drawables = new Drawable[]{
                getResources().getDrawable(R.drawable.lines),
                getResources().getDrawable(R.drawable.curves),
                getResources().getDrawable(R.drawable.circular),
                getResources().getDrawable(R.drawable.spiral)

        };
       showimages();




        appButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//next button
               // showimages();
                //  delay();        ////delayko code chahiyo ????

                        Intent inn = new Intent(Astigmatism.this, Astigma.class);
                        startActivity(inn);
                    }
                });
    }


         public void showimages(){
         random = new Random();
         int randomNumber = random.nextInt(drawables.length);

         drawable = drawables[randomNumber];
         appImageView.setImageDrawable(drawable); // set the image to the ImageView
                }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_astigmatism, menu);
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
