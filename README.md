## TranThiMinhHuyen_20105231_WWW
# How to run
- To run program, let's change your port Mariadb or MySQL in file connectDB.
- My computer uses port 3307, it may be different from your computer, so please check and change the port to match mariadb.
- String url = "jdbc:mariadb://localhost:3307/mydb?createDatabaseIfNotExist=true";
# Database diagram
![img_3.png](img_3.png)

# Rule
- Admin: Insert, edit account, delete, see all list account
- User: Edit information (not edit role), can only view your own information

# UI
1. Login
   ![login.png](login.png)
2. Dashboard
   ![img.png](img.png)
3. Insert new account
   ![img_4.png](img_4.png)
   After insert
   ![img_5.png](img_5.png)
4. Edit role (Only admin can edit role)
   ![img_1.png](img_1.png)
5. See detail information
   ![img_2.png](img_2.png)
6. Admin delete account
   ![img_6.png](img_6.png)
