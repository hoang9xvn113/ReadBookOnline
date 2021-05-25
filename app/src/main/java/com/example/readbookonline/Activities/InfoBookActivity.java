package com.example.readbookonline.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.readbookonline.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.readbookonline.Helpers.*;

public class InfoBookActivity extends AppCompatActivity {

    RelativeLayout chaplist_infostr;
    String account_id;
    int book_id, max_chapter, status_bookmark,chapter_curr=1;
    RequestQueue requestQueue,requestStatusBookmark, requestSaveChapterCurr;
    TextView txt_content,book_author,book_status,book_amount, book_name, txt_chaplist;
    ImageView book_img;
    BottomNavigationView bottom_nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_book);
        mapping();
        loadingData();
        control();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("account_id", account_id);
        startActivity(intent);
        this.finish();
    }

    void loadingData(){
        String url = "https://hochoihamhoc.000webhostapp.com/getBook.php?book_id=" + book_id+"&account_id="+account_id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    try {
                        int id = Integer.parseInt(response.getString("id"));
                        String name = response.getString("name");book_name.setText(name);
                        String author = response.getString("author");book_author.setText("Tác giả: "+author);
                        String status = response.getString("status");book_status.setText("Trạng thái: "+status);
                        String amount = response.getString("amount");book_amount.setText("Số trang: "+amount);
                        max_chapter = Integer.parseInt(amount);
                        txt_chaplist.setText(txt_chaplist.getText() + "Trang  " + amount);
                        chapter_curr = Integer.parseInt(response.getString("chapter_curr"));
                        status_bookmark = Integer.parseInt(response.getString("status_bookmark"));
                        String des = response.getString("des");txt_content.setText(des);
                        DataImage dataImage = new DataImage();
                        int img = Integer.parseInt(response.getString("img"));book_img.setImageResource(dataImage.list_image[img]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(InfoBookActivity.this, "ERROR1", Toast.LENGTH_LONG).show();
                    }
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InfoBookActivity.this, "ERROR2", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
    }

    void followBook(){
        String url = "https://hochoihamhoc.000webhostapp.com/followBook.php?book_id="+book_id + "&account_id="+account_id+"&status_bookmark="+status_bookmark;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (status_bookmark == 0){
                    Toast.makeText(InfoBookActivity.this, "Bạn đã theo dõi", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InfoBookActivity.this, "Bạn đã bỏ theo dõi", Toast.LENGTH_SHORT).show();
                }
                status_bookmark = Integer.parseInt(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InfoBookActivity.this, "ERROR2", Toast.LENGTH_LONG).show();
            }
        });
        requestStatusBookmark.add(stringRequest);
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



    void mapping(){
        //Nhận giá trị từ SearchActivity hoặc HomeFragment hoặc CategoryFragment
        book_id = this.getIntent().getIntExtra("book_id",1);
        account_id = this.getIntent().getStringExtra("account_id");

        //Ánh xạ các biến trong layout
        book_name = findViewById(R.id.book_name);
        book_author = findViewById(R.id.book_author);
        book_amount = findViewById(R.id.book_amount);
        book_status = findViewById(R.id.book_status);
        txt_content = findViewById(R.id.txt_content);
        book_img = findViewById(R.id.book_img);
        txt_chaplist = findViewById(R.id.txt_chaplist);
        chaplist_infostr = findViewById(R.id.chaplist_infostr);
        bottom_nav = findViewById(R.id.bottom_nav);

        //request JSon
        requestQueue = Volley.newRequestQueue(this);
        requestStatusBookmark = Volley.newRequestQueue(this);
        requestSaveChapterCurr = Volley.newRequestQueue(this);
    }

    void control(){

        //click open ChapterActivity
        chaplist_infostr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChapterActivity.class);
                intent.putExtra("book_id", book_id);
                intent.putExtra("book_amount", max_chapter);
                intent.putExtra("account_id", account_id);
                v.getContext().startActivity(intent);
            }
        });

        //click open ContentActivity va theo doi
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = new Intent(InfoBookActivity.this, ContentActivity.class);

                switch (item.getItemId()) {
                    case R.id.nav_follow:
                        followBook();
                        break;
                    case R.id.nav_readnow:
                        intent.putExtra("book_id", book_id);
                        intent.putExtra("book_chapter",1);
                        intent.putExtra("book_amount", max_chapter);
                        intent.putExtra("account_id", account_id);
                        saveChapterCurr(1);
                        startActivity(intent);
                        break;
                    case R.id.nav_readcon:
                        intent.putExtra("book_id", book_id);
                        intent.putExtra("book_chapter",chapter_curr);
                        intent.putExtra("book_amount", max_chapter);
                        intent.putExtra("account_id", account_id);
                        startActivity(intent);
                        break;
                }

                return true;
            }
        });
    }
}