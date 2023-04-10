package application;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PipedInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;

/**
 * 
 * @author Arthur Fasano
 * @version 4/10/2023
 *
 */
public class MainSceneController {
	@FXML
	private TextArea txtAreaUI;
	@FXML
	private TextField tfWords;
	



	/**
	 * Button event
	 * @param event for the button to be clicked
	 * @throws IOException accessing a file or a URL for the poem
	 */
	@FXML
	public void btnOKClicked(ActionEvent event) throws IOException {
		
		// parse number from GUI
		String text = tfWords.getText();
		
		// wordOccur.getOccurrences(words, Integer.valueOf(text)).forEach(w -> result, w.word, w.frequency));
		
		Document document = Jsoup.connect("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm").get();
		Element poem = document.select(".chapter").first();
		String[] words = poem.text().split(" ");
		wordOccur.getOccurrences(words, Integer.valueOf(text)).forEach(w -> System.out.printf("%s %s\n", w.word, w.frequency));
		
		// output console text to GUI
		txtAreaUI.setText(System.out + "\n");
	}
	


}
