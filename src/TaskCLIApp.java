import services.TaskService;

public class TaskCLIApp {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        switch (args[0]){
            case "add":
                service.add(args[1]);
                System.out.println("Success!");
                break;
            case "list":
                if(args.length==1){
                    service.list(null);
                    System.out.println("Success!");
                }else  service.list(args[1]);
                break;
            case "mark":
                service.update(Integer.parseInt(args[1]), args[2]);
                System.out.println("Success!");
                break;
            case "delete":
                service.delete(Integer.parseInt(args[1]));
                System.out.println("Success!");
                break;
            default:
                return;
        }
    }
}