package Gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.imageio.ImageIO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;

import Cli.Decode;
import Cli.Encode;

import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;

//CLASSE GUI avec utilisation de WINDOWS BUILDER

public class InterfaceGraph {
	

	
//LA FENETRE
	private JFrame frmProjetPoo;
//zone de texte repertoire
	private JTextField txtCheminDuDossier;
//zone de texte msg a dissimulé
	private JTextField txtmessagecache;
	
//jtextfield de l'affichage du message caché
	private JTextField msgtextField;
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGraph window = new InterfaceGraph();
					window.frmProjetPoo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceGraph() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProjetPoo = new JFrame();
		frmProjetPoo.getContentPane().setBackground(Color.WHITE);
		frmProjetPoo.setBackground(Color.WHITE);
		frmProjetPoo.setTitle("Projet POO");
		frmProjetPoo.setBounds(100, 100, 633, 409);
		frmProjetPoo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//L'ecran avec les differents composant ( a l'interieur du jFrame ) 
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.focus"));
		
		
		//ECRAN (affichage metadaata + repertoire) = L'endroit ou on va afficher les metadonné d'une image ou l'ensemble du repertoire trié
		JTextPane Ecran = new JTextPane();
		Ecran.setBackground(UIManager.getColor("FormattedTextField.inactiveBackground"));
		Ecran.setBounds(12, 44, 290, 316);
		panel.add(Ecran);
		
		//CHAMP DE TEXTE (CHEMIN) qu'on appelle txtCheminDuDossier

		txtCheminDuDossier = new JTextField();
		txtCheminDuDossier.setBackground(UIManager.getColor("OptionPane.warningDialog.titlePane.background"));
		txtCheminDuDossier.setBounds(78, 12, 279, 25);
		txtCheminDuDossier.setToolTipText("");
		txtCheminDuDossier.setText("ex:/home/mohammed/Bureau/image/");
		txtCheminDuDossier.setHorizontalAlignment(SwingConstants.LEFT);
		txtCheminDuDossier.setForeground(new Color(51, 51, 51));
		panel.add(txtCheminDuDossier);
		txtCheminDuDossier.setColumns(10);
		
		frmProjetPoo.getContentPane().add(panel, BorderLayout.CENTER);
		
		//Creation du bouton METADATA
		JButton btnMetadata = new JButton("Métadonnée");
		btnMetadata.setBackground(SystemColor.menu);
		//Position et taille du bouton METADATA
		btnMetadata.setBounds(369, 12, 134, 25);
		panel.setLayout(null);
		//Ajout du bouton a notre panel 
		panel.add(btnMetadata);
		
		
		//L'ACTION DU BOUTON METADATA--------------------------------------------------------
		btnMetadata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		
			
				Metadonne Meta = new Metadonne();
				
				//Creation d'une variable qui va prendre le text ecris dans la champ de texte (le chemin dossier / image)
				
				String chemin =txtCheminDuDossier.getText();
				
				/*Creation d'une variable qui va lancer la methode "meta" de notre classe metadonne tout en prennant le chemin	
				en parametre*/
				
				String text=Meta.meta(chemin);
				
				/*afficher dans notre textepanel la variable text comprenant toute nos metadonne*/
				
