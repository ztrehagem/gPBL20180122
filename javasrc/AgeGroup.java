package bean;
public class AgeGroup{
   String range;
    long sum;
    int quitn;
    public AgeGroup(String range,long sum,int quitn){
        this.range = range;
        this.sum = sum;
        this.quitn = quitn;
    }
    public String getRange(){
      return this.range;
    }
    public int getQuitn(){
      return this.quitn;
    }
    public long getSum(){
      return this.sum;
    }
}
