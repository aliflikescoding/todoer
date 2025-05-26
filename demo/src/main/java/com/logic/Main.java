public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // Membuat beberapa task
        Task task1 = new WorkTask("Laporan PBO", "Selesaikan laporan tugas besar PBO");
        Task task2 = new PersonalTask("Olahraga", "Jogging pagi selama 30 menit");
        Task task3 = new Shopping("Belanja mingguan", "Beli sayur dan kebutuhan dapur");

        // Menambahkan ke task manager
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        // Menampilkan semua task
        System.out.println("===== Semua Tugas =====");
        taskManager.showAllTasks();

        // Tandai salah satu task sebagai selesai
        System.out.println(">> Menandai 'Olahraga' sebagai selesai...\n");
        taskManager.markTaskDone("Olahraga");

        // Tampilkan task yang masih belum selesai
        System.out.println("===== Tugas Belum Selesai =====");
        taskManager.showPendingTasks();
    }
}
