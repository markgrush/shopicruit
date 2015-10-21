package mgrush.shopicruit;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import java.util.List;

import mgrush.shopicruit.endpoint.ShopicruitEndpoint;
import mgrush.shopicruit.fragments.ProductsFragment;
import mgrush.shopicruit.model.Product;
import mgrush.shopicruit.model.Store;
import mgrush.shopicruit.service.ServiceGenerator;
import mgrush.shopicruit.service.ShopicruitService;
import retrofit.Callback;
import retrofit.Endpoint;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpFragment();
    }

    private void setUpFragment() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new ProductsFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
