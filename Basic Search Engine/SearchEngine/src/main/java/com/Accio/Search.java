package com.Accio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//we are making com.Accio.Search class our servlet by this annotation
@WebServlet("/Search") //  "/search"   is the path or route of the servlet
//we are inheriting the property of http Servlet

public class Search extends HttpServlet {
    //we will create request object  and response object using the doGet function
    protected void doGet (HttpServletRequest request , HttpServletResponse response)throws IOException {
        //we are getting the request in the form of text
        // we are creating a search keyword for further actions like response
        String keyword = request.getParameter("keyword");

        //first we will establish connection with database &
        //using this keyword we will search in our database  & get top 30 results using ranking algorithm
        try {
            Connection connection = DatabaseConnection.getConnection();

            //store the query of user in history table
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into history values(?, ?);");
            preparedStatement.setString(1,keyword);
            preparedStatement.setString(2,"http://localhost:8080/SearchEngine/Search?keyword="+keyword);
            preparedStatement.executeUpdate();

            //getting results after running the ranking query
            ResultSet resultSet = connection.createStatement().executeQuery("select pagetitle , pagelink , (Length(lower(pagetext)) - length(replace(lower(pagetext),'" + keyword.toLowerCase() + "', '')))/length('" + keyword.toLowerCase() + "') as countoccurance from pages order by countoccurance desc limit 30;");


            //we created an arraylist to store our ResultSet that will store page title & page link
            ArrayList<SearchResult> results = new ArrayList<SearchResult>();
            //transferring values from resultset to results arraylist
            while(resultSet.next()){
                SearchResult searchResult = new SearchResult();
                searchResult.setTitle(resultSet.getString("pageTitle"));
                searchResult.setLink(resultSet.getString("pageLink"));
                results.add(searchResult);
            }


            //displaying the result araaylist in console:
            for(SearchResult result : results){
                System.out.println(result.getTitle()+"\n"+result.getLink()+"\n");
            }


            //now we will send our result arraylist to frontend:
            request.setAttribute("results" , results);
            //now we create a frontend file for displaying the results -> search.jsp
            //we will forwarding the request & response to this search.jsp file
            request.getRequestDispatcher("search.jsp").forward(request,response);


            //we are generating a html response instead of printing it in the console
            //for that first we have to set  the content to  html
            response.setContentType("text/html");

            //it will generate the response using search keyword here
            PrintWriter out = response.getWriter();

        }
        catch (SQLException | ServletException sqlException){
            sqlException.printStackTrace();
        }


    }
}
