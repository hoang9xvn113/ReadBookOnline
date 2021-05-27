package com.example.readbookonline.Activities.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.readbookonline.Adapters.BookBookmarkAdapter;
import com.example.readbookonline.Models.Book;
import com.example.readbookonline.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class BookmarkFragment extends Fragment {

    RecyclerView list_book;
    ArrayList<Book> data_holder;
    String account_id;
    RequestQueue requestQueue;
    View view;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        mapping();
        loadingData();
        return view;
    }

    void loadingData(){
        String url = "https://hochoihamhoc.000webhostapp.com/getBookBookmark.php?account_id=" + account_id;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject book = response.getJSONObject(i);
                        int id = Integer.parseInt(book.getString("id"));
                        String name = book.getString("name");
                        String author = book.getString("author");
                        String status = book.getString("status");
                        String page_curr = book.getString("page_curr");
                        int img = Integer.parseInt(book.getString("img"));
                        data_holder.add(new Book(id,name,author,status,page_curr,img));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                list_book.setAdapter(new BookBookmarkAdapter(data_holder, account_id));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    void mapping(){
        //Nhận các giá trị từ MainActivity
        account_id = getArguments().getString("account_id");

        //List book
        list_book = view.findViewById(R.id.list_book);
        list_book.setLayoutManager(new LinearLayoutManager(getContext()));
        data_holder = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getContext());
    }
}
