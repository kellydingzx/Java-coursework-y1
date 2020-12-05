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

@WebServlet("/number_of_patients_in_each_age_range.html")
public class NumberofPatientsInEachAgeRange extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Model model = null;
        try {
            model = ModelFactory.getModel();
        } catch (FrameException e) {
            e.printStackTrace();
        }

        assert model != null;
        ArrayList<String> age_range_result = new ArrayList<>();
        try {
            age_range_result = model.getAllAgeRange();
        } catch (FrameException e) {
            e.printStackTrace();
        }

        request.setAttribute("ageRangeResults", age_range_result);

        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/number_of_patients_in_each_age_range.jsp");
        dispatch.forward(request, response);
    }
}
