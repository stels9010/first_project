package myproject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Map;


/**
 * Bean class for managing main page actions and navigation
 * @author Stela
 */
@Named(value = "bean")
@SessionScoped
public class MainPageBean implements Serializable {
    
    // Method to handle user logout
    public String logout() {
        // Redirect to login page
        try {
            // Invalidate session and redirect to login page
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.invalidateSession();

            // Clear session variables
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            sessionMap.remove("loggedIn");

            return "logInPage";  // Redirect to login page after logout
        } catch (Exception e) {
            return "error";   // Return error if exception occurs
        }
    }

     // Methods to navigate to different wine pages
    public String goToWhiteWinePage() {
        return "whiteWinePage";
    }

    public String goToRedWinePage() {
        return "redWinePage";
    }

    public String goToChampagnePage() {
        return "champagnePage";
    }

    public String goToRoseWinePage() {
        return "roseWinePage";
    }
    
    // Method to navigate to wine form Create page
    public String goToCreate() {
        return "/wineform/Create";
    }
   
    // Method to navigate to the main page
    public String goToMainPage() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        boolean isLoggedIn = sessionMap.get("loggedIn") != null && (Boolean) sessionMap.get("loggedIn");
        if (isLoggedIn) {
            return "mainPage";  // Redirect to main page if logged in
        } else {
            return "logInPage"; // Redirect to login page if not logged in
        }
    }

      // Methods to navigate to different pages based on user's login status
    public String goToShopPage() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        boolean isLoggedIn = sessionMap.get("loggedIn") != null && (Boolean) sessionMap.get("loggedIn");
        if (isLoggedIn) {
            return "shopPage";
        } else {
            return "logInPage"; // Redirect to login page if not logged in
        }
    }

    public String goToContactsPage() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        boolean isLoggedIn = sessionMap.get("loggedIn") != null && (Boolean) sessionMap.get("loggedIn");
        if (isLoggedIn) {
            return "aboutusPage";
        } else {
            return "logInPage"; // Redirect to login page if not logged in
        }
    }

    // Method to navigate to the login page
    public String goToLoginPage() {
        return "logInPage";
    }

    // Method to navigate to browse items page
    public String goToBrowseItems() {
        return "browseItems";
    }

    // Method to navigate to sale items page
    public String goToSaleItems() {
        return "saleItems";
    }
    
    // Method to navigate to the register page
     public String goToRegisterPage() {
        return "registerPage";
    }
   
}
