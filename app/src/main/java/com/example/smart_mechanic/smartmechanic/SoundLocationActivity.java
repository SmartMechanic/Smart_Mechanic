package com.example.smart_mechanic.smartmechanic;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;


public class SoundLocationActivity extends ActionBarActivity {


    Complex[] fftResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_location_activity);



        //Set click listener for back button
        ImageButton back = (ImageButton)findViewById(R.id.imageButton_back);
        final Intent intent = new Intent(this, MainActivity.class);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        //Set click listener for microphone button
        ImageButton micButton = (ImageButton)findViewById(R.id.imageButton_start);
        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //Start the audio record
                AudioRecordTest audioRecordTest = new AudioRecordTest();
                audioRecordTest.startRecording();

                //Start recording PCM from the mic
                //PCMRecord pcmRecord = new PCMRecord();
               // pcmRecord.StartRecording();

                //Show progress bar for 5000 milliseconds while recording
                //Pass audioRecordTest to stop recording after post delayed 5 secs
                ShowProgressBar(5000, audioRecordTest);





            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sound_location_avtivity, menu);
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


    //FUNCTION TO SHOW A PROGRESS BAR DURING RECORDING
    void ShowProgressBar(int milliseconds, final AudioRecordTest audioRecord) {
        final ProgressBar progressBar = new ProgressBar(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final AlertDialog alert = builder.create();
        alert.setTitle("Recording in progress...");
        alert.setView(progressBar);
        alert.show();
        Handler handler = new Handler();
        final Intent intent = new Intent(this,SuccessActivity.class);
        final Intent recordIntent = new Intent(this, AudioRecordTest.class);



        //Delays the handler  for X milliseconds and stop the recording
        handler.postDelayed(new Runnable() {
            public void run() {

                //TODO: Validate successful capture
                //TODO: Add Delay for DSP
                //TODO: Determine need to write results to file
                startActivity(intent);
                audioRecord.stopRecording();


                //Stop the recoding after the handler
                //pcmRecord.StopRecording();
                //pcmRecord.ComplexFFT(fftResult);


            }
        }, milliseconds);

    }

}
