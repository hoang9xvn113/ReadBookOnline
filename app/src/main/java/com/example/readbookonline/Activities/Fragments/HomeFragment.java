
package com.example.readbookonline.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
import com.example.readbookonline.Activities.SearchBookActivity;
import com.example.readbookonline.Adapters.BookHomeAdapter;
import com.example.readbookonline.Models.*;
import com.example.readbookonline.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;



public class HomeFragment extends Fragment {


    RecyclerView list_book;
    ArrayList<Book> data_holder;
    ImageButton search_book;
    String url = "https://hochoihamhoc.000webhostapp.com/getBookHome.php";
    RequestQueue requestQueue;
    String account_id;
    View view;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mapping();
        loadingData();
        control();
        return view;
    }

    void loadingData(){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject Book = response.getJSONObject(i);
                        int id = Integer.parseInt(Book.getString("id"));
                        String name = Book.getString("name");
                        String author = Book.getString("author");
                        String status = Book.getString("status");
                        String amount = Book.getString("amount");
                        int img = Integer.parseInt(Book.getString("img"));
                        data_holder.add(new Book(id, name, author, status, amount, img));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
                    }
                    list_book.setLayoutManager(new LinearLayoutManager(getContext()));
                    list_book.setAdapter(new BookHomeAdapter(data_holder, account_id));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(request);
    }

    void mapping(){
        //Nhận giá trị từ MainActivity
        account_id = getArguments().getString("account_id");

        //Ánh xạ với các id từ layout
        search_book = view.findViewById(R.id.search_book);


        //List book
        data_holder = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getContext());
        list_book = view.findViewById(R.id.list_book);
    }

    void control(){
        //Thiết lập sự kiện click vào ImageButtom search_book để mở SearchBookAcitivity
        search_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchBookActivity.class);
                intent.putExtra("account_id", account_id);
                startActivity(intent);
            }
        });


    }





}
