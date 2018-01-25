package bean;
import java.sql.*;
import java.util.*;
import java.time.*;

public class PersonalData{

    private int  id; // String?
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
        return -1;
    }
    /*
    public static List<PersonalData> getAllPersonalData() throws SQLException{
       SqlConnection sc = new SqlConnection();
        String sql ="select * from employee";
        sc.execSql(sql);
        ResultSet rset = sc.getResultSet();
        List<PersonalData> pdlist = new ArrayList<PersonalData>();
        while(rset.next()){
          PersonalData pd = new PersonalData(
              rset.getInt(1),
              birthdayToAge(rset.getString(2)),
              rset.getString(3));
          if (pd.id!=0) pdlist.add(pd);
        }
        sc.close();
        return pdlist;
    }

    public void setDataFromId(int id) throws SQLException{
        SqlConnection sc = new SqlConnection();
        String sql ="select * from employee where id="+id;
        sc.execSql(sql);
        ResultSet rset = sc.getResultSet();
        while(rset.next()){
          this.id = rset.getInt(1);
          this.age = birthdayToAge(rset.getString(2));
          this.address = rset.getString(3);
        }
        sc.close();
    }
    */
}
