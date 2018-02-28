package casino;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class Wager {

    private String ownerId;
    private String wagerType;
    private int payoutOdds;
    private int wagerContent;
    private double wagerAmount;
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
    public Wager(String id, String type, double amount, int content) {
        ownerId = id;
        wagerType = type;
        payoutOdds = wagerTypePayoutMap.get(wagerType);
        wagerAmount = amount;
        wagerContent = content;
    }

    public Wager(int id) {
        ownerId = id;
    }

    // getters
    public String getWagerId() {
        return ownerId;
    }

    public String getWagerType() {
        return wagerType;
    }

    public int getPayoutOdds() {
        return payoutOdds;
    }

    public int getWagerContent() {
        return wagerContent;
    }

    public double getWagerAmount() {
        return wagerAmount;
    }
    
        // setters
    public void setWagerType(String type) {
        wagerType =type;
        payoutOdds = wagerTypePayoutMap.get(wagerType);
    }

    public void setWagerContent(int content) {
        wagerContent = content;
    }

    public void setWagerAmount(double amount) {
        wagerAmount = amount;
    }

}
