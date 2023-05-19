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

		// Saisir le nombre de colis et le poids de l'expédition.
		//int nombreColis = enterNumberOfPackages();
		//double poids = enterWeight();

		int zone = findZoneByVille(destinataire.getVille());
		//int zone = findZoneByVille("GUEUGNON");
		System.out.println("zone --> " + zone + " correspond à la ville :" + destinataire.getVille());
		
		//Tarif findTarif1 =  findTarif(zone, destinataire.getIdClient());
		Tarif findTarif1 =  findTarif(zone, 6);
		System.out.println("Tarif --> " + findTarif1);
		
		Tarif compTarif =  computeTarif(destinataire);
		System.out.println("CompTarif --> " + compTarif);
		
		BigDecimal montant =  findMontant(destinataire);
		System.out.println("Montant --> " + montant);
		

		BigDecimal montantTotalHT =  calculateHTAmount(destinataire);
		System.out.println("MontantHT --> " + montantTotalHT);

		///////////////////////////////////////
		
		Payeur payeur =  selectPayeur();
		System.out.println("Type payeur --> " + payeur);
		
		BigDecimal cpt =  calculateTax(destinataire);
		System.out.println("Compute Tax --> " + cpt);

		
		///////////////////////////////////////


//		BigDecimal montantTotal =  calculateTax(destinataire);
//		System.out.println("Montant --> " + montantTotal);


		//BigDecimal montantUnitaire = findMontant(destinataire);
		//System.out.println("montantUnitaire --> " + montantUnitaire);

		//BigDecimal montantHT = calculateHTAmount(destinataire, poids, nombreColis);
		//System.out.println("montantHT --> " + montantHT);

		// Sélectionner qui de l'expéditeur ou du destinataire règle le transport (port
		// payé ou port dû respectivement)
