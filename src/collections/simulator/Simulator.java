package collections.simulator;

import java.util.*;
import static collections.simulator.HandType.*;
import org.apache.commons.lang3.SerializationUtils;


public class Simulator {

    private Map<HandType, Integer> frequencyMap;
    private int numberOfSimulations = 700000;

    public Simulator() {

        frequencyMap = new HashMap<>();

        frequencyMap.put(PAIR, 0);
        frequencyMap.put(TWO_PAIRS, 0);
        frequencyMap.put(THREE_OF_A_KIND, 0);
        frequencyMap.put(FULL_HOUSE, 0);

        for (int outerCycle = 0; outerCycle < numberOfSimulations; outerCycle++) {
            Hand hand = new Hand();

            ArrayList<ArrayList> deck = createDeck();
            Collections.shuffle(deck);

            // System.out.println(this.deck.get(0).get(0).getClass());  // class collections.simulator.Card$CardValue
            // System.out.println(this.deck.get(0).get(1).getClass());  // class collections.simulator.Card$CardSuit
            // Card.CardValue value = (Card.CardValue) this.deck.get(i).get(0); // Why does it turn into an object and needs to be casted?

            for (int i = 0; i < 5; i++) {
                Card.CardValue value = (Card.CardValue) deck.get(i).get(0);
                Card.CardSuit suit = (Card.CardSuit) deck.get(i).get(1);
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
        // System.out.println(frequencyMap + " with " + numberOfSimulations + " iterations.");
    }

    public Map<HandType, Double> calculateProbabilities() {
        Map<HandType, Double> probabilityMap = new HashMap<>();
        for (HandType handValue : frequencyMap.keySet()) {
            probabilityMap.put(handValue, (double) frequencyMap.get(handValue) * 100 / numberOfSimulations);
        }
        // System.out.println(probabilityMap);
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
