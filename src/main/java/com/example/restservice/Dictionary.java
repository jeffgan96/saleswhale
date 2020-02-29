package com.example.restservice;

import com.example.restservice.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class Dictionary {

    private static Set<String> dictionary = new HashSet<>();

    @PostConstruct
    private void init() {
        StringBuilder sb = new StringBuilder();
        sb.append(Common.ENV_PATH);
        sb.append(Common.FILE_DICTIONARY);

        parseDictionary(sb.toString());
    }

    public static String size() {
        return String.valueOf(dictionary.size());
    }

    private static void parseDictionary(String s) throws ResourceNotFoundException {
        File file = new File(s);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String next = "";
            while ((next = br.readLine()) != null) {
                dictionary.add(next.toUpperCase());
            }
            br.close();
        } catch (IOException e) {
            throw new ResourceNotFoundException("File not found: " + s);
        }
    }

    public static boolean isValidDictionaryWord(String s) {
        return dictionary.contains(s);
    }

}
