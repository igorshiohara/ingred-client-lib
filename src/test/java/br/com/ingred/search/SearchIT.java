package br.com.ingred.search;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import br.com.ingred.client.ClientFactory;
import br.com.ingred.client.ClientFactoryImpl;
import br.com.ingred.domain.Product;
import br.com.ingred.engine.SearchAPI;
import br.com.ingred.engine.impl.SearchAPIImpl;

import com.google.common.base.Suppliers;
import com.google.common.collect.Lists;

public class SearchIT {
	
	private final ClientFactory clientFactory = new ClientFactoryImpl(Suppliers.ofInstance("ingredcluster"),Suppliers.ofInstance("localhost"),Suppliers.ofInstance(9300));
	
	@Test
	public void testSearchWithIngredients() throws InterruptedException, ExecutionException {
		List<String> ingredientsMust = Lists.newArrayList("amido");
		List<String> ingredientsMustNot = Lists.newArrayList();
		List<String> categories = Lists.newArrayList();//Lists.newArrayList(Category.builder().withName("congelados").build());
		
		SearchAPI searchAPI = new SearchAPIImpl(clientFactory);
		List<Product> products = searchAPI.getProductsBy(ingredientsMust, ingredientsMustNot, categories);
		
		assertTrue(products.size() > 0);
	}
	
}
