package com.appinventory.bmicalculator.bmicalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.editText)
    EditText weightEdtTxt;
    @BindView(R.id.editText2)
    EditText heightEdtTxt;
    @BindView(R.id.spinner)
    Spinner weightSpinner;
    @BindView(R.id.spinner2)
    Spinner heightSpinner;
    String weight, height, heightUnite, weighUnite;
    Button calculateBtn;
    float calculatedValue, h, w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        calculateBtn = (Button) findViewById(R.id.btn_calculate);
        calculateBtn.setOnClickListener((View v) -> {

            weight = weightEdtTxt.getText().toString();
            height = heightEdtTxt.getText().toString();

            weighUnite = weightSpinner.getSelectedItem().toString();
            heightUnite = heightSpinner.getSelectedItem().toString();

            try {
                h = Float.parseFloat(height);
                w = Float.parseFloat(weight);
            } catch (NumberFormatException e) {
                Log.e("Exception msg", e.getMessage());
            }
            Toast.makeText(MainActivity.this, "you're in", Toast.LENGTH_SHORT).show();

            if (heightUnite.equalsIgnoreCase("cm") && weighUnite.equalsIgnoreCase("kg")) {
                calculatedValue = calculatrBMI(h, w);
                showDialogue(calculatrBMI(h, w));
            } else if (heightUnite.equalsIgnoreCase("m") && weighUnite.equalsIgnoreCase("kg")) {
                calculatedValue = calculatrBMI(h, w);
                showDialogue(calculatrBMI1(h, w));

            } else if (heightUnite.equalsIgnoreCase("inch") && weighUnite.equalsIgnoreCase("lb")) {
                showDialogue(calculatrBMI2(h, w));
                calculatedValue = calculatrBMI(h, w);

            } else if (heightUnite.isEmpty() | weighUnite.isEmpty()) {
                creatAlertDialogue("Please make sure you entered weight and height values", "Undefined Action");
            } else {
                creatAlertDialogue("Please make sure you chose the right weight and height units", "Undefined Action");

            }
        });
    }

    private float calculatrBMI(float height, float weight) {
        return (weight / (height * height)) * 10000;
    }

    private float calculatrBMI1(float height, float weight) {
        return (weight / (height * height));
    }

    private float calculatrBMI2(float height, float weight) {
        return (weight / (height * height)) * 703;
    }


    private void showDialogue(float calculatedValue) {

        if (calculatedValue < 18.5) {
            if (calculatedValue < 10) {
                creatAlertDialogue("Results are not logical!" + "\n" +
                        "Please make sure you entered your measurements in the place specified, and that you chose the right measuring unite", "Error!");
            } else {
                creatAlertDialogue("Your BMI is:" + calculatedValue + "\n" + "Your are Underweight :(", "Underweight");

            }
        } else if (calculatedValue == 18.5 | calculatedValue < 25) {
            creatAlertDialogue("Your BMI is:" + calculatedValue + "\n" + "You have a perfect BMI :)", "Ideal");

        } else if (calculatedValue == 25) {
            creatAlertDialogue("Your BMI is:" + calculatedValue + "\n" + "You are in the edge of having normal BMI, Normal BMI is between 18 and 25 :)", "That's Okay");

        } else if (calculatedValue > 25 | calculatedValue < 30) {
            creatAlertDialogue("Your BMI is:" + calculatedValue + "\n" + "You are Overweight :/", "Overweight");

        } else if (calculatedValue > 30) {
            creatAlertDialogue("Your BMI is:" + calculatedValue + "\n" + "You are Obese, Please follow a healthy life style", "Danger!");

        } else if (calculatedValue > 80) {
            creatAlertDialogue("Your BMI is:" + calculatedValue + "\n" + "+80 BMI, are you sure you entered values and units right", "Illogical!");

        }

    }


    private void creatAlertDialogue(String Message, String title/*int img*/) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);

        alertDialog.setMessage(Message);
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok",(DialogInterface dialog, int which) ->{
                alertDialog.dismiss();
        });
        alertDialog.show();

    }
}
