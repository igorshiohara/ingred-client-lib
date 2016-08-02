package br.com.ingred.engine;

import java.util.List;

import br.com.ingred.domain.Product;

/**
 * Service that consumes the ElasticSearch database to search items
 * 
 * @author igor
 *
 */
public interface SearchAPI {
	
	/**
	 * Search in ElasticSearch database to get all Products with Ingredients listed (must and must not) and Categories listed
	 * @param ingredients, categories
	 * @return Product's list that contains that ingredients
	 */
	List<Product> getProductsBy(final List<String> ingredientsMust, final List<String> ingredientsMustNot, final List<String> categories);
	
	String injectTest(final String message); 
}
