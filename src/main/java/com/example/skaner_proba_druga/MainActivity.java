package com.example.skaner_proba_druga;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class MainActivity extends AppCompatActivity {
    Button button_zdjencie, button_galeriaxd;
    TextView textView_data;

    Bitmap bitmap;

    private static final int REQUEST_CAMERA_CODE = 100;

        @Override
        protected void onCreate(Bundle savedInstance) {
            super.onCreate(savedInstance);
            setContentView(R.layout.activity_main);

            button_zdjencie = findViewById(R.id.zrob_zdjencie);
            button_galeriaxd = findViewById(R.id.galeriaxd);
            textView_data = findViewById(R.id.text_data);

            System.out.print("nigger");

            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.CAMERA }, REQUEST_CAMERA_CODE);
            }

            button_zdjencie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int czarnuch = Toast.LENGTH_LONG;
                    Toast.makeText(MainActivity.this, "Nigger", czarnuch).show();

                }
            });


            button_galeriaxd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if (requestCode == ){ TU JEST DO NAPRAWIENIA
        // CropImage.ActivityResult result = CropImage.GetActivityResult(Data)
        // if (resultCode == ResultOK){
        //
        // Uri resultUri = result.GetUri();
        // try{
        // bitmap = MediaStore.Images.Media.getBitmap(this.GetContentResolver(), resultUri);
        //
        // }catch (IOException e) {
        //      e.printStackTrace();
        //  }
        //}
    }
    private void GetTextFromImage(Bitmap bitmap){
        TextRecognizer recognizer = new TextRecognizer.Builder(this).build();
        if(!recognizer.isOperational()) {
            Toast.makeText(MainActivity.this,"MURZYN",Toast.LENGTH_LONG).show();
        }
        else{
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray = recognizer.detect(frame);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0; i<textBlockSparseArray.size();i++){
                TextBlock textblock = textBlockSparseArray.valueAt(i);
                stringBuilder.append(textblock.getValue());
                stringBuilder.append("\n");
            }
            textView_data.setText(stringBuilder.toString());
            button_galeriaxd.setText("zrob kolejne zdjecie");
            button_galeriaxd.setVisibility(View.VISIBLE);



        }
    }

    private void SkopiujDoSchowka(String text){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Skopiowano",text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(MainActivity.this,"Skopiowano do Schowka",Toast.LENGTH_SHORT);

    }
}
