package com.jaminbucur.assignment2.api_prototype;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class ApiPrototypeApplication {

	public static void main(String[] args) {
		getRandomBibleVerse();
	}
	public static void getRandomBibleVerse() {
        try {
            String url = "https://bible-api.com/?random=verse";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonVerse = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonVerse);

            String book_name = root.findValue("book_name").asText();
            String chapter = root.findValue("chapter").asText();
			String verse = root.findValue("verse").asText();
            String text = root.findValue("text").asText();

            System.out.println("**********Random Bible Verse********");
            System.out.println(text + book_name + " " + chapter + ":" + verse);

        } catch (JsonProcessingException ex) {
            Logger.getLogger(ApiPrototypeApplication.class.getName()).log(
                    Level.SEVERE,
                    null, ex);
            System.out.println("error in getRandomBibleVerse");

        }
    }

}
