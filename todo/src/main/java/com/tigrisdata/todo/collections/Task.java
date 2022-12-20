package com.tigrisdata.todo.collections;

import com.tigrisdata.db.annotation.TigrisField;
import com.tigrisdata.db.annotation.TigrisPrimaryKey;
import com.tigrisdata.db.type.TigrisDocumentCollectionType;

import java.util.Date;import java.util.Objects;
import java.util.Arrays;


// Task Collection of documents with tasks details
@com.tigrisdata.db.annotation.TigrisCollection(value = "tasks")
public class Task implements TigrisDocumentCollectionType {
    @TigrisField(description = "Indicate task completion state")
    private boolean completed;
    @TigrisField(description = "Task completion date")
    private Date completed_at;
    @TigrisField(description = "Detail explanation of the task")
    private String details;
    @TigrisField(description = "Task due date")
    private Date due_at;
    @TigrisField(description = "A unique identifier for the task")
    @TigrisPrimaryKey(order = 1, autoGenerate = true)
    private long id;
    @TigrisField(description = "Name of the task")
    private String name;
    @TigrisField(description = "The list of task categories")
    private String[] tags;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getCompleted_at() {
        return completed_at;
    }

    public void setCompleted_at(Date completedAt) {
        this.completed_at = completedAt;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDue_at() {
        return due_at;
    }

    public void setDue_at(Date dueAt) {
        this.due_at = dueAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Task() {};

    public Task(
        boolean completed,
        Date completedAt,
        String details,
        Date dueAt,
        long id,
        String name,
        String[] tags
    ) {
        this.completed = completed;
        this.completed_at = completedAt;
        this.details = details;
        this.due_at = dueAt;
        this.id = id;
        this.name = name;
        this.tags = tags;
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task other = (Task) o;
        return
            completed == other.completed &&
            completed_at == other.completed_at &&
            details == other.details &&
            due_at == other.due_at &&
            id == other.id &&
            name == other.name &&
            Arrays.equals(tags, other.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            completed,
            completed_at,
            details,
            due_at,
            id,
            name,
            tags
        );
    }
}


