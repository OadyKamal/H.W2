
package Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Account {
    private int id;
    private int userId;
    private int accountNumber;
    private String username;
    private String currency;
    private double balance;
    private int creationDate;

    public Account(int accountNumber, String username, String currency, double balance, int creationDate) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.currency = currency;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
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

    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public int save() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO ACCOUNT(USER_ID, ACCOUNT_NUMBER, USERNAME, CURRENCY, BALANCE, CREATION_DATE) VALUES (?, ?, ?, ?, ?, ?)";
        ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, this.getUserId());
        ps.setInt(2, this.getAccountNumber());
        ps.setString(3, this.getUsername());
        ps.setString(4, this.getCurrency());
        ps.setDouble(5, this.getBalance());
        ps.setInt(6, this.getCreationDate());
        recordCounter = ps.executeUpdate();

        if (recordCounter > 0) {
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.setId(generatedKeys.getInt(1));
            }
            System.out.println(this.getUsername() + "'s account was added successfully!");
        }

        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public static ArrayList<Account> getAllAccounts() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM ACCOUNT";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Account account = new Account(rs.getInt("ACCOUNT_NUMBER"), rs.getString("USERNAME"), rs.getString("CURRENCY"), rs.getDouble("BALANCE"), rs.getInt("CREATION_DATE"));
            account.setId(rs.getInt("ID"));
            accounts.add(account);
        }

        if (ps != null) {
            ps.close();
        }
        c.close();
        return accounts;
    }

    public int update() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "UPDATE ACCOUNT SET ACCOUNT_NUMBER=?, USERNAME=?, CURRENCY=?, BALANCE=?, CREATION_DATE=? WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getAccountNumber());
        ps.setString(2, this.getUsername());
        ps.setString(3, this.getCurrency());
        ps.setDouble(4, this.getBalance());
        ps.setInt(5, this.getCreationDate());
        ps.setInt(6, this.getId());
        recordCounter = ps.executeUpdate();

        if (recordCounter > 0) {
            System.out.println("Account with ID: " + this.getId() + " was updated successfully!");
        }

        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public int delete() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "DELETE FROM ACCOUNT WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();

        if (recordCounter > 0) {
            System.out.println("The account with ID: " + this.getId() + " was deleted successfully!");
        }

        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }
}
