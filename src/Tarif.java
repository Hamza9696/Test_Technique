import java.math.BigDecimal;

public class Tarif {
    private String codeDepartement;
    private int idClient;
    private int idClientHeritage;
    private BigDecimal montant;
    private int zone;

    public Tarif(String codeDepartement, int idClient, int idClientHeritage, BigDecimal montant, int zone) {
        this.codeDepartement = codeDepartement;
        this.idClient = idClient;
        this.idClientHeritage = idClientHeritage;
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

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdClientHeritage() {
        return idClientHeritage;
    }

    public void setIdClientHeritage(int idClientHeritage) {
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
}
