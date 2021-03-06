package com.example.readbookonline.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

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
    int book_page;
    RequestQueue requestQueue,requestSavePageCurr;
    BottomNavigationView bottom_nav;
    int max_page, min_page=1;
    String account_id;
    int text_size;
    int background_color;
    int text_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        mapping();
        loadingData(book_id, book_page);
        control();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, InfoBookActivity.class);
        intent.putExtra("book_id", book_id);
        intent.putExtra("account_id", account_id);
        startActivity(intent);
    }

    void loadingData(int book_id, int book_page){
        String url = "https://hochoihamhoc.000webhostapp.com/getContent.php?book_id=+"+book_id + "&page="+book_page;

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
        //??nh x??? c??c bi???n trong layout
        book_content = findViewById(R.id.book_content);
        bottom_nav = findViewById(R.id.bottom_nav);
        
        //Nh???n gi?? tr??? t??? PageActivity ho???c InfoBookActivity
        Intent intent = this.getIntent();
        book_id = intent.getIntExtra("book_id",1);
        max_page = intent.getIntExtra("book_amount", 1);
        book_page = intent.getIntExtra("book_page", 1);
        account_id = intent.getStringExtra("account_id");
        text_size = intent.getIntExtra("text_size", 18);
        background_color = intent.getIntExtra("background_color", R.color.dark_gray);
        text_color = intent.getIntExtra("text_color", R.color.dark);


        //request Json
        requestQueue = Volley.newRequestQueue(this);
        requestSavePageCurr = Volley.newRequestQueue(this);

        //Thi???t l???p thu???c t??nh
        book_content.setTextColor(getResources().getColor(text_color));
        book_content.setBackgroundColor(getResources().getColor(background_color));
        book_content.setTextSize(text_size);
    }
    
    void control(){
        //Thi???t l???p s??? ki???n khi ch???n item
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = new Intent();
                switch (item.getItemId()){
                    case R.id.page_prev:
                        if (book_page > min_page) {
                            intent.setClass(ContentActivity.this, ContentActivity.class);
                            intent.putExtra("book_page", book_page - 1);
                        } else {
                            return true;
                        }
                        break;
                    case R.id.page_next:
                        if (book_page < max_page) {
                            intent.setClass(ContentActivity.this, ContentActivity.class);
                            intent.putExtra("book_page", book_page + 1);
                            savePageCurr(book_page +1);
                        } else {
                            return true;
                        }
                        break;
                    case R.id.page_list:
                        intent.setClass(ContentActivity.this, PageActivity.class);
                        break;
                    case R.id.setting:
                        intent.setClass(ContentActivity.this, SettingActivity.class);
                        intent.putExtra("book_page", book_page);
                }
                intent.putExtra("book_id", book_id);
                intent.putExtra("book_amount", max_page);
                intent.putExtra("account_id", account_id);
                intent.putExtra("background_color", background_color);
                intent.putExtra("text_color", text_color);
                intent.putExtra("text_size", text_size);
                finish();
                startActivity(intent);
                return true;
            }
        });
    }

    void savePageCurr(int page){
        String url = "https://hochoihamhoc.000webhostapp.com/savePageCurr.php?book_id=+" + book_id + "&account_id=" + account_id + "&page="+ page;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestSavePageCurr.add(request);
    }
}