package collections.simulator;

import java.util.*;
import static collections.simulator.Card.CardSuit.*;
import static collections.simulator.Card.CardValue.*;
import static collections.simulator.HandType.*;

public class Simulator {

    private ArrayList<ArrayList> deck;
    private Map<HandType, Integer> frequencyMap;
    private int numberOfSimulations = 100000;

    public Simulator() {

        frequencyMap = new HashMap<>();

        frequencyMap.put(PAIR, 0);
        frequencyMap.put(TWO_PAIRS, 0);
        frequencyMap.put(THREE_OF_A_KIND, 0);
        frequencyMap.put(FULL_HOUSE, 0);

        for (int outerCycle = 0; outerCycle < numberOfSimulations; outerCycle++) {
            Hand hand = new Hand();

            this.deck = createDeck();
            Collections.shuffle(this.deck);

            // System.out.println(this.deck.get(0).get(0).getClass());  // class collections.simulator.Card$CardValue
            // System.out.println(this.deck.get(0).get(1).getClass());  // class collections.simulator.Card$CardSuit
            // Card.CardValue value = (Card.CardValue) this.deck.get(i).get(0); // Why does it turn into an object and needs to be casted?

            for (int i = 0; i < 5; i++) {
                Card.CardValue value = (Card.CardValue) this.deck.get(i).get(0);
                Card.CardSuit suit = (Card.CardSuit) this.deck.get(i).get(1);
                hand.addCard(new Card(value, suit));
            }

            if (hand.isOnePair()) {
                frequencyMap.put(PAIR, frequencyMap.get(PAIR) + 1);
            } if (hand.isTrips()) {
                frequencyMap.put(THREE_OF_A_KIND, frequencyMap.get(THREE_OF_A_KIND) + 1);
            } if (hand.isFullHouse()) {
                frequencyMap.put(FULL_HOUSE, frequencyMap.get(FULL_HOUSE) + 1);
            } if (hand.isTwoPairs()) {
                frequencyMap.put(TWO_PAIRS, frequencyMap.get(TWO_PAIRS) + 1);
            }
        }
        System.out.println(frequencyMap + " with " + numberOfSimulations + " cicles.");
    }

    public Map<HandType, Double> calculateProbabilities() {
        Map<HandType, Double> probabilityMap = new HashMap<>();
        for (HandType handValue : frequencyMap.keySet()) {
            probabilityMap.put(handValue, (double) frequencyMap.get(handValue) * 100 / numberOfSimulations);
        }
        System.out.println(probabilityMap);
        return probabilityMap;
    }

    public ArrayList<ArrayList> createDeck() {
        ArrayList<ArrayList> primaryList = new ArrayList<>();
        ArrayList<Object> secondaryList = new ArrayList<>();

        for (Card.CardValue value : Card.CardValue.values()) {
            for (Card.CardSuit suit : Card.CardSuit.values()) {
                secondaryList.addAll(Arrays.asList(value, suit));
                primaryList.add(secondaryList);
                secondaryList = new ArrayList<>();
            }
        }

        return primaryList;
    }
}
