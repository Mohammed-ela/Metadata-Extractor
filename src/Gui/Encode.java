package Gui;

import java.awt.Color;

import java.awt.image.BufferedImage;

import java.math.BigInteger;

public final class Encode {

	final static String encodeMessage (String message) {
		String bitString = new BigInteger(message.getBytes()).toString(2);
		
		if (bitString.length()%8 != 0) {
			String zeroes = "";
			while ((bitString.length() + zeroes.length()) % 8 != 0) {
				zeroes = zeroes + "0";
			}
			bitString = zeroes + bitString;
		}
		
		return bitString;
	}
	
	final static BufferedImage encodeImage (String bit, BufferedImage image) {
		int pointer = bit.length()-1; 
		
		for (int x = image.getWidth()-1; x >= 0; x--) {
			for (int y = image.getHeight()-1; y >= 0; y--) { 
				
				Color c = new Color(image.getRGB(x,y)); 
				byte r = (byte) c.getRed(); 
				byte g = (byte) c.getGreen(); 
				byte b = (byte) c.getBlue(); 
				byte[] RGB = {r, g, b};
				byte[] newRGB = new byte[3];
				
				for (int i = 2; i >= 0; i--) { 
					if (pointer >= 0) { 
					
						int lsb;
						if ((RGB[i] & 1) == 1) {
							lsb = 1;
						} else {
							lsb = 0;
						}
						
						
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
