package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.model.dataframes.FrameException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

@WebServlet("/multiple_field_search_results.html")
public class MultipleFieldSearchServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        ArrayList<String> fields = new ArrayList<>(Arrays.asList(request.getParameterValues("fields")));
        String keywords = request.getParameter("keywords");
        ArrayList<String> keyword_set;
        if(keywords.contains(",")) {
            keyword_set = new ArrayList<>(Arrays.asList(keywords.split(",")));
        }else{
            keyword_set = new ArrayList<>(Arrays.asList(keywords));
        }
        Model model = null;
        try {
            model = ModelFactory.getModel();
        } catch (FrameException e) {
            e.printStackTrace();
        }

        HashMap<String,String> searchResult = new HashMap<>();
        try {
            searchResult = model.multipleFieldSearchResult(fields,keyword_set);
        } catch (FrameException e) {
            e.printStackTrace();
        }

        request.setAttribute("result", searchResult);

        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/searchResult.jsp");
        dispatch.forward(request, response);

    }
}
