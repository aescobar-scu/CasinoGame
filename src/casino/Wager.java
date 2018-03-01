package casino;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class Wager {

    private String ownerId;
    private String wagerType;
    private int payoutOdds;
    private List<Integer> wagerContent = new ArrayList<Integer>();
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
            put("Reds", 1);
            put("Blacks", 1);
            put("Highs", 1);
            put("Lows", 1);
            put("Odds", 1);
            put("Evens", 1);
        }
    };

    // constructors
    // integer content are list of legal ints for the wager type
    public Wager(String id, String type, double amount, List<Integer> content) {
        ownerId = id;
        wagerType = type;
        payoutOdds = wagerTypePayoutMap.get(wagerType);
        wagerAmount = amount;
        wagerContent = content;
    }
    // some wagers like red/black do not have integer content
    public Wager(String id, String type, double amount) {
        ownerId = id;
        wagerType = type;
        payoutOdds = wagerTypePayoutMap.get(wagerType);
        wagerAmount = amount;
        wagerContent = null;
    }

    public Wager(String id) {
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

    public List<Integer> getWagerContent() {
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

    public void setWagerContent(List<Integer> content) {
        wagerContent = content;
    }

    public void setWagerAmount(double amount) {
        wagerAmount = amount;
    }

}
