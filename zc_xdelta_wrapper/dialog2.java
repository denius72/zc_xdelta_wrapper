package zc_xdelta_wrapper;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.DatatypeConverter;

import java.awt.Toolkit;

public class dialog2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
    private JTextField originalTextField;
    private JTextField novoTextField;
    private JTextField patchTextField;
    //File listFile[];
    static String s = "";
    private JTextField xdeltaTextField;
    private JTextField txtbe;
    private JTextField txtvdf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			//String f = new File(dialog.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
			//String name = new java.io.File(dialog.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
			//f = f.replace(name, "");
			//f = f.substring(0, f.length() -1);
			//File f2 = new File(URLDecoder.decode(f.replace(name, ""), "UTF-8"));
			
			//walkin(f2);
			try {

			    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

			} 
			catch (Exception e) {
			   // handle exception
			}
			dialog2 dialog = new dialog2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog2() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("zc_xdelta_wrapper 1.0");
		setBounds(0, 0, 800, 338);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			JLabel lblNewLabel = new JLabel("<html>zc_xdelta_wrapper \u00E9 um GUI desenvolvido por Denisu @uberkappa para uso em<br />            RPG Maker e outros programas. Baseado no xdelta 3.1.0 por jmacd</html>");
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel.setBounds(20, 7, 458, 41);
			getContentPane().add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Carregar diret\u00F3rio:");
			lblNewLabel_1.setBounds(20, 64, 103, 14);
			getContentPane().add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Pasta Original:");
			lblNewLabel_2.setBounds(20, 118, 89, 14);
			getContentPane().add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Pasta Novo:");
			lblNewLabel_3.setBounds(20, 146, 116, 14);
			getContentPane().add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Pasta do Patch:");
			lblNewLabel_4.setBounds(20, 174, 89, 14);
			getContentPane().add(lblNewLabel_4);
			
			originalTextField = new JTextField();
			originalTextField.setBounds(109, 115, 661, 20);
			getContentPane().add(originalTextField);
			originalTextField.setColumns(10);
			
			novoTextField = new JTextField();
			novoTextField.setBounds(109, 143, 661, 20);
			getContentPane().add(novoTextField);
			novoTextField.setColumns(10);
			
			patchTextField = new JTextField();
			patchTextField.setBounds(109, 171, 661, 20);
			getContentPane().add(patchTextField);
			patchTextField.setColumns(10);
			
			JButton patchcreatebtn = new JButton("Criar Patch");
			patchcreatebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					File f = new File(originalTextField.getText());
					File orig = new File(originalTextField.getText());
					File f2 = new File(novoTextField.getText());
					File f3 = new File(patchTextField.getText());
					File f4 = new File(xdeltaTextField.getText());
					String param = txtbe.getText();
					String patchparam = txtvdf.getText();
					try {
					      File myObj = new File("temp.bat");
					      if (myObj.createNewFile()) 
					      {
					        System.out.println("Arquivo criado: " + myObj.getName());
					      }
					      else
					      {
					    	  PrintWriter writer = new PrintWriter(myObj);
					    	  writer.print("");
					    	  writer.close();
					      }
					      
					      File myObj1 = new File(f3+"\\Aplicar Patch.bat");
					      if (myObj1.createNewFile()) 
					      {
					          System.out.println("Arquivo criado: " + myObj1.getName());
					          PrintWriter writer = new PrintWriter(myObj1);
					    	  writer.print("");
					    	  writer.print("@echo off\r\n" + 
					    	  		"mkdir old\n");
					    	  writer.close();
					      }
					      else
					      {
					    	  PrintWriter writer = new PrintWriter(myObj1);
					    	  writer.print("");
					    	  writer.print("@echo off\r\n" + 
					    	  		"mkdir old\n");
					    	  writer.close();
					      }
					      
						  walkin(f,orig,f2,f3,f4,param,patchparam,myObj,myObj1);
						  Runtime.getRuntime().exec("cmd /c start \"\" "+myObj);
						  
						  String temp = patchTextField.getText();
						  File dest = new File(patchTextField.getText()+"\\xdelta.exe");
						  System.out.println(f4);
						  System.out.println(patchTextField.getText());
						  copy(f4, dest);
						  
						  //escrevendo no final do patch.bat
				    	  FileWriter myWriter = new FileWriter(myObj1, true);
				    	  myWriter.append("del /s /q /f .\\*.bak");
				    	  myWriter.append("\necho Completado!\r\n" + 
				    	  		"@pause");
				    	  myWriter.close();
						  
					    } catch (IOException e1) {
					      System.out.println("Ocorreu um erro.");
					      e1.printStackTrace();
				    }
					
				}
			});
			patchcreatebtn.setBounds(515, 60, 255, 23);
			getContentPane().add(patchcreatebtn);
			
			JButton btnNewButton = new JButton("Original");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser chooser = new JFileChooser();
				    chooser.setCurrentDirectory(new java.io.File("."));
			        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
			        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
			        { 
			        	originalTextField.setText(chooser.getSelectedFile().toString());
					}
			        else
			        {
				        originalTextField.setText(chooser.getCurrentDirectory().getAbsolutePath());
			        }
				}
			});
			btnNewButton.setBounds(113, 59, 89, 23);
			getContentPane().add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Novo");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
				    chooser.setCurrentDirectory(new java.io.File("."));
			        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
			        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
			        { 
			        	novoTextField.setText(chooser.getSelectedFile().toString());
					}
			        else
			        {
				        novoTextField.setText(chooser.getCurrentDirectory().getAbsolutePath());
			        }
				}
			});
			btnNewButton_1.setBounds(212, 60, 89, 23);
			getContentPane().add(btnNewButton_1);
			
			JButton btnNewButton_2 = new JButton("Patch");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
				    chooser.setCurrentDirectory(new java.io.File("."));
			        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
			        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
			        { 
			        	patchTextField.setText(chooser.getSelectedFile().toString());
					}
			        else
			        {
				        patchTextField.setText(chooser.getCurrentDirectory().getAbsolutePath());
			        }
				}
			});
			btnNewButton_2.setBounds(311, 60, 89, 23);
			getContentPane().add(btnNewButton_2);
		}
		
		JButton btnNewButton_3 = new JButton("xdelta");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new java.io.File("."));
			    
		        chooser.setFileFilter(new FileNameExtensionFilter("xdelta-3.1.0-x86_64.exe", "exe"));
		        
		        int returnVal = chooser.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		            xdeltaTextField.setText(chooser.getSelectedFile().toString());
		        }
				
			}
		});
		btnNewButton_3.setBounds(410, 60, 89, 23);
		getContentPane().add(btnNewButton_3);
		
		xdeltaTextField = new JTextField();
		xdeltaTextField.setBounds(109, 199, 525, 20);
		getContentPane().add(xdeltaTextField);
		xdeltaTextField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Local do xdelta:");
		lblNewLabel_5.setBounds(20, 202, 89, 14);
		getContentPane().add(lblNewLabel_5);
		
		txtbe = new JTextField();
		txtbe.setText("-B 1073741824 -e -9 -S djw -vfs");
		txtbe.setBounds(146, 250, 624, 20);
		getContentPane().add(txtbe);
		txtbe.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Argumentos do xdelta:");
		lblNewLabel_6.setBounds(20, 253, 116, 14);
		getContentPane().add(lblNewLabel_6);
		
		JButton btnNewButton_4 = new JButton("Usar Diret\u00F3rio Raiz");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String f = new File(dialog2.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
					String name = new java.io.File(dialog2.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
					f = f.replace(name, "");
					f += "xdelta-3.1.0-x86_64.exe";
					
					xdeltaTextField.setText(f);
					
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_4.setBounds(644, 198, 126, 23);
		getContentPane().add(btnNewButton_4);
		
		JLabel lblNewLabel_7 = new JLabel("Argumentos do patch:");
		lblNewLabel_7.setBounds(20, 278, 132, 14);
		getContentPane().add(lblNewLabel_7);
		
		txtvdf = new JTextField();
		txtvdf.setText("-v -d -s");
		txtvdf.setBounds(146, 275, 624, 20);
		getContentPane().add(txtvdf);
		txtvdf.setColumns(10);
	}
	
	private static void copy(File src, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dest);

            // buffer size 1K
            byte[] buf = new byte[1024];

            int bytesRead;
            while ((bytesRead = is.read(buf)) > 0) {
                os.write(buf, 0, bytesRead);
            }
        } finally {
            is.close();
            os.close();
        }
    }
	
	public static void walkin(File orig, File pattern, File dir2, File dir3, File dir4, String param, String patchparam, File fbat, File applypatch) {
		//String pattern = orig.toString();
        File listFile[] = orig.listFiles();
        if(listFile != null) {
            for(int i=0; i<listFile.length; i++) {
                if(listFile[i].isDirectory()) {
                    walkin(listFile[i],pattern , dir2, dir3, dir4, param, patchparam, fbat, applypatch);
                } else {

					//f = f.replace(name, "");
					//f = f.substring(0, f.length() -1);
					  //System.out.println(listFile[i].getPath());
					  
					  String pathnoname = listFile[i].getPath();
					  pathnoname = pathnoname.replace(listFile[i].getName(), "");
					  //System.out.println(pathnoname);
					  
					  String namenopath = listFile[i].getPath();
					  namenopath = namenopath.replace(pattern.toString(), "");
					  //System.out.println("name: "+namenopath);
					  
					  String s = listFile[i].getPath();
					  s = s.replace(pattern.toString(), dir2.getPath());
					  //System.out.println(s);
					  String s2 = listFile[i].getPath();
					  s2 = s2.replace(pattern.toString(), dir3.getPath()+"\\vcdiff");
					  s2 += ".vcdiff";
					  //System.out.println(s2);
					  
					  String dir = pathnoname.replace(pattern.toString(), dir3.getPath()+"\\vcdiff");
					  
					  File file = new File(dir);
					  
					  if(file.mkdirs()){System.out.println("Diretório criado: "+dir);}
					  
					  FileWriter myWriter; 
					  FileWriter myWriter2; 
					  
					 
						try {
						  
						  byte[] a = Files.readAllBytes(Paths.get(s));
						  byte[] hasha = MessageDigest.getInstance("MD5").digest(a);
						  byte[] b = Files.readAllBytes(Paths.get(listFile[i].getPath()));
						  byte[] hashb = MessageDigest.getInstance("MD5").digest(b);
						  String shasha = DatatypeConverter.printHexBinary(hasha);
						  String shashb = DatatypeConverter.printHexBinary(hashb);
						  System.out.println("S: "+s+" HASH: "+shasha);
						  System.out.println("listFile[i]: "+listFile[i].getPath()+" HASH: "+shashb);
						  
						  if(!shasha.toString().contentEquals(shashb.toString()))
						  {
							  System.out.println("DIFERENÇA ENCONTRADA...");
							  myWriter = new FileWriter(fbat, true);
							  //myWriter.write(".\\"+dir4+" "+param+" \""+listFile[i].getPath()+"\" \""+s+"\" \""+s2+"\""+"\n");
							  myWriter.write(".\\xdelta-3.1.0-x86_64.exe "+param+" \""+listFile[i].getPath()+"\" \""+s+"\" \""+s2+"\""+"\n");
						      myWriter.close();
						      
							  myWriter2 = new FileWriter(applypatch, true);
							  myWriter2.write("rename \"."+namenopath+"\" \""+listFile[i].getAbsolutePath().substring(listFile[i].getAbsolutePath().lastIndexOf("\\")+1)+".bak\" ");
							  myWriter2.write("\n.\\xdelta.exe "+patchparam+" \"."+namenopath+".bak\" \".\\vcdiff"+namenopath+".vcdiff\" \"."+namenopath+"\""+System.getProperty( "line.separator" ));
							  //myWriter2.write("move \".\\"+namenopath+"\" old");
						      myWriter2.close();
						  }
						 
						  //TO DO: fazer com que o executável do xdelta não seja hard coded
						  
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				      
                }
            }
        }
	}
}
