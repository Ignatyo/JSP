package Servlets;

import Model.Lists;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/main")
public class MainPage extends HttpServlet {

    private Lists lists;

    @Override
    public void init() throws ServletException {
        try {
            lists = Lists.getInstance();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("lists", lists.getList());
        request.getServletContext().getRequestDispatcher("/jsp/helloworld.jsp").forward(request,response);
    }


}