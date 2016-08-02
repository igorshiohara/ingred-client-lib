package br.com.ingred.engine.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

import br.com.ingred.client.ClientFactory;
import br.com.ingred.domain.Product;
import br.com.ingred.engine.IndexAPI;

public class IndexAPIImpl implements IndexAPI {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchAPIImpl.class);
	
	private final ClientFactory clientFactory;
	
	@Inject
	public IndexAPIImpl(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void save(Product product) {
		try (Client client = clientFactory.getClient()) {

            Map<String, Object> source = new HashMap<>();
            source.put("name", product.getName());
            source.put("description", product.getDescription());
            source.put("category", product.getCategory());
            IndexResponse indexResponse =
                client.prepareIndex("ingred", "product").setId(product.getId().toString())
                    .setSource(source).execute().actionGet();
            LOGGER.info("Saved {} with id {} and version {}", product, indexResponse.getId(),
                indexResponse.getVersion());
        }
	}

}
