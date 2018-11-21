package com.example.barba.sharedpreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String PREFERENCES_NAME = "myPreferences";
    private static final String PREFERENCES_TEXT_FIELD = "textField";
    private static final String PREFERENCES_TEXT_FIELD2 = "textField2";
    private EditText etToSave;
    private EditText etToSave2;
    private Button btnSave;

    private SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        etToSave = (EditText) findViewById(R.id.etToSave);
        etToSave2 = (EditText) findViewById(R.id.etToSave2);
        btnSave = (Button) findViewById(R.id.btnSave);
        initButtonOnClick();
        restoreData();
    }

    private void initButtonOnClick() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveData();
                showToast("Data saved");
            }
        });
    }

    private void saveData() {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        String editTextData = etToSave.getText().toString();
        String editTextData2 = etToSave2.getText().toString();
        preferencesEditor.putString(PREFERENCES_TEXT_FIELD, editTextData);
        //preferencesEditor.putString(PREFERENCES_TEXT_FIELD2, editTextData2);
        preferencesEditor.commit();
    }

    private void restoreData() {
        String textFromPreferences = preferences.getString(PREFERENCES_TEXT_FIELD, "");
        etToSave.setText(textFromPreferences);

        String textFromPreferences2 = preferences.getString(PREFERENCES_TEXT_FIELD2, "");
        if(textFromPreferences2=="")
            etToSave2.setText("default text");
        else
            etToSave2.setText(textFromPreferences2);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}