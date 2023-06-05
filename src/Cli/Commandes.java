package Cli;

import java.awt.image.BufferedImage;


import java.io.IOException;

import java.io.File;

import javax.imageio.ImageIO;



//Classe avec les commandes dans le Cli

public class Commandes {

	
	public static void main(String[] args) throws IOException {
		
	
		
	
		
		 
		 
		//Si aucun argument n'est rentré dans la console (le args[0] ne contiens rien)
		 
		if (args.length<1||args.length==0) {
			
			System.out.println("Vous n'avez entré aucun parametre \n Taper java -jar cli.jar -h pour obtenir de l'aide.");
			
		}
		
		//Si l'argument rentré est -h et que la longueur est forcement supérieur a 0 ( 1 dans notre cas) 
		
		 if (args.length > 0 && args[0].equals("-h")){
	            
			 //Afficher les différentes commandes à l'aider d'un SysO
			 
			 System.out.println(
			 "java -jar cli.jar -f file.(ext) : Afficher les métadonnées de notre IMAGE \n"+
			 "java -jar cli.jar -d /repertoire/ : Parcours le repertoire et retourne les fichiers IMAGE\n"+
			 "java -jar cli.jar -f file.(ext) -s 'Texte à dissimuler' : Stocker le message \n"+
			 "java -jar cli.jar -f file.(ext) -e : Extraction du message caché"+
			 "java -jar gui.jar : Permet de lancer l'IHM graphique ");
			 
 }
		
		// longueur = 2 ,  car on rentre le -f et le chemin de l'image  
		// on va rentre -f en premiere argument d'ou args[0]="-f"
		 if (args.length==2 && args[0].equals("-f")){
	
			 //on creer un nouvelle objet de la classe metadonnée qu'on appelle extraction
			Metadonne extraction =new Metadonne();
				
			// extraction va permettre d'appeller notre methode "meta" (puisque cest un objet de la classe metadonnée)
		   // la methode meta prend en parametre un chemin qui correspond à notre args[1]
			extraction.meta(args[1]);
			
		 }
		 
			else if (args[0].equals("-f") && args[2].equals("-e")) {
				
			    
              //Creation d'une variable de type String qui va prendre notre chemin du fichier à décoder
				
                String filePath = args[1];

                
//Creation d'une variable de type File qui va prendre le chemin du fichier à decoder(qui ne plus etre de type String mais File) 
                
                File outFile = new File(filePath);
                
//Creation d'une variable de type BufferedImage ( qui va nous permettre de lire un fichier image )                
                BufferedImage image = null;
//La variable image de type BufferedImage va etre utiliser                 
//Lecture du fichier de l'image de type File 
                image = ImageIO.read(outFile);

 //Appelle de la methode decodeMessage en mettant notre image en parametre (cela est possible vu qu'il est de type bufferedImage)   
                String bitMessage = Decode.decodeMessage(image);

            
 //Afficher le message caché                
                System.out.println("Voici le message dissimulé :"+Decode.getMessage(bitMessage));
			
		}
			
			
	
//longueur=2 , car on a 2 parametres qui sont le -d et le chemin qui correspond au args[1]
		 
		 if (args.length==2 && args[0].equals("-d")){
		//Creation d'un objet de la classe Filtre3 qu'on va nommé filtre3
	 Filtre filtre =new Filtre();
		//filtre  qui est un objet de la classe Filtre va nous permettre de faire appel a la methode Filtrage
	 //On met en parametre args[1] qui correspond a notre chemin pour pouvoir utiliser la methode Filtrage
			 filtre.Filtrage(args[1]);
			 
		}
		 
		 
//longueur=4 , car on a le -f , le chemin args[1] , le -s et le message a dissimule qui correspond au args[3]
		 
		 if (args.length==4 &&args[0].equals("-f") && args[2].equals("-s")) {
			 
		//on stock le message caché dans args[3] depuis la console ( encodage est stocké dans cette variable message)
				String message = args[3];
	   //on stock le chemin du fichier dans args[1] qu'on doit encoder depuis la console
				String chemin = args[1];
			

	 //notre chemin image va etre stocke dans la variable inFile et va etre de type fichier (pour pouvoir etre lu par d'autre methode)
				File file = new File(chemin);
	//Initialisation de notre methode bufferedImage qui nous permet de lire un fichier image
				BufferedImage image = null;
	//notre buffered va lire notre fichier de type fichier 
				image = ImageIO.read(file);

				
				//Creation d'une variable de type String qui etre l'appelle de la methode "encodeMessage" de la classe Encode
				//le message de l'args[3] va etre utiliser en tant que parametre
				
				  String msgbit = Encode.encodeMessage(message);
				  
		//Creation d'une variable de type Image qui va permettre d'appeller la methode "encodeImage" de la classe Encode

				  BufferedImage msgfinal=Encode.encodeImage(msgbit,image);
				  //Creation d'une variable de type File pour pouvoir manipuler notre msgfinal et ecrire le msg caché
	              File finalImage = new File(chemin);	
			// utilisation de la methode write afin d'ecrire dans notre fichier "finalimage" le "msgfinal" 
					ImageIO.write(msgfinal,"png",finalImage);
					
				
				
				
			//affichage 
					System.out.println("Voici le chemin de votre nouvelle image encodé :"+finalImage+"\n"+
					"Pour Décoder veuillez saisir : java -jar cli.jar -f file.(ext) -e "
							
							
							
							);
					
				}
		 }
			 
			
				

	}
	


	



