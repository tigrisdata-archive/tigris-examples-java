# Todo Project

## Prerequisites

This project requires [Docker](https://docs.docker.com/get-docker/) and [Maven](https://maven.apache.org/install.html) to be installed.

## Starting Project Locally

```sh
mvn clean compile exec:java -Dexec.mainClass="com.tigrisdata.todo.TodoApplication"
```

This will start up the project at http://localhost:8080

To start the project in the detached docker container run:

```sh
docker compose --build -d
```

## Project Structure

```
├── docker-compose.yml
├── Dockerfile
├── pom.xml
├── README.md
└── src
└── main
├── java
│   └── todo
│       ├── collections
│       │   └── Task.java
│       ├── controller
│       │   └── TaskController.java
│       ├── todoApplication.java
│       └── spring
│           ├── TigrisInitializer.java
│           └── TigrisSpringConfiguration.java
└── resources
├── application.yml
└── logback.xml
```

### Data Models
The `src/main/java/todo/collections` directory contains collections models, which is basically the structure of the document persisted
in the particular collection.

For example:

```java
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
```

This model types can be modified to add new fields to the document.

### Routes

The `src/main/java/todo/controllers` directory contains classes which setup [Spring](https://spring.io) Web framework CRUD routes for every collection model.
Once project is started, they can be tested using curl commands.

For example:

#### Create document in the `tasks` collection:
```
curl -X POST "localhost:8080/tasks" -H 'Content-Type: application/json' 
    -d "{ JSON document body corresponding to the collections.Task }"
```

#### Read document from the `tasks` collection:
```
curl -X GET "localhost:8080/tasks/{document id}"
```

#### Delete document from the `tasks` collection:
```
curl -X DELETE "localhost:8080/tasks/{document id}"
```

Full Tigris documentation [here](https://docs.tigrisdata.com).

Be brave. Have fun!
