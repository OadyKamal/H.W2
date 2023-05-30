
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Account {

    private int id;
    private int user_id;
    private int account_number;
    private String username;
    private String currency;
   private double balance;
      private int creation_date;

    public Account(int account_number, String username, String currency, double balance, int creation_date) {
        this.account_number = account_number;
        this.username = username;
        this.currency = currency;
        this.balance = balance;
        this.creation_date = creation_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(int creation_date) {
        this.creation_date = creation_date;
    }

    public int save() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO ACCOUNT(ID,USER_ID,ACCOUNT_NUMBER,USERNAME,CURRENCY,BALANCE,CREATION_DATE) VALUES(?, ?, ?, ?,?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setInt(2, this.getUser_id());
        ps.setInt(3, this.getAccount_number());
        ps.setString(4, this.getUsername());
        ps.setString(5, this.getCurrency());
        ps.setDouble(6, this.getBalance());
        ps.setInt(7, this.getCreation_date());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getUsername()
                    + " Accounts was added successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    
    public static ArrayList<Account> getAllAccount() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM ACCOUNT ";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Account account = new Account(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getInt(6));
            account.setId(rs.getInt(1));
            accounts.add(account);

        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return accounts;
    }
  public int delete() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "DELETE FROM ACCOUNT WHERE ID=? ";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The account with id : " + this.getId() + " was deleted successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }
   
  
  
  
    public int update() throws SQLException, ClassNotFoundException {
           Connection c = DB.getInstance().getConnection();
     PreparedStatement ps = null;
            int recordCounter = 0;
      String sql = "UPDATE ACCOUNT ACCOUNT_NUMBER=?, USERNAME=? , CURRENCY=?,BALANCE=? ,CREATION_DATE=? WHERE ID=?";
          ps = c.prepareStatement(sql);
         ps.setInt(1, this.getAccount_number());
       ps.setString(2, this.getUsername());
         ps.setString(3, this.getCurrency());
     ps.setDouble(4, this.getBalance());
ps.setInt(5, this.getCreation_date());
ps.setInt(6, this.getId());
   recordCounter = ps.executeUpdate();
       if (recordCounter > 0) {
         
           System.out.println("Account with id : " + this.getId() + " was updated successfully!");
       
       
       
       }
       if (ps != null) {
            ps.close();
       
        
        
        }
        c.close();
      
        
        return recordCounter;
    }

  

}
