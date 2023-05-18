package models;

public class Localite {
    private String codePostal;
    private String ville;
    private Integer zone;

    public Localite(String codePostal, String ville, Integer zone) {
        this.codePostal = codePostal;
        this.ville = ville;
        this.zone = zone;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }
    
    @Override
    public String toString() {
        return "Localite{" +
                "codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", zone=" + zone +
                '}';
    }
}
