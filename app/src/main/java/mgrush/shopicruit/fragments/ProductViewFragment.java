package mgrush.shopicruit.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import mgrush.shopicruit.R;
import mgrush.shopicruit.model.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductViewFragment extends Fragment {

    public static final String PRODUCT_ARG_KEY = "mgrush.shopicruit.fragments.ProductViewFragment";

    private Product product;

    @Bind(R.id.image) ImageView productImageView;

    public static Fragment createFragment(Product product) {

        ProductViewFragment productViewFragment = new ProductViewFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(PRODUCT_ARG_KEY, Parcels.wrap(product));

        productViewFragment.setArguments(bundle);

        return productViewFragment;

    }

    public ProductViewFragment() {
        // Required empty public constructor
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

        Picasso.with(getActivity())
                .load(product.getImages().get(0).getSrc())
                .into(productImageView);

        for (int i = 0; i < product.getVariants().size(); i++) {
            Log.v("variants", "" + product.getVariants().get(i));
        }


        return view;
    }

    private void assertArgsNotNull(Bundle args) {
        if (args == null || args.getParcelable(PRODUCT_ARG_KEY) == null) {
            throw new IllegalArgumentException("ProductViewFragment must receive a Product as " +
                    "an argument upon creation!");
        }
    }

}
