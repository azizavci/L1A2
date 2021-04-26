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

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductHolder> {

    private ArrayList<String> productNameList;
    private ArrayList<String> productCategoryList;
    private ArrayList<String> productColorList;
    private ArrayList<String> productCommentList;
    private ArrayList<String> productSizeList;
    private ArrayList<String> productImageUrlList;



    public ProductRecyclerAdapter(ArrayList<String> productNameList,ArrayList<String> productImageUrlList) {
        this.productNameList = productNameList;
        this.productImageUrlList=productImageUrlList;
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
        Picasso.get().load(productImageUrlList.get(position)).into(holder.imageView);
        //holder.productUnitPriceText.setText(productUnitPriceList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return productNameList.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        TextView productNameText;
        ImageView imageView;
        //TextView productUnitPriceText;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            productNameText = itemView.findViewById(R.id.recycler_row_product_name_text);
            imageView=itemView.findViewById(R.id.iv_recycler_row_product);
            //productUnitPriceText = itemView.findViewById(R.id.recycler_row_product_unit_price);
        }
    }
}
