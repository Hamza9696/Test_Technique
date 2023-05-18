import java.util.List;

import mappers.XmlClientMapper;
import mappers.XmlTarifMapper;
import mappers.XmlConditiOnTaxationMapper;
import mappers.XmlLocaliteMapper;
import models.Client;
import models.ConditionTaxation;
import models.Localite;
import models.Tarif;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Client> clients = XmlClientMapper.getClients();

		System.out.println(clients.size());
		
        for (int i = 0; i < clients.size(); i++) {
            Client c = clients.get(i);
            System.out.println(c.toString());
        }
		
		List<Tarif> tarifs = XmlTarifMapper.getTarifs();

		System.out.println(tarifs.size());
		
        for (int i = 0; i < tarifs.size(); i++) {
        	Tarif t = tarifs.get(i);
            System.out.println(t.toString());
        }

		List<ConditionTaxation> conditionTaxations = XmlConditiOnTaxationMapper.getConditionTaxations();

		System.out.println(conditionTaxations.size());
		
        for (int i = 0; i < conditionTaxations.size(); i++) {
        	ConditionTaxation cT = conditionTaxations.get(i);
            System.out.println(cT.toString());
        }
		
		List<Localite> localites = XmlLocaliteMapper.getLocalites();

		System.out.println(localites.size());
		
        for (int i = 0; i < localites.size()-1500; i++) {
        	Localite l = localites.get(i);
            System.out.println(l.toString());
        }
        
        

	}
}
