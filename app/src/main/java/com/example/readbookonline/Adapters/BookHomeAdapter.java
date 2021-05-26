package com.example.readbookonline.Adapters;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

public class BookHomeAdapter extends RecyclerView.Adapter<BookHomeAdapter.BookViewHolder> implements Filterable {

    ArrayList<Book> data_holder, data_holder_filter;
    String account_id;

    public BookHomeAdapter(ArrayList<Book> data_holder, String account_id) {
        this.data_holder = data_holder; this.account_id = account_id;
        data_holder_filter = data_holder;
    }



    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_book_home, parent, false);
        return new BookViewHolder(view);
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    data_holder_filter = data_holder;
                } else {
                    ArrayList<Book> data = new ArrayList<>();
                    for (Book row : data_holder) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            data.add(row);
                        }
                    }
                    data_holder_filter = data;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = data_holder_filter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                data_holder_filter = (ArrayList<Book>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface BookAdapterListener {
        void onContactSelected(Book book);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        DataImage dataImage = new DataImage();
        holder.img.setImageResource(dataImage.list_image[data_holder_filter.get(position).getImg()]);
        holder.book_name.setText(data_holder_filter.get(position).getName());
        holder.book_author.setText("Tác giả: " + data_holder_filter.get(position).getAuthor());
        holder.book_status.setText("Trạng thái: " + data_holder_filter.get(position).getStatus());
        holder.book_amount.setText("Số trang: "+data_holder_filter.get(position).getAmount() + " trang");

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InfoBookActivity.class);
                intent.putExtra("book_id",data_holder.get(position).getId());
                intent.putExtra("account_id", account_id);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data_holder_filter.size();
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
