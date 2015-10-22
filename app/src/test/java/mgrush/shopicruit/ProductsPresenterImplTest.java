package mgrush.shopicruit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import mgrush.shopicruit.presenters.ProductsPresenterImpl;
import mgrush.shopicruit.repositories.ProductsRepository;
import mgrush.shopicruit.repositories.ProductsRepositoryCallback;
import mgrush.shopicruit.view.ProductsView;

import static org.mockito.Mockito.verify;

/**
 * Created by Mark on 2015-10-21.
 */

@RunWith(MockitoJUnitRunner.class)
public class ProductsPresenterImplTest {

    @Mock
    private ProductsView view;

    private BrokenRepo repository;

    private ProductsPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        repository = new BrokenRepo();
        presenter = new ProductsPresenterImpl(view, repository);
    }

    @Test
    public void shouldShowErrorWhenSomethingIsWrongWithFetchingProducts() throws Exception {

        presenter.requestProducts();

        // presenter should call hideLoading() and onError() methods
        verify(view).hideLoading();
        verify(view).onError(repository.errorMessage);

    }

    private class BrokenRepo implements ProductsRepository {

        public final String errorMessage = "Oh no an error occurred...";

        @Override
        public void requestProducts(ProductsRepositoryCallback callback) {
            callback.onLoadedError(errorMessage);
        }
    }
}