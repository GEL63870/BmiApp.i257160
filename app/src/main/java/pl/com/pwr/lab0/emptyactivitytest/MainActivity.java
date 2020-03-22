package pl.com.pwr.lab0.emptyactivitytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
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

        height = (EditText) findViewById(R.id.heightText);
        mass = (EditText) findViewById(R.id.massText);
        result = (TextView) findViewById(R.id.bmiView);
    }
    public void calculateBMI(View v){
        String heightStr = height.getText().toString();
        String massStr = mass.getText().toString();

            float heightValue = Float.parseFloat(heightStr) / 100;
            float massValue = Float.parseFloat(massStr);
            float bmi = massValue / (heightValue * heightValue);

            displayBMI(bmi);
    }
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
}

// Add Options Menu to switch between Metric and Imperial type of Data
