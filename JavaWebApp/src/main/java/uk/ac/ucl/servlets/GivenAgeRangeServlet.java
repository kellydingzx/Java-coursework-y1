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

@WebServlet("/givenAgePatients.html")
public class GivenAgeRangeServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        int lower_bound = Integer.parseInt(request.getParameter("lower_bound"));
        int upper_bound = Integer.parseInt(request.getParameter("upper_bound"));

        Model model = null;
        try {
            model = ModelFactory.getModel();
        } catch (FrameException e) {
            e.printStackTrace();
        }
        ArrayList<String> ids_found = null;
        if(lower_bound <= upper_bound) {
            try {
                ids_found = model.getGivenAgeRangePatients(lower_bound, upper_bound);
            } catch (FrameException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("patientsgivenRange", ids_found);


        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/givenAgePatients.jsp");
        dispatch.forward(request, response);
    }
}
