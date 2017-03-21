package com.example.ubuntu.eyetest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//for right eye.....
public class VisualAcuity extends ActionBarActivity {
    float a[] = {90, 50, 30, 20, 15,15, 10, 10, 8 , 5};//for size of the string
    String[] rightEyeLetterTest = new String[]{"ufn", "tgh", "kpel", "uhnd", "edjkt", "tfudll","erty" , "adgd" ,"nmkn" ,"dfrw"};//displays in textview
    String[] leftEyeLetterTest = new String[]{"qwert","uiop","cxbcn","tytu","ijnk", "powu", "kaxer", "iyht", "plmsf", "aqwer"};//displays in textview

    String whichEyeTest;
    String whatTest;
    String[] numberLeftEye = {"12" ,"85" ,"148" ,"178", "7894", "56432","156" ,"1635","21143","12444"};
    String[] numberRightEye = {"61","37","85","137","1834","4567", "4321", "5476","46464","5547"};
    int count = 0;
    int marks = 0;
    String questionBeingAsked;
    int totalQuestionsInSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_acuity);

        marks = getIntent().getIntExtra("marks", 0);
        whichEyeTest = getIntent().getStringExtra("whichEyeTest");
        whatTest = getIntent().getStringExtra("whatTest");

        Button bn = (Button) findViewById(R.id.bbb2);//next
        bn.setOnClickListener(new View.OnClickListener() {      //this next button is to display another array bb2=next button
            @Override
            public void onClick(View v) {
                checkAnswer();
                if(count == totalQuestionsInSet){
                    Intent intent = new Intent();
                    intent.putExtra("marks", marks);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                textdisplay();
            }
        });


        textdisplay(); //this happens for one time when activity is created

        findViewById(R.id.bbb1).setVisibility(View.GONE); // remove it from xml resouce... not from here.... ma alchi bhako le :)

/*yesma count == 2 vayepchi matrai display vako cha???*/
/*        final EditText et = (EditText) findViewById(R.id.et1);
        String s;// = et.getText().toString();
        Button but = (Button) findViewById(R.id.bbb1);//bb1=done
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ts = et.getText().toString();
                for (i = 0; i < 5; i++) {
                    if (p[i].equals(ts)) {
                        marks = marks + 10;
                    }
                    et.setText("");
                }

            }
        });*/
    }

    private void checkAnswer() {
        final EditText et = (EditText) findViewById(R.id.et1);
        String answer = et.getText().toString().toUpperCase();
        if(questionBeingAsked.toUpperCase().equals(answer)){
            marks += 10;
        }
        et.setText("");
    }

    private String[] getQuestionSet(){
        String[] questionSet = null;
        if(whatTest.equals("letter") && whichEyeTest.equals("leftEye")){
            questionSet = leftEyeLetterTest;
        }
        else if(whatTest.equals("letter") && whichEyeTest.equals("rightEye")){
            questionSet = rightEyeLetterTest;
        }
        else if(whatTest.equals("number") && whichEyeTest.equals("leftEye")){
            questionSet = numberLeftEye;
        }
        else {
            questionSet = numberRightEye;
        }
        return questionSet;
    }

    public void textdisplay() {
        String[] questionSet = getQuestionSet();
        totalQuestionsInSet = questionSet.length;

        // p.length holds the length of array, which you are referring as 5
        if(count == totalQuestionsInSet) // crash bhako chai count++ hudai jada array ma nabhako index lina khojeko le.. p[11] = null so
            return; // this is required to avoid tala ko code execution..

        TextView tv = (TextView) findViewById(R.id.t1);
        questionBeingAsked = questionSet[count];
        tv.setText(questionBeingAsked);
        tv.setTextSize(a[count]);
        count++;
        // make one function do exactly one job... this way your code is more manageable / readable
//        if (count == 5) {
//            startActivity(inn);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visual_acuity, menu);
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

