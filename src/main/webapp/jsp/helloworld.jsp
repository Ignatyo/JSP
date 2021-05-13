<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
         "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*, Servlets.MainPage" %>
<jsp:useBean id="hello" class="Servlets.MainPage" scope="page" />
<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Ферма</title>
  </head>
    <body>
    <%
        LinkedHashMap<String, ArrayList<String>> lists = (LinkedHashMap<String, ArrayList<String>>) request.getAttribute("lists");
    %>
        <script language="JavaScript">
          function on(clicked_id, size) {
              var tmp = document.getElementById(clicked_id).textContent;
              if(tmp.includes("[-]")) {
                  document.getElementById(clicked_id).textContent = "[+]";
                  for (var i = 0; i < size; i++) {
                      var element = document.getElementById(clicked_id.replace("_"," ") + i);
                      element.style.display = "none";
                  }
              } else {
                  document.getElementById(clicked_id).textContent = "[-]";
                  for (var i = 0; i < size; i++) {
                      var element = document.getElementById(clicked_id.replace("_"," ") + i);
                      element.style.display = "inline";
                  }
              }
          }

          function checkAdd(listSize) {
              var addForm = document.forms['add'];
              var firstField = addForm.elements.firstNumber.value;
              var secondField = addForm.elements.secondName.value;
              if (isNaN(firstField)) {
                  alert('not a number');
                  return false
              }
              if (firstField > listSize || firstField < 0) {
                  alert('list is not exist');
                  return false;
              }
              if (firstField === '' || secondField === '') {
                  alert('field is empty');
                  return false;
              }
              if (secondField.length > 10) {
                  alert('oversize');
                  return false;
              }
              return true;
          }

          function checkDelete(listSize) {
              var deleteForm = document.forms['delete'];
              var firstField = deleteForm.elements.firstNumber.value;
              var secondField = deleteForm.elements.secondName.value;

              if (isNaN(firstField)) {
                  alert('not a number');
                  return false
              }
              if (firstField > listSize || firstField < 0) {
                  alert('list is not exist');
                  return false;
              }
              if (firstField === '' || secondField === '') {
                  alert('field is empty');
                  return false;
              }
              return true;
          }

        </script>
        <%
            for (Map.Entry<String, ArrayList<String>> entry : lists.entrySet()) {
        %>
                <%=entry.getKey()%><span id="<%=entry.getKey().replace(" ", "_")%>"
                                 onclick="on(this.id, <%=entry.getValue().size()%>)">[+]</span>
                <br>
                <%
                    for (int i = 0; i < entry.getValue().size(); i++) {
                %>
                <ul style="display:none" id="<%=entry.getKey().replace(" ", "_") + i%>">*<%=entry.getValue().get(i)%><br></ul>
        <%
                }
            }
        %>

        <form action="/add" method="post" name="add" onsubmit="return checkAdd(<%=lists.size()%>)">
            <fieldset>
                <legend>Add</legend>
                First number: <input type="text" name="firstNumber"> <br> <br>
                Second item: <input type="text" name="secondName"> <br> <br>
                <input type="submit" name="add">
            </fieldset>
        </form>

        <form action="/delete" method="post" name="delete" onsubmit="return checkDelete(<%=lists.size()%>)">
            <fieldset>
                <legend>Delete</legend>
                First number: <input type="text" name="firstNumber"> <br> <br>
                Second item: <input type="text" name="secondName"> <br> <br>
                <input type="submit" name="add">
            </fieldset>
        </form>
    </body>
</html>