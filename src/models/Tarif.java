package models;
import java.math.BigDecimal;

public class Tarif {
    private String codeDepartement;
    private int idClient;
    private int idClientHeritage;
    private BigDecimal montant;
    private int zone;
    
    public Tarif() {}

    public Tarif(String codeDepartement, Integer idClient, Integer idClientHeritage, BigDecimal montant, int zone) {
        this.codeDepartement = codeDepartement;
        this.idClient = idClient;
        this.idClientHeritage = idClientHeritage;
        this.montant = montant;
        this.zone = zone;
    }
    
    public Tarif(String codeDepartement, Integer idClient, BigDecimal montant, int zone) {
        this.codeDepartement = codeDepartement;
        this.idClient = idClient;
        this.montant = montant;
        this.zone = zone;
    }

    // Getters and Setters
    public String getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getIdClientHeritage() {
        return idClientHeritage;
    }

    public void setIdClientHeritage(Integer idClientHeritage) {
        this.idClientHeritage = idClientHeritage;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }
    
    public String toString() {
        return "Tarif{" +
                "codeDepartement='" + codeDepartement + '\'' +
                ", idClient=" + idClient +
                ", idClientHeritage=" + idClientHeritage +
                ", montant=" + montant +
                ", zone=" + zone +
                '}';
    }
}
