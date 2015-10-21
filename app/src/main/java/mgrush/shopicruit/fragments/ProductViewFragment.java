package mgrush.shopicruit.fragments;


import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mgrush.shopicruit.R;
import mgrush.shopicruit.model.Product;
import mgrush.shopicruit.model.Variant;

/**
 * Fragment that displays information about the first variant of a product
 */
public class ProductViewFragment extends Fragment {

    public static final String PRODUCT_ARG_KEY = "mgrush.shopicruit.fragments.ProductViewFragment";

    @Bind(R.id.image)
    ImageView productImageView;
    @Bind(R.id.title)
    TextView titleTextView;
    @Bind(R.id.price)
    TextView priceTextView;
    @Bind(R.id.weight)
    TextView weightTextView;
    @Bind(R.id.available)
    TextView availableTextView;

    // the product to show
    private Product product;

    public ProductViewFragment() {
        // Required empty public constructor
    }

    public static Fragment createFragment(@NonNull Product product) {

        ProductViewFragment productViewFragment = new ProductViewFragment();

        // set arguments
        Bundle bundle = new Bundle();
        bundle.putParcelable(PRODUCT_ARG_KEY, Parcels.wrap(product));
        productViewFragment.setArguments(bundle);

        return productViewFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_view, container, false);

        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        assertArgsNotNull(args);
        Parcelable parcelable = args.getParcelable(PRODUCT_ARG_KEY);
        product = Parcels.unwrap(parcelable);

        setVariantViews();

        return view;
    }

    private void setVariantViews() {
        Variant variant = product.getVariants().get(0);

        // get image from product object, not from variant
        Picasso.with(getActivity())
                .load(product.getImages().get(0).getSrc())
                .into(productImageView);

        titleTextView.setText(variant.getTitle());
        priceTextView.setText("$" + variant.getPrice());
        weightTextView.setText(variant.getGrams() + " grams");

        boolean isAvailable = variant.getAvailable();
        availableTextView.setText(
                isAvailable ? "Available" : "Not available"
        );
        availableTextView.setTextColor(
                isAvailable ? ContextCompat.getColor(getActivity(), R.color.product_available)
                        : ContextCompat.getColor(getActivity(), R.color.product_unavailable)
        );
    }

    private void assertArgsNotNull(Bundle args) {
        if (args == null || args.getParcelable(PRODUCT_ARG_KEY) == null) {
            throw new IllegalArgumentException("ProductViewFragment must receive a Product as " +
                    "an argument upon creation!");
        }
    }

}
