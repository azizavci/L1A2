package com.azizavci.l1a2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryHolder> {


    private ArrayList<String> categoryNameList;

    public CategoryRecyclerAdapter(ArrayList<String> categoryNameList) {
        this.categoryNameList = categoryNameList;
    }


    @NonNull
    @Override
    public CategoryRecyclerAdapter.CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_recycler_row,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerAdapter.CategoryHolder holder, int position) {

        holder.categoryNameText.setText(categoryNameList.get(position));

    }

    @Override
    public int getItemCount() {
        return categoryNameList.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder {

        TextView categoryNameText;


        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            categoryNameText = itemView.findViewById(R.id.recycler_row_category_name_text);

        }
    }
}
