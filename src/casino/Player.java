package casino;

public class Player {

	  private String name;
	  private String nickName;
	  private double account;

	  Player(String name, String nickName, double account){
	    this.name = name;
	    this.nickName = nickName;
	    this.account = account;
	  }

	  Player(){
	    name = "Blank";
	    nickName = "Blank";
	    account = 0;
	  }

	  public void setName(String n){
	    this.name = n;
	  }
	  public String getName(){
	    return name;
	  }
	  public void setNickName(String nn) {
		  this.nickName = nn;
	  }
	  public String getNickName() {
		  return nickName;
	  }
	  public void setAccount(double a){
	    this.account = a;
	  }
	  public double getAccount(){
	    return account;
	  }
}
