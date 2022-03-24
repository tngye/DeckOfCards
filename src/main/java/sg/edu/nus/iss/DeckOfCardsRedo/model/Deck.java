package sg.edu.nus.iss.DeckOfCardsRedo.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Deck {

    private String deckId;
    private int deckRemaining;
    private List<Card> cards;

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public int getDeckRemaining() {
        return deckRemaining;
    }

    public void setDeckRemaining(int deckRemaining) {
        this.deckRemaining = deckRemaining;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Deck create(String body) throws IOException {
        JsonObject data = null;
        try (InputStream is = new ByteArrayInputStream(body.getBytes())) {
            JsonReader reader = Json.createReader(is);
            data = reader.readObject();
        } catch (Exception ex) {

        }

        Deck d = new Deck();
        d.setDeckId(data.getString("deck_id"));
        d.setDeckRemaining(data.getInt("remaining"));

        return d;
    }

    public Deck draw(String body) {
        

        JsonObject data = null;
        try (InputStream is = new ByteArrayInputStream(body.getBytes())) {
            JsonReader reader = Json.createReader(is);
            data = reader.readObject();
        } catch (Exception ex) {

        }
        System.out.println(">>>data:  " + data);

        Deck d = new Deck();
        d.setDeckId(data.getString("deck_id"));
        d.setDeckRemaining(data.getInt("remaining"));

        JsonArray cardsArr = data.getJsonArray("cards");
        System.out.println(">>>cardsArr:  " + cardsArr);
        List<Card> cards = new ArrayList<Card>();

        for (int i = 0; i < cardsArr.size(); i++) {
            JsonObject cardDetails = cardsArr.getJsonObject(i);
            System.out.println(">>>cardDetails:  " + cardDetails);
            cards.add(Card.create(cardDetails));
        }

        d.setCards(cards);
        System.out.println(">>>cards:  " + cards);

        return d;
    }

}
