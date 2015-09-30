package mgrush.shopicruit.presenters;

import java.util.List;

import mgrush.shopicruit.model.Product;
import mgrush.shopicruit.repositories.ProductsRepository;
import mgrush.shopicruit.repositories.ProductsRepositoryImpl;
import mgrush.shopicruit.view.ProductsView;

/**
 * Created by Mark on 9/30/2015.
 */
public class ProductsPresenterImpl implements ProductsPresenter {

    private ProductsView view;
    private ProductsRepository repository;

    public ProductsPresenterImpl(ProductsView view) {
        this.view = view;
        repository = new ProductsRepositoryImpl(this);
    }

    @Override
    public void requestProducts() {
        view.showLoading();
        repository.requestProducts();
    }

    @Override
    public void setProducts(List<Product> products) {
        view.hideLoading();
        view.setProducts(products);
    }

    @Override
    public void onError(String message) {
        view.onError(message);
    }
}
