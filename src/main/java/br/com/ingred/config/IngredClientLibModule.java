package br.com.ingred.config;

import br.com.ingred.client.ClientFactory;
import br.com.ingred.client.ClientFactoryImpl;
import br.com.ingred.engine.IndexAPI;
import br.com.ingred.engine.SearchAPI;
import br.com.ingred.engine.impl.IndexAPIImpl;
import br.com.ingred.engine.impl.SearchAPIImpl;

import com.google.inject.AbstractModule;
import com.netflix.governator.guice.lazy.LazySingletonScope;

public class IngredClientLibModule extends AbstractModule {

	@Override
	public void configure() {
		bind(ClientFactory.class).to(ClientFactoryImpl.class).in(LazySingletonScope.get());
		bind(SearchAPI.class).to(SearchAPIImpl.class);
		bind(IndexAPI.class).to(IndexAPIImpl.class);
	}

}
