package br.com.ingred.engine.impl;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ingred.client.ClientFactory;
import br.com.ingred.domain.Product;
import br.com.ingred.engine.SearchAPI;


/**
 * Implementation class for SearchAPI
 * 
 * @author igor
 *
 */
public class SearchAPIImpl implements SearchAPI {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchAPIImpl.class);
	
	private final ClientFactory clientFactory;
	
	@Inject
	public SearchAPIImpl(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	/**
	 * {@inheritDoc}
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public List<Product> getProductsBy(final List<String> ingredientsMust, final List<String> ingredientsMustNot, final List<String> categories) {
		
		BoolQueryBuilder bool = boolQuery();

		for (String ingredMust : ingredientsMust) {
            bool.must(queryString(ingredMust + "~").field("ingredients"));
		}
		for (String ingredMustNot : ingredientsMustNot) {
            bool.must(queryString(ingredMustNot + "~").field("ingredients"));
		}
		for (String categ : categories) {
			bool.must(queryString(categ + "~").field("category"));
		}

        LOGGER.info("Query used: {}", bool);
        System.out.println(bool);
        try (Client client = clientFactory.getClient();) {
            SearchResponse searchResponse =
                client.prepareSearch("ingred").setTypes("product").setQuery(bool).execute()
                    .get();
            int totalHits = (int) searchResponse.getHits().getTotalHits();
            LOGGER.info("Total hits found in search: [{}]", totalHits,
                totalHits);
            List<Product> listProducts = new ArrayList<>();
            for (SearchHit hit : searchResponse.getHits()) {
                Product product = extract(hit);
                listProducts.add(product);
            }
            System.out.println("Hits: " + listProducts.size());
            System.out.println(listProducts);
            return Collections.unmodifiableList(listProducts);
        } catch (InterruptedException | ExecutionException e) {
        	LOGGER.error("Error found when tried to connect on ElasticSearch and search products");
		}
        return Collections.emptyList();
	}

	/**
	 * Extract the data from Elastic search response
	 * @param hit
	 * @return
	 */
	private Product extract(SearchHit hit) {
		Map<String, Object> source = hit.sourceAsMap();
		String id = hit.getId();
        String name = Objects.toString(source.get("name"), null);
        String description = Objects.toString(source.get("description"), null);
        String category = Objects.toString(source.get("category"), null);
        String ingredients = Objects.toString(source.get("ingredients"), null);
        return Product.builder().withId(id).withName(name).withDescription(description).withCategory(category).withIngredients(ingredients).build();
	}

	@Override
	public String injectTest(String message) {
		return "Wow, it works! //" + message;
	}
	
}
