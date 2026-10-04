package ro.ucv.inf.ead.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ro.ucv.inf.ead.model.Contact;

/**
 * Servlet implementation class ContactServletController
 */
@WebServlet("/contacts")
public class ContactServletController extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Build model used by view. This can be extracted from a database.
    Contact contact = new Contact("Mihai", "+4012345678");

    // Add to request the model.
    request.setAttribute("contact", contact);

    // Choose the view to display model.
    RequestDispatcher view = request.getRequestDispatcher("contact.jsp");
    view.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
