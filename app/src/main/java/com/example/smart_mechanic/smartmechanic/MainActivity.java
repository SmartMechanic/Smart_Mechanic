package com.example.smart_mechanic.smartmechanic;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


public class MainActivity extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener
{

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);


        //Build the google API for play services
        buildGoogleApiClient();



        final ImageButton next = (ImageButton) findViewById(R.id.imageButton_continue);
        next.setVisibility(View.INVISIBLE);
        final Button buttonTryAnyway = (Button) findViewById(R.id.button_try_anyway);

        //Populate the makes spinner
        Spinner makes_spinner = (Spinner) findViewById(R.id.make_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> makes_adapter = ArrayAdapter.createFromResource(this,
                R.array.makes_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        makes_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Populate the models spinner
        final Spinner models_spinner = (Spinner) findViewById(R.id.model_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> ford_models_adapter = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.models_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        ford_models_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        models_spinner.setAdapter(ford_models_adapter);


        // Apply the adapter to the spinner
        makes_spinner.setAdapter(makes_adapter);
        makes_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //Ford Case
                    case 1:
                        // Create an ArrayAdapter using the string array and a default spinner layout
                        ArrayAdapter<CharSequence> ford_models_adapter = ArrayAdapter.createFromResource(MainActivity.this,
                                R.array.ford_models_array, android.R.layout.simple_spinner_item);
                        // Specify the layout to use when the list of choices appears
                        ford_models_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        // Apply the adapter to the spinner
                        models_spinner.setAdapter(ford_models_adapter);

                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Populate the year spinner
        Spinner years_spinner = (Spinner) findViewById(R.id.year_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> years_adapter = ArrayAdapter.createFromResource(this,
                R.array.years_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        years_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        years_spinner.setAdapter(years_adapter);
        years_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case (1):
                        next.setVisibility(view.VISIBLE);
                        break;

                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final Intent intent = new Intent(this, SoundLocationActivity.class);

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(intent);
            }
        });
        buttonTryAnyway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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

    //Set up the Google Play Services API
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    //Callback method for Google API Client
    @Override
    public void onConnected(Bundle connectionHint) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        Toast.makeText(this, "in onConnected", Toast.LENGTH_LONG);
        if (mLastLocation != null) {

            Toast.makeText(this,String.valueOf(mLastLocation.getLatitude()), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}