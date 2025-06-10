package model;

public enum TaskStatus {
    TODO, IN_PROGRESS, DONE;

    public boolean equals(String filter) {
        return switch (filter) {
            case "todo" -> this.equals(TaskStatus.TODO);
            case "in-progress" -> this.equals(TaskStatus.IN_PROGRESS);
            case "done" -> this.equals(TaskStatus.DONE);
            case null, default -> true;
        };
    }

    public static TaskStatus parseStatus(String status){
        return switch (status) {
            case "in-progress" -> TaskStatus.IN_PROGRESS;
            case "done" -> TaskStatus.DONE;
            default -> TaskStatus.TODO;
        };
    }
}
