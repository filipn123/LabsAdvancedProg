package lab5.Zad2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Anagrams {
    
    public static void main(String[] args) {
        findAll(System.in);
    }
    
    public static void findAll(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);
        List<String> words = new ArrayList<>();
        
        while (sc.hasNextLine()) {
            words.add(sc.nextLine().trim());
        }
        
        LinkedHashMap<String, List<String>> map = new LinkedHashMap<>();
        
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }
        
        for (List<String> group : map.values()) {
            if(group.size() >= 5) {
                Collections.sort(group);
                System.out.println(String.join(" ", group));
            }
        }
    }
}
