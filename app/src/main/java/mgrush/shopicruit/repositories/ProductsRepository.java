package mgrush.shopicruit.repositories;

import java.util.List;

import mgrush.shopicruit.model.Product;

/**
 * Created by Mark on 9/30/2015.
 */
public interface ProductsRepository {

    void requestProducts(ProductsRepositoryCallback callback);

}
