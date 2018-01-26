<%@ page import = "bean.PersonalData" %>
<%@ page import="java.util.*" %>
<%
  List<PersonalData> pdlist = (List<PersonalData>) request.getAttribute("pdlist");
%>
<%  for(PersonalData pd : pdlist){ %>
<employee>
  <id><%=pd.getId()%></id>
  <age><%if(pd.getAge() != -1) { /*age != -1あとで修正 */%><%=pd.getAge()%><% } %></age>
  <addr><% if(pd.getAddress()!=null){ %><%=pd.getAddress()%><% } %></addr>
  <quit><%=pd.getQuit()%></quit>
</employee>
<% } %>
