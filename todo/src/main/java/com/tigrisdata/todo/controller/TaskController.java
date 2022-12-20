package com.tigrisdata.todo.controller;

import com.tigrisdata.db.client.Filters;
import com.tigrisdata.db.client.TigrisCollection;
import com.tigrisdata.db.client.TigrisDatabase;
import com.tigrisdata.db.client.error.TigrisException;
import com.tigrisdata.todo.collections.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tasks")
public class TaskController {

  private final TigrisCollection<Task> taskTigrisCollection;

  public TaskController(TigrisDatabase tigrisDatabase) {
    this.taskTigrisCollection = tigrisDatabase.getCollection(Task.class);
  }

  @PostMapping("/")
  public ResponseEntity<String> create(@RequestBody Task task) throws TigrisException {
    taskTigrisCollection.insert(task);
    return ResponseEntity.status(HttpStatus.CREATED).body("task created");
  }

  @GetMapping("/{id}")
  public Task read(@PathVariable("id") int id) throws TigrisException {
    return taskTigrisCollection.readOne(Filters.eq("id", id)).get();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") int id) throws TigrisException {
    taskTigrisCollection.delete(Filters.eq("id", id));
    return ResponseEntity.status(HttpStatus.OK).body("task deleted");
  }
}

