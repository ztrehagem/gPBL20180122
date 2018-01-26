import java.sql.*;

public class SqlConnection{

  private Connection conn;
  private Statement stmt;
  private ResultSet rset;

  public ResultSet getResultSet(){
    return this.rset;
  }
  public void execSql(String sql){
    try{
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      this.conn = DriverManager.getConnection(
      "jdbc:mysql://db/test","root","");
      this.stmt = conn.createStatement();
      this.rset = stmt.executeQuery(sql);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  public void close() throws SQLException{
    this.conn.close();
    this.stmt.close();
    this.rset.close();
  }
}
