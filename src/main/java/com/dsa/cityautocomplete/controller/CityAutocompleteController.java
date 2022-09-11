package com.dsa.cityautocomplete.controller;

import com.dsa.cityautocomplete.datastructures.IMTester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityAutocompleteController {

    @GetMapping("/autocompleteUsingTrie/{str}")
    public String autocompleteUsingTrie(@PathVariable String str){
        return IMTester.testMatches(str);
    }

    @GetMapping("/containsCityUsingTrie/{str}")
    public String containsCityUsingTrie(@PathVariable String str){
        return IMTester.testContains(str);
    }

    @GetMapping("/longestPrefixCityUsingTrie/{str}")
    public String longestPrefixCityUsingTrie(@PathVariable String str){
        return IMTester.testPrefix(str);
    }

    @GetMapping("/suffixSearch/{str}")
    public boolean suffixSearch(@PathVariable String str){
        return IMTester.suffixSearch(str);
    }

    @GetMapping("/kmpSearch/{str}")
    public boolean kmpSearch(@PathVariable String str){
        return IMTester.kmpSearch(str);
    }
}
