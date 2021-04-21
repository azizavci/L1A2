package com.azizavci.l1a2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductHolder> {

    private ArrayList<String> productNameList;


    public ProductRecyclerAdapter(ArrayList<String> productNameList) {
        this.productNameList = productNameList;
        //this.productUnitPriceList = productUnitPriceList;
    }


    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row,parent,false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        holder.productNameText.setText(productNameList.get(position));
        //holder.productUnitPriceText.setText(productUnitPriceList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return productNameList.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        TextView productNameText;
        //TextView productUnitPriceText;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            productNameText = itemView.findViewById(R.id.recycler_row_product_name_text);
            //productUnitPriceText = itemView.findViewById(R.id.recycler_row_product_unit_price);
        }
    }
}
