<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Currency Converter</title>
</head>
<body>
<h1><%= "Welcome to Currency Conversion site!" %></h1>
<!--Input Currency Selection (using radio buttons)-->
<form id="CurrencyForm" action="HelloServlet" method="post"> <!--ID for later identification-->
    <!--Getting the Amount-->
    <p>Please enter the Amount you wish to convert:</p>
    <label for="amount">Amount:</label> <!--Label for Amount, helps with maintainability-->
    <input type="text" id="amount" name="Amount" required="required">
    <br>
    <!--Select Input Currency (Only one choice)-->
    <p>Select entered amount currency:</p>
    <label for="inputCurrency">Currency:</label>
    <select id="inputCurrency" name="InputCurrency">
        <option value="USD">USD</option>
        <option value="EUR">EUR</option>
        <option value="JPY">JPY</option>
        <option value="GBP">GBP</option>
        <option value="YUAN">YUAN</option>
    </select>
    <br>
    <!--Output Currency Selection-->
    <p>Select currency you wish to convert to:</p>
    <label for="outputCurrency">Currency:</label>
    <select id="outputCurrency" name="OutputCurrency">
        <option value="USD">USD</option>
        <option value="EUR">EUR</option>
        <option value="JPY">JPY</option>
        <option value="GBP">GBP</option>
        <option value="YUAN">YUAN</option>
    </select>
    <br>
    <!--Submit-->
    <button type="button" onclick="convertCurrency()">Convert</button>
</form>
<p id="Result">Here will be displayed the converted amounts</p>

<script>
    function convertCurrency() {
        console.log("convertCurrency() called"); // Debug log

        // Getting info from CurrencyForm
        let formData = new FormData(document.getElementById('CurrencyForm'));
        console.log("FormData:", formData); // Debug log

        // Convert FormData to URL-encoded format
        let urlEncodedData = new URLSearchParams(formData).toString();
        console.log("URL-encoded data:", urlEncodedData); // Debug log

        // Send the request to the servlet
        fetch("HelloServlet", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: urlEncodedData
        })
            .then(response => {
                console.log("Response received:", response); // Debug log
                return response.text();
            })
            .then(data => {
                console.log("Data received:", data); // Debug log
                // Display the result
                document.getElementById('Result').innerHTML = data;
            })
            .catch(error => {
                console.error('Error:', error); // Debug log
                document.getElementById('Result').innerHTML = "Error: Unable to convert currency.";
            });
    }
</script>
</body>
</html>
