import java.sql.*;

public class EmployeeInfo{
/*  
  public ResultSet execSql(String sql,ResultSet rset){
    Connection conn;
    Statement stmt;
    String sql;
    ResultSet rset;
    try{
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(
      "jdbc:mysql://db/employee","test","password");
      stmt = conn.createStatement();
      sql ="select * from employee_personaldata where employee_id="+this.employee_id;
      rset = stmt.executeQuery(sql);
      stmt.close();
      conn.close();
    }catch(Exception e){
      System.out.print(e.toString());
      if (e instanceof SQLException) {
        System.out.println("エラー・コード: " +((SQLException)e).getErrorCode());
      }
    }
    return rset;
  }
*/
} 


