import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorld extends HttpServlet{
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    
    String id = request.getParameter("id");
    ResultInfo ri = new ResultInfo(id);
    ri.predict();

    PrintWriter out = response.getWriter();
    out.println("<user>");
    out.println("<id>"+ri.getId()+"</id>");
    out.println("<name>" + ri.getName()+"</name>");
    out.println("<addr>"+ri.getAddress() + "</addr>");
    out.println("<yesno>"+ri.getYesno() + "</yesno>");
    out.println("</user>");
    out.flush();
    //request.setAttribute("out",out);
    /*request.setAttribute("result",ri);
    request.setAttribute("id",ri.getId());
    request.setAttribute("name",ri.getName());
    request.setAttribute("address",ri.getAddress());
    request.setAttribute("yesno",ri.getYesno());
*/
 //   RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/test.jsp");
   // dispatch.forward(request,response);
  }
}

