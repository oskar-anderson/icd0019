package collections.simulator;

public class Card implements Comparable<Card> {

    public enum CardValue { s2, s3, s4, s5, s6, s7, s8, s9, s10, J, Q, K, A }

    public enum CardSuit { C, D, H, S }

    private CardValue value;
    private CardSuit suit;

    public Card(CardValue value, CardSuit suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public boolean equals(Object obj) {
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public int compareTo(Card other) {
        int originalIndex = CardValue.valueOf(String.valueOf(this.value)).ordinal();
        int comparisonIndex = CardValue.valueOf(String.valueOf(other.value)).ordinal();
        return originalIndex - comparisonIndex;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", value, suit);
    }

    public CardValue getValue() {
        return value;
    }
}
