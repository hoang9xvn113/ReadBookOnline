package com.example.readbookonline.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readbookonline.Activities.InfoBookActivity;
import com.example.readbookonline.Models.Book;

import java.util.ArrayList;

import com.example.readbookonline.Helpers.*;
import com.example.readbookonline.R;

public class BookBookmarkAdapter extends RecyclerView.Adapter<BookBookmarkAdapter.BookViewHolder>{
    ArrayList<Book> data_holder;
    String account_id;

    public BookBookmarkAdapter(ArrayList<Book> data_holder, String account_id) {
        this.data_holder = data_holder; this.account_id = account_id;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_book_bookmark, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        DataImage dataImage = new DataImage();
        holder.img.setImageResource(dataImage.list_image[data_holder.get(position).getImg()]);
        holder.book_name.setText(data_holder.get(position).getName());
        holder.book_author.setText("Tác giả: " + data_holder.get(position).getAuthor());
        holder.book_status.setText("Trạng thái: " + data_holder.get(position).getStatus());
        holder.book_amount.setText("Đang đọc: " +  data_holder.get(position).getAmount());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InfoBookActivity.class);
                intent.putExtra("book_id", data_holder.get(position).getId());
                intent.putExtra("account_id", account_id);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data_holder.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView book_name;
        TextView book_author;
        TextView book_status;
        TextView book_amount;
        RelativeLayout item;


        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.book_img);
            book_name = itemView.findViewById(R.id.book_name);
            book_author = itemView.findViewById(R.id.book_author);
            book_status = itemView.findViewById(R.id.book_status);
            book_amount = itemView.findViewById(R.id.book_amount);
            item = itemView.findViewById(R.id.item);
        }
    }
}
