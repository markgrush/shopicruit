package mgrush.shopicruit.repositories;

import java.util.List;

import mgrush.shopicruit.model.Product;

/**
 * Created by Mark on 2015-10-21.
 */
public interface ProductsRepositoryCallback {

    void onLoadedProducts(List<Product> products);

    void onLoadedError(String message);
}
