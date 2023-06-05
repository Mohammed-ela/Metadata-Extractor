package Gui;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Filtre {
	
	public String filtrage (String chemin) throws IOException {
		
 //Creation d'une variable String qu'on retournera a la fin
		
		String filtrat;
		
//Creation de 2 listes de type string en utilisant notre methode filtre , qui prend en parametre un chemin de type Paths et l'extension
            List<String> files = filtre(Paths.get(chemin),"jpg");
            List<String> files2 = filtre(Paths.get(chemin),"png");
            
//2 listes sont stocké dans une variable filtrat ( pour que notre methode retourne un String )
            
            filtrat=files.toString()+"\n"+files2.toString();
            
//Retourne notre variable de type String 
            
           return filtrat;
    }

	
	
    public static List<String> filtre(Path path, String fileExtension) throws IOException {

//Creation d'une list de type String qu'on nomme result
    	
        List<String> resultat;
//Files.walk(path) permet de parcourir tout les fichiers du dossier et des sous dossiers
//Verifie si notre path est bien un dossier
//.toString() permet de convertir les paths en string
//Collect permet de mettre ce qu'on a filtré dans la liste result
//endswith peut etre utiliser pour filtrer car le path a ete convertis en String
        
 //Creation d'une liste d'objet de type Path qui va correspondre au parcours de notre repertoire
        try (Stream<Path> walk = Files.walk(path)) {
resultat = walk.filter(p -> !Files.isDirectory(p)).map(p -> p.toString()).filter(f -> f.endsWith(fileExtension)).collect(Collectors.toList());
			}
        	
       //Notre methode retournera tout les fichiers filtré en fonction de l'extension qu'on pourra ainsi stocké dans une list 	
return resultat;

    }
}
    


