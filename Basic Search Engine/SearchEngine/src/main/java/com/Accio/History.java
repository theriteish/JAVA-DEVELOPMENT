package com.Accio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/History")
public class History extends HttpServlet {
    protected void doGet(HttpServletRequest request , HttpServletResponse response){
        //establish the connection;
        Connection connection = DatabaseConnection.getConnection();

        try {
            //we will get the data of history table using this query
            ResultSet resultSet = connection.createStatement().executeQuery("select * from history;");

            //creating arraylist of history Result;
            ArrayList<HistoryResult> results  = new ArrayList<HistoryResult>();
            while(resultSet.next()){
                HistoryResult historyResult = new HistoryResult();
                historyResult.setKeyword(resultSet.getString("keyword"));
                historyResult.setLink(resultSet.getString("link"));
                results.add(historyResult);
            }
            //we will forward this request to frontend:
            request.setAttribute("results" , results);
            request.getRequestDispatcher("history.jsp").forward(request,response);

            //creating a writer
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
