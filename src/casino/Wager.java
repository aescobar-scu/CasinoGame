package casino;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class Wager {

    private int ownerId;
    private String wagerType;
    private int payoutOdds;
    private ArrayList<Integer> wager;
    private double wagerAmount;
    private double winLoseAmount;
    private static final Map<String, Integer> wagerTypePayoutMap =new HashMap<String, Integer>(){
        {
            put("Straight", 35);
            put("Split", 17);
            put("Street", 11);
            put("Square", 8);
            put("Six Line", 5);
            put("Columns", 2);
            put("Dozens", 2);
            put("Red", 1);
            put("Black", 1);
            put("Highs", 1);
            put("Lows", 1);
            put("Odds", 1);
            put("Evens", 1);
        }
    };

    // constructors
    public Wager(int id, String type, double amount, ArrayList<Integer> wager) {
        ownerId = id;
        wagerType = type;
        payoutOdds = wagerTypePayoutMap.get(wagerType);
        wagerAmount = amount;
        this.wager = new ArrayList<Integer>(wager);;
        winLoseAmount = 0.00;
    }

    public Wager(int id) {
        ownerId = id;
    }

    // getters
    public int getWagerId() {
        return ownerId;
    }

    public String getWagerType() {
        return wagerType;
    }

    public int getPayoutOdds() {
        return payoutOdds;
    }

    public ArrayList<Integer> getWager() {
        return wager;
    }

    public double getWagerAmount() {
        return wagerAmount;
    }

    public double getWinLoseAmount(boolean win) {
        if (win) {
            winLoseAmount = wagerAmount * payoutOdds;
        } else {
            winLoseAmount -= wagerAmount;
        }
        return winLoseAmount;
    }

        // setters
    public void setWagerType(String type) {
        wagerType =type;
        payoutOdds = wagerTypePayoutMap.get(wagerType);
    }

    public void setWager(ArrayList<Integer> wager) {
        wager = wager;
    }

    public void setWagerAmount(double amount) {
        wagerAmount = amount;
    }


    public static void main(String[] args){
        ArrayList<Integer> wager = new ArrayList<>(3);
        Wager myWager = new Wager(123, "Straight", 50.1, wager);
        double winOrLose = myWager.getWinLoseAmount(true);
        System.out.println(winOrLose);
    }

}
