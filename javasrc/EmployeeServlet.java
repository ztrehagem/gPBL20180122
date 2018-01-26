import bean.PersonalData;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServlet extends HttpServlet{
  public void doGet(HttpServletRequest request, HttpServletResponse response){
    try{
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=UTF-8");
      String sql = (String) request.getAttribute("sql");
      List<PersonalData> pdlist = new ArrayList<PersonalData>();
      SqlConnection sc = new SqlConnection();
      sc.execSql(sql);
      ResultSet rset = sc.getResultSet();
      while(rset.next()){
        PersonalData pd = new PersonalData(
          rset.getInt(1),
          PersonalData.birthdayToAge(rset.getString(2)),
          rset.getString(3)
        );
           //predict add pd.quit
        if (pd.getId()!=0) pdlist.add(pd);

      }
      sc.close();
      request.setAttribute("pdlist",pdlist);
      RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/employee.jsp");
      dispatch.include(request,response);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
