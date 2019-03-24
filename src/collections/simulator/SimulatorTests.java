package collections.simulator;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.*;

import static collections.simulator.Card.CardSuit.*;
import static collections.simulator.Card.CardValue.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimulatorTests {

    @Test
    public void onePair() {
        Hand hand = new Hand();

        hand.addCard(new Card(s7, C));
        hand.addCard(new Card(s2, H));
        hand.addCard(new Card(s3, C));
        hand.addCard(new Card(s3, D));
        hand.addCard(new Card(J, D));

        assertTrue(hand.isOnePair());


    }

    @Test
    public void handKnowsWhetherItContainsOnePair() {
        Hand hand = new Hand();

        hand.addCard(new Card(K, S));
        hand.addCard(new Card(A, H));

        assertFalse(hand.isOnePair());

        hand.addCard(new Card(A, S));

        assertTrue(hand.isOnePair());

        hand.addCard(new Card(A, D));

        assertTrue(hand.isTrips());

        assertFalse(hand.isOnePair());
    }

    @Test
    public void calculatesProbabilityOfOnePairUsingSimulation() {

        Simulator simulator = new Simulator();

        Map<HandType, Double> map = simulator.calculateProbabilities();

        // https://en.wikipedia.org/wiki/Poker_probability#Frequency_of_5-card_poker_hands

        assertThat(map.get(HandType.PAIR), is(closeTo(42.3)));
    }

    @Test // 4 points instead of 5 if this test fails
    public void cardsCanBeCompared() {
        assertThat(new Card(A, C).compareTo(new Card(A, S)), is(0));
        assertThat(new Card(A, C).compareTo(new Card(K, S)), is(1));
        assertThat(new Card(K, H).compareTo(new Card(A, H)), is(-1));
    }

    @Test // 4 points instead of 5 if this test fails
    public void cardsCanBeSorted() {
        List<Card> cards = Arrays.asList(
                new Card(K, H),
                new Card(Q, H),
                new Card(J, H),
                new Card(Q, S),
                new Card(A, C));

        Collections.sort(cards);

        assertThat(cards.toString(),
                is("[(J, H), (Q, H), (Q, S), (K, H), (A, C)]"));
    }

    @Test // 4 points instead of 5 if this test fails
    public void handKnowsItsType() {
        Hand hand = new Hand();

        hand.addCard(new Card(s2, H));
        hand.addCard(new Card(J, S));
        hand.addCard(new Card(s2, S));
        hand.addCard(new Card(J, H));
        hand.addCard(new Card(J, C));

        assertTrue(hand.isFullHouse());

        assertFalse(hand.isOnePair());
        assertFalse(hand.isTwoPairs());
        assertFalse(hand.isTrips());
    }

    @Test // 4 points instead of 5 if this test fails
    public void calculatesProbabilitiesUsingSimulation() {

        Simulator simulator = new Simulator();

        Map<HandType, Double> map = simulator.calculateProbabilities();

        assertThat(map.get(HandType.PAIR), is(closeTo(42.3)));
        assertThat(map.get(HandType.TWO_PAIRS), is(closeTo(4.8)));
        assertThat(map.get(HandType.THREE_OF_A_KIND), is(closeTo(2.1)));
        assertThat(map.get(HandType.FULL_HOUSE), is(closeTo(0.1)));
    }

    private Matcher<Double> closeTo(double value) {
        double precision = 0.1;

        return Matchers.closeTo(value, precision);
    }
}
