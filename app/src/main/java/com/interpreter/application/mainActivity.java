package com.interpreter.application;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.io.InputStreamReader;

public class mainActivity extends AppCompatActivity {
     private RecyclerView recyclerView;
     private RecyclerView.LayoutManager layoutManager;

     private myAdapter mAdapter;
     private EditText editText;
     private Button translator;

     @Override
    protected void onCreate(Bundle savedInstantState){
         super.onCreate(savedInstantState);
         setContentView(R.layout.activity_main);

         recyclerView = findViewById(R.id.result);
         recyclerView.setHasFixedSize(true);
         layoutManager = new LinearLayoutManager(this);
         recyclerView.setLayoutManager(layoutManager);

         translator = findViewById(R.id.btn_translate);
         editText = findViewById(R.id.editText);

         mAdapter = new myAdapter();
         mAdapter.setMyDataSet(new String[]{"Enter the word to translate"});
         recyclerView.setAdapter(mAdapter);


         translator.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 mAdapter.setMyDataSet(
                         translations(editText.getText().toString()));
                 mAdapter.notifyMyDataSetChanged();

             }
         });
     }

    String[] translations(String word){
        ArrayList<String> swahili_words = new ArrayList<>();

        try (
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(getAssets().open("mydata.csv")))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].equals(word)){
                    swahili_words.add(values[0]);
                }

            }

            String[] array = new String[swahili_words.size()];
            for (int x = 0; x < swahili_words.size(); x++) {
                array[x] = swahili_words.get(x);
            }

            if (array.length == 0){
                array = new String[]{"Word cannot be found"};
            }

            return array;

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new String[]{"Empty String"};

    }

}
