package mgrush.shopicruit.view;

import java.util.List;

import mgrush.shopicruit.model.Product;

/**
 * Created by Mark on 9/30/2015.
 */
public interface ProductsView {

    void setProducts(List<Product> products);

    void showLoading();

    void hideLoading();

    void onError(String message);
}
