package pl.com.pwr.lab0.emptyactivitytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    private EditText height;
    private EditText mass;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        height = findViewById(R.id.heightText);
        mass = findViewById(R.id.massText);
        result = findViewById(R.id.bmiView);

        Button btn_more_info = findViewById(R.id.btn_more_info);
        btn_more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.wikipedia_bmi_link)));
                startActivity(browserIntent);
            }
        });
    }

    // Be sure that data are correct to calculate BMI

    private boolean isValidInput(EditText editText) {
        return getTextAsDouble(editText) > 0;
    }
    private double getTextAsDouble(EditText editText) {
        String input = editText.getText().toString().replace(',', '.');
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }


    // Function to Calculate BMI

    public void calculateBMI(View v) {
        if (isValidInput(height) && isValidInput(mass)) {
            result.setText(getString(R.string.bmi));
            String heightStr = height.getText().toString();
            String massStr = mass.getText().toString();

            float heightValue = Float.parseFloat(heightStr) / 100;
            float massValue = Float.parseFloat(massStr);
            float bmi = massValue / (heightValue * heightValue);

            displayBMI(bmi);
        } else {
            result.setText("");
            result.setText("");
        }
    }
    // Execute BMI function and return the correct Label with according Colors
    private void displayBMI(float bmi){
        String bmiLabel = "";

        if (Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.Underweight);
            result.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.UnderWeightColor));
        } else if  (Float.compare(bmi, 18.5f) > 0 && Float.compare(bmi, 24.9f) <= 0) {
            bmiLabel = getString(R.string.Normal);
            result.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.NormalWeightColor));
        }else if  (Float.compare(bmi, 24.9f) > 0 && Float.compare(bmi, 29.9f) <= 0) {
            bmiLabel = getString(R.string.Overweight);
            result.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.OverWeightColor));
        }else if  (Float.compare(bmi, 29.9f) > 0 && Float.compare(bmi, 34.9f) <= 0) {
            bmiLabel = getString(R.string.Obesity_class_1);
            result.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.Obesity_1_Color));
        }else if  (Float.compare(bmi, 34.9f) > 0 && Float.compare(bmi, 39.9f) <= 0) {
            bmiLabel = getString(R.string.Obesity_class_2);
            result.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.Obesity_2_Color));
        }else if  (Float.compare(bmi, 40f) >= 0) {
            bmiLabel = getString(R.string.Obesity_class_3);
            result.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.Obesity_3_Color));
        }
        bmiLabel = bmi + "\n\n" + bmiLabel;
        result.setText(bmiLabel);

    }

    // Being able to switch between Metric and Imperial parameters

    SharedPreferences sharedPreferences;
    private final static String PREF_IS_METRIC = "system_of_unit";

    // Test if data is Metric or not
    private boolean isMetric() {
        boolean defaultToMetric = getString(R.string.default_unit).equals(getString(R.string.metric));
        return sharedPreferences.getBoolean(PREF_IS_METRIC, defaultToMetric);
    }




    public void setSystemOfUnits(View v) {
        sharedPreferences.edit().putBoolean(PREF_IS_METRIC, v.getId() == R.id.btn_metric).apply();
        setSystemOfUnits();
    }

    private void setSystemOfUnits() {
        RadioButton btn_metric = findViewById(R.id.btn_metric);
        RadioButton btn_imperial = findViewById(R.id.btn_imperial);
        btn_metric.setChecked(isMetric());
        btn_imperial.setChecked(!isMetric());

        height = findViewById(R.id.heightText);
        mass = findViewById(R.id.massText);
        height.setHint(isMetric() ? getString(R.string.mass_metric) : getString(R.string.mass_imperial));
        mass.setHint(isMetric() ? getString(R.string.height_metric) : getString(R.string.height_imperial));
    }
}


