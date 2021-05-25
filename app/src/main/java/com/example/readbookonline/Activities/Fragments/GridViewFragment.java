package com.example.readbookonline.Activities.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.readbookonline.Adapters.GridViewCategoryAdapter;
import com.example.readbookonline.Models.*;
import com.example.readbookonline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GridViewFragment extends Fragment {
    private List<Book> bookList;
    GridView gridView;
    RequestQueue requestQueue;
    String account_id="1";
    int category_id;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_categorygridview, container, false);
        mapping();
        loadingData();
        return view;
    }

    void loadingData(){

        String url = "https://hochoihamhoc.000webhostapp.com/getBookCategory.php?category_id=" + category_id;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject book = response.getJSONObject(i);
                        int id = Integer.parseInt(book.getString("id"));
                        String name = book.getString("name");
                        int img = Integer.parseInt(book.getString("img"));
                        bookList.add(new Book(id, name, img));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    gridView.setAdapter(new GridViewCategoryAdapter(bookList, getContext(), account_id));
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
        //Nhận các giá trị từ CategoryFragment
        category_id = getArguments().getInt("category_id");
        account_id = getArguments().getString("account_id");

        //List book
        gridView = (GridView) view.findViewById(R.id.gridView);
        bookList = new ArrayList();

        //requestJson
        requestQueue = Volley.newRequestQueue(this.getContext());
    }
}
