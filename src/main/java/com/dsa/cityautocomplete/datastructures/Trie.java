package com.dsa.cityautocomplete.datastructures;

import com.dsa.cityautocomplete.model.City;

import java.util.*;

public class Trie
{
    private TrieNode root;

    public Trie()
    {
        root = new TrieNode();
    }

    public TrieNode getRoot(){
        return root;
    }

    public void insert(String key)
    {

        TrieNode cur = root;

        for(int i = 0; i < key.length(); i++){
            cur.createChild(key.charAt(i));
            cur = cur.children.get(key.charAt(i));
        }

        if(cur != null)
            cur.endOfKey = true;
    }


    public void loadKeys(City[] keys)
    {
        for (int i = 0; i < keys.length; i++)
        {
            insert(keys[i].getName());
        }
        return;
    }

    private TrieNode getPrefixNode(String key)
    {

        TrieNode maxNode = root;

        for(int i = 0; i < key.length(); i++){
            if(maxNode.children.get(key.charAt(i)) != null)
                maxNode = maxNode.children.get(key.charAt(i));
            else
                return maxNode;
        }

        return maxNode;
    }

    public String getPrefix(String key)
    {
        return getPrefixNode(key).toString();
    }

    public boolean contains(String key)
    {

        if(getPrefix(key).length() != key.length())
            return false;

        return getPrefixNode(key).endOfKey;
    }

    public ArrayList<String> getAllPrefixMatches( String prefix )
    {
        ArrayList<String> stringList = new ArrayList<String>();

        TrieNode cur = getPrefixNode(prefix);
        String tmp = cur.toString();

        if(tmp.length() != prefix.length())
            return stringList;

        if(cur.endOfKey){
            stringList.add(tmp);
        }
        stringList.addAll(recursivePrefix(cur));

        return stringList;
    }

    public ArrayList<String> recursivePrefix(TrieNode cur){
        ArrayList<String> list = new ArrayList<String>();

        if(cur == null)
            return list;

        for(Map.Entry<Character, TrieNode> node : cur.children.entrySet()){
            list.addAll(recursivePrefix(node.getValue()));

            if(node != null && node.getValue().endOfKey)
                list.add(node.getValue().getUpperString(node.getValue()));
        }

        return list;
    }

    private class TrieNode
    {

        private TrieNode   parent;
        private Map<Character, TrieNode> children;
        private int        depth;
        private char       charInParent;
        private boolean endOfKey;

        public TrieNode()
        {
            children = new HashMap<>();
            endOfKey = false;
            depth = 0;
            charInParent = (char)0;
        }

        public TrieNode createChild(char  c)
        {
            TrieNode child = new TrieNode();

            if(!children.containsKey(c)){
                this.children.put(c, child);

                child.charInParent = c;
                child.depth = this.depth + 1;
                child.parent = this;

                child.endOfKey = false;

            }

            return child;
        }

        public TrieNode getChild(char c)
        {
            return children.get(c);
        }

        public boolean isEndOfKey()
        {
            return endOfKey;
        }

        public void setEndOfKey(boolean endOfKey)
        {
            this.endOfKey = endOfKey;
        }

        public String toString()
        {
            return this.getUpperString(this);
        }

        public String getUpperString(TrieNode input){

            if(input.parent == null)
                return "";

            return getUpperString(input.parent) + input.charInParent;
        }
    }


}
