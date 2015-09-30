package mgrush.shopicruit.repositories;

import android.util.Log;

import java.util.List;

import mgrush.shopicruit.endpoint.ShopicruitEndpoint;
import mgrush.shopicruit.model.Product;
import mgrush.shopicruit.model.Store;
import mgrush.shopicruit.presenters.ProductsPresenter;
import mgrush.shopicruit.presenters.ProductsPresenterImpl;
import mgrush.shopicruit.service.ServiceGenerator;
import mgrush.shopicruit.service.ShopicruitService;
import retrofit.Callback;
import retrofit.Endpoint;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Mark on 9/30/2015.
 */
public class ProductsRepositoryImpl implements ProductsRepository {

    private ProductsPresenter presenter;

    private Endpoint endpoint;
    private ShopicruitService shopicruit;

    public ProductsRepositoryImpl(ProductsPresenter presenter) {
        this.presenter = presenter;

        endpoint = new ShopicruitEndpoint();
        shopicruit = ServiceGenerator.createService(ShopicruitService.class, endpoint);
    }

    @Override
    public void requestProducts() {
        shopicruit.getStore(new Callback<Store>() {
            @Override
            public void success(Store store, Response response) {
                presenter.setProducts(store.getProducts());
            }

            @Override
            public void failure(RetrofitError error) {
                presenter.onError(error.getMessage());
            }
        });
    }
}
