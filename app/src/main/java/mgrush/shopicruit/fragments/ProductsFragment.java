package mgrush.shopicruit.fragments;

import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mgrush.shopicruit.R;
import mgrush.shopicruit.adapters.ProductsAdapter;
import mgrush.shopicruit.listeners.ProductClickListener;
import mgrush.shopicruit.model.Product;
import mgrush.shopicruit.presenters.ProductsPresenter;
import mgrush.shopicruit.presenters.ProductsPresenterImpl;
import mgrush.shopicruit.repositories.ProductsRepositoryImpl;
import mgrush.shopicruit.view.ProductsView;

public class ProductsFragment extends Fragment implements ProductsView, ProductClickListener {

    private ProductsPresenter presenter;

    private List<Product> products;

    private ProductsAdapter productsAdapter;
    private RecyclerView.LayoutManager productsLayoutManager;

    @Bind(R.id.spinner) ProgressBar spinner;
    @Bind(R.id.products_recycler_view) RecyclerView productsRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ProductsPresenterImpl(this, new ProductsRepositoryImpl());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        ButterKnife.bind(this, view);

        presenter.requestProducts();

        return view;
    }

    private void createProductsRecyclerView() {
        productsAdapter = new ProductsAdapter(getActivity(), products, this);
        productsLayoutManager = new LinearLayoutManager(getActivity());
        productsRecyclerView.setLayoutManager(productsLayoutManager);
        productsRecyclerView.setAdapter(productsAdapter);
    }

    @Override
    public void setProducts(List<Product> products) {
        this.products = products;
        createProductsRecyclerView();
    }

    @Override
    public void showLoading() {
        spinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        spinner.setVisibility(View.GONE);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getActivity(), "Oops! An error occurred.",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClickedProduct(View view, Product product) {
        Fragment productViewFragment = ProductViewFragment.createFragment(product);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setUpTransition(productViewFragment);
        }
        replaceFragment(productViewFragment);
    }

    private void replaceFragment(Fragment productViewFragment) {
        FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, productViewFragment)
                .addToBackStack("transaction");
        fragmentTransaction.commit();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setUpTransition(Fragment productViewFragment) {
        this.setExitTransition(
                TransitionInflater.from(getActivity()).inflateTransition(R.transition.explode)
        );

        productViewFragment.setEnterTransition(
                TransitionInflater.from(getActivity()).inflateTransition(R.transition.explode)
        );
    }
}
