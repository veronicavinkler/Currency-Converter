Currency Converter: https://www.geeksforgeeks.org/java-projects/?ref=gcse_outind 

This project is a very basic project in Java that is used to convert a currency from one to another. A web-based interface for converting currency and getting the output value, for example, here displays converting the currency of the US dollar to INR

We see variations with different countries using different currencies. Be aware of the current exchange prices in the market and then can convert from one currency to another. A calculator-like application, developed using Ajax, Applet, and web features of Java servlets. You also get a regular and also the conversion rate.

People use this application basically for business, shares and finance-related areas where it is a preferred choice to convert any country's currency. Just enter the amount, the currency you wish to transform to, click enter and you get the output.

Technologies Required: Java programming language, Java Servlets, Web Features, Applet, Ajax

This application uses mainly the Java programming language and Intellij Ultimate as an IDE. In this project Jakarta EE, the successor to Java EE was also used. Java EE is a set of specifications and APIs for building enterprise-level applications in Java. It includes technologies like Servlets, JSP, EJB, JPA etc. In this project Servlets were a more important technology as we used it in this project. Java EE and Jakarta EE provides a s<tandardised way to build scalable, secure and robust enterprise applications. It also includes tools for web development, database connectivity, messaging and more.

Java Servlet is a java class that handles HTTP requests and responses. It runs on a web server and is used to create dynamic web content. In this project Java Servlet HelloServlet processes currency conversion requests. Java Servlets are used to handle backend logic for web applications (processing from data, interacting with database, etc) and acts as the middle layer between the frontend (browser) and backend (database or external APIs)
Web Features (HTML and Java Script).
For web server and servlet container we are using TomCat which implements Java Servlet and JSP technologies. TomCat is used to deploy and run web applications.

JSP file (JavaServer pages) is a technology used to create dynamic web pages. It allows embedding java code directly into HTML using special tags. In this case we are using a JSP file to create a user interface or handles the frontend presentation.

The servlet handles HTTP POST requests, fetches exchange rates from an external API, and performs currency conversion. Java servlet includes two methods: fetchExchangeRate and doPost.


fetchingExchangeRate includes multiple variables in the try code block. String responseStr which later has messages added to it. String API_KEY holds API key to get access to json file from open access website. We need this to get real time conversion rates.

String urlStr is our String form of URL which has been formed from static link and API_KEY. Later we will use this to form our actual URL. URL url is literally just a URL, we are declaring it here for later use.

In this code block we first form a URI (new URI from the url string we declared previously) and then convert it to URL. URI is used to create a valid URL string. The toURL() method converts the URI to a URL object. We form URLs through URI so that we can avoid warnings.
HttpURLConnection is used to establish a connection to the external API and send a HTTP GET request to fetch exchange rates.
URI (Uniform Resource Identifier) is a string of characters used to identify a resource (file, website API endpoint etc). 

URL is a type of URI that specifies the location of a resource and how to access it.


The catch code block below is used in case of failure to establish connection.


In this code block we first form a new StringBuilder and declare a String variable line. Then in a while loop above BufferedReader reads the API response line by line and stores it in a StringBuilder for further processing.

BufferedReader is a Java class used to read text from an input stream for example used in this servlet to read API response. It is efficient in reading large amounts of data.
StringBuilder is used to create and manipulate mutable strings. It allows efficiently build strings, especially in loops or when concatenating multiple strings.

Here we handle all possible errors that could occur and in case of it we return -1.

Finally we also disconnect if all the lines have been read.



The API response we got is in JSON format. JSONObject is used to parse the response and extract the exchange rates. Conversion_rates is a key in the JSON that contains the exchange rates for all currencies which we will store in rates after extracting conversion rates.

Next we are using the doPost method to handle POST Request. First we set the content type into plain text. This means we will display content on the website as plain simple text (not for example html type content).
Then we set getWriter into out variable for shorter code.
After we ask for parameters from variables we have set in a jsp file and store it in different variables. Amount from the text field will be stored in amountStr, InputCurrency in inputCurrency and OutputCurrency in outputCurrency which we have gotten from different dropdown menus.
After getting parameters, if everything works correctly we output selected currencies otherwise if there is none we display error: missing required parameters.

In the try code block we retrieve the parse amount and print out that if the selected currencies are the same we print out that the converted amount is equal. If the exchangeRate is -1, we print out that we got an error. If it is none of the options we perform conversion and output that. If there is a format exception we output that.


In the index.jsp file we have code which provides a user interface for input and displays the conversion result.


First we take the amount the user wishes to convert in a text field. The number is associated with the name Amount. Entering the amount is mandatory. We have an ID written down to associate the label above with it.
We take the currency of the entered amount.







We take the currency the user wishes to convert to.






Submitting.



Above we are calling CurrencyForm value after the user enters submit the answers are encoded to URL encoded format. After that we are sending request to the servlet and entering information to log.

