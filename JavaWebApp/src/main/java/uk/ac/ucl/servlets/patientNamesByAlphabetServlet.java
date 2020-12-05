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

@WebServlet("/patientfirstnamesByAlphabet.html")
public class patientNamesByAlphabetServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        char c = request.getParameter("char").charAt(0);
        Model model = null;
        try {
            model = ModelFactory.getModel();
        } catch (
                FrameException e) {
            e.printStackTrace();
        }
        ArrayList<String> names = new ArrayList<>();
        try {
            assert model != null;
            names = model.getPartitionResult("FIRST").get(c);
        } catch (FrameException e) {
            e.printStackTrace();
        }

        // Then add the data to the request object that will be sent to the Java Server Page, so that
        // the JSP can access the data (a Java data structure).
        request.setAttribute("charcter", c);
        request.setAttribute("firstnamesByAlphabet", names);

        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patientfirstnamesByAlphabet.jsp");
        dispatch.forward(request, response);
    }
}
