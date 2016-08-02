package br.com.ingred.client;

import static java.util.Objects.requireNonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.netflix.governator.annotations.Configuration;

/**
 * Class that implements ClientFactory
 * 
 * @author igor
 *
 */
@Singleton
public class ClientFactoryImpl implements ClientFactory {

	@Configuration("ingred.client.lib.elasticsearch.cluster.name")
	private final Supplier<String> clusterName;

	@Configuration("ingred.client.lib.elasticsearch.cluster.url")
	private final Supplier<String> clusterUrl;

	@Configuration("ingred.client.lib.elasticsearch.cluster.port")
	private final Supplier<Integer> clusterPort;

	@Inject
	public ClientFactoryImpl() {
		this(Suppliers.ofInstance("ingredcluster"), Suppliers.ofInstance("localhost"), Suppliers.ofInstance(9300));
	}
	
	public ClientFactoryImpl(final Supplier<String> clusterName,
			final Supplier<String> clusterUrl,
			final Supplier<Integer> clusterPort) {

		this.clusterName = requireNonNull(clusterName);
		this.clusterUrl = requireNonNull(clusterUrl);
		this.clusterPort = requireNonNull(clusterPort);
	}

	public Client getClient() {
		final Settings settings = ImmutableSettings.settingsBuilder()
				.build();
		@SuppressWarnings("resource")
		final Client client = new TransportClient(settings, false)
				.addTransportAddress(new InetSocketTransportAddress(clusterUrl
						.get(), clusterPort.get()));
		return client;
	}

}
