package mgrush.shopicruit.service;

import java.util.List;

import mgrush.shopicruit.model.Product;
import mgrush.shopicruit.model.Store;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * Created by Mark on 9/30/2015.
 */
public interface ShopicruitService {

    @GET("/products.json")
    @Headers("Accept: application/json")
    public void getStore(Callback<Store> callback);
}
