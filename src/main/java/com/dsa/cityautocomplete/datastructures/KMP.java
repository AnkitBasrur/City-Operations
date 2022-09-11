package com.dsa.cityautocomplete.datastructures;

public class KMP {

    char[] patternArr;
    char[] textArr;

    int matchTable[];

    public KMP(String pattern, String text) {

        this.patternArr = pattern.toCharArray();
        this.textArr = text.toCharArray();
        this.matchTable = createMatchTable();

    }

    public int search(String pattern, String text) {

        int s = 0;
        int t = 0;

        while( t + s < textArr.length){

            if( patternArr[s] == textArr[t+s]){
                s++;
                if( s == patternArr.length)
                    return t;
            }
            else if( matchTable[s] == -1){
                s = 0;
                t = t + s + 1;
            }
            else{
                t = t + s - matchTable[s];
                s = matchTable[s];
            }
        }
        return -1;
    }

    public int[] createMatchTable(){

        int[] matchT = new int[textArr.length];

        matchT[0] = -1;
        matchT[1] = 0;
        int j = 0;
        int pos = 2;

        while( pos < matchT.length){
            if(textArr[pos-1] == textArr[j]){
                matchT[pos] = j + 1;
                pos++;
                j++;
            }
            else if( j > 0){
                j = matchT[j];
            }
            else{
                matchT[pos] = 0;
                pos++;
            }
        }

        return matchT;
    }

}