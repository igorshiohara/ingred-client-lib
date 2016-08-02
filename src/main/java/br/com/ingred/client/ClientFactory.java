package br.com.ingred.client;

import org.elasticsearch.client.Client;

public interface ClientFactory {

	/**
	 * Get a ElasticSearch client
	 * @return Client
	 */
	Client getClient();
	
}
