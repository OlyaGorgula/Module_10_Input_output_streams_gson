import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Bill", 30, true, 1.78);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(person);

        System.out.println(json);

    }
}

class Person {
    private String name;
    private int age;
    private boolean married;
    private double height;
    private HashMap<String, String> hashMapStr = new HashMap<>();
    private HashMap<String, Integer> hashMapInt = new HashMap<>();

    public Person(String name, int age, boolean married, double height) {
        this.name = name;
        this.age = age;
        this.married = married;
        this.height = height;
        hashMapStr.put("name", name);
        hashMapStr.put("name1", "Olya");
        hashMapInt.put("age", age);
    }

}
