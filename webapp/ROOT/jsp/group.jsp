<%@ page import = "bean.AgeGroup" %>
<%@ page import="java.util.*" %>
<%
  List<AgeGroup> aglist = (List<AgeGroup>) request.getAttribute("aglist");
%>
<%  for(AgeGroup ag : aglist){ %>
<group>
  <range><%=ag.getRange()%></range>
  <sum><%=ag.getSum()%></sum>
  <quit><%=ag.getQuitn()%></quit>
</group>
<% } %>
