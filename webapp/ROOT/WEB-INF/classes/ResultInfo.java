public class ResultInfo{
	private String id, name, address;
  private boolean yesno;

  ResultInfo(String id){
    this.id = id; 
    //set sample
    this.name = "NAMETEST";
    this.address = "ADDRTEST";
  }
	
  public void predict() {
      //analyze  yes or no 
      this.yesno = true;
  }  
 
  public String toString(){
    return this.id + this.name + this.address + this.yesno;
  }

  public String getId(){
    return this.id;
  }
  public String getName(){
    return this.name;
  }
  public String getAddress(){
    return this.address;
  }
  public boolean getYesno(){
    return this.yesno;
  }

}

