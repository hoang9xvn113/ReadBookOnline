package com.example.readbookonline.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.readbookonline.R;

public class SettingActivity extends AppCompatActivity {

    int text_color;
    int background_color;
    int text_size;
    int book_id, max_page, book_page;
    String account_id;
    EditText edt_textS;
    Spinner spinner;
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mapping();
        loadingData();
        control();
    }

    void mapping(){
        edt_textS = findViewById(R.id.text_size);
        spinner = findViewById(R.id.spinner);
        btn = findViewById(R.id.btn);

        Intent intent = getIntent();
        book_id = intent.getIntExtra("book_id",1);
        max_page = intent.getIntExtra("book_amount", 1);
        book_page = intent.getIntExtra("book_page", 1);
        account_id = intent.getStringExtra("account_id");
        text_size = intent.getIntExtra("text_size", 18);
        background_color = intent.getIntExtra("background_color", R.color.dark_gray);
        text_color = intent.getIntExtra("text_color", R.color.dark);
    }

    void loadingData(){
        String[] items = new String[]{"white", "dark"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

        edt_textS.setText((String.valueOf(text_size)));
    }

    void control(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ContentActivity.class);
                if (!edt_textS.getText().toString().equals("")){
                    text_size = Integer.parseInt(edt_textS.getText().toString());
                }

                background_color = getBackground_color(spinner.getSelectedItem().toString());
                text_color = getTextColor(spinner.getSelectedItem().toString());
                intent.putExtra("book_id", book_id);
                intent.putExtra("book_amount", max_page);
                intent.putExtra("account_id", account_id);
                intent.putExtra("background_color", background_color);
                intent.putExtra("text_color", text_color);
                intent.putExtra("text_size", text_size);
                intent.putExtra("book_page", book_page);
                startActivity(intent);
            }
        });
    }


    int getBackground_color(String background_color){
        int backgroundC = this.background_color;
        if (background_color == "white"){
            backgroundC = R.color.white;
        } else if(background_color == "dark") {
            backgroundC = R.color.dark;
        }
        return backgroundC;
    }

    int getTextColor(String background_color){
        int textC = text_color;
        if (background_color == "white"){
            textC = R.color.dark;
        } else if(background_color == "dark") {
            textC = R.color.white;
        }
        return textC;
    }

}
