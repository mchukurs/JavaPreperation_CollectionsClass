package com.chukurs;

import java.util.*;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Card aceOfHearts = Card.getFaceCard(Card.Suit.HEART,'A');
        Card kingOfClubs = Card.getFaceCard(Card.Suit.CLUB,'K');

        List<Card> acesOfHearts = Collections.nCopies(13,aceOfHearts);
        //Card.printDeck(acesOfHearts,"Aces of hearts",1);

        List<Card> kingsOfClubs = Collections.nCopies(13,kingOfClubs);
        //Card.printDeck(kingsOfClubs,"Kings of clubs",1);

        //
        Card[] cardArray = new Card[13];
        Arrays.fill(cardArray,aceOfHearts);


        List<Card> cards= new ArrayList<>(52);
        //Card.printDeck(cards);

        Collections.addAll(cards,cardArray);
        //Card.printDeck(cards,"cards list with aces", 1);

        Collections.copy(cards, kingsOfClubs);
        //Card.printDeck(cards,"cards list with kings", 1);

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);
        Collections.shuffle(deck);
        Card.printDeck(deck,"before sort",13);
        //this can be done on Comparator class, but actually the List.sort() is called anyway
        //also the lambda can be replaced with method reference
        //comparator takes in the suit, which is ENUM and the order it uses for sorting is as-defined
        var sortingAlgorithm = Comparator.comparing((Card s)-> s.rank()).thenComparing((Card s)-> s.suit());
        Collections.sort(deck,sortingAlgorithm);
        Card.printDeck(deck,"after sort",13);

        //Collections.reverse(deck);
        Card.printDeck(deck,"after reverse, ready to check for sublist of tens",13);


        List<Card> kings = new ArrayList<>( deck.subList(4,8));
        Card.printDeck(kings,"only kings", 1);

        List<Card> tens = new ArrayList<>( deck.subList(16,20));
        Card.printDeck(tens,"only tens", 1);

        int indexOfSublist = Collections.indexOfSubList(deck,tens);
        System.out.println("Index of sublist is: "+ indexOfSublist);
        System.out.println("List contains all items from sublist: "+ deck.containsAll(tens));

        //Collections.shuffle(deck);

//        System.out.println("After shuffle checking if list contains all items from sublist: "+deck.containsAll(tens));
//        int indexOfSublist2 = Collections.indexOfSubList(deck,tens);
//        System.out.println("Index of TENS after shuffling deck: "+ indexOfSublist2);

        boolean disJoint = Collections.disjoint(deck,tens);
        System.out.println("Have no elements in common: "+disJoint);

        deck.sort(sortingAlgorithm);
        Card tenOfHeart = Card.getNumericCard(Card.Suit.HEART,10);
        Card oneOfSpade = Card.getNumericCard(Card.Suit.SPADE,1);
        Card tenOfSpade = Card.getNumericCard(Card.Suit.SPADE,10);

        int indexOfHeart = Collections.binarySearch(deck,tenOfHeart, sortingAlgorithm);
        System.out.println("Matched index: "+ indexOfHeart);
        System.out.println(deck.get(indexOfHeart));
        System.out.println(deck.indexOf(tenOfHeart));

        Card tenOfClubs = Card.getNumericCard(Card.Suit.CLUB,10);
//        Collections.replaceAll(deck, tenOfHeart, tenOfClubs);
//        Collections.replaceAll(deck, tenOfSpade, tenOfClubs);
//        Collections.replaceAll(deck, oneOfSpade, tenOfClubs);
        Card.printDeck(deck.subList(32,36), "32 to 36 tens row",1);
        System.out.println(Collections.frequency(deck, tenOfClubs));

        System.out.println("Best card in the deck: "+ Collections.max(deck, sortingAlgorithm));
        System.out.println("Worst card in the deck: "+ Collections.min(deck, sortingAlgorithm));
        var sortBySuit = Comparator.comparing((Card s)->s.suit()).thenComparing((s)->s.rank());
        deck.sort(sortBySuit);
        Card.printDeck(deck,"Sorted by suit",4);

        List<Card> copied = new ArrayList<>(deck.subList(0,13));
        Collections.rotate(copied,2);
        Card.printDeck(deck.subList(0,13),"non-rotated",1);
        Card.printDeck(copied," rotated ",1);
        copied = new ArrayList<>(deck.subList(0,13));

        Card.printDeck(copied,"Before swapping ",1);
        for(int i =0;i< copied.size()/2;i++){

            Collections.swap(copied,i,copied.size()-1-i);
        }
        Card.printDeck(copied,"After swapping ",1);
        copied = new ArrayList<>(deck.subList(0,13));
        Card.printDeck(copied,"Before reverse ",1);
        Collections.reverse(copied);
        Card.printDeck(copied,"After reverse ",1);




    }

}
