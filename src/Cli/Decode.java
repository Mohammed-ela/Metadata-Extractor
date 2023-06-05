package Cli;

import java.awt.Color;

import java.awt.image.BufferedImage;



public final class Decode {

//Methode permettant de d'obtenir le message en lettre à partir d'une variable de type string qui est en bit
	
public static String getMessage (String msgbit) {
	//variable qui va prendre le bit le plus a gauche
		int count = msgbit.length()-1;
		
		//creation d'un message de type StringBuilder	
		StringBuilder message = new StringBuilder();
		
		int values = msgbit.length()/8;
		byte[] ba = new byte[values];
		int arrayCount = values-1;
		while (arrayCount > 0) {
			StringBuilder bits = new StringBuilder();
			for (int i = 0; i < 8; i++) {
				bits.insert(0,msgbit.charAt(count-i));
			}
			byte b = (byte) Integer.parseInt(bits.toString(), 2);
			int x = Byte.toUnsignedInt(b);
			ba[arrayCount] = (byte) x;
			char c = (char) x;
			message.insert(0,c);
			
				count = count - 8;
				arrayCount--;
			
		}
		String fin = new String(ba);
		
		return fin;
	} 
//methode permettant de recuperer le message caché sous forme de bit a partir d'une image
	public static String decodeMessage(BufferedImage image) {
		StringBuilder sb = new StringBuilder();

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				
//Creation d'une variable c un object de la classe Color qui va correspondre a la couleur d'un pixel 
				Color c = new Color(image.getRGB(x,y)); //color of pixel
				// r qui sera le rouge
				byte r = (byte) c.getRed(); 
				// v qui sera le vert
				byte g = (byte) c.getGreen(); 
				// b qui sera le bleu
				byte b = (byte) c.getBlue(); 
				
//initialisation d'un tableau de type byte qui contiens les variable r,g,b
				byte[] RGB = {r, g, b};
				//Parcour du tableau
				for (int i = 0; i < 3; i++) {
					
					//si le bit de poids faible est egale a 1
					if ((RGB[i] & 1) == 1) { 
						//ecrire 1
						sb.append("1");
					} else { //else it is a 0
					//sinon ecrire 0
						sb.append("0");
					}
				}
			}
		}
		//retourne une variable qui contiendra notre message mais sous forme binaire (1,et0)
		return sb.toString();
	}

}
