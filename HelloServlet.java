package com.demo.demo;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//Used materials:
//https://www.tutorialspoint.com/servlets/servlets-form-data.htm
//https://www.youtube.com/watch?v=-SiJypBbO3M&list=PLfu_Bpi_zcDOn8ajnuLY6g1C6hc_eeDFl&index=4
//https://www.youtube.com/watch?v=DZdtFlLi_I4&list=PLfu_Bpi_zcDOn8ajnuLY6g1C6hc_eeDFl&index=3
//https://www.youtube.com/watch?v=UiBtz7rZ6Ec&list=PLfu_Bpi_zcDOn8ajnuLY6g1C6hc_eeDFl&index=2
//https://www.youtube.com/watch?v=0FpLve7ffoY&list=PLfu_Bpi_zcDOn8ajnuLY6g1C6hc_eeDFl&index=1
//https://www.sitepoint.com/community/t/form-checkbox-submit-to-servlet/2264
//https://www.youtube.com/watch?v=F1tx1O195as
//ChatGPT

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    public void init() {
    }
    //Method to handle GET method request
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Currency Converter</h1>");

        //Input Currency Selection (using radio buttons)
        out.println("<form action=\"hello-servlet\" method=\"POST\">");

        //Getting the Amount
        out.println("<p>Enter amount: </p>");
        out.println("<input type=\"text\" name=\"CurrencyNum\" size=\"10\">");
        out.println("<br>");

        //Getting Currency
        out.println("<br><p>Select entered amount currency</p>");
        out.println("<input type=\"radio\" name=\"InputCurrency\" value=\"USD\">USD <br>");
        out.println("<input type=\"radio\" name=\"InputCurrency\" value=\"EUR\">EUR <br>");
        out.println("<input type=\"radio\" name=\"InputCurrency\" value=\"GBP\">GBP <br>");
        out.println("<input type=\"radio\" name=\"InputCurrency\" value=\"YEN\">YEN <br>");
        out.println("<input type=\"radio\" name=\"InputCurrency\" value=\"YUAN\">YUAN <br>");

        //Output Currency Selection (using checkbox buttons)
        out.println("<p>Select currency you wish to convert to:</p>");
        out.println("<input type=\"checkbox\" name=\"OutputCurrency\" value=\"USD\">USD <br>");
        out.println("<input type=\"checkbox\" name=\"OutputCurrency\" value=\"EUR\">EUR <br>");
        out.println("<input type=\"checkbox\" name=\"OutputCurrency\" value=\"GBP\">GBP <br>");
        out.println("<input type=\"checkbox\" name=\"OutputCurrency\" value=\"YEN\">YEN <br>");
        out.println("<input type=\"checkbox\" name=\"OutputCurrency\" value=\"YUAN\">YUAN <br>");

        //Submit
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form>");

        out.println("</body></html>");
    }
    //Method to handle POST method request
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String InputCurrency = request.getParameter("InputCurrency");
    String[] OutputCurrency = request.getParameterValues("OutputCurrency");
    String CurrencyNum = request.getParameter("CurrencyNum");

    response.setContentType("text/html");
    PrintWriter out = response.getWriter(); //Simplifying
    out.println("<html><body>");
    if (InputCurrency != null) {
        out.println("<h3>Entered Currency " + InputCurrency + " and amount " + CurrencyNum + " </h3>");
        out.println("<h3>---------------------------------------------------</h3>");
        for (String currency : OutputCurrency) {
            if (currency != null) {
                out.println("<h3>Converted to " + currency + " which equals to  " + CurrencyNum + "\n</h3>");
            }
        }
    }
    else{out.println("<h3>Input currency was not chosen, please click done and try again</h3><br>");}
    out.println("<a href=\"hello-servlet\">Done</a>");
    out.println("</body></html>");

    }


    public void destroy() {
    }
}