				Ecran.setText(text);
			
			
				
			}
		});
	
		
		//Ça ne change pas , l'affiche du jlabel "chemin :"
		
		JLabel lblChemin = new JLabel("Chemin:");
		lblChemin.setBounds(12, 17, 70, 15);
		panel.add(lblChemin);
		//Ça ne change pas , l'affiche du jlabel "steganographie :"
		JLabel lblStganographie = new JLabel("Stéganographie:");
		lblStganographie.setBounds(316, 91, 148, 27);
		panel.add(lblStganographie);
		
		
		
		
		
		
		
		
		//CREATION DU BOUTON EXPLORATION
		JButton btnExplo = new JButton("Exploration");
		btnExplo.setBackground(SystemColor.menu);
		btnExplo.setBounds(369, 59, 134, 25);
		panel.add(btnExplo);
		//L'ACTION DU BOUTON Exploration----------------------------------------------------------------------
		btnExplo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Filtre Rep = new Filtre();
				
				//Creation d'une variable qui va prendre le text ecris dans la champ de texte (le chemin dossier / image)
				
				String chemin =txtCheminDuDossier.getText();
				
				/*Creation d'une variable qui va lancer la methode "meta" de notre classe metadonne tout en prennant le chemin	
				en parametre*/
				
				
				
				String text = null;
				try {
					text = Rep.filtrage(chemin);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				
				
				/*afficher dans notre textepanel la variable text comprenant toute nos metadonne*/
				//for(int i=0;i<0;i++) {
					
				
				Ecran.setText(text);
				
				
				}
			
				
				
				
				
			
		});
		
		//BOUTON ENCODER
		
		//Creation + Position du bouton
		JButton btnStocker = new JButton("Encoder");
		btnStocker.setBackground(SystemColor.menu);
		btnStocker.setBounds(314, 154, 117, 25);
		//ajouter du bouton
		panel.add(btnStocker);
		
		//ACTION DU BONTON ENCODER -------------------------------------------------------------------
		btnStocker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				String chemin = txtCheminDuDossier.getText();
				String message = txtmessagecache.getText();
                
				//Creation d'une variable de type File qui va prendre le chemin du fichier à decoder(qui ne plus etre de type String mais File) 
				 				
				                File file = new File(chemin);
				                
				//Creation d'une variable de type BufferedImage ( qui va nous permettre de lire un fichier image )                
				                BufferedImage image = null;
				//La variable image de type BufferedImage va etre utiliser                 
				//Lecture du fichier de l'image de type File 
				                try {
									image = ImageIO.read(file);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

				 //Appelle de la methode decodeMessage en mettant notre image en parametre (cela est possible vu qu'il est de type bufferedImage)   
				               
				                
				              String msgbit = Encode.encodeMessage(message);
				          	//Creation d'une variable de type Image qui va permettre d'appeller la methode "encodeImage" de la classe Encode
				              BufferedImage msgfinal=Encode.encodeImage(msgbit,image);
				              //Creation d'une variable de type File pour pouvoir manipuler notre msgfinal et ecrire le msg caché
				              File finalImage = new File(chemin);	
				           // utilisation de la methode write afin d'ecrire dans notre fichier le "msgfinal"
							try {
								ImageIO.write(msgfinal,"png",finalImage);
								ImageIO.write(msgfinal,"jpg",finalImage);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//affichage 
				               msgtextField.setText("Encodage effectué avec succès !");
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		
		
		//BOUTON DECODER 
		
				//Creation + Position du bouton
		
		JButton btnDecoder = new JButton("Decoder");
		btnDecoder.setBackground(SystemColor.menu);
		
		btnDecoder.setBounds(314, 308, 117, 25);
		//ajouter du bouton
		panel.add(btnDecoder);
		
		//ACTION DU BONTON DECODER --------------------------------------------------------------------
		
		btnDecoder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String filePath = txtCheminDuDossier.getText();

                
				//Creation d'une variable de type File qui va prendre le chemin du fichier à decoder(qui ne plus etre de type String mais File) 
				                
				                File outFile = new File(filePath);
				                
				//Creation d'une variable de type BufferedImage ( qui va nous permettre de lire un fichier image )                
				                BufferedImage image = null;
				//La variable image de type BufferedImage va etre utiliser                 
				//Lecture du fichier de l'image de type File 
				                try {
									image = ImageIO.read(outFile);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

				 //Appelle de la methode decodeMessage en mettant notre image en parametre (cela est possible vu qu'il est de type bufferedImage)   
				                String bitMessage = Decode.decodeMessage(image);

				            
				 //Afficher le message caché                
				               
				                String msg =Decode.getMessage(bitMessage);
			               	
				                
				                
				             
				               msgtextField.setVisible(true);
				              
				               msgtextField.setText(msg);
				               
				
				
				
				
			
				
				
				
				
				
				
			}
		});
	
		//ZONE DE TEXTE OU ON VA ECRIRE LE MESSAGE A ENCODER
		txtmessagecache = new JTextField();
		txtmessagecache.setForeground(Color.RED);
		txtmessagecache.setBackground(UIManager.getColor("OptionPane.warningDialog.titlePane.background"));
		txtmessagecache.setText("Entrer votre message à cacher");
		txtmessagecache.setBounds(314, 120, 267, 22);
		panel.add(txtmessagecache);
		txtmessagecache.setColumns(10);
		
		msgtextField = new JTextField();
		msgtextField.setFont(new Font("Dialog", Font.BOLD, 13));
		msgtextField.setHorizontalAlignment(SwingConstants.LEFT);
		msgtextField.setForeground(Color.BLACK);
		msgtextField.setBackground(UIManager.getColor("FormattedTextField.inactiveBackground"));
		msgtextField.setBounds(314, 191, 267, 44);
		msgtextField.setVisible(true);
		panel.add(msgtextField);
		msgtextField.setColumns(10);
		//ACTION BOUTON CLEAR ------------------------------------------------------------------------
		
		JButton button = new JButton("Clear");
		button.setBackground(SystemColor.menu);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				  msgtextField.setText("");
				  txtmessagecache.setText("");
			}
		});
		button.setBounds(488, 154, 117, 25);
		panel.add(button);
		
	
		
		
	

		
	}
}

