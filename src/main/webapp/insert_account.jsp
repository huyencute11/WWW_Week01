<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        .container {
            max-width: 600px;
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
            margin-left: 10px;
            margin-right: 10px;
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
</head>
<body>
<div class="container">
    <h1>Insert new Account</h1>
    <form action="account111" method="post">
        <label for="id">Id:</label>
        <input id="id" name="id" type="text" value="" required/>
        <label for="fullname">Full Name:</label>
        <input id="fullname" name="fullname" type="text" value="" required/>
        <label for="password">Password:</label>
        <input id="password" name="password" type="password" value="" required>
        <label for="email">Email:</label>
        <input id="email" name="email" type="text" value="" required/>
        <label for="phone">Phone:</label>
        <input id="phone" name="phone" type="text" value="" required/>
        <label for="status">Status:</label>
            <select class="form-select" aria-label="Default select example" id="status" name="status">
                <option value="ACTIVE">ACTIVE</option>
                <option value="DEACTIVE">DEACTIVE</option>
                <option value="DELETED">DELETED</option>
            </select>
        <label for="role">Role:</label>
        <select class="form-select" aria-label="Default select example" id="role" name="role">
            <option value="admin">Admin</option>
            <option value="user">User</option>
        </select>

        <input type="hidden" name="action" value="insert_account">
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>