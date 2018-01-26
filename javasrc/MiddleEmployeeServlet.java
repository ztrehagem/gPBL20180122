import bean.PersonalData;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MiddleEmployeeServlet extends HttpServlet{
  public void doGet(HttpServletRequest request, HttpServletResponse response){
      String str = "select * from employee ";
      String condition1 = "not exists(select * from retirement where employee.id = retirement.id) ";
      String currentage =  "((YEAR(CURDATE()) - YEAR(employee.birthday)) - (RIGHT(CURDATE(), 5) < RIGHT(employee.birthday, 5))) ";
      String condition2 = currentage + "< 30 ";
      String condition3 = currentage + ">= 20 ";
    try{
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=UTF-8");
      String sql = str+"where "+condition1 + "and " + condition2 + "and " +condition3; 
      request.setAttribute("sql",sql);
      RequestDispatcher dispatch = request.getRequestDispatcher("/EmployeeServlet");
      dispatch.forward(request,response);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
