package com.demo.demo;

import java.io.*;
import java.net.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import java.net.URI;//better use URI and then convert to URL

import java.util.logging.Logger;

@WebServlet(name = "helloServlet", value = "/HelloServlet")
public class HelloServlet extends HttpServlet {
    //For logging if error occurs
    private static final Logger logger = Logger.getLogger(HelloServlet.class.getName());

    private double fetchExchangeRate(String inputCurrency, String outputCurrency) {
        String responseStr;
        HttpURLConnection connection = null;
        try {
            String API_KEY = "6519ef42200cea38fec7e886";
            String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + inputCurrency;
            URL url;
            try{
                url = new URI(urlStr).toURL();
                connection = (HttpURLConnection) url.openConnection();
            } catch (URISyntaxException e) {
                logger.severe("URI syntax exception: Invalid URL format");
                return -1;
            }
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                responseStr = response.toString();
            }
        } catch (MalformedURLException e) {
            logger.severe("MalformedURLException: " + e.getMessage());
            return -1;
        } catch (ProtocolException e) {
            logger.severe("ProtocolURLException: " + e.getMessage());
            return -1;
        } catch (IOException e) {
            logger.severe("IOException: " + e.getMessage());
            return -1;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        try {
            JSONObject jsonObject = new JSONObject(responseStr); //Parse JSON response
            JSONObject rates = jsonObject.getJSONObject("conversion_rates"); //Extract conversion rates
            return rates.getDouble(outputCurrency); //Get the rate for output currency
        } catch (org.json.JSONException e) {
            logger.severe("JSONException: " + e.getMessage());
            return -1;
        }
    }
    //Method to handle POST method request
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String amountStr = request.getParameter("Amount");
        String inputCurrency = request.getParameter("InputCurrency");
        String outputCurrency = request.getParameter("OutputCurrency");

        if (inputCurrency != null){
            out.print("You have entered " + inputCurrency + " currency and ");
        }
        if (outputCurrency != null){
            out.print("you have entered " + outputCurrency + " currency, ");
        }

        if (amountStr == null || inputCurrency == null || outputCurrency == null) {
            out.println("Error: Missing required parameters.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            if (inputCurrency.equals(outputCurrency)) {
                out.println("Converted: " + amount + " " + inputCurrency + " = " + amount + " " + outputCurrency);
                return;
            }
            double exchangeRate = fetchExchangeRate(inputCurrency, outputCurrency);
            if (exchangeRate == -1) {
                out.println("Error: Unable to retrieve exchange rate.");
                return;
            }
            double convertedAmount = amount * exchangeRate;
            out.println("Converted: " + amount + " " + inputCurrency + " = " + convertedAmount + " " + outputCurrency);
        } catch (NumberFormatException e) {
            out.println("Error: Invalid amount format.");
        }
    }
}
