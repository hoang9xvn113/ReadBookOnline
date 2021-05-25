package com.example.readbookonline.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.readbookonline.Activities.InfoBookActivity;
import com.example.readbookonline.Models.Book;

import java.util.List;

import com.example.readbookonline.Helpers.*;
import com.example.readbookonline.R;

public class GridViewCategoryAdapter extends BaseAdapter {

    List<Book> book_list;
    LayoutInflater layoutInflater;
    Context context;
    String account_id;

    public GridViewCategoryAdapter(List<Book> book_list, Context context, String account_id) {
        this.book_list = book_list;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.account_id = account_id;
    }

    @Override
    public int getCount() {
        return book_list.size();
    }

    @Override
    public Book getItem(int position) {
        return book_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_book_category, null);
            holder = new ViewHolder();
            holder.book_img = convertView.findViewById(R.id.book_img);
            holder.book_name = convertView.findViewById(R.id.book_name);
            holder.item = convertView.findViewById(R.id.item);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }

        Book book = getItem(position);
        DataImage dataImage = new DataImage();
        holder.book_name.setText(book.getName());
        holder.book_img.setImageResource(dataImage.list_image[book.getImg()]);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InfoBookActivity.class);
                intent.putExtra("book_id", book.getId());
                intent.putExtra("account_id", account_id);
                v.getContext().startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        ImageView book_img;
        TextView book_name;
        RelativeLayout item;
    }
}
