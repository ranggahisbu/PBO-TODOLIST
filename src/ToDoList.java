import java.io.*;
import java.util.*;


public class ToDoList {
    static final String FILE_NAME = "todolist.txt";
    static ArrayList<String> todoList = new ArrayList<>();

    public static void main(String[] args) {
        loadTodoList();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===TO DO LIST MENU===");
            System.out.println("1. Lihat Daftar List");
            System.out.println("2. Tambah List");
            System.out.println("3. Ubah List");
            System.out.println("4. Hapus List");
            System.out.println("5. Keluar");
            System.out.print("Pilih : ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showTodoList();
                    break;

                case 2:
                    addTodo(scanner);
                    break;
                case 3:
                    updateTodo(scanner);
                    break;
                case 4:
                    deleteTodo(scanner);
                    break;
                case 5:
                    System.out.println("Keluar...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid...");
            }
        } while (choice != 5);

        scanner.close();
    }



    public static void loadTodoList(){
        try{
            File file = new File(FILE_NAME);
            if (file.exists()){
                Scanner reader = new Scanner(file);
                while(reader.hasNextLine()){
                    todoList.add(reader.nextLine());
                }
                reader.close();
            }else {
            file.createNewFile();
        }
        }catch (IOException e ){
            System.out.println("Ada kesalahan saat membaca file...");
        }
    }

    public static void saveTodoList(){
        try{
            FileWriter writer = new FileWriter(FILE_NAME);
            for (String item : todoList){
                writer.write(item + "\\n");
        }
        writer.close();
    }catch (IOException e) {
        System.out.println("Gagal Menyimpan Data");
        }
    }

    public static void showTodoList(){
        System.out.println("Daftar TO DO List");
        if (todoList.isEmpty()){
            System.out.println("Belum ada TO DO List");
        }else {
            for (int i = 0; i < todoList.size(); i++){
            System.out.println((i + 1)+ ". " + todoList.get(i));
            }
        }
    }

    public static void addTodo(Scanner scanner){
        System.out.print("Masukkan list baru : ");
        String newItem = scanner.nextLine();
        todoList.add(newItem);
        saveTodoList();
        System.out.println("List ditambahkan : ");
    }

    public static void updateTodo(Scanner scanner){
        showTodoList();
        System.out.print("Pilih nomer yang mau di ubah : ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        if (index >= 0 && index < todoList.size()){
            System.out.print("Masukkan Nilai Baru : ");
            String newValue = scanner.nextLine();
            todoList.set(index, newValue);
            saveTodoList();
            System.out.println("List diperbarui...");
        }else {
            System.out.println("Nomer tidak valid...");
        }
    }

    public static void deleteTodo(Scanner scanner){
        showTodoList();
        System.out.print("Pilih nomer yang ingin di hapus : ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        if (index >= 0 && index < todoList.size()){
            todoList.remove(index);
            saveTodoList();
            System.out.println("List dihapus...");
        }else {
            System.out.println("Nomer tidak valid...");
        }
    }
}
