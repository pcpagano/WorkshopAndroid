package com.example.workshopclase1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private EditText nombreTextBox;
    private TextView textResponse;
    private Button buttonGo;
    private Button buttonCamera;
    private ImageView avatarImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombreTextBox = findViewById(R.id.nombreTextBox);
        textResponse = findViewById(R.id.textResponseMain);
        textResponse.setText("");
        buttonGo = findViewById(R.id.buttonGo);
        buttonCamera = findViewById(R.id.buttonCamera);
        avatarImage = findViewById(R.id.avatarImage);

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startResponseActivity();
            }
        });
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCamera();
            }
        });
    }

    private void startResponseActivity() {
        Intent intent = new Intent(this, ResponseActivity.class);
        String texto = nombreTextBox.getText().toString();
        intent.putExtra("nombre", texto);
        startActivityForResult(intent, 666);
    }

    private void startCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 666:
                if (resultCode == Activity.RESULT_OK) {
                    String respuesta = data.getExtras().getString("textResponse");
                    textResponse.setText(respuesta);
                } else {
                    textResponse.setText("");
                }
                break;
            case 123:
                if (resultCode == Activity.RESULT_OK) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    avatarImage.setImageBitmap(photo);
                }
                break;
        }
    }
}