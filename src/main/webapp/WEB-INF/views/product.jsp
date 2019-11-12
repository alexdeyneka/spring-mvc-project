<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Product is created</title>
    </head>
    <body>
        <h2><b>Please, fill in the form below:</b></h2>
        <form method='POST'>
          Product id:
          <br>
          <input type="text" name="id" placeholder="1">
          <br>
          <br>
          Product name:
          <br>
          <input type="text" name="name" placeholder="Alex">
          <br>
          <br>
          Quantity:
          <br>
          <input type="text" name="quantity" placeholder="100">
          <br>
          <br>
          Price:
          <br>
          <input type="text" name="price" placeholder="25.5">
          <br>
          <br>
          Production date:
          <br>
          <input type="text" name="productionDate" placeholder="2019-11-22">
          <br>
          <br>
          Expiration date:
          <br>
          <input type="text" name="expirationDate" placeholder="2019-11-22">
          <br>
          <br>
          <input type="submit" value="Add">
        </form>
        </br>
        </br>
        <a href="home">Home page</a>
    </body>
    </html>