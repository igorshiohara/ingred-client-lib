package br.com.ingred.engine;

import br.com.ingred.domain.Product;


/**
 * This is responsible to insert, update and delete data (index) in ElasticSearch database
 */
public interface IndexAPI {

	/**
	 * Insert a product into ElasticSearch database
	 * @param product
	 */
	void save(final Product product);
	
}
