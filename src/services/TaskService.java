package services;

import model.Task;
import model.TaskStatus;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class TaskService {

    private List<Task> tasks;

    {
        readFile();
    }

    public TaskService() {

        tasks.sort((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    }

    public void list(String filter) {
        tasks.forEach(task -> {
            if(task.getStatus().equals(filter)){
                System.out.println(task.toJson());
            }
        });
    }

    public void delete(int id) {
        for(int i=0; i<tasks.size(); i++){
            if(tasks.get(i).getId()==i){
                tasks.remove(i);
                break;
            }
        }
        saveFile();
    }

    public void update(int id, String status) {
        for(int i=0; i<tasks.size(); i++){
            if(tasks.get(i).getId()==i){
                tasks.get(i).setStatus(TaskStatus.parseStatus(status));
                tasks.get(i).setUpdateAt(LocalDateTime.now());
                break;
            }
        }
        saveFile();
    }

    public void add(String description) {
        Task task = new Task();
        task.setDescription(description);
        task.setCreateAt(LocalDateTime.now());
        task.setUpdateAt(LocalDateTime.now());
        task.setStatus(TaskStatus.TODO);
        if(tasks.isEmpty()) task.setId(1);
        else task.setId(tasks.getLast().getId()+1);
        tasks.add(task);
        saveFile();
    }

    private void readFile() {
        Path path = Paths.get("task.json");
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            tasks = new ArrayList<>();
            String fileContent = Files.readString(path);
            if (fileContent.isBlank()) return;
            StringBuilder sb = new StringBuilder(fileContent);
            for(int i=0; i<sb.length(); i++){
                if(sb.charAt(i)=='\n'||sb.charAt(i)==',' ||sb.charAt(i)=='['){
                    sb.deleteCharAt(i);
                    i--;
                }
            }
            sb.deleteCharAt(0);
            sb.deleteCharAt(sb.length()-1);
            sb.deleteCharAt(sb.length()-1);
            fileContent = sb.toString();
            String[] taskContents = fileContent.trim().split("}\\{");
            for (String taskContent : taskContents) {
                Task task = new Task();
                task.fromJson(taskContent);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveFile() {
        Path path = Paths.get("task.json");
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (Task task : tasks) {
            sb.append(task.toJson());
            sb.append(",\n");
        }
        sb.deleteCharAt(sb.length()-2);
        sb.append("]");
        try {
            Files.writeString(path, sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
