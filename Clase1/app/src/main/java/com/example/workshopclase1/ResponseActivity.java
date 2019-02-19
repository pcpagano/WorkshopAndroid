package com.example.workshopclase1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResponseActivity extends AppCompatActivity {

    private TextView textSaludo;
    private EditText textRespuesta;

    private Button buttonAccept;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);

        textSaludo = (TextView) findViewById(R.id.textSaludo);
        textRespuesta = (EditText) findViewById(R.id.textRespuesta);
        buttonAccept = (Button) findViewById(R.id.buttonAccept);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);
        Intent intent = getIntent();

        String saludo = textSaludo.getText().toString();
        saludo = String.format(saludo, intent.getStringExtra("nombre"));

        textSaludo.setText(saludo);

        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToPreviousActivity(Activity.RESULT_OK);
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToPreviousActivity(Activity.RESULT_CANCELED);
            }
        });
    }

    private void returnToPreviousActivity(int result) {
        Intent intent = new Intent();
        if (result == Activity.RESULT_OK) {
            intent.putExtra("textResponse", textRespuesta.getText().toString());
        }
        setResult(result, intent);
        finish();
    }
}
