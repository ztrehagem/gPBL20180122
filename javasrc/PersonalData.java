package bean;
import java.sql.*;
import java.util.*;
import java.time.*;

public class PersonalData{

    private int  id;
    private int  age;
    private String address;
    private boolean quit;

    public PersonalData(int id,int age,String address){
        this.id = id;
        this.age = age;
        this.address = address;
    }

    public PersonalData(){}

    public void setQuit(boolean result){
      this.quit = result;
    }
    public int getId(){
      return this.id;
    }
    public String getAddress(){
      return this.address;
    }
    public String getQuit(){
      return this.quit==true ? "yes" : "no";
    }
    public int getAge(){
      return this.age;
    }

    public static int birthdayToAge(String birthday){
        LocalDate today = LocalDate.now();
        if(birthday!=null){
          return Period.between(LocalDate.parse(birthday), today).getYears();
        }
        return -1; //あとで直す?　ちょっと面倒な気がする...
    }
  }
