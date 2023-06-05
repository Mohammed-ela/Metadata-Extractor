package Cli;



import java.awt.Color;

import java.awt.image.BufferedImage;

import java.math.BigInteger;



public final class Encode {
//methode qui permet de transformer notre message en bit 
//methode qui retourne un string (ensemble de 1 et 0) et qui prend en parametre notre message en String
	
	public final static String encodeMessage (String message) {
		//BigInteger methode permettant de faire des calcules
		//bitString contiens notre message mais sous forme de bits (getBytes)
		
		String bitString = new BigInteger(message.getBytes()).toString(2);

		//si le nombre de bit n'est pas un multiple de 8 ( 8 , 16 , 32 ...) , codé sur 8 octets 
		if (bitString.length()%8 != 0) {
			String zeroes = "";
		//si le nombre de bit est un multiple de 8 ( 8 , 16 , 32 ...) 
		//tant que l'addition du bitString et zeroes n'est pas codé sur 8 octets alors remplissez en rajoutant des "0"
			while ((bitString.length() + zeroes.length()) % 8 != 0) {
				zeroes = zeroes + "0";
			}
			//Si c'est bien codé sur 8 octets 
			bitString = zeroes + bitString;
		}
		//retourne notre message string codé sous forme de bit
		return bitString;
	}
//Methode qui permet d'introduire le message caché dans le fichier image
//Qui va prendre en parametre le message caché sous forme de bit et l'image de type image
	public final static BufferedImage encodeImage (String bit, BufferedImage image) {
		
		//variable pointeur qui correspond au bit le plus faible
		int pointer = bit.length()-1; 
		
		//Parcours de toute l'image 
		for (int x = image.getWidth()-1; x >= 0; x--) {
			for (int y = image.getHeight()-1; y >= 0; y--) {
				
//Creation d'une variable c un object de la classe Color qui va correspondre a la couleur d'un pixel 
				Color c = new Color(image.getRGB(x,y)); 
				// r qui sera le rouge
				byte r = (byte) c.getRed(); 
				// g qui sera le vert
				byte g = (byte) c.getGreen(); 
				// b qui sera le bleu
				byte b = (byte) c.getBlue(); 
				
				//initialisation d'un tableau de type byte qui contiens les variable r,g,b
				byte[] RGB = {r, g, b};
				//Creation d'un tableau avec 3 emplacements
				byte[] newRGB = new byte[3];
				//Parcour du tableau
				for (int i = 2; i >= 0; i--) { 
					//si le bit de plus faible est superieur ou egale a 0
					if (pointer >= 0) {
						//creation d'une varible de type entier
						int lsb;
						//Si parmis les 3 pixels de couleurs r v b 
						//les 3 pixels sont egale a 1 bit alors lsb =1 sinon lsb=0
						if ((RGB[i] & 1) == 1) {
							lsb = 1;
						} else {
							lsb = 0;
						}
						
				//si lsb=1
				//methode qui retourne une valeur entiere a partir d'un caractere char
				
						if (Character.getNumericValue(bit.charAt(pointer)) != lsb) {
							if (lsb == 1) { 
								newRGB[i] = (byte) (RGB[i] & ~(1));
							} else { 
								newRGB[i] = (byte) (RGB[i] | 1);
							}
						} else {
							newRGB[i] = RGB[i];
						}
					} else {  
						
						newRGB[i] = (byte) (RGB[i] & ~(1));
					}
					
					pointer--;
				}
			
				Color newColor = new Color(Byte.toUnsignedInt(newRGB[0]), Byte.toUnsignedInt(newRGB[1]), Byte.toUnsignedInt(newRGB[2]));
				image.setRGB(x,y,newColor.getRGB());
			}
		}
		return image;
	}
	
}
