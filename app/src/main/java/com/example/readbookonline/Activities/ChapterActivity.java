package com.example.readbookonline.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.readbookonline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChapterActivity extends AppCompatActivity {
     ListView chaplist;
     int book_id, book_amount;
     ArrayList<String> list;
     RequestQueue queue;
     RequestQueue requestSaveChapterCurr;
     ArrayAdapter<String> arrayAdapter;
     String account_id;
     SearchView action_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        mapping();
        loadingData();
        control();



    }

    void loadingData (){

        String url = "https://hochoihamhoc.000webhostapp.com/getChapters.php?book_id="+ book_id ;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0; i<response.length();i++){
                            try {
                                JSONObject item = response.getJSONObject(i);
                                String chapter = item.getString("chapter");
                                list.add("Trang "+chapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(ChapterActivity.this, "Error1", Toast.LENGTH_LONG);
                            }
                        }
                        arrayAdapter = new ArrayAdapter<String>(ChapterActivity.this ,android.R.layout.simple_list_item_1, list);
                        chaplist.setAdapter(arrayAdapter);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChapterActivity.this, "Error2", Toast.LENGTH_LONG);
            }
        }
        );
        queue.add(jsonArrayRequest);
    }

    void mapping(){
        //Nhận giá trị từ InfoBookActivity hoặc ContentActivity
        book_id = getIntent().getIntExtra("book_id", 1);
        book_amount = getIntent().getIntExtra("book_amount", 1);
        account_id = getIntent().getStringExtra("account_id");

        //Ánh xạ
        list = new ArrayList<String>();
        chaplist = findViewById(R.id.chaplist);
        action_search = findViewById(R.id.action_search);

        //Request Json
        queue = Volley.newRequestQueue(this);
        requestSaveChapterCurr = Volley.newRequestQueue(this);
    }

    void control(){

        action_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });


        //CLick chọn chương truyện
        chaplist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chap = (String)chaplist.getItemAtPosition(position);
                Intent intent = new Intent(ChapterActivity.this, ContentActivity.class);
                intent.putExtra("book_chapter", Integer.parseInt(chap.substring(6)));
                intent.putExtra("book_id", book_id);
                intent.putExtra("book_amount", book_amount);
                intent.putExtra("account_id", account_id);
                saveChapterCurr(Integer.parseInt(chap.substring(6)));
                finish();
                startActivity(intent);
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





