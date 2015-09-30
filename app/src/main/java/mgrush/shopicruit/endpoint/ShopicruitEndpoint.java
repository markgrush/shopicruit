package mgrush.shopicruit.endpoint;

import retrofit.Endpoint;

/**
 * Created by Mark on 9/30/2015.
 */
public class ShopicruitEndpoint implements Endpoint {

    @Override
    public String getUrl() {
        return "http://shopicruit.myshopify.com";
    }

    @Override
    public String getName() {
        return "Shopicruit";
    }
}
