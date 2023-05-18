package models;

import java.math.BigDecimal;

public class ConditionTaxation {
    private int idClient;
    private BigDecimal taxePortDu;
    private BigDecimal taxePortPaye;
    private boolean useTaxePortDuGenerale;
    private boolean useTaxePortPayeGenerale;

    public ConditionTaxation(int idClient, BigDecimal taxePortDu, BigDecimal taxePortPaye,
                             boolean useTaxePortDuGenerale, boolean useTaxePortPayeGenerale) {
        this.idClient = idClient;
        this.taxePortDu = taxePortDu;
        this.taxePortPaye = taxePortPaye;
        this.useTaxePortDuGenerale = useTaxePortDuGenerale;
        this.useTaxePortPayeGenerale = useTaxePortPayeGenerale;
    }
    
    public ConditionTaxation( BigDecimal taxePortDu, BigDecimal taxePortPaye,
            boolean useTaxePortDuGenerale, boolean useTaxePortPayeGenerale) {
this.taxePortDu = taxePortDu;
this.taxePortPaye = taxePortPaye;
this.useTaxePortDuGenerale = useTaxePortDuGenerale;
this.useTaxePortPayeGenerale = useTaxePortPayeGenerale;
}

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public BigDecimal getTaxePortDu() {
        return taxePortDu;
    }

    public void setTaxePortDu(BigDecimal taxePortDu) {
        this.taxePortDu = taxePortDu;
    }

    public BigDecimal getTaxePortPaye() {
        return taxePortPaye;
    }

    public void setTaxePortPaye(BigDecimal taxePortPaye) {
        this.taxePortPaye = taxePortPaye;
    }

    public boolean isUseTaxePortDuGenerale() {
        return useTaxePortDuGenerale;
    }

    public void setUseTaxePortDuGenerale(boolean useTaxePortDuGenerale) {
        this.useTaxePortDuGenerale = useTaxePortDuGenerale;
    }

    public boolean isUseTaxePortPayeGenerale() {
        return useTaxePortPayeGenerale;
    }

    public void setUseTaxePortPayeGenerale(boolean useTaxePortPayeGenerale) {
        this.useTaxePortPayeGenerale = useTaxePortPayeGenerale;
    }
}