//        
//        // Présenter le détail du calcul (montant ht tarif (issu de tarif.xml) + taxe à appliquer (issue de conditiontaxation.xml))
//        double montantHT = calculateHTAmount(destinataire, poids, portPaye);
//        double taxe = calculateTax(destinataire, portPaye);
//        double montantTotal = montantHT + taxe;
		
		
		//BigDecimal total = calculateTax(destinataire);
		//System.out.println("Total --> " + total);

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


	public static Integer findZoneByVille(String ville) {
	    List<Localite> localites = XmlLocaliteMapper.getLocalites();
	    return localites.stream()
	            .filter(localite -> localite.getVille().equals(ville))
	            .mapToInt(Localite::getZone)
	            .findFirst()
	            .orElse(-1);
	}
	
	public static int enterNumberOfPackages() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Veuillez saisir le nombre de colis : ");
		int nombreColis = scanner.nextInt();
		return nombreColis;
	}

	public static double enterWeight() {
		// Demander à l'utilisateur de saisir le poids de l'expédition et retourner la
		// valeur saisie
		Scanner scanner = new Scanner(System.in);
		System.out.print("Veuillez saisir le poids de l'expédition : ");
		double poids = scanner.nextDouble();
		return poids;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//	public static Tarif findTarif(Integer zone, Integer idClient) {
//	    List<Tarif> tarifs = XmlTarifMapper.getTarifs();
//	    Tarif tarif = tarifs.stream()
//	            .filter(element -> (element.getIdClient().equals(idClient) && element.getZone() == zone)
//	            .findFirst()
//	            .orElse(null);
//	    if(tarif == null && zone > 0) {
//	    	return findTarif( zone-1, idClient);
//	    }
//	    return null;
//	}
	
	
	public static Tarif findTarif(Integer zone, Integer idClient) {
	    List<Tarif> tarifs = XmlTarifMapper.getTarifs();
			for (Tarif tarif : tarifs) {
				if (tarif.getIdClient().equals(idClient) && tarif.getZone() == zone) {
					return tarif;
				} 
				if (tarif == null && zone > tarif.getZone()) {
					return findTarif( zone-1, idClient);
				} 
			}
		return null; // If no matching locality is found
	}	
	
//	public static Tarif computeTarif(Client client) {
//		int clientZone = findZoneByVille(client.getVille());
//		Tarif clientTarif = findTarif(clientZone, client.getIdClient());
//		if(clientTarif == null){
//			clientTarif = findTarif(clientZone, 0); 
//			return clientTarif;
//		}
//		return clientTarif;
//	}

	
	public static Tarif computeTarif(Client client) {
		int clientZone = findZoneByVille(client.getVille());
		Tarif clientTarif = findTarif(clientZone, client.getIdClient());
		if(clientTarif == null){    	// le client ne posséde pas de Tarif pour ce département 
			clientTarif = findTarif(clientZone, 0); 
			return clientTarif;
		}
		return clientTarif;
	}
	
	
	
	public static BigDecimal findMontant(Client client) {
		Tarif clientTarif = computeTarif(client);
	    if (clientTarif != null) {
	        return clientTarif.getMontant();
	    } else {
	        // Handle the case when no matching tariff is found
	        System.out.println("No tariff found for the client and zone.");
	        return null;
	    }
		
	}
	
	public static Payeur selectPayeur() {
		Scanner scanner = new Scanner(System.in);
		Payeur payeur;
		System.out.println("Sélectionnez le mode de paiement :");
		System.out.println("1. Port payé");
		System.out.println("2. Port dû");
		int choice = scanner.nextInt();

		if (choice == 1) {
			payeur = Payeur.SENDER; // Port payé par l'expéditeur
		} else if (choice == 2) {
			payeur = Payeur.RECEIVER; // Port dû par le destinataire
		} else {
			payeur = selectPayeur(); // do while
		}

		scanner.close();
		return payeur;

	}

	public static BigDecimal calculateHTAmount(Client client) {
		// Calculer le montant HT en utilisant les informations du destinataire, le
		// poids et le mode de paiement
		double poids = enterWeight();
		int nombreColis = enterNumberOfPackages();
		BigDecimal montant = findMontant(client);
		BigDecimal res = BigDecimal.valueOf(poids).multiply(montant);
		BigDecimal montantHT = BigDecimal.valueOf(nombreColis).multiply(res);
		return montantHT;

		// Montant HT = Montant du tarif * Nombre de colis * poids + Taxe à applique
	}
	
	public static ConditionTaxation findConditionTaxation(Integer idClient) {
	    List<ConditionTaxation> conditionTaxations = XmlConditiOnTaxationMapper.getConditionTaxations();
	    return conditionTaxations.stream()
	            .filter(element -> (element.getIdClient() == idClient))
	            .findFirst()
	            .orElse(null);
	}
	
	
//	public static BigDecimal findTaxE(Client destinataire) {
//    	List<ConditionTaxation> conditionTaxations = XmlConditiOnTaxationMapper.getConditionTaxations();
//			for (ConditionTaxation conditionTaxation : conditionTaxations) {
//				if (conditionTaxation.getIdClient() == destinataire.getIdClient()) {
//					return conditionTaxation.getTaxePortPaye();
//				} 
//			}
//		return null; 
//	}
    
	
	
	public static ConditionTaxation computeTaxation(Client client, Payeur payeur) {
		ConditionTaxation conditionTaxationClient = findConditionTaxation(client.getIdClient());
		
		if((conditionTaxationClient.isUseTaxePortDuGenerale() == true && payeur == Payeur.SENDER) 
				|| (conditionTaxationClient.isUseTaxePortPayeGenerale() == true && payeur == Payeur.RECEIVER)) {
			conditionTaxationClient = findConditionTaxation(0);
		}
		return conditionTaxationClient;
	}
	
	
	public static BigDecimal calculateTax(Client client) {
		Payeur payeur = selectPayeur();
		double poids = enterWeight();
		int nombreColis = enterNumberOfPackages();
		BigDecimal montantHT = calculateHTAmount(client) ;
		if (payeur == Payeur.RECEIVER) {
			BigDecimal tax = computeTaxation(client,payeur).getTaxePortDu();
			return montantHT.add(tax);
		}
		BigDecimal tax = computeTaxation(client,payeur).getTaxePortPaye();
		return montantHT.add(tax);
	}
	
	
}

//    }
//    
//    public static void displayCalculationDetails(Client expediteur, Client destinataire, int nombreColis, double poids, boolean portPaye, double montantHT, double taxe, double montantTotal) {
//        // Afficher les détails du calcul, y compris les informations sur l'expéditeur, le destinataire, le nombre de colis, le poids, le mode de paiement, le montant HT, la taxe et le montant total
//    }
//}
