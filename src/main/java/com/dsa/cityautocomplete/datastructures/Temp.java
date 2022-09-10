package com.dsa.cityautocomplete.datastructures;

import java.util.*;

public class Temp
{
    private TrieNode root;

    public Temp()
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
            cur = cur.children[(int)key.charAt(i)];
        }

        if(cur != null)
            cur.endOfKey = true;
    }


    public void loadKeys(ArrayList<String> keys)
    {
        for (int i = 0; i < keys.size(); i++)
        {
            insert(keys.get(i));
        }
        return;
    }

    private TrieNode getPrefixNode(String key)
    {

        TrieNode maxNode = root;

        for(int i = 0; i < key.length(); i++){
            if(maxNode.children[(int)key.charAt(i)] != null)
                maxNode = maxNode.children[(int)key.charAt(i)];
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

        for(TrieNode node : cur.children){
            list.addAll(recursivePrefix(node));

            if(node != null && node.endOfKey)
                list.add(node.getUpperString(node));
        }

        return list;
    }


    private class TrieNode
    {

        public static final int NUMCHILDREN = 256;

        private TrieNode   parent;
        private TrieNode[] children;
        private int        depth;
        private char       charInParent;
        private boolean endOfKey;

        public TrieNode()
        {
            children = new TrieNode[NUMCHILDREN];
            endOfKey = false;
            depth = 0;
            charInParent = (char)0;
        }

        public TrieNode createChild(char  c)
        {
            TrieNode child       = new TrieNode();

            int index = (int)c;

            if(children[index] == null){
                this.children[index] = child;

                child.charInParent = c;
                child.depth = this.depth + 1;
                child.parent = this;

                child.endOfKey = false;

            }

            return child;
        }

        public TrieNode getChild(char c)
        {
            return children[ c ];
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
