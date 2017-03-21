package com.example.ubuntu.eyetest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class NewActivity2 extends ActionBarActivity {

    private static final int VISUAL_ACQUITY_LETTER_LEFT = 100; // these are jpt numbers..
    private static final int VISUAL_ACQUITY_LETTER_RIGHT = 101;
    private static final int VISUAL_ACQUITY_NUMBER_LEFT = 102;
    private static final int VISUAL_ACQUITY_NUMBER_RIGHT = 103;

    private String whatTest;
    private int marks = 0;

    private TextView instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity2);
        instruction = (TextView) findViewById(R.id.instruction);
        instruction.setText("Close your left eye and hold your mobile at least one meter away....");

        whatTest = getIntent().getStringExtra("whatTest");

        Button bu = (Button)findViewById(R.id.bb1);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(whatTest.equals("letter"))
                    startLetterTest();
                else if(whatTest.equals("number"))
                    startNumberTest();
            }
        });
    }

    private boolean isThisFirstEyeTest = true;
    private void startLetterTest() {
        if(isThisFirstEyeTest)
            startLeftEyeLetterTest(0);
        else { // this will be false .. see onActivityResult below... you should care of resultCode
            // startActivityForResult submits resultCode to target activity,  on which it will be expecting result..see onActivityResult.
            // the resultCode used during startActivityForResult should be same as it gets on onActivityResult after the target activity finishes
            startRightEyeLetterTest(marks);
        }
    }
    private void startNumberTest() {
        if(isThisFirstEyeTest)
            startLeftEyeNumberTest(0);
        else
            startRightEyeNumberTest(marks);
    }


    private void startLeftEyeNumberTest(int marks) {
        Intent q = new Intent(NewActivity2.this,VisualAcuity.class);
        q.putExtra("marks", marks);
        q.putExtra("whatTest", "number");
        q.putExtra("whichEyeTest", "leftEye");
        startActivityForResult(q, VISUAL_ACQUITY_NUMBER_LEFT);
    }

    private void startRightEyeNumberTest(int marks) {
        Intent q = new Intent(NewActivity2.this,VisualAcuity.class);
        q.putExtra("marks", marks);
        q.putExtra("whatTest", "number");
        q.putExtra("whichEyeTest", "rightEye");
        startActivityForResult(q, VISUAL_ACQUITY_NUMBER_RIGHT);
    }

    private void startLeftEyeLetterTest(int marks) {
        Intent q = new Intent(NewActivity2.this,VisualAcuity.class);
        q.putExtra("marks", marks);
        q.putExtra("whatTest", "letter");
        q.putExtra("whichEyeTest", "leftEye");
        startActivityForResult(q, VISUAL_ACQUITY_LETTER_LEFT);
    }

    private void startRightEyeLetterTest(int marks) {
        Intent q = new Intent(NewActivity2.this,VisualAcuity.class);
        q.putExtra("marks", marks);
        q.putExtra("whatTest", "letter");
        q.putExtra("whichEyeTest", "rightEye");
        startActivityForResult(q, VISUAL_ACQUITY_LETTER_RIGHT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == VISUAL_ACQUITY_LETTER_LEFT){
                marks = data.getIntExtra("marks", 0);
                instruction.setText("Close your right eye and hold your mobile at least one meter away....");
                isThisFirstEyeTest = false; // to start next eye test after user clicks start
            }
            else if (requestCode == VISUAL_ACQUITY_LETTER_RIGHT){
                marks = data.getIntExtra("marks", 0);
                showMarks(marks);
            }
            else if(requestCode == VISUAL_ACQUITY_NUMBER_LEFT){
                marks = data.getIntExtra("marks", 0);
                instruction.setText("Close your right eye and hold your mobile at least one meter away....");
                isThisFirstEyeTest = false; // to start next eye test after user clicks start
            }
            else if (requestCode == VISUAL_ACQUITY_NUMBER_RIGHT){
                marks = data.getIntExtra("marks", 0);
                showMarks(marks);
            }

            // what happens here is...first left eye ko test and secondly right eye ko test
            // left eye ko test sakye pachi that activity will return marks obtained for left eye.. thats why startActivityForResult() is done.. it ensures result is returned here
            // we pass that marks to right eye ko test through intent.putExtra()... in target activity we get this intent data as getIntent().getIntExtra()..

        }
    }

    private void showMarks(int marks) {
        Intent result = new Intent(this, vaResult.class);
        //float f = (marks / 160)*100;

        if (marks >= 180) {
            //String msg = "Your Visual acuity accuracy is = "  + f;
            String msg = "Awesome.....your visual acuity is 90% accurate :)";
            result.putExtra("message", msg);
            startActivity(result);
        }
        else if  (marks <180 && marks >= 150) {
                //String msg = "Your Visual acuity accuracy is = "  + f;
                String msg = "Good...your visual acuity is 75% accurate :)";
                result.putExtra("message", msg);
                startActivity(result);
            }

        else if (marks <150 && marks >= 130) {
                //String msg = "Your Visual acuity accuracy is = "  + f;
                String msg = "Your visual acuity is 60% accurate :)";
                result.putExtra("message", msg);
                startActivity(result);
            }
        else if (marks <130) {
            //String msg = "Your Visual acuity accuracy is = "  + f;
            String msg = "Oops your visual acuity is below 50% ";
            result.putExtra("message", msg);
            startActivity(result);
        }
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_activity2, menu);
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
