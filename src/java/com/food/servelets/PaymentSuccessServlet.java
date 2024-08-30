package com.food.servelets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooddilivery.module.PayPalConfig;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

/**
 * Servlet implementation class PaymentSuccessServlet
 */
@WebServlet("/PaymentSuccess")
public class PaymentSuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");

        try {
            PayPalConfig payPalConfig = new PayPalConfig();
            Payment payment = new Payment();
            payment.setId(paymentId);

            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(payerId);
            APIContext context = payPalConfig.getAPIContext();

            Payment createdPayment = payment.execute(context, paymentExecution);
            if (createdPayment.getState().equals("approved")) {
                // Payment success logic here
                response.sendRedirect("orderSuccess.jsp");
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
	
	}

}
