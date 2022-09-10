package com.dsa.cityautocomplete.datastructures;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dsa.cityautocomplete.model.City;

import java.io.File;
import java.io.IOException;

public class IMTester {
    City[] mainList;
    static Trie trie;

    public IMTester() throws IOException {
        loadInfo();

        trie = new Trie();
        trie.loadKeys(mainList);
        System.out.println("The trie should now contain " + mainList.length + " words.");
    }

    private void loadInfo() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        mainList = objectMapper.readValue(new File("cities.json"), City[].class);
    }

    public static String testContains(String input) {
        return "Check if trie contains "+input +" => "+ trie.contains(input);
    }

    public static String testPrefix(String input) {
        return "The longest prefix of "+ input +" => "+ trie.getPrefix(input);
    }

    public static String testMatches(String input) {
        return "Autocomplete of "+ input +" => "+ trie.getAllPrefixMatches(input).toString();
    }

    private String matchesList(String[] row) {
        String result = "";
        for(int i=1; i<row.length-1; i++) result+=row[i] + ", ";
        if(row.length>1) result+=row[row.length-1];
        return result;
    }

    public static void main(String args) throws IOException {
        IMTester test = new IMTester();
    }

}
