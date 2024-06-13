/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myproject;

/**
 *
 * @author Stela
 */
import java.sql.SQLIntegrityConstraintViolationException;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Entity
@Named(value = "register")
@SessionScoped
public class Register implements Serializable {

     // Entity attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REGISTERID")
    private Integer registerid;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "CONFIRMPASSWORD")
    private String confirmPassword;
    private List<Wineform> wineformList;

     // DataSource for database connection
    DataSource dataSource;

    // Constructor
    public Register() {
        try {
            dataSource = (DataSource) new InitialContext().lookup("jdbc/wines");
        } catch (NamingException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Register(Integer registerid) {
        this.registerid = registerid;
    }

    public Register(Integer registerid, String username) {
        this.registerid = registerid;
        this.username = username;
    }
    

     // Getters and setters for entity attributes

    public Integer getRegisterid() {
        return registerid;
    }

    public void setRegisterid(Integer registerid) {
        this.registerid = registerid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<Wineform> getWineformList() {
        return wineformList;
    }

    public void setWineformList(List<Wineform> wineformList) {
        this.wineformList = wineformList;
    }

    // Method to register a new user
    public String register() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        // Establish database connection
        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            // Prepare and execute SQL statement to insert new user into the database
            PreparedStatement addUser = connection.prepareStatement("INSERT INTO REGISTER (USERNAME,EMAIL,PASSWORD, CONFIRMPASSWORD) VALUES ( ?, ?, ?, ?)");
            addUser.setString(1, getUsername());
            addUser.setString(2, getEmail());
            addUser.setString(3, getPassword());
            addUser.setString(4, getConfirmPassword());
            addUser.executeUpdate();

            // Reset form fields
            setUsername("");
            setEmail("");
            setPassword("");
            setConfirmPassword("");

            return "logInPage";   // Redirect to login page after successful registration
        } catch (SQLIntegrityConstraintViolationException ex) {
            // If username or email already exists, show error message
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: This username already exists.", "Error: This username or email already exists."));
            return null;
        } finally {
            connection.close(); // Close database connection
        }
    }

     // Method to handle user login
    public String login() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM REGISTER WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        // If login is successful, set session attributes and redirect to main page
                        FacesContext context = FacesContext.getCurrentInstance();
                        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
                        session.setAttribute("loggedIn", true);
                        session.setAttribute("username", username);
                        setUsername("");
                        setPassword("");
                        return "mainPage";
                    } else {
                        return "logInPage";  // If login fails, redirect to login page
                    }
                }
            }
        } catch (SQLException ex) {
            return "error";

        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registerid != null ? registerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Register)) {
            return false;
        }
        Register other = (Register) object;
        if ((this.registerid == null && other.registerid != null) || (this.registerid != null && !this.registerid.equals(other.registerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myproject.Register[ registerid=" + registerid + " ]";
    }

}
