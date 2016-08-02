package br.com.ingred.client;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.NodeBuilder;

/**
 * Local ElasticSearch client Factory
 * @author igor
 *
 */
public class LocalClientFactoryImpl implements ClientFactory {

	@Override
	public Client getClient() {
		return NodeBuilder.nodeBuilder().node().client();
	}

}
