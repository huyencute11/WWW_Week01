<%@ page import="iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.models.Account" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <!-- Add any necessary CSS styles here -->
</head>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #007bff;
            color: #fff;
            text-align: center;
            padding: 20px 0;
        }

        h1 {
            font-size: 24px;
        }

        section {
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            font-size: 20px;
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: #fff;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }

        a:hover {
            text-decoration: underline;
        }

        footer {
            text-align: center;
            margin-top: 20px;
        }
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body>
<header>
    <h1>Welcome to Your Dashboard</h1>
</header>

<section>
    <h2>User Information</h2>
    <table>
        <%
            if("1".equals(request.getAttribute("role_account"))){
                System.out.println("12345  "+request.getAttribute("role_account")  );
        %>
        <tr>
            <th>Full Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th style="text-align: center">Role</th>
            <th style="text-align: center">Handle</th>
        </tr>
        <%
            List<Account> accounts = (List<Account>) request.getAttribute("accounts");
            for (Account account : accounts) {
        %>
        <tr>
            <td><%= account.getFullName() %></td>
            <td><%= account.getEmail() %></td>
            <td><%= account.getPhone() %></td>
            <td style="text-align: center">
                <a href="#" class="btn btn-primary"
                   data-toggle="modal" data-target="#editModalRole"
                   onclick="openDetailDialogSeeRoleAndEdit('<%= account.getAccountId() %>')">Edit Role</a>
                <span id="roleName1"></span>
            </td>
            <td style="text-align: center">
                <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#infoModal"
                   onclick="openDetailDialog('<%= account.getAccountId() %>', '<%= account.getFullName() %>', '<%= account.getEmail() %>', '<%= account.getPhone() %>')">
                    Detail information
                </a>
                <a href="#" class="btn btn-danger" onclick="confirmDelete('<%= account.getAccountId() %>')">Delete</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="insert_account.jsp" class="btn btn-primary">Insert</a>
    <%}else{
    %>
    <h2>Logged In User Information</h2>
    <table>
        <tr>
            <th>Full Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Account id</th>
            <th style="text-align: center">More</th>
        </tr>
        <%
            Account loggedInAccount = (Account) request.getAttribute("loggedInAccount");
            request.setAttribute("loggedInAccount", loggedInAccount);
            System.out.println("12345  "+loggedInAccount);
        %>
        <tr>
            <td><%= loggedInAccount.getFullName() %></td>
            <td><%= loggedInAccount.getEmail() %></td>
            <td><%= loggedInAccount.getPhone() %></td>
            <td><%= loggedInAccount.getAccountId() %></td>
            <td style="text-align: center">
                <a href="update_account.jsp?loggedInAccount=<%= loggedInAccount.getAccountId() %>" class="btn btn-primary">Edit</a>
            </td>
            </td>
        </tr>
    </table>
    <%}%>
    <!-- Add more user information as needed -->
</section>

<!-- Add other sections or content specific to your dashboard -->

<footer>
    <h1>WWW_WEEK1_TranThiMinhHuyen</h1>
</footer>
<!-- Add a hidden modal dialog -->
<!-- Bootstrap modal for editing user accounts -->
<div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="infoModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="infoModalLabel">Infomation Detail Account</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Add form fields for editing user account information -->
                <form action="edit_account.jsp" method="post">
                    <input type="hidden" id="Id" name="accountId" value="">
                    <div class="form-group">
                        <label for="fullName">Full Name:</label>
                        <input type="text" class="form-control" id="fullName" name="fullName" disabled>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text" class="form-control" id="email" name="email" disabled>
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone:</label>
                        <input type="text" class="form-control" id="phone" name="phone" disabled>
                    </div>

                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="editModalRole" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel1">Infomation Detail Role</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <!-- Display a confirmation message after the role is updated -->
            <div id="roleUpdateConfirmation" class="alert alert-success" style="display: none;">
                Role updated successfully.
            </div>

            <!-- Display an error message if role update fails -->
            <div id="roleUpdateError" class="alert alert-danger" style="display: none;">
                Failed to update role.
            </div>
            <div class="modal-body">
                <!-- Add form fields for editing user account information -->
                <form  id="formEditRole">
                    <input type="hidden" id="role" name="role" value="">
                    <div class="form-group">
                        <label for="roleName">Role Name:</label>
                        <input type="text" class="form-control" id="roleName" name="roleName" disabled>
                    </div>
                    <div class="form-group">
                        <label for="editRoleSelect">Edit Role:</label>
                        <select class="form-select" aria-label="Default select example" id="editRoleSelect" name="editRole" >
                            <option value="1">Admin</option>
                            <option value="2">User</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary" >Save</button>

                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<script>
    function getRoleAccountById(accountId) {
        $.ajax({
            url: 'account111', // Replace with the correct URL to your Java Servlet
            method: 'POST',
            data: {
                action: 'getRoleAccountById',
                accountID: accountId
            },
            dataType: 'json', // Ensure that you expect JSON as the response
            success: function (response) {
                // Handle the response, which should contain the role
                if (response.role) {
                    const role = JSON.parse(response.role); // Parse the role JSON
                    // const roleId = response.role.roleId; // Access roleId from response.role
                    const roleId =  role.roleId
                    // JSON.parse()
                    if(roleId == '1'){
                        $('#roleName').val("Admin")
                    }else{
                        $('#roleName').val("User")
                    }
                    // You can use roleId for further processing
                    localStorage.setItem("accountId", accountId);
                } else {
                    // Handle the case where the role is not found
                    alert('Role not found.');
                }
            },
            error: function () {
                alert('An error occurred while fetching the role.');
            }
        });
    }

    <%--handle edit user--%>
    function openDetailDialog(accountId, fullName, email, phone){
        $('#Id').val(accountId);
        $('#fullName').val(fullName);
        $('#email').val(email);
        $('#phone').val(phone);
    }
        <%--function handleEditAccount() {--%>
        <%--    console.log("Edit button clicked")--%>
        <%--    debugger;--%>
        <%--// Get the loggedInAccount attribute--%>
        <%--    var loggedInAccount = <%= request.getAttribute("loggedInAccount") %>;--%>
        <%--    // Encode the loggedInAccount object as a query parameter--%>
        <%--    var encodedAccount = encodeURIComponent(JSON.stringify(loggedInAccount)); // Convert to JSON and encode--%>
        <%--    // Redirect to the update_account.jsp page with the loggedInAccount parameter--%>
        <%--    window.location.href = 'update_account.jsp?loggedInAccount=' + encodedAccount;--%>
        <%--}--%>


    // function openDetailDialogEdit(accountId, fullName, email, phone, pass) {
    //         // Update the role in the UI
    //         $('#accountId').val(accountId);
    //         $('#editFullName').val(fullName);
    //         $('#editEmail').val(email);
    //         $('#editPhone').val(phone);
    //         $('#editPass').val(pass);
    // }
    // function editAccount(accountId, fullName, email, phone, pass) {
    //     $.ajax({
    //         url: 'account111', // Replace with the correct URL to your Java Servlet
    //         method: 'POST',
    //         data: {
    //             action: 'editAccount',
    //             accountId: accountId,
    //
    //         },
    //         success: function (response) {
    //             if (response === 'true') {
    //                 // Account deleted successfully, you can update the table or take other actions
    //                 alert('Account deleted successfully.');
    //                 location.reload(); // Reload the page to update the table
    //             } else {
    //                 // Deletion failed
    //                 alert('Failed to delete account.');
    //             }
    //         },
    //         error: function () {
    //             alert('An error occurred while deleting the account.');
    //         }
    //     });
    // }

    <%--handle delete user--%>
    function confirmDelete(accountId) {
        if (confirm("Are you sure you want to delete this account?")) {
            // If the user confirms, call the deleteAccount function
            deleteAccount(accountId);
        }
    }
    function deleteAccount(accountId) {
        $.ajax({
            url: 'account111', // Replace with the correct URL to your Java Servlet
            method: 'POST',
            data: {
                action: 'deleteAccount',
                accountId: accountId
            },
            success: function (response) {
                if (response === 'true') {
                    // Account deleted successfully, you can update the table or take other actions
                    alert('Account deleted successfully.');
                    location.reload(); // Reload the page to update the table
                } else {
                    // Deletion failed
                    alert('Failed to delete account.');
                }
            },
            error: function () {
                alert('An error occurred while deleting the account.');
            }
        });
    }

    <%--handle see infomation--%>
    function handleEditRoleAccountByAdmin (accountId){
        $.ajax({
            url: 'account111', // Replace with the correct URL to your Java Servlet
            method: 'POST',
            data: {
                action: 'seeDetailInfoAccount',
                accountId: accountId
            },
            success: function (response) {
                if (response === 'true') {
                    // Account deleted successfully, you can update the table or take other actions
                    // alert('Account deleted successfully.');
                    // location.reload(); // Reload the page to update the table
                } else {
                    // Deletion failed
                    alert('Failed to see detail account.');
                }
            },
            error: function () {
                alert('An error occurred while deleting the account.');
            }
        });
    }
    function openDetailDialogSeeRoleAndEdit(accountId){
        getRoleAccountById(accountId)
        // if(response.get)
    }
    function handleEditRole(){
        const selectElement = document.getElementById("editRoleSelect");
        const selectedValue = selectElement.value;

        // Get the accountId from localStorage
        const accountId = localStorage.getItem("accountId");

        $.ajax({
            url: 'account111', // Replace with the correct URL to your Java Servlet
            method: 'POST',
            data: {
                action: 'adminUpdateRoleAccount',
                editRole: selectedValue, // Change 'role' to 'editRole'
                accountID: accountId, // Change 'accountId' to 'accountID'
            },
            dataType: 'json', // Expect JSON response
            success: function (response) {
                if (response.success) {
                    // Role updated successfully, you can update the UI or take other actions
                    $('#roleUpdateConfirmation').show(); // Show success message
                    $('#roleUpdateError').hide(); // Hide error message
                    // Optionally update the UI or reload the page
                } else {
                    // Role update failed
                    $('#roleUpdateError').show(); // Show error message
                    $('#roleUpdateConfirmation').hide(); // Hide success message
                }
                window.location.href = 'login.jsp';
            },
            error: function () {
                $('#roleUpdateError').show(); // Show error message
                $('#roleUpdateConfirmation').hide(); // Hide success message
            }
        });
    }
</script>


