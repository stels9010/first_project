/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myproject;


import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;



/**
 *
 * @author Stela
 */
@Entity
@Table(name = "WINEFORM")
@NamedQueries({
    @NamedQuery(name = "Wineform.findAll", query = "SELECT w FROM Wineform w"),
    @NamedQuery(name = "Wineform.findByWineId", query = "SELECT w FROM Wineform w WHERE w.wineid = :wineid"),
    @NamedQuery(name = "Wineform.findByWineName", query = "SELECT w FROM Wineform w WHERE w.wineName = :wineName"),
    @NamedQuery(name = "Wineform.findByDescription", query = "SELECT w FROM Wineform w WHERE w.description = :description"),
    @NamedQuery(name = "Wineform.findBySortWine", query = "SELECT w FROM Wineform w WHERE w.sortWine = :sortWine"),
    @NamedQuery(name = "Wineform.findByPrice", query = "SELECT w FROM Wineform w WHERE w.price = :price")})
public class Wineform implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WINEID")
    private Integer wineid;
    @Column(name = "WINE_SIZE")
    private String wineSize;
    @JoinColumn(name = "REGISTERID", referencedColumnName = "REGISTERID")
    @ManyToOne
    private Register registerid;
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "WINE_NAME")
    private String wineName;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SORT")
    private String sortWine;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "COUNTRY")
    private String country;
      

    public Wineform(){}

     public Wineform(Integer wineid) {
        this.wineid = wineid;
    }
     
    public Wineform(Integer wineid, String wineName) {
        this.wineid = wineid;
        this.wineName = wineName;
    }
    

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSortWine() {
        return sortWine;
    }

    public void setSortWine(String sortWine) {
        this.sortWine = sortWine;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

   
    public Integer getWineid() {
        return wineid;
    }

    public void setWineid(Integer wineid) {
        this.wineid = wineid;
    }

    public String getWineSize() {
        return wineSize;
    }

    public void setWineSize(String wineSize) {
        this.wineSize = wineSize;
    }

    public Register getRegisterid() {
        return registerid;
    }

    public void setRegisterid(Register registerid) {
        this.registerid = registerid;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wineid != null ? wineid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wineform)) {
            return false;
        }
        Wineform other = (Wineform) object;
        if ((this.wineid == null && other.wineid != null) || (this.wineid != null && !this.wineid.equals(other.wineid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myproject.Wineform[ wineid=" + wineid + " ]";
    }

}
