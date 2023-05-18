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
		
		List<Tarif> tarifs = XmlTarifMapper.getTarifs();

		System.out.println(tarifs.size());

		List<ConditionTaxation> conditionTaxations = XmlConditiOnTaxationMapper.getConditionTaxations();

		System.out.println(conditionTaxations.size());
		
		List<Localite> localites = XmlLocaliteMapper.getLocalites();

		System.out.println(localites.size());

	}
}
