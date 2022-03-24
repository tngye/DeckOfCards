package sg.edu.nus.iss.DeckOfCardsRedo.services;

import java.io.IOException;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.JsonObject;
import sg.edu.nus.iss.DeckOfCardsRedo.model.Deck;

@Service
public class DoCService {
    
    public Deck createDeck(int deck_count) throws IOException{
        String url = UriComponentsBuilder
        .fromUriString("https://deckofcardsapi.com/api/deck/new/shuffle/")
        .queryParam("deck_count", deck_count)
        .toUriString();


        RequestEntity req = RequestEntity.get(url).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        System.out.println(">>>>resp: " + resp);

        Deck d = new Deck();
        d = d.create(resp.getBody());

        System.out.println(">>>>d: " + d);

        return d;
    
    }

    public Deck drawCards(String deckId, Integer count) {
        String url = UriComponentsBuilder
        .fromUriString("https://deckofcardsapi.com/api/deck/")
        .path(deckId + "/draw/")
        .queryParam("count", count)
        .toUriString();

        RequestEntity req = RequestEntity.get(url).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        

        Deck d = new Deck();
        d = d.draw(resp.getBody());
        System.out.println(">>>>d2: " + d);
        return d;
    }
}
