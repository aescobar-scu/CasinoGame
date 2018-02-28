package casino;

import java.util.*;

public class Wager {
    
    private int ownerId;
    private String wagerType;
    private int payoutOdds;
    private ArrayList<Integer> wager;
    private double wagerAmount;
    private double winLoseAmount;
    private final Map<String, Integer> wagerTypePayoutMap = Map.of("Straight", 35, "Split", 17, "Street", 11, "Square", 8, "Six Line", 5, "Red", 1, "Black", 1, "Dozens", 2, "Highs", 1, "Lows", 1, "Odds", 1, "Evens", 1, "Columns", 2);
    
    
    // constructor 1
    public Wager(int id) {
        ownerId = id;
    }
    
    public Wager(int id, String type, double amount, ArrayList<Integer> wager) {
        ownerId = id;
        wagerType = type;
        payoutOdds = wagerTypePayoutMap.get(wagerType);
        wagerAmount = amount;
        wager = wager; 
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
    
    public double getWinLoseAmount() {
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
    
    public void setWinLoseAmount(boolean win) {
        winLoseAmount = wagerAmount * payoutOdds;
        if (!win) {
            winLoseAmount = -winLoseAmount;
        }
    }
    
}
