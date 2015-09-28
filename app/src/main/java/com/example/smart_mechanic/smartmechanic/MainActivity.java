package com.example.smart_mechanic.smartmechanic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        //Variable to hide next button
        boolean hide = true;
        final ImageButton next = (ImageButton) findViewById(R.id.imageButton_continue);
        next.setVisibility(View.INVISIBLE);


        //Populate the makes spinner
        Spinner makes_spinner = (Spinner) findViewById(R.id.make_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> makes_adapter = ArrayAdapter.createFromResource(this,
                R.array.makes_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        makes_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        makes_spinner.setAdapter(makes_adapter);


        //Populate the models spinner
        Spinner models_spinner = (Spinner) findViewById(R.id.model_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> models_adapter = ArrayAdapter.createFromResource(this,
                R.array.models_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
         models_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        models_spinner.setAdapter(models_adapter);



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
                switch(position) {

                    case (1):
                        next.setVisibility(view.VISIBLE);
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
}
