package collections.simulator;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand {

    ArrayList<Card> hand = new ArrayList<>();

    public void addCard(Card card) {
        hand.add(card);
    }

    @Override
    public String toString() {
        return Arrays.toString(hand.toArray());
    }

    public boolean isOnePair() {
        return highestCombo().equals("pair");
    }

    public boolean isTwoPairs() {
        return highestCombo().equals("2 pairs");
    }

    public boolean isTrips() {
        return highestCombo().equals("triple");
    }

    public boolean isFullHouse() {
        return highestCombo().equals("full house");
    }

    public boolean isElse() {
        return highestCombo().equals("else");
    }

    public ArrayList<Card.CardValue> turnCardsListToValues() {
        ArrayList<Card.CardValue> cardValues = new ArrayList<>();
        for (Card card : hand) {
            cardValues.add(card.getValue());
        }
        return cardValues;
    }


    public String highestCombo() {
        ArrayList<Card.CardValue> cardValues = turnCardsListToValues();

        Integer firstSteak = 1;
        Integer secondSteak = 1;
        while (!(cardValues.size() <= 1)){
            firstSteak = lengthOfFirstStreak(cardValues);
            if (firstSteak > 1) {
                while (secondSteak == 1 && !(cardValues.size() <= 1)) {
                    secondSteak = lengthOfFirstStreak(cardValues);
                    }
            }
        }

        if (firstSteak == 2 && secondSteak == 2) {
            return "2 pairs";
        } if (firstSteak == 3 && secondSteak == 2 || firstSteak == 2 && secondSteak == 3) {
            return "full house";
        } if (firstSteak == 2) {
            return "pair";
        } if (firstSteak == 3) {
            return "triple";
        } else {
            return "else";
        }
    }

    public Integer lengthOfFirstStreak(ArrayList<Card.CardValue> cardValues) {
        Card.CardValue currentCard;
        Integer Streak = 1;

        currentCard = cardValues.get(0);
        cardValues.remove(0);
        for (int i = 0; i < cardValues.size(); i++) {
            if (currentCard.equals(cardValues.get(i))) {
                Streak++;
                cardValues.remove(i);
                i--;
            }
        }
        return Streak;
    }
}
