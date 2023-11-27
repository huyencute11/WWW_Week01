package iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.controllers;
import org.json.JSONObject; // Import the JSON library
import com.google.gson.Gson;

import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.models.*;
import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.reponsitories.AccountReponsitory;
import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.reponsitories.GrantAccessReponsitory;
import iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.reponsitories.LogReponsitory;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@WebServlet(name = "accountServlet", value = "/account111")
public class ControllerServlet extends HttpServlet {
    private final AccountReponsitory accountReponsitory = new AccountReponsitory();
    private final GrantAccessReponsitory grantAccessReponsitory = new GrantAccessReponsitory();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Account a = accountReponsitory.getAllAccount().get(0);
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + a.toString() + "</h1>");
        out.println("</body></html>");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("login".equals(action)) {
            try {
                login(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if("insert_account".equals(action)){
            try {
                insertAccount(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else if ("deleteAccount".equals(action)) {
            try {
                deleteAccount(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else if ("update_account".equals(action)) {
            // Handle account update
            try {
                updateAccount(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else if("getAccount_byID".equals(action)){
            try {
                getAccountById(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        else if("adminUpdateRoleAccount".equals(action)){
            try {
                adminUpdateRoleAccount(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else if("getRoleAccountById".equals(action)){
            try {
                getRoleAccountById(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        // Add other actions (register, update-account, delete-account) here if needed.
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String email = request.getParameter("email"); // Change "name" to "email"
        String password = request.getParameter("password"); // Change "pass" to "password"
        String url = "/dashboard.jsp";
        LogReponsitory lr = new LogReponsitory();
        GrantAccessReponsitory gr = new GrantAccessReponsitory();
        try {
            Optional<Account> optionalAccount = accountReponsitory.login(email, password);
            if (!optionalAccount.isPresent()) {
                url = "/login.jsp";
                request.setAttribute("loginError", "Email or password is incorrect");
            } else {
                // If login is successful, set the account as an attribute to be displayed on the dashboard.
                Account account = optionalAccount.get();
                request.setAttribute("loggedInAccount", account);
                request.setAttribute("accounts",accountReponsitory.getAllAccount());
                Logs l = new Logs(new Account(account.getAccountId()), LocalDate.now(), LocalDate.now(), account.getAccountId());
                lr.createLog(l);
                //get ra role cua account tu grant_access =>
                request.setAttribute("role_account",  gr.getGrantByAccountId(account.getAccountId()).getRole().getRoleId());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.setAttribute("accounts", accountReponsitory.getAllAccount());
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void insertAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException{
        String id = request.getParameter("id");
        String fullname = request.getParameter("fullname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String status = request.getParameter("status");
        String role = request.getParameter("role");
        String url = "/dashboard.jsp";
        Account a = new Account(id, fullname, password, email, phone, Status.valueOf(status));
        String idGrant = "1";
        if(role == "user"){
            idGrant = "2";
        }
        Role r = new Role(idGrant);
        GrantAccess gr = new GrantAccess(r, a, Grant.ENABLE, fullname);

        try {
            boolean insert = accountReponsitory.insertAccount(a);
            boolean insertGrantAccess = grantAccessReponsitory.insertGrantAccess(gr);
            if (insert){
                System.out.printf("Insert thanh cong");
                request.setAttribute("loggedInAccount", a);
                request.setAttribute("accounts",accountReponsitory.getAllAccount());
            }else {
                System.out.printf("insert that bai");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.setAttribute("accounts", accountReponsitory.getAllAccount());
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void updateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String accountId = request.getParameter("accountId");
        String fullname = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // Add fields for password, phone, and status
        String phone = request.getParameter("phone");
//        String status = request.getParameter("editStatus");

        System.out.println("accountId "+accountId);
        Account updatedAccount = new Account(accountId, fullname, password, email, phone);

        try {
            boolean updateSuccess = accountReponsitory.updateAccount(updatedAccount);
            if (updateSuccess) {

                request.setAttribute("loggedInAccount", updatedAccount);
            } else {
                // Account update failed
                // You can redirect back to the edit_account.jsp page with an error message
                request.setAttribute("updateError", "Failed to update account.");
                request.getRequestDispatcher("update_account.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle database errors
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/dashboard.jsp");
        rd.forward(request, response);
    }

    private void adminUpdateRoleAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException{
        String role = request.getParameter("editRole");
        String accountID = request.getParameter("accountID");

        JSONObject jsonResponse = new JSONObject();

        try {
            boolean updateSuccess = grantAccessReponsitory.updateIdRoleInGrantAccess( role, accountID);
            if (updateSuccess) {
                // Account updated successfully
                jsonResponse.put("success", true);
            } else {
                // Account update failed
                jsonResponse.put("success", false);
                jsonResponse.put("error", "Failed to update account.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            jsonResponse.put("success", false);
            jsonResponse.put("error", "Database error occurred.");
        }
        response.getWriter().write(jsonResponse.toString());
    }
    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String accountId = request.getParameter("accountId"); // Get the account ID to delete
        String url = "/dashboard.jsp";

        try {
            boolean deleteSuccess = accountReponsitory.deleteAccount(accountId);
            if (deleteSuccess) {
                // Account deleted successfully
                response.getWriter().write("true");
            } else {
                // Account deletion failed
                response.getWriter().write("false");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void getAccountById (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException{
        String accountID = request.getParameter("accountId");
        System.out.println(accountID);
        JSONObject jsonResponse = new JSONObject();
        try {
            Account a = accountReponsitory.getAccountById(accountID);
            request.setAttribute("account",a);
            if (a != null) {

                Gson gson = new Gson();
                String accountJson = gson.toJson(a);
                jsonResponse.put("account", accountJson);
            } else {
                // Handle the case where the role is not found
                jsonResponse.put("error", "account not found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle database errors
            jsonResponse.put("error", "Database error.");
        }

        // Set the response content type to JSON
        response.setContentType("application/json");
        // Write the JSON response to the response output stream
        response.getWriter().write(jsonResponse.toString());
    }
    private void getRoleAccountById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException{
        String accountID = request.getParameter("accountID");
        JSONObject jsonResponse = new JSONObject();
        try {
            GrantAccess gr = grantAccessReponsitory.getGrantByAccountId(accountID);
            System.out.printf("ggggggg", gr);
            if (gr != null) {
                Role role = new Role(gr.getRole().getRoleId());
                Gson gson = new Gson();
                String roleJson = gson.toJson(role);
                jsonResponse.put("role", roleJson);
            } else {
                // Handle the case where the role is not found
                jsonResponse.put("error", "Role not found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle database errors
            jsonResponse.put("error", "Database error.");
        }

        // Set the response content type to JSON
        response.setContentType("application/json");
        // Write the JSON response to the response output stream
        response.getWriter().write(jsonResponse.toString());
    }
}
