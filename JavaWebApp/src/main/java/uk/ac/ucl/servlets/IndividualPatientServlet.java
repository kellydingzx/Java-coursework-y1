package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Patient;
import uk.ac.ucl.model.dataframes.FrameException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/patientDetail.html")
public class IndividualPatientServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String colname = request.getParameter("colname");
        String patient_name = request.getParameter("patientname");
        String patientDetails = null;
        try {
            patientDetails = new Patient(colname,patient_name).toString();
        } catch (FrameException e) {
            e.printStackTrace();
        }
        // Then add the data to the request object that will be sent to the Java Server Page, so that
        // the JSP can access the data (a Java data structure).
        request.setAttribute("patientDetails", patientDetails);

        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patientDetail.jsp");
        dispatch.forward(request, response);
    }
}
