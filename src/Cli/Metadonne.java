package Cli;

import com.groupdocs.metadata.Metadata;
import com.groupdocs.metadata.core.IExif;
import com.groupdocs.metadata.core.ImageRootPackage;
import com.groupdocs.metadata.core.ExifGpsPackage;
import com.groupdocs.metadata.core.ExifIfdPackage;
import com.groupdocs.metadata.core.ExifPackage;


	//Classe d'extraction des metadonnées

public class Metadonne {
	
	public void meta(String chemin) {
	
		
		
   try (Metadata metadata = new Metadata(chemin)) {
	         
	          
       ImageRootPackage root = metadata.getRootPackageGeneric();

       
       
       //Metadonné 1
       
       System.out.println("Format:" + root.getImageType().getFileFormat());
       System.out.println("Boutisme:" + root.getImageType().getByteOrder());
       System.out.println("Type Mime:" + root.getImageType().getMimeType());
       System.out.println("Extension:" + root.getImageType().getExtension());
       System.out.println("Largeur:" + root.getImageType().getWidth());
       System.out.println("Hauteur:" + root.getImageType().getHeight());
       
        IExif data = (IExif) metadata.getRootPackage();
       ExifPackage exifPackage = data.getExifPackage();
       
       //Metadonné 2
       System.out.println("Appareil Photo : " + exifPackage.getMake());
       System.out.println("Model : " + exifPackage.getModel());
       System.out.println("Largeur 2 : " + exifPackage.getImageWidth());
       System.out.println("Hauteur 2 : " + exifPackage.getImageLength());
       System.out.println("Date : " + exifPackage.getDateTime());          
     
       
       //Métadonné 3
       
       ExifGpsPackage exifGpsPackage = exifPackage.getGpsPackage();
       
       System.out.println("Altitude : " + exifGpsPackage.getAltitude());
       System.out.println("Latitude : " + exifGpsPackage.getLatitudeRef());
       System.out.println("Longitude : " + exifGpsPackage.getLongitudeRef());
       
       //Métadonne 4
       
       ExifIfdPackage exifIfdPackage = exifPackage.getExifIfdPackage();
       
       System.out.println("Numéro de serie : " + exifIfdPackage.getBodySerialNumber());
       System.out.println("Propriétaire : " + exifIfdPackage.getCameraOwnerName());
      
       
   }



	}	
}
