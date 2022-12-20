package com.tigrisdata.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {

  public static void main(String[] args) {
    new SpringApplication(TodoApplication.class).run(args);
  }
}
