package mgrush.shopicruit.presenters;

import java.util.List;

import mgrush.shopicruit.model.Product;
import mgrush.shopicruit.repositories.ProductsRepository;
import mgrush.shopicruit.repositories.ProductsRepositoryCallback;
import mgrush.shopicruit.repositories.ProductsRepositoryImpl;
import mgrush.shopicruit.view.ProductsView;

/**
 * Created by Mark on 9/30/2015.
 */
public class ProductsPresenterImpl implements ProductsPresenter, ProductsRepositoryCallback {

    private ProductsView view;
    private ProductsRepository repository;

    public ProductsPresenterImpl(ProductsView view, ProductsRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void requestProducts() {
        view.showLoading();
        repository.requestProducts(this);
    }

    @Override
    public void onLoadedProducts(List<Product> products) {
        view.hideLoading();
        view.setProducts(products);
    }

    @Override
    public void onLoadedError(String message) {
        view.hideLoading();
        view.onError(message);
    }
}
