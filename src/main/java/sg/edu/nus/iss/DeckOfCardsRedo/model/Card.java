package sg.edu.nus.iss.DeckOfCardsRedo.model;

import jakarta.json.JsonObject;

public class Card {
    private String image;
    private String value;
    private String suit;
    private String code;
    
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getSuit() {
        return suit;
    }
    public void setSuit(String suit) {
        this.suit = suit;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public static Card create(JsonObject cardDetails) {
        Card cards = new Card();
        cards.setCode(cardDetails.getString("code"));
        cards.setImage(cardDetails.getString("image"));
        cards.setValue(cardDetails.getString("value"));
        cards.setSuit(cardDetails.getString("suit"));
        
        return cards;
    }

    
}
