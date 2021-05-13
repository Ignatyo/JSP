package Servlets;

import Model.Lists;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    private Lists lists;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<String> keys = new ArrayList<String>(lists.getList().keySet());
        int firstNumber = Integer.parseInt(req.getParameter("firstNumber"));
        String secondName = req.getParameter("secondName");
        if (firstNumber == 0) {
            lists.addFirst(secondName);
        } else {
            String firstName = keys.get(--firstNumber);
            lists.addSecond(firstName, secondName);
        }
        resp.sendRedirect(req.getContextPath() + "/main");
    }

    @Override
    public void init() throws ServletException {
        try {
            lists = Lists.getInstance();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
