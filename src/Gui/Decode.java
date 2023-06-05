package Gui;


import java.awt.Color;

import java.awt.image.BufferedImage;


public final class Decode {

public static String getMessage (String encoded) {
		int count = encoded.length()-1;
		StringBuilder message = new StringBuilder();
		int values = encoded.length()/8;
		byte[] ba = new byte[values];
		int arrayCount = values-1;
		while (arrayCount > 0) {
			StringBuilder bits = new StringBuilder();
			for (int i = 0; i < 8; i++) {
				bits.insert(0,encoded.charAt(count-i));
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

	public static String decodeMessage(BufferedImage image) {
		StringBuilder sb = new StringBuilder();

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				Color c = new Color(image.getRGB(x,y)); 
				byte r = (byte) c.getRed(); 
				byte g = (byte) c.getGreen(); 
				byte b = (byte) c.getBlue(); 
				byte[] RGB = {r, g, b};
				
				for (int i = 0; i < 3; i++) {
					 
					if ((RGB[i] & 1) == 1) { 
						sb.append("1");
					} else { 
						sb.append("0");
					}
				}
			}
		}
		
		return sb.toString();
	}

}
