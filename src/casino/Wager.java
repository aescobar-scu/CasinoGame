package casino;
import java.util.*;


public class Wager {

    private String playerName;
    private String wagerType;
    private int payoutOdds;
    private List<Integer> betNumber;
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
    public Wager(String playerName, String wagerType, double wagerAmount, List<Integer> betNumber) {
        this.playerName = playerName;
        this.wagerType = wagerType;
        payoutOdds = wagerTypePayoutMap.get(this.wagerType);
        this.wagerAmount = wagerAmount;
        this.betNumber = betNumber;
    }

    public Wager(String playerName, String wagerType, double wagerAmount) {
        this.playerName = playerName;
        this.wagerType = wagerType;
        payoutOdds = wagerTypePayoutMap.get(this.wagerType);
        this.wagerAmount = wagerAmount;
    }

    public Wager(String playerName) {
        this.playerName = playerName;
    }


    // getters
    // this is the screenName
    public String getPlayerName() {
        return playerName;
    }

    public String getWagerType() {
        return wagerType;
    }

    public int getPayoutOdds() {
        return payoutOdds;
    }

    public List<Integer> getBetNumber() {
        return betNumber;
    }

    public double getWagerAmount() {
        return wagerAmount;
    }

    // setters
    public void setWagerType(String wagerType) {
        this.wagerType = wagerType;
        payoutOdds = wagerTypePayoutMap.get(this.wagerType);
    }

    public void setBetNumber(List<Integer> betNumber) {
        this.betNumber = betNumber;
    }

    public void setWagerAmount(double wagerAmount) {
        this.wagerAmount = wagerAmount;
    }


    // this is for compiling test
    public static void main(String[] args){
        List<Integer> content = new ArrayList<>();
        content.add(3);
        content.add(4);

//        Wager myWager = new Wager("stone", "Split", 50.1, content);
//        System.out.println(myWager.getPlayerName());
//        System.out.println(myWager.getWagerType());
//        System.out.println(myWager.getWagerAmount());
//        System.out.println(myWager.getPayoutOdds());
//        System.out.println(myWager.getBetNumber());


        Wager wager2 = new Wager("stone");
//        wager2.setWagerType("split");
        wager2.setWagerAmount(69.99);
        wager2.setBetNumber(content);
        System.out.println(wager2.getPlayerName());
        System.out.println(wager2.getWagerType());
        System.out.println(wager2.getWagerAmount());
        System.out.println(wager2.getPayoutOdds());
        System.out.println(wager2.getBetNumber());
    }
}



