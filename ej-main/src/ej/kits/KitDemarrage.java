package ej.kits;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

import ej.blocs.IBloc;
import ej.exceptions.IllegalBlocException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.io.FileNotFoundException;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class KitDemarrage {
	
	private Logger logger = LogManager.getLogger(KitDemarrage.class);

	private Set<IBloc> blocs = new LinkedHashSet<>();
	private Set<String> motsCles = new LinkedHashSet<>();

	public Set<IBloc> getBlocs() {
		return blocs;
	}

	public Set<String> getMotsCles() {
		return motsCles;
	}

	public KitDemarrage(final Set<IBloc> blocs) throws IllegalBlocException {
		this.blocs.addAll(blocs);

		motsCles.add("Cabane");
		motsCles.add("Muraille");
		motsCles.add("Maison");
	}

	public void afficherKit() {
		System.out.println("Nombre de blocs dans le kit : " + blocs.size());
		System.out.print("Liste des mots clés du kit : ");
		
		// Implementation of lambda 
		//@FunctionnalInterface
		Consumer<String> fonction = (motCle) -> {System.out.println(motCle + " ");};
		
		motsCles.forEach(fonction);
		
		//for (String motCle : motsCles) {
			//System.out.print(motCle + " ");
		//}
	}
	
	
	// Sauvegarder un kit dans un fichier
	public void sauvegarder() throws IOException {
		// Construire une chaine de caractère
		StringBuilder builder = new StringBuilder();
		
		// Ajouter dans builder les chaines de caractères qui vont composer la chaine finale
		builder.append("Kit de demarrage \n");
		
		//Parcourir la liste de mot clés pour construire la chaine de texte
		
		for(String motCle : motsCles){
			builder.append(motCle + " ");
		}
		
		try {
			//Ecrire dans un fichier. Pour cela il faut acceder a ce fichier
			BufferedWriter writer = new BufferedWriter(new FileWriter("Kit.txt"));
		//Ecrire dans le fichier. A noter que toString transform le contenu d'un objet en une chaine de caractere et donc va nous renvoyer tout les élements qu'on a placé à l'intérieur du builder
			writer.write(builder.toString());
			writer.close();
		} catch (IOException e) {
			logger.error("Impossible d'écrire dans le fichier");
		}
	}

	// Lisons le contenu du fichier
	public void charger(){
		try (BufferedReader reader = new BufferedReader(new FileReader("Kit.txt"))){
			String line = null;
			while((line = reader.readLine()) != null) {
				System.out.println("oooooooooooooooooooo " + line);
			} 
		} catch (FileNotFoundException e) {
			logger.error("Ah oui le fichier n'existe pas. ");
		} catch (IOException e){
			logger.error("Impossible de lire le fichier kit.txt");
		}
	
	
	}

	
 }

