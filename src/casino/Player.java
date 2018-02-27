package casino;

public class Player {

	  private String name;
	  private int account;
	  Player(String name, int account){
	    this.name = name;
	    this.account = account;
	  }
	  Player(){
	    name = "Blank";
	    account = 0;
	  }
	  public void setName(String n){
	    this.name = n;
	  } 
	  public String getName(){
	    return name;
	  }
	  public void setAccount(int a){
	    this.account = a;
	  } 
	  public int getAccount(){
	    return account;
	  }  
}
