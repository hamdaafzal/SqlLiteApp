package com.example.sqlliteapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {
    Context context;
    private static String DATABASE_NAME = "mydb.db";

    private static int DATABASE_VERSION = 1;
    private static String createTableQuery = "create table imageInfo (imageName TEXT"
            + ", image BLOB)";

    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInBytes;


    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(createTableQuery);
            Toast.makeText(context, "Table Created Successfully", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void storeImage(ModelClass ObjectModelClass) {
        try {
            SQLiteDatabase objectSQliteDatabase = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = ObjectModelClass.getImage();

            objectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);

            imageInBytes = objectByteArrayOutputStream.toByteArray();
            ContentValues objectContentValue = new ContentValues();

            objectContentValue.put("imageName", ObjectModelClass.getImageName());
            objectContentValue.put("image", imageInBytes);

            long checkIfQueryRuns = objectSQliteDatabase.insert("imageInfo", null, objectContentValue);
            if (checkIfQueryRuns != -1) {
                Toast.makeText(context, "Data Added", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Fails to Add Data", Toast.LENGTH_LONG).show();
                objectSQliteDatabase.close();
            }

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList<ModelClass> getAllImageData() {


        try {

            SQLiteDatabase objectSqLiteDatabase = this.getReadableDatabase();
            ArrayList<ModelClass> objectModelClass = new ArrayList<>();

            Cursor objectCursor = objectSqLiteDatabase.rawQuery("select * from imageInfo", null);
            if (objectCursor.moveToNext()) {
                while (objectCursor.moveToNext()) {
                    String nameofImage = objectCursor.getString(0);
                    byte[] imageByte = objectCursor.getBlob(1);
                    Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                    objectModelClass.add(new ModelClass(nameofImage, objectBitmap));

                }
                return objectModelClass;
            } else {
                Toast.makeText(context, "No Values In DataBase", Toast.LENGTH_SHORT).show();
                return null;
            }


        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }


    }
}
