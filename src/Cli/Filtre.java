
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class Filtre {
    
    // chemin = variable contenant le chemin d'un répertoire
   
   public void Filtrage(String chemin) throws IOException {
       
       
       

// Creation d'une liste d'objet de type Path
 // Files.walk permet de parcourir une arborescence de fichiers et filtrer les opérations stream et rechercher des fichiers correspondant à une extension à partir d'un dossier 
//et de ses sous-dossiers.     	 
      Stream<Path> path = Files.walk(Paths.get(chemin));

      // Récuperer le répertoire et le parcourir
      // ceci est un repertoire et non un String,

      path = Files.walk(Paths.get(chemin));
      
      System.out.println("La liste des fichiers jpg/jpeg:");
      
      // convertir path à un String tout d'abord avec .toString()
      // .filter(var -> var.endsWith(fileExtension)) pour commencer à filtrer le path qui a etait convertis en String
      // Trouver les fichiers avec extension `jpg, jpeg` du fichier se trouvant dans le chemin specifié dans la variable "chemin" .endsWith(".jpg ou .jpeg")
      path = path.filter(var -> var.toString().endsWith(".jpg")||var.toString().endsWith(".jpeg"));
      
      // Parcourir toute la liste path (la liste d'objets de type Path recuperée) et afficher tout les fichiers trouvés
      
     
     path.forEach(System.out::println);  
      
      // Trouver les fichiers avec extension `png` du fichier se trouvant dans le chemin specifié dans la variable 'chemin' .endsWith(".png")
      // même chose ici
     
      path = Files.walk(Paths.get(chemin));
      System.out.println("La liste des fichiers png:");
      path = path.filter(var -> var.toString().endsWith(".png"));
      path.forEach(System.out::println);    
      
             
    }
}
