import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import mappers.XmlClientMapper;
import mappers.XmlConditiOnTaxationMapper;
import mappers.XmlLocaliteMapper;
import mappers.XmlTarifMapper;
import models.Client;
import models.ConditionTaxation;
import models.Localite;
import models.Tarif;

public class Cli {
    public static void main(String[] args) {
        // Sélectionner un expéditeur et un destinataire (issus du fichier client.xml)
		List<Client> clients = XmlClientMapper.getClients();
        Client expediteur = selectClient(clients);
        Client destinataire = selectClient(clients);
    	List<Localite> localites = XmlLocaliteMapper.getLocalites();
    	List<ConditionTaxation> conditionTaxations = XmlConditiOnTaxationMapper.getConditionTaxations();
		List<Tarif> tarifs = XmlTarifMapper.getTarifs();

        
        // Saisir le nombre de colis et le poids de l'expédition.
        int nombreColis = enterNumberOfPackages();
        double poids = enterWeight();
                
        int zone = findZoneByVille(destinataire);
        System.out.println( "zone --> " + zone);
        
        BigDecimal montantUnitaire = findMontant(destinataire);
        System.out.println( "montantUnitaire --> " + montantUnitaire);
        
        BigDecimal montantHT = calculateHTAmount(destinataire, poids, nombreColis);
        System.out.println( "montantHT --> " + montantHT);
        
        
       // Sélectionner qui de l'expéditeur ou du destinataire règle le transport (port payé ou port dû respectivement)
        boolean portPaye = selectPaymentMethod();
//        
//        // Présenter le détail du calcul (montant ht tarif (issu de tarif.xml) + taxe à appliquer (issue de conditiontaxation.xml))
//        double montantHT = calculateHTAmount(destinataire, poids, portPaye);
//        double taxe = calculateTax(destinataire, portPaye);
//        double montantTotal = montantHT + taxe;
        BigDecimal total = calculateTax(destinataire,  portPaye);     
        System.out.println( "Total --> " + total);

//        displayCalculationDetails(expediteur, destinataire, nombreColis, poids, portPaye, montantHT, taxe, montantTotal);
    }
    

    
    public static Client selectClient(List<Client> clients) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Veuillez sélectionner un client :");
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            System.out.println((i + 1) + ". " + client.getRaisonSociale());
        }
        
        int choix = scanner.nextInt();
        return clients.get(choix - 1);
    }
    
    public static int enterNumberOfPackages() {
        // Demander à l'utilisateur de saisir le nombre de colis et retourner la valeur saisie
        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez saisir le nombre de colis : ");
        int nombreColis = scanner.nextInt();
        return nombreColis;
    }
    
    public static double enterWeight() {
        // Demander à l'utilisateur de saisir le poids de l'expédition et retourner la valeur saisie
        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez saisir le poids de l'expédition : ");
        double poids = scanner.nextDouble();
        return poids;
    }
        
	public static int findZoneByVille(Client client) {
    	List<Localite> localites = XmlLocaliteMapper.getLocalites();
			for (Localite localite : localites) {
				if (localite.getVille().equals(client.getVille())) {
					return localite.getZone();
				} 
			}
		return -1; // If no matching locality is found
	}

	public static BigDecimal findMontant(Client client) {
		List<Tarif> tarifs = XmlTarifMapper.getTarifs();
		for (Tarif tarif : tarifs) {
			if (tarif.getIdClient().equals(client.getIdClient()) && tarif.getZone() == findZoneByVille(client)) {
				return tarif.getMontant();
			} else {			if (tarif.getIdClient() == 0 && tarif.getZone() == findZoneByVille(client)) { //Tarif générale correspondand au idClient égal à 0
				return tarif.getMontant();
			} 

			}
		}
	return null; // If no matching locality is found
}

	
	
	
    public static boolean selectPaymentMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sélectionnez le mode de paiement :");
        System.out.println("1. Port payé");
        System.out.println("2. Port dû");
        int choice = scanner.nextInt();
        
        boolean isPaidBySender;
        
        if (choice == 1) {
            isPaidBySender = true; // Port payé par l'expéditeur
        } else if (choice == 2) {
            isPaidBySender = false; // Port dû par le destinataire
        } else {
            System.out.println("Choix invalide. Utilisation du mode par défaut (port payé).");
            isPaidBySender = true; // Utilisation du mode par défaut (port payé)
        }
        
        scanner.close();
        return isPaidBySender;
    }

	
	
	
	
	
    public static BigDecimal calculateHTAmount(Client destinataire, double poids, int nombreColis) {
        // Calculer le montant HT en utilisant les informations du destinataire, le poids et le mode de paiement
    	BigDecimal montant = findMontant(destinataire);
    	BigDecimal res = BigDecimal.valueOf(poids).multiply(montant);
    	BigDecimal montantHT = BigDecimal.valueOf(nombreColis).multiply(res);
    	return montantHT;
    	
    	//Montant HT = Montant du tarif  * Nombre de colis * poids + Taxe à appliquer

    }
    
    
    
    
	public static BigDecimal findTaxD(Client destinataire) {
    	List<ConditionTaxation> conditionTaxations = XmlConditiOnTaxationMapper.getConditionTaxations();
			for (ConditionTaxation conditionTaxation : conditionTaxations) {
				if (conditionTaxation.getIdClient() == destinataire.getIdClient()) {
					return conditionTaxation.getTaxePortDu();
				} 
			}
		return null; 
	}
    
	public static BigDecimal findTaxE(Client destinataire) {
    	List<ConditionTaxation> conditionTaxations = XmlConditiOnTaxationMapper.getConditionTaxations();
			for (ConditionTaxation conditionTaxation : conditionTaxations) {
				if (conditionTaxation.getIdClient() == destinataire.getIdClient()) {
					return conditionTaxation.getTaxePortPaye();
				} 
			}
		return null; 
	}
    
    
    
    
	public static BigDecimal calculateTax(Client destinataire, boolean portPaye) {
		portPaye = selectPaymentMethod();
		double montantHT = 69.02;
		if (portPaye == true) {
			BigDecimal tax = findTaxD(destinataire);
			return BigDecimal.valueOf(montantHT).add(tax);
		}
		BigDecimal tax = findTaxE(destinataire);
		return BigDecimal.valueOf(montantHT).add(tax);
	}

//    }
//    
//    public static void displayCalculationDetails(Client expediteur, Client destinataire, int nombreColis, double poids, boolean portPaye, double montantHT, double taxe, double montantTotal) {
//        // Afficher les détails du calcul, y compris les informations sur l'expéditeur, le destinataire, le nombre de colis, le poids, le mode de paiement, le montant HT, la taxe et le montant total
//    }
}


