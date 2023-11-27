<%@ page import="iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.models.Account" %><%--
  Created by IntelliJ IDEA.
  User: MinhHuyen
  Date: 9/21/2023
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        .container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-top: 10px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        select.form-select {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="hidden"] {
            display: none;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        a {
            display: block;
            text-align: center;
            margin-top: 10px;
            text-decoration: none;
            color: #007bff;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Update account</h1>
    <%Account a = (Account) request.getAttribute("account");%>
    <form action="account111" method="post">
        <label for="accountId">Id:</label>
        <input id="accountId" name="accountId" type="text" value="<%=a.getAccountId()%>" readonly/>
        <label for="fullName">Full Name:</label>
        <input id="fullName" name="fullName" type="text" value="<%=a.getFullName()%>" />
        <label for="password">Password:</label>
        <input id="password" name="password" type="text" value="<%=a.getPassword()%>" >
        <label for="email">Email:</label>
        <input id="email" name="email" type="text" value="<%=a.getFullName()%>"/>
        <label for="phone">Phone:</label>
        <input id="phone" name="phone" type="text" value="<%=a.getPhone()%>"/>
        <label for="status">Status:</label>
        <select class="form-select" aria-label="Default select example" id="status" name="status" readonly>
            <option value="ACTIVE">ACTIVE</option>
            <option value="DEACTIVE">DEACTIVE</option>
            <option value="DELETED">DELETED</option>
        </select>

        <input type="hidden" name="action" value="update_account">
        <input type="submit" value="Submit"/>
    </form>
</div>

<script>

    // $(document).ready(function() {
    //     getAccountById();
    //
    //     function getAccountById() {
    //         console.log("URL: " + window.location.href);
    //         console.log(1)
    //         // Get the URL parameters
    //         var urlParams = new URLSearchParams(window.location.search);
    //
    //         // Retrieve the loggedInAccountId from the URL
    //         var loggedInAccountId = urlParams.get('loggedInAccount');
    //
    //         // Display the loggedInAccountId in the console for testing
    //         console.log("Logged-in Account ID: " + loggedInAccountId);
    //         $.ajax({
    //
    //             url: 'account111', // Replace with the correct URL to your Java Servlet
    //             method: 'POST',
    //             data: {
    //                 action: 'getAccount_byID',
    //                 accountId: loggedInAccountId
    //             },
    //             dataType: 'json', // Ensure that you expect JSON as the response
    //             success: function (response) {
    //                 // Handle the response, which should contain the account
    //                 if (response.account) {
    //                     const account = JSON.parse(response.account); // Parse the account JSON
    //
    //                     // Set the form field values with the retrieved account data
    //                     $('#accountId').val(account.accountId);
    //                     $('#fullName').val(account.fullName);
    //                     $('#password').val(account.password);
    //                     $('#email').val(account.email);
    //                     $('#phone').val(account.phone);
    //                     $('#status').val(account.status);
    //                     $('#role').val(account.role); // Assuming account.role holds the role value
    //                     // You can use other fields as needed
    //                 } else {
    //                     // Handle the case where the account is not found
    //                     alert('Account not found.');
    //                     $('.container').html
    //                     (`
    //                         <h1>Login</h1>
    //                         <p>Logged-in account is not available.</p>
    //                         <p><a href="login.jsp">Return to Login</a></p>
    //                 `   );
    //                 }
    //             },
    //             error: function () {
    //                 alert('An error occurred while fetching the account.');
    //             }
    //         });
    //     }
    // });

    // You can now use loggedInAccountId in your JavaScript logic

</script>
</body>
</html>





