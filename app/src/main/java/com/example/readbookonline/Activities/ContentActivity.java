package com.example.readbookonline.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.readbookonline.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;



public class ContentActivity extends AppCompatActivity {

    TextView book_content;
    int book_id;
    int book_chapter;
    RequestQueue requestQueue,requestSaveChapterCurr;
    BottomNavigationView bottom_nav;
    int max_chapter, min_chapter=1;
    String account_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        mapping();
        loadingData(book_id, book_chapter);
        control();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, InfoBookActivity.class);
        intent.putExtra("book_id", book_id);
        intent.putExtra("account_id", account_id);
        startActivity(intent);
    }

    void loadingData(int book_id, int book_chapter){
        String url = "https://hochoihamhoc.000webhostapp.com/getContent.php?book_id=+"+book_id + "&chapter="+book_chapter;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    book_content.setText(response.getString("content"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);
    }

    void mapping(){
        //Ánh xạ các biến trong layout
        book_content = findViewById(R.id.book_content);
        bottom_nav = findViewById(R.id.bottom_nav);
        
        //Nhận giá trị từ ChapterActivity hoặc InfoBookActivity
        Intent intent = this.getIntent();
        book_id = intent.getIntExtra("book_id",1);
        max_chapter = intent.getIntExtra("book_amount", 1);
        book_chapter = intent.getIntExtra("book_chapter", 1);
        account_id = intent.getStringExtra("account_id");

        //request Json
        requestQueue = Volley.newRequestQueue(this);
        requestSaveChapterCurr = Volley.newRequestQueue(this);
    }
    
    void control(){
        //Thiết lập sự kiện khi chọn item
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = new Intent();
                switch (item.getItemId()){
                    case R.id.chapter_prev:
                        if (book_chapter > min_chapter) {
                            intent.setClass(ContentActivity.this, ContentActivity.class);
                            intent.putExtra("book_chapter", book_chapter - 1);
                            intent.putExtra("book_amount", max_chapter);
                        } else {
                            return true;
                        }
                        break;
                    case R.id.chapter_next:
                        if (book_chapter < max_chapter) {
                            intent.setClass(ContentActivity.this, ContentActivity.class);
                            intent.putExtra("book_chapter", book_chapter + 1);
                            intent.putExtra("book_amount", max_chapter);
                            saveChapterCurr(book_chapter +1);
                        } else {
                            return true;
                        }
                        break;
                    case R.id.chapter_list:
                        intent.setClass(ContentActivity.this, ChapterActivity.class);
                        intent.putExtra("book_amount", max_chapter);
                        break;
                }
                intent.putExtra("book_id", book_id);
                intent.putExtra("account_id", account_id);
                finish();
                startActivity(intent);
                return true;
            }
        });

    }

    void saveChapterCurr(int chapter){
        String url = "https://hochoihamhoc.000webhostapp.com/saveChapterCurr.php?book_id=+" + book_id + "&account_id=" + account_id + "&chapter="+ chapter;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestSaveChapterCurr.add(request);
    }
}