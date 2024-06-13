/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package myproject;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * WineSearchBean is a managed bean responsible for searching for wine details.
 * It handles the search operation and retrieves wine information from the database.
 *  @author Stela
 */

@Named(value = "wineSearch")
@SessionScoped
public class WineSearchBean implements Serializable {

     // Attributes
    private String newWineName;
    private Wineform wineForm;

    /**
     * Creates a new instance of WineSearchBean
     */
    public WineSearchBean() {
    }

    /**
     * Method to search for wine details based on the provided wine name.
     * It retrieves wine details from the database and sets them to wineForm.
     */
    public void searchWine() {
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WinesPU"); EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Query query = entityManager.createQuery(
                    "SELECT w FROM Wineform w WHERE w.wineName = :wineName", Wineform.class)
                    .setParameter("wineName", newWineName);

            try {

                Wineform wineform = (Wineform) query.getSingleResult();
                // Retrieve wine details
                String wineName = wineform.getWineName();
                String description = wineform.getDescription();
                String sortWine = wineform.getSortWine();
                String size = wineform.getWineSize();
                BigDecimal price = wineform.getPrice();
                String country = wineform.getCountry();

                // Set wine details to wineForm
                wineForm = new Wineform();
                wineForm.setWineName(wineName);
                wineForm.setDescription(description);
                wineForm.setSortWine(sortWine);
                wineForm.setWineSize(size);
                wineForm.setPrice(price);
                wineForm.setCountry(country);


            } catch (NoResultException ex) {
                // Wine does not exist
                wineForm = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wine not found", "Error"));
            }
        }
    }

    public String getWineName() {
        return newWineName;
    }

    public void setWineName(String wineName) {
        this.newWineName = wineName;
    }

    public Wineform getWineForm() {
        return wineForm;
    }

    public void setWineForm(Wineform wineForm) {
        this.wineForm = wineForm;
    }
}
