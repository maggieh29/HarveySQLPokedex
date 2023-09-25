package com.example.harveypokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText numInput;
    EditText nameInput;
    EditText speciesInput;
    EditText heightInput;
    EditText weightInput;
    EditText hpInput;
    EditText attackInput;
    EditText defenseInput;

    TextView numTV;
    TextView nameTV;
    TextView specTV;
    TextView genTV;
    TextView heightTV;
    TextView weightTV;
    TextView hpTV;
    TextView attackTV;
    TextView defenseTV;

    Spinner levelInput;
    RadioButton female;
    RadioButton male;
    RadioGroup gender;
    Button resetButton;
    Button submitButton;

    //listeners
    View.OnClickListener reset = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            resetEntries();
        }
    };

    View.OnClickListener submit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            checkEntries();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.linear_layout_main);
        //setContentView(R.layout.table_layout_main);


        numInput = findViewById(R.id.numInput);
        nameInput = findViewById(R.id.nameInput);
        speciesInput = findViewById(R.id.speciesInput);
        heightInput = findViewById(R.id.heightInput);
        weightInput = findViewById(R.id.weightInput);
        hpInput = findViewById(R.id.hpInput);
        attackInput = findViewById(R.id.attackInput);
        defenseInput = findViewById(R.id.defenseInput);
        levelInput = findViewById(R.id.levelSpinner);
        female = findViewById(R.id.female);
        male = findViewById(R.id.male);
        gender = findViewById(R.id.genderGroup);
        numTV = findViewById(R.id.natNumTV);
        nameTV = findViewById(R.id.nameTV);
        specTV = findViewById(R.id.specTV);
        genTV = findViewById(R.id.genTV);
        heightTV = findViewById(R.id.heightTV);
        weightTV = findViewById(R.id.weightTV);
        hpTV = findViewById(R.id.hpTV);
        attackTV = findViewById(R.id.attackTV);
        defenseTV = findViewById(R.id.defTV);


        //buttons and their listeners
        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(reset);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(submit);

        resetEntries();

    }

    public void resetEntries(){
        numInput.setText("896");
        nameInput.setText("Glastrier");
        speciesInput.setText("Wild Horse Pokémon");
        heightInput.setText("2.2");
        weightInput.setText("800.0");
        hpInput.setText("0");
        attackInput.setText("0");
        defenseInput.setText("0");
        gender.clearCheck();
        levelInput.setSelection(0);

    }

    public void checkEntries(){
        boolean allChecksPass = true;
        //reset text color in case of some errors fixed but some not
        nameTV.setTextColor((Color.rgb(0,0,0)));
        numTV.setTextColor((Color.rgb(0,0,0)));
        specTV.setTextColor((Color.rgb(0,0,0)));
        genTV.setTextColor((Color.rgb(0,0,0)));
        heightTV.setTextColor((Color.rgb(0,0,0)));
        weightTV.setTextColor((Color.rgb(0,0,0)));
        hpTV.setTextColor((Color.rgb(0,0,0)));
        attackTV.setTextColor((Color.rgb(0,0,0)));
        defenseTV.setTextColor((Color.rgb(0,0,0)));

        //Must be an int between 0 and 1010
        int natNumber = Integer.parseInt(numInput.getText().toString());
        if(natNumber < 0 || natNumber > 1010){
            numTV.setTextColor((Color.rgb(200,0,0)));
            allChecksPass = false;
            Toast.makeText(this, "National Number must be between 0 and 1010", Toast.LENGTH_LONG).show();
        }

        //The Name must be between 3 and 12 alphabetical characters
        String name = nameInput.getText().toString();
        int charCount = name.length();
        if(charCount < 3 || charCount > 12){
            nameTV.setTextColor((Color.rgb(200,0,0)));
            allChecksPass = false;
            Toast.makeText(this, "Name must be between 3 and 12 characters.", Toast.LENGTH_LONG).show();
        }

        String species = speciesInput.getText().toString();
        int char2 = species.length();
        if(char2 <= 0){
            specTV.setTextColor((Color.rgb(200,0,0)));
            allChecksPass = false;
            Toast.makeText(this, "Please fill out species", Toast.LENGTH_LONG).show();
        }

        if(gender.getCheckedRadioButtonId() == -1){
            genTV.setTextColor((Color.rgb(200,0,0)));
            allChecksPass = false;
            Toast.makeText(this, "Please select a gender.", Toast.LENGTH_LONG).show();
        }

        //Height must be between 0.3 and 19.99
        double heightNum = Double.parseDouble(heightInput.getText().toString());
        if(heightNum < 0.3 || heightNum > 19.99){
            heightTV.setTextColor((Color.rgb(200,0,0)));
            allChecksPass = false;
            Toast.makeText(this, "Height must be between 0.3 and 19.99", Toast.LENGTH_LONG).show();
        }

        //Weight must be between 0.1 and 820.0
        double weightNum = Double.parseDouble(weightInput.getText().toString());
        if(weightNum < 0.1 || weightNum > 820.0){
            weightTV.setTextColor((Color.rgb(200,0,0)));
            allChecksPass = false;
            Toast.makeText(this, "Weight must be between 0.1 and 820.0", Toast.LENGTH_LONG).show();
        }

        //HP must be between 1 and 362
        int hp = Integer.parseInt(hpInput.getText().toString());
        if(hp < 1 || hp > 362){
            hpTV.setTextColor((Color.rgb(200,0,0)));
            allChecksPass = false;
            Toast.makeText(this, "HP must be between 1 and 362", Toast.LENGTH_LONG).show();
        }

        //Attack must be between 5 and 526
        int attack = Integer.parseInt(attackInput.getText().toString());
        if(attack < 5 || attack > 526){
            attackTV.setTextColor((Color.rgb(200,0,0)));
            allChecksPass = false;
            Toast.makeText(this, "Attack must be between 5 and 526.", Toast.LENGTH_LONG).show();
        }

        //Defense must be between 5 and 614
        int defense = Integer.parseInt(defenseInput.getText().toString());
        if(defense < 5 || defense > 614){
            defenseTV.setTextColor((Color.rgb(200,0,0)));
            allChecksPass = false;
            Toast.makeText(this, "Defense must be between 5 and 526.", Toast.LENGTH_LONG).show();
        }


        if(allChecksPass == true){
            nameTV.setTextColor((Color.rgb(0,0,0)));
            numTV.setTextColor((Color.rgb(0,0,0)));
            specTV.setTextColor((Color.rgb(0,0,0)));
            genTV.setTextColor((Color.rgb(0,0,0)));
            heightTV.setTextColor((Color.rgb(0,0,0)));
            weightTV.setTextColor((Color.rgb(0,0,0)));
            hpTV.setTextColor((Color.rgb(0,0,0)));
            attackTV.setTextColor((Color.rgb(0,0,0)));
            defenseTV.setTextColor((Color.rgb(0,0,0)));
            Toast.makeText(this, "Information was stored in the database.", Toast.LENGTH_LONG).show();
        }
    }
}