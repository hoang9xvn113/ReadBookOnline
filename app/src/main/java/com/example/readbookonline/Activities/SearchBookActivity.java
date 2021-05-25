package com.example.readbookonline.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.readbookonline.Adapters.BookHomeAdapter;
import com.example.readbookonline.Models.Book;
import com.example.readbookonline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class SearchBookActivity extends AppCompatActivity {
    RecyclerView listbook;
    SearchView action_search;
    String url = "https://hochoihamhoc.000webhostapp.com/getBookHome.php";
    RequestQueue requestQueue;
    ArrayList<Book> data_holder;
    String account_id;
    BookHomeAdapter bookHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);
        mapping();
        jsonParse();
        control();

    }
    void jsonParse(){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject book = response.getJSONObject(i);
                        int id = Integer.parseInt(book.getString("id"));
                        String name = book.getString("name");
                        String author = book.getString("author");
                        String status = book.getString("status");
                        String amount = book.getString("amount");
                        int img = Integer.parseInt(book.getString("img"));
                        data_holder.add(new Book(id, name, author, status, amount, img));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "ERROR", Toast.LENGTH_LONG).show();
                    }
                    listbook.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    bookHomeAdapter = new BookHomeAdapter(data_holder, account_id);
                    listbook.setAdapter(bookHomeAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(request);
    }

    void mapping(){
        //Nhận giá trị từ home fargment hoặc categoryfragment
        account_id = getIntent().getStringExtra("account_id");
        //Lấy id từ SearchBook layout
        listbook= findViewById(R.id.listbook);
        listbook = (RecyclerView) findViewById(R.id.listbook);
        action_search = findViewById(R.id.action_search);
        //Khopiwr atoj giá trị
        data_holder = new ArrayList();
        requestQueue = Volley.newRequestQueue(this);

    }

    void control(){
        action_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                bookHomeAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}