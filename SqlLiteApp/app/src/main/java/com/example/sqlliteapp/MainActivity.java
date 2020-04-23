package com.example.sqlliteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText imageDetailET;
    private ImageView ObjectImageView;
    private static final int PICK_IMAGE_REQUEST=100;
    private Uri imageFilePath;
    DataBaseHandler objectDatabaseHandler;
    private Bitmap iamgeToStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            imageDetailET=findViewById(R.id.ImageNameET);
            ObjectImageView=findViewById(R.id.image);
            objectDatabaseHandler=new DataBaseHandler(this);
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        }
        public void choosrImage(View ObjectView)
        {
            try
            {
                Intent ObjectIntent = new Intent();
                ObjectIntent.setType ("Image/*");
                ObjectIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(ObjectIntent,PICK_IMAGE_REQUEST);

            }
            catch (Exception e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {

            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imageFilePath=data.getData();
                iamgeToStore= MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);
                ObjectImageView.setImageBitmap(iamgeToStore);

            }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void storeImage (View view)
    {
        try {
            if(!imageDetailET.getText().toString().isEmpty() && ObjectImageView.getDrawable()!=null && iamgeToStore!=null)
            {
                objectDatabaseHandler.storeImage(new ModelClass(imageDetailET.getText().toString(),iamgeToStore));
            }
            else
            {
                Toast.makeText(this,"Please select Image Name and Image",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }








}

