package com.example.ubuntu.eyetest;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ColorBlindness extends ActionBarActivity {
    private ImageView appImageView;
    private Button appButton1;
    private Button appButton2;
    private Drawable drawable;
    private Random random;

    private Drawable[] drawables = null;
    int[] answer = {2, 6, 7, 73, 35, 63};
    private List<Image> data;
    private int currentlyShowingQuestion;
    static int count1 = 0;
    static int count2 = 0;
    static int marks = 0;

    class Image {
        private int answer;
        private Drawable image;

        public Image(int ans, Drawable img) {
            answer = ans;
            image = img;
        }

        public int getAnswer() {
            return answer;
        }

        public Drawable getImage() {
            return image;
        }
    }

    private List<Image> makeImageList(int[] answers, Drawable[] images) {
        if (answers.length != images.length)
            return null;
        List<Image> toReturn = new ArrayList<Image>();
        for (int i = 0; i < answers.length; i++) {
            Image img = new Image(answers[i], images[i]);
            toReturn.add(img);
        }
        return toReturn;
    }

    private List<Integer> alreadyShownImages = new ArrayList<Integer>();

    private void renderNextImage() {
        random = new Random();
        int ran = random.nextInt(drawables.length);
        while (alreadyShownImages.contains(ran) && alreadyShownImages.size() != drawables.length) { //ensures previouly shown images doesn't repeats...also if all images are shown (drawables.length == alreadyShownImages.size() ) there'll be infinite loog
            ran = random.nextInt(drawables.length);
        }
        currentlyShowingQuestion = ran; // this is stored because its needed while checking if answer if correct, in compare()
        alreadyShownImages.add(currentlyShowingQuestion);
        Image question = data.get(currentlyShowingQuestion);
        appImageView.setImageDrawable(question.getImage()); // set the image to the ImageView
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_blindness);

        drawables = new Drawable[]{  // create a Drawables array that stores location of different images
                getResources().getDrawable(R.drawable.aaa),
                getResources().getDrawable(R.drawable.bbb),
                getResources().getDrawable(R.drawable.ccc),
                getResources().getDrawable(R.drawable.ddd),
                getResources().getDrawable(R.drawable.fff),
                getResources().getDrawable(R.drawable.ggg)
        };


        appImageView = (ImageView) findViewById(R.id.cim1);
/*        appButton1 = (Button) findViewById(R.id.cb1);//next button
        appButton1.setVisibility(View.GONE); // @TODO remove it from xml
*/
 /*
* Store the location of images inside the array
*/
        data = makeImageList(answer, drawables); //length of answer and drawable should be same
        if (data == null)
            Log.e("error", "length of answer and drawable should be same");

/*        appButton1.setOnClickListener(new View.OnClickListener() {//appbutton1 = next button
            @Override
            public void onClick(View view) {//next button
                renderNextImage();
            }

        });*/

        appButton2 = (Button) findViewById(R.id.cb2);//done button
        appButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//done  button
                if (compare()) {
                    marks = marks + 10;
                    count1++;
                }
               else {
                    count2++;
                    // wrong answer.... you can show in some textview
                }
                if(alreadyShownImages.size() == drawables.length) { //means all images are served, now time to show result
                    showResult();
                    // marks computation is done above, if compare() returns true...
                }
                renderNextImage(); // show next image even if
            }
        });

        renderNextImage(); // first ma image empty aaucha so load it ownself..
    }

    public boolean compare() {
        EditText number = (EditText) findViewById(R.id.aet);
        //String n1 = number.getText().toString();
        // Below is required because.. Integer.parseInt fails if string is empty causing app to crash
        if(number.getText().toString()==null || number.getText().toString().equals(""))
            return false;
        int n1 = Integer.parseInt(number.getText().toString());
        number.setText("");

        // currentlyShowingQuestion is the index of current question.
        // We stored this index for this purpose in renderNextImage() function
        Image answerToCheck = data.get(currentlyShowingQuestion);
        if (answerToCheck.getAnswer() == n1) // currently showing image index == n1??
            return true;// milyo @TODO
        else
            return false;//milena @TODO
    }




    public void showResult() {
        Intent result = new Intent(this, cbresult.class);
        if (marks >= 30 && marks <= 60) {
            String msg = "Awesome.....your eye has normal vision....:)";
            result.putExtra("message", msg);
            startActivity(result);
        }
        else {
            if (marks > 0 && marks < 30) {
                String msg = "Ooops....this test reveals colorblindness..:(";
                result.putExtra("message", msg);
                startActivity(result);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_color_blindness, menu);
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
