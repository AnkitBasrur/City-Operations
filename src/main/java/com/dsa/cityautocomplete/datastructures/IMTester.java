package com.dsa.cityautocomplete.datastructures;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dsa.cityautocomplete.model.City;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IMTester {
    static City[] mainList;
    static Trie trie;
    static SuffixTree sTree;

    public IMTester() throws IOException {
        loadInfo();

//        trie = new Trie();
//        trie.loadKeys(mainList);
//        System.out.println("The trie should now contain " + mainList.length + " words.");
        buildTree();
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

    public void buildTree() {
        sTree = new SuffixTree(mainList[0].getName());
        List<SuffixTree.Node> links = new ArrayList<>();
        for (City city : mainList){
            if (!city.getName().equals("")) {
                links = sTree.createSuffixTree(city.getName(), sTree.root, links);
            }
        }
        return;
    }

    public static boolean suffixSearch(String str){
        return sTree.search(str);
    }

    public static boolean kmpSearch(String str){
        boolean result = false;

        for(City city : mainList){
            if(city.getName().length()<str.length())
                continue;
            KMP kmp = new KMP(str, city.getName());
            if(kmp.search(str, city.getName())!=-1)
                return true;
        }
        return false;
    }

}
