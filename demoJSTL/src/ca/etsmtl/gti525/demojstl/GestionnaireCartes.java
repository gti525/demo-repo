package ca.etsmtl.gti525.demojstl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;


public class GestionnaireCartes {
	
	private List<BeanCarte> liste = null;
	
	public GestionnaireCartes(){
		JsonFactory factory = new JsonFactory();
		liste = new ArrayList<BeanCarte>();
		ObjectMapper mapper = new ObjectMapper(factory);
	
		try {
			InputStream in = this.getClass().getClassLoader()
                    .getResourceAsStream("cartes.json");
			JsonNode rootNode = mapper.readTree(in);
			
			Iterator<JsonNode> cartesIterator = rootNode.getElements();
			int i=0;
			while (cartesIterator.hasNext()) {
				JsonNode node = cartesIterator.next();
				
				BeanCarte bean = new BeanCarte();
				bean.setFaction(node.get("faction").getTextValue());
				bean.setNom(node.get("title").getTextValue());
				bean.setImage(node.get("imagesrc").getTextValue());
				bean.setNumero(++i);
				liste.add(bean);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<BeanCarte> obtenirListeCartes(){
		return liste;
	}
	
	public List<BeanCarte> obtenirListeCartes(String tri){
		if (tri == null){
			return obtenirListeTriExtension();
		}
		if (tri.equals("nom")){
			return obtenirListeTriNom();
		}
		if (tri.equals("faction")){
			return obtenirListeTriFaction();
		}
		return obtenirListeTriExtension();
	}
	
	private List<BeanCarte> obtenirListeTriNom(){
		Collections.sort(liste, BeanCarte.COMPARER_PAR_NOM);
		return liste;
	}
	
	private List<BeanCarte> obtenirListeTriExtension(){
		Collections.sort(liste);
		return liste;
	}
	
	private List<BeanCarte> obtenirListeTriFaction(){
		Collections.sort(liste, BeanCarte.COMPARER_PAR_FACTION);
		return liste;
	}
}
