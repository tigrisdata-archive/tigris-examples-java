package com.tigrisdata.todo.spring;

import com.tigrisdata.db.client.TigrisClient;
import com.tigrisdata.db.client.TigrisDatabase;
import com.tigrisdata.todo.collections.Order;
import com.tigrisdata.todo.collections.Product;
import com.tigrisdata.todo.collections.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class TigrisInitializer implements CommandLineRunner {

  private final TigrisClient tigrisClient;
  private final String projectName;

  private static final Logger log = LoggerFactory.getLogger(TigrisInitializer.class);

  public TigrisInitializer(TigrisClient tigrisClient, String projectName) {
    this.tigrisClient = tigrisClient;
    this.projectName = projectName;
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("get database db: {}", projectName);
    TigrisDatabase tigrisDatabase = tigrisClient.getDatabase();
    log.info("creating collections on db {}", projectName);
    tigrisDatabase.createOrUpdateCollections(
            Task.class
    );
    log.info("Finished initializing Tigris");
  }
}
