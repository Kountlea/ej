package ej;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import ej.blocs.IBloc;
import ej.blocs.Mur;
import ej.blocs.Porte;
import ej.exceptions.IllegalBlocException;
import ej.kits.KitDemarrage;
import ej.blocs.Toit;
import ej.blocs.Type;


public class Main {

	private static Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) throws IOException, IllegalBlocException {

		logger.info("Lancement du programme Epicrafter's Journey.");

		try {
				// Le programme commence avec un Kit de démarrage.
				KitDemarrage kit = new KitDemarrage(constructionSetBlocs());
				kit.sauvegarder();
				kit.charger();
				System.out.println("\n Vous possédez un kit de démarrage ! \n");
				
			} catch (IllegalBlocException e) {
				System.out.println("Impossible de construire le Kit de démarrage.");
			}
		
		System.out.println("Options pour le choix de l'utilisateur: ");
		userChoice();
	
			logger.info("Arret du programme Epicrafter's Journey.");
			
		
	}

	private static Set<IBloc> constructionSetBlocs() throws IllegalBlocException {
		Set<IBloc> blocs = new LinkedHashSet<>();

		// Le kit contient 4 blocs Mur.

		blocs.add(new Mur(3, 2, 2, true));
		blocs.add(new Mur(3, 2, 2, true));
		blocs.add(new Mur(2, 1, 2, false));
		blocs.add(new Mur(2, 1, 2, false));

		// Le kit contient 1 bloc Porte.
		blocs.add(new Porte(1, 2, 2, true));

		// Le kit contient 4 blocs Toit.
		blocs.add(new Toit(3, 1, 1));
		blocs.add(new Toit(3, 1, 1));
		blocs.add(new Toit(3, 1, 1));
		blocs.add(new Toit(3, 1, 1));

		return blocs;
		
	}

	

	public static void userChoice() throws IOException, IllegalBlocException {
		KitDemarrage kit = new KitDemarrage(constructionSetBlocs());
		System.out.println("Quel choix voulez-vous faire? \n 1. Idées de constructions ... \n ou \n 2. Nombre de blocs de chaque type présent dans le kit" );
		BufferedReader reading = new BufferedReader(new InputStreamReader(System.in));
		String response = reading.readLine();
		
		if (response.equals("1")) {
			// Il affiche les mots clés associés au Kit pour donner des idées à l'utilisateur.
			System.out.println("\n Voici quelques idées de constructions avec le Kit de démarrage : ");
			Set<String> motsCles = kit.getMotsCles();
			for(String mot : motsCles) {
				System.out.println(mot);
				
			}
			
		} else if(response.equals("2")) {
			// Il affiche à l'utilisateur le nombre de blocs en fonction du type à contenu par le Kit.
			System.out.println("\n Voici le nombre de blocs de chaque type contenu dans le Kit de démarrage : ");
			Map<Type, Integer> quantiteBloc = new TreeMap<Type, Integer>(); // La TreeMap permet de trier les entrées par ordre alphabétique de la clé.
			for (IBloc bloc : kit.getBlocs()) {
				Type type = Type.valueOf(bloc.getClass().getSimpleName().toUpperCase());
				int quantite = quantiteBloc.getOrDefault(type, 0) + 1; // Quantite existante + 1.
				quantiteBloc.put(type, quantite);
			}
			Set<Type> types = quantiteBloc.keySet();
			for(Type type : types) {
				System.out.println(type.toString() + " " + quantiteBloc.get(type));
			}
			
		} else {
			System.out.println("La valeur n'est pas valide entrer le chiffre 1 ou 2.");
		}
		
	}
	
	
	
}







