package models;
public class Client {
    private String codePostal;
    private int idClient;
    private String raisonSociale;
    private String ville;

    // Constructor
    public Client() {}
    public Client(String codePostal, int idClient, String raisonSociale, String ville) {
        this.codePostal = codePostal;
        this.idClient = idClient;
        this.raisonSociale = raisonSociale;
        this.ville = ville;
    }

    // Getters and Setters
    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    
    public String toString() {
        return "Client{" +
                "codePostal='" + codePostal + '\'' +
                ", idClient=" + idClient +
                ", raisonSociale='" + raisonSociale + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
