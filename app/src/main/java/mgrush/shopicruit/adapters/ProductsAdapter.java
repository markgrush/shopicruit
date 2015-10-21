package mgrush.shopicruit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mgrush.shopicruit.R;
import mgrush.shopicruit.listeners.ProductClickListener;
import mgrush.shopicruit.model.Product;

/**
 * Created by Mark on 9/30/2015.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private Context context;
    private List<Product> products;
    private ProductClickListener productClickListener;

    public ProductsAdapter(@NonNull Context context, @NonNull List<Product> products,
                           @NonNull ProductClickListener productClickListener) {
        this.context = context;
        this.products = products;
        this.productClickListener = productClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card, parent, false);
        return new ViewHolder(view, productClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = products.get(position);

        holder.product = product;
        Picasso.with(context)
                .load(product.getImages().get(0).getSrc())
                .into(holder.image);
        holder.title.setText(product.getTitle());
        holder.vendor.setText(product.getVendor());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    /**
     * inner ViewHolder class
     */

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ProductClickListener productClickListener;
        public Product product;
        @Bind(R.id.image) public ImageView image;
        @Bind(R.id.title) public TextView title;
        @Bind(R.id.vendor) public TextView vendor;

        public ViewHolder(View view, @NonNull ProductClickListener productClickListener) {
            super(view);
            this.productClickListener = productClickListener;
            view.setOnClickListener(this);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View view) {
            productClickListener.onClickedProduct(view, product);
        }
    }
}
