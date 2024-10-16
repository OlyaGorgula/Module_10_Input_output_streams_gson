import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {

        String absolutePath= "file.txt";
        File file = new File(absolutePath);

        if(!file.exists()) {
            throw new RuntimeException("File with name "+file.getName()+" not exists");
        }

        List<User> listUser = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            int row = 1;
            while (line != null) {

                if (row > 1){

                    String[] parts = line.split(" ");
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    User user = new User(name, age);
                    listUser.add(user);
                }

                row++;
                line = reader.readLine();
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (listUser.isEmpty()) return;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listUser);

        File fileGson = new File("user.json");
        checkIfFileAvailable(fileGson);
        try (PrintStream printStream = new PrintStream(fileGson))
        {
            printStream.println(json);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkIfFileAvailable(File file){
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                System.err.println(e.getMessage());
            }
        }
    }
}
