/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
*/
import java.sql.*;

public class EmployeePersonaldata{

    private String employee_id;
    private String birthday;
    private String address;
    
    EmployeePersonaldata( String employee_id /*String birthday,String address */) {
      this.employee_id = employee_id;
    /*    this.birthday = birthday;
        this.address = address;*/
    }
    
    public void setDataFromId() throws SQLException{
      Connection conn;
      Statement stmt;
      String sql;
      try{
        conn = DriverManager.getConnection(
        "jdbc:mysql://localhost/employee","root","password");
        stmt = conn.createStatement();
        sql ="select * from employee_personaldata where id=" + this.employee_id;
        ResultSet rset = stmt.executeQuery(sql); 
        if(rset.next()){ //don't need?
          //set data
          this.birthday=rset.getString(2);
          this.address=rset.getString(3);
        }
        rset.close();
        stmt.close();
        conn.close();
      }catch(Exception e){
        e.printStackTrace();
      }
    }
        
    public static void save() throws SQLException { 
        Connection conn;
        Statement stmt;
        try {
            conn = DriverManager.getConnection(
            "jdbc:mysql://localhost/employee","root","password");
            stmt = conn.createStatement();
            /*String tmp = "INSERT INTO employee_personaldata (employee_id,birthday,address) VALUES (?,?)";
            PreparedStatement stat = conn.prepareStatement(tmp);
            stat.setString(1, this.employee_id);
            stat.setString(2, this.birthday);
            stat.setString(3,this.address);
            stat.execute();  */
            String sql =
            "load data infile 'employee_personaldata.csv' \n" +
            "replace \n" +
            "into table db.employee_personaldata \n" +
            "columns terminated by '\\t' \n" +
            "ignore 1 lines";
            stmt.execute(sql);
            /*String sql = 
            "LOAD DATA INFILE \'employee_personaldata.csv\'"+
　　        "INTO TABLE db.employee_personaldata"+
　　        "FIELDS TERMINATED BY \',\' ENCLOSED BY \'\"\'"+
　　        "LINES TERMINATED BY \'\r\n\'";
            stmt.execute(sql);*/
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.print(e.toString());
            if (e instanceof SQLException) {
                    System.out.println("エラー・コード: " +
                    ((SQLException)e).getErrorCode());
            }
        }
    }
}
