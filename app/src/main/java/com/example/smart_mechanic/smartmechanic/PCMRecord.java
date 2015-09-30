package com.example.smart_mechanic.smartmechanic;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Phillip on 9/29/2015.
 */
public class PCMRecord extends Activity {


    public AudioRecord audioRecord;
    public int result = 0;
    public int size = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
    short audioData[] = new short[size];

    File file = new File("recordResult.dat");







    FileOutputStream fos = null;


    public void StartRecording() {

        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 44100,
                AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT,
                size);

        audioRecord.startRecording();
        Thread thread = new Thread() {

            @Override

            public void run() {
                while (audioRecord.getRecordingState() != AudioRecord.RECORDSTATE_STOPPED) {
                    result = audioRecord.read(audioData, 0, size);
                    if (result == AudioRecord.ERROR_INVALID_OPERATION) {
                       break;
                    } else if (result == AudioRecord.ERROR_BAD_VALUE) {
                       break;
                    }
                }
            }
        };
        thread.start();



    }







    public void WriteFile() throws IOException {

      // BufferedWriter writer = new BufferedWriter(new FileWriter("recordResult.dat"));

        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "recordResult.bin");
        if(file.exists())
        {
            file.delete();
        }

        DataOutputStream fo = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));


        }



    public void StopRecording() {
        audioRecord.stop();
        audioRecord.release();




    }

    public Complex[] ComplexFFT(Complex[] fftResult){

        //Write data to complex array
        Complex[] complexData = new Complex[audioData.length];
        for (int i = 0; i < complexData.length; i++) {
            complexData[i] = new Complex(audioData[i], 0);
        }

         fftResult = FFT.fft(complexData);

        for(int i = 0; i<fftResult.length; i++) {
            Log.d("FFT", fftResult[i].toString());
        }
        return fftResult;

    }




}
