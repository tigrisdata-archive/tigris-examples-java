package com.tigrisdata.todo.spring;

import com.tigrisdata.db.client.StandardTigrisClient;
import com.tigrisdata.db.client.TigrisClient;
import com.tigrisdata.db.client.TigrisDatabase;
import com.tigrisdata.db.client.config.TigrisConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TigrisSpringConfiguration {
  @Bean
  public TigrisDatabase tigrisDatabase(TigrisClient client) {
    return client.getDatabase();
  }

  @Bean
  public TigrisClient tigrisClient(
      @Value("${tigris.server.url}") String serverURL,
      @Value("${tigris.project.name}") String projectName,
      @Value("${tigris.network.usePlainText:false}") boolean usePlainText) {
    TigrisConfiguration.NetworkConfig.Builder networkConfigBuilder =
            TigrisConfiguration.NetworkConfig.newBuilder();
    if (usePlainText) {
      networkConfigBuilder.usePlainText();
    }
    TigrisConfiguration configuration =
            TigrisConfiguration.newBuilder(serverURL, projectName)
            .withNetwork(networkConfigBuilder.build())
            .build();
    return StandardTigrisClient.getInstance(configuration);
  }

  @Bean
  public TigrisInitializer tigrisInitializr(
          TigrisClient tigrisClient, @Value("${tigris.project.name}") String projectName) {
    return new TigrisInitializer(tigrisClient, projectName);
  }
}
