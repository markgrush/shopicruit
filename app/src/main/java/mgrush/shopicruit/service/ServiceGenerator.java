package mgrush.shopicruit.service;

import retrofit.Endpoint;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Mark on 9/30/2015.
 */
public class ServiceGenerator {

    // No need to instantiate this class.
    private ServiceGenerator() {
    }

    public static <S> S createService(Class<S> serviceClass, Endpoint endpoint) {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .build();

        return adapter.create(serviceClass);
    }
}
