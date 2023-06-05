package Gui;

import com.groupdocs.metadata.Metadata;
import com.groupdocs.metadata.core.IExif;
import com.groupdocs.metadata.core.ImageRootPackage;
import com.groupdocs.metadata.core.ExifGpsPackage;
import com.groupdocs.metadata.core.ExifIfdPackage;
import com.groupdocs.metadata.core.ExifPackage;


	//Classe d'extraction des metadonnées

public class Metadonne {
	
	//methode qui retourne un String et qui prend en paramere notre chemin qui est aussi de type String
	
	public String  meta(String chemin) {
	
		//Creation d'une variable de type String qu'on appelle metadataa
		
		String metadataa;
		
		
		
   try (Metadata metadata = new Metadata(chemin)) {
	         
	    //creation des variable permettant d'appeler la methode provenant de la librarie       
       ImageRootPackage root = metadata.getRootPackageGeneric();

        IExif data = (IExif) metadata.getRootPackage();
      ExifPackage exifPackage = data.getExifPackage();
      ExifGpsPackage exifGpsPackage = exifPackage.getGpsPackage();
      ExifIfdPackage exifIfdPackage = exifPackage.getExifIfdPackage();
      
       //Métadonné
      //On ajoute a notre variable "metadataa" l'affichage de toute nos metadonnées qui sont de forme de String
      
      metadataa = root.getImageType().getFileFormat().toString()+"\n"+ 
       root.getImageType().getMimeType().toString()+ "\n" + 
       root.getImageType().getByteOrder().toString()+"\n"+
    		
    		  Integer.toString(root.getImageType().getWidth()).toString()+"\n"+ 
    		  Integer.toString(root.getImageType().getHeight()).toString()+"\n"+
    		
			  exifPackage.getMake()+"\n"+ 
			  exifPackage.getModel()+"\n"+ 
      		  exifPackage.getDateTime()+"\n"+ 
      		  exifGpsPackage.getAltitude()+"\n"+ 
      		  exifGpsPackage.getLatitudeRef()+"\n"+ 
      		  exifGpsPackage.getLongitudeRef()+"\n"+ 
      		  exifIfdPackage.getBodySerialNumber()+"\n"+ 
      		  exifIfdPackage.getCameraOwnerName()+"\n"+
      		  exifIfdPackage.getBodySerialNumber()+"\n"+
              exifIfdPackage.getCameraOwnerName();
   	 
       
       
     
 //on retourne notre variable "metadataa" de type String contenant toute les methodes permettant de recuperer les metadonnées
       
      
      return metadataa; 
      
   }



	}	
}

