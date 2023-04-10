package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.platform.commons.annotation.Testable;


class Word implements Comparable<Word>{
    
    public String word;
    public int frequency;
    
    public Word(String word, int frequency){
        this.word = word;
        this.frequency = frequency;
    }
    
    @Override
    public int compareTo(Word w){
        if(w.frequency > this.frequency) return 1;
        
        else if (w.frequency < this.frequency) return -1;
        
        return 0;
    }
}
/**
 * 
 * @author Arthur Fasano
 * @version 4/10/2023
 *
 */
public class wordOccur {
	/**
	 * 
	 * @param args for main method
	 * @throws IOException for accessing a URL for the poem
	 */
    public static void main(String args[]) throws IOException {
    	
    	// Jsoup to get HTML DOC
    	
		Document document = Jsoup.connect("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm").get();
		Element poem = document.select(".chapter").first();
		String[] words = poem.text().split(" ");
		
    	// testing
    	// String[] words = new String[] {"foo", "baz", "bar", "foo", "foo", "baz", "baz", "baz", "bar", "bar", "bar"};
        getOccurrences(words, 20).forEach(w -> System.out.printf("%s %s\n", w.word, w.frequency));
    }
    /**
     * 
     * @param words for the poem or the words from a string array
     * @param limit for the number of words to count
     * @return a sorted list to print out
     */
    public static List<Word> getOccurrences(String[] words, int limit){
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        for(String word : words){
        	String fixedWords = word.toLowerCase().replaceAll("[^A-Za-z]+", "");
            if(map.containsKey(fixedWords)){
                map.put(fixedWords, map.get(fixedWords) + 1);
            }
            else{
                map.put(fixedWords, 1);
            }
        }
        
        List<Word> wordList = new ArrayList<Word>();
        
        map.entrySet().forEach(e -> wordList.add(new Word(e.getKey(),e.getValue())));
        Collections.sort(wordList);        

        return wordList.stream().limit(limit).toList();
    }
}