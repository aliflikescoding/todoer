import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
<<<<<<< Updated upstream
=======
import java.util.Optional;
import java.util.stream.Collectors;
>>>>>>> Stashed changes

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean removetask(String taskName) {
        return tasks.removeIf(task -> task.getName().equals(taskName));
    }

    public boolean markTaskDone(String taskName) {
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getName().equals(taskName))
                .findFirst();

        if (task.isPresent()) {
            task.get().markAsDone();
            return true;
        } else {
            return false;
        }
    }

<<<<<<< HEAD
    // Menampilkan hanya yang belum selesai
    public void showPendingTasks() {
        tasks.stream()
            .filter(task -> !task.isDone())
            .forEach(Task::displayTask);
=======
    public List<Task> showAllTasks() {
        return new ArrayList<>(tasks);
>>>>>>> 075115fa057cee02e7adc0d012ba680b2b3cb6cb
    }

    public Optional<Task> showTaskByName(String taskName) {
        // kembalikan task dengan parameter nama taskName
    }


    public List<WorkTask> getOverdueWorkTasks() {
        // kembalikan list work task yang deadline nya sudah lewat
    }

    public List<Task> showPendingTasks() {
        // Kembalikan list task yang belum selesai
    }

    public List<Task> showArchivedTasks() {
        // Kembalikan list task yang sudah diarsipkan
    }

    

}