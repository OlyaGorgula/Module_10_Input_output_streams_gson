import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CountFrequencyOfWords {
    public static void main(String[] args) {
        String absolutePath= "words.txt";
        File file = new File(absolutePath);

        if(!file.exists()) {
            throw new RuntimeException("File with name "+file.getName()+" not exists");
        }

        HashMap<String, Integer>  listWords = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {

               String[] words = line.split(" ");
               for (String word: words){
                   int count = listWords.get(word) == null ? 0 : listWords.get(word);
                   count++;
                   listWords.put(word, count);
               }

                line = reader.readLine();
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Map<String, Integer> sortedMap = listWords.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));
        sortedMap.entrySet().forEach(System.out::println);
    }
}
