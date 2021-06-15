package com.dsa.cityautocomplete.datastructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class IMTester {
    String[] mainList;
    static Trie trie;

    public IMTester(String filename) {
        loadInfo(filename);

        trie = new Trie();
        trie.loadKeys(new java.util.ArrayList<String>(Arrays.asList(mainList)));
        System.out.println("The trie should now contain " + mainList.length + " words.");
    }

    private void loadInfo(String filename) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filename));
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        mainList = new String[Integer.parseInt(sc.nextLine())];
        for(int i = 0; i<mainList.length; i++) mainList[i] = sc.nextLine();
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

    public static void main(String args) {
        String filename = "C:\\Ankit\\City Autocomplete\\city-autocomplete\\src\\main\\java\\com\\dsa\\cityautocomplete\\datastructures/firstHundred.txt";
        IMTester test = new IMTester(filename);
    }

}
