package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Task {
    private int id;
    private String description;
    private TaskStatus status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Task(String description, TaskStatus status, LocalDateTime createAt, LocalDateTime updateAt) {
        this.description = description;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Task() {

    }

    public void setId(int id) {

        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public String toJson() {
        return String.format("{ \"id\":\"%d\", \"description\":\"%s\", \"status\":\"%s\" ,\"createAt\":\"%s\", \"updateAt\":\"%s\"}", id, description, status, createAt, updateAt);
    }

    public void fromJson(String json) {
        StringBuilder sb = new StringBuilder(json);
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length()-1);

        json = sb.toString();
        String[] splitContent = json.split("\" \"");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.n");
        for (String e : splitContent) {
            String[] keyValue = e.trim().split("\":\"");
            if (keyValue[0].contains("id")) id = Integer.parseInt(keyValue[1]);
            else if (keyValue[0].contains("description")) description = keyValue[1];
            else if (keyValue[0].contains("status")) status = TaskStatus.valueOf(keyValue[1]);
            else if (keyValue[0].contains("createAt")) createAt = LocalDateTime.parse(keyValue[1], formatter);
            else updateAt = LocalDateTime.parse(keyValue[1], formatter);
        }
    }

}