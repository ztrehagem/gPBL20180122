import bean.AgeGroup;
import bean.PersonalData;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GraphServlet extends HttpServlet{
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException,IOException{
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    String sql = "select * from employee where not exists(select * from retirement where employee.id = retirement.id)";
    List<PersonalData> pdlist = new ArrayList<PersonalData>();
    try{
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
       List<AgeGroup> aglist = new ArrayList<AgeGroup>();

       //とりあえずそれぞれ全部入れて動作確認あとで直す.
       AgeGroup all = new AgeGroup(
           "all",
           pdlist.size(),
           93
       );
       AgeGroup young = new AgeGroup(
            "under20",
            pdlist.stream().filter(d -> d.getAge() < 20).count(),
            3//test

        );
       AgeGroup middle = new AgeGroup(
           "age20_30",
            pdlist.stream().filter(d -> 20 <= d.getAge() && d.getAge() < 30).count(),
            60
       );
       AgeGroup old = new AgeGroup(
           "over30",
            pdlist.stream().filter(d -> 30 <= d.getAge() ).count(),
            30
       );


       aglist.add(young);
       aglist.add(middle);
       aglist.add(old);
       aglist.add(all);
       request.setAttribute("aglist",aglist);
       RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/group.jsp");
       dispatch.include(request,response);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
