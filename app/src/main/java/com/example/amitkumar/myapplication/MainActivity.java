package com.example.amitkumar.myapplication;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadGrades(View view) {

        Resources res = getResources();
        InputStream is = res.openRawResource(R.raw.employee_information);

        Scanner scanner = new Scanner(is);
        StringBuilder builder = new StringBuilder();

        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }

        parseJson(builder.toString());

}

    private void parseJson(String s) {
        TextView txtDisplay = (TextView) findViewById(R.id.text_display);

        StringBuilder builder = new StringBuilder();
        try {
            JSONObject root = new JSONObject(s);

            JSONObject employee = root.getJSONObject("employee_information");
            builder.append("Name: ")
                    .append(employee.getString("name")).append("\n");

            builder.append("Full Time: ")
                    .append(employee.getBoolean("full-time")).append("\n");

            builder.append("age: ")
                    .append(employee.getString("age")).append("\n\n");

            JSONArray knowledge = employee.getJSONArray("knowledge");

            for(int i =0; i<knowledge.length(); i++) {
                JSONObject obj = knowledge.getJSONObject(i);
                builder.append(obj.getString("Department"))
                        .append(":")
                        .append(obj.getString("skill set"))
                        .append("\n");
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


        txtDisplay.setText(builder.toString());
    }
}

