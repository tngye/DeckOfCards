package sg.edu.nus.iss.DeckOfCardsRedo.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.DeckOfCardsRedo.model.Card;
import sg.edu.nus.iss.DeckOfCardsRedo.model.Deck;
import sg.edu.nus.iss.DeckOfCardsRedo.services.DoCService;

@Controller
@RequestMapping("/deck")
public class DoCController {

    @Autowired
    public DoCService svc;
    
    public String deckId;
    private Map<String,List<Card>> cards = new HashMap<>();
    
    @PostMapping
    public String deck(Model model) throws IOException{
        int deck_count = 1; 
        Deck deck = svc.createDeck(deck_count);
        deckId = deck.getDeckId();
        model.addAttribute("deckId", deckId);
        model.addAttribute("deck", deck);
        model.addAttribute("cards", List.of());
        // int deckRemaining = deck.getDeckRemaining();
        return "createDeck";
    }

    @PostMapping("/{deckId}")
    public String draw(@PathVariable String deckId, @RequestBody MultiValueMap<String, String> form, Model model){
        deckId = this.deckId;
        model.addAttribute("deckId", deckId);
        Integer count = Integer.parseInt(form.getFirst("count"));

        Deck deck = svc.drawCards(deckId, count);

        model.addAttribute("deck", deck);
        

        if(cards.containsKey(deckId)){

            List<Card> cardsExists = cards.get(deckId);
            deck.getCards().stream().forEach(c-> cardsExists.add(c));
            cards.put(deckId, cardsExists);
            deck.setCards(cards.get(deckId));
        }
        else{
            cards.put(deckId, deck.getCards());
        }
        model.addAttribute("cards", deck.getCards());

        return"createDeck";

    }
}
