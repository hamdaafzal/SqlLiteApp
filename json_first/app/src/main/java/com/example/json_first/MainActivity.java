package com.example.json_first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson=new Gson();
/*

        Employee employee=new Employee("hamda",30,"hamda@gmail.com");
        String json = gson.toJson(employee);
*/

        String json= "{\n" +
                "  \"age\": 30,\n" +
                "  \"name\": \"Hamda\",\n" +
                "  \"email\": \"hamda@gmail.com\"\n" +
                "}";
        Employee employee=gson.fromJson(json,Employee.class);
        }
    }

