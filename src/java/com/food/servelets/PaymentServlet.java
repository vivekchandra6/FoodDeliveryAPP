package com.food.servelets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooddelivery.daoImpl.PayPalService;
import com.fooddilivery.module.PayPalConfig;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@WebServlet("/Payment")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PayPalService payPalService;

	public void init(ServletConfig config) throws ServletException {
	      // Initialize PayPalConfig using default constructor
	     PayPalConfig payPalConfig = new PayPalConfig();
	     
	        payPalService = new PayPalService(payPalConfig);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                         Double amount =  (Double) request.getSession().getAttribute("totalAmount");
                      
                         if (amount == null) {
                             response.sendRedirect("checkout.jsp");
                             return;
                         }
                         double conversionRate = 0.012; // Example: 1 INR = 0.012 USD
                         double amountInInr = amount;
                         System.out.println("amount in Rs:  "+amountInInr);
                         double amountInUsd = amountInInr * conversionRate;
                         System.out.println("amount in USD:  "+amountInUsd);
	        try {
	            Payment payment = payPalService.createPayment(
	                 amountInUsd,
	                "USD", // Currency
	                "paypal", // Payment method
	                "sale", // Intent
	                "Test payment description",
	                "http://localhost:8080/food_Delivery_app/cancel", // Cancel URL
	                "http://localhost:8080/food_Delivery_app/execute-payment" // Success URL
	            );
	            for(Links links : payment.getLinks()){
	                if(links.getRel().equals("approval_url")){
	                    response.sendRedirect(links.getHref());
	                    return;
	                }
	            }
	        } catch (PayPalRESTException e) {
	            e.printStackTrace();
	            response.sendRedirect("errorPage.jsp");
	        }
	
	}

}
