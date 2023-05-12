package com.libro.networking;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;



public class DemoCliente 
{
	public static void main(String[] args) throws Exception
	 {
		DemoCliente dc = new DemoCliente();
		dc.Info_PC();
		 
	 }
	public void Info_PC() throws IOException 
	{
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket s = null;
        try 
        {
        	
        	s = new Socket("25.9.7.55",3112);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			String nombre = JOptionPane.showInputDialog("Ingrese su nombre");
			oos.writeObject(nombre);
        	ArrayList <String> InfoPC = new ArrayList<String>();
        	String os = System.getProperty("os.name");
        	//System.out.println(os);
        	
        	if ( os.equals("Windows 10")||os.equals("Windows 7"))
        	{
	            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "wmic cpu get name && "
	            		+ "wmic cpu get NumberOfCores && "
	            		+ "wmic cpu get maxclockspeed && "
	            		+ "wmic os get caption");
	            		
	            builder.redirectErrorStream(true);
	            Process process = builder.start();
	            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            
	            StringBuilder salida = new StringBuilder();
	            String linea;
	            while ((linea = br.readLine()) != null) 
	            {
	                if (!linea.trim().isEmpty()) 
	                { // Verificamos si la línea no está vacía
	                    InfoPC.add(linea);
	                }
	            }
	            File[] roots = File.listRoots();
		        long totalSpace = 0;
		        for (File root : roots) {
		            totalSpace += root.getTotalSpace();
		        }
		        long gbTotalSpace = totalSpace / (1024 * 1024 * 1024);
		        String dd = Long.toString(gbTotalSpace);
		        InfoPC.add(dd);
	            /*
	             // Imprimimos la salida de los comandos
	           for(String datos : InfoPC)
	           {
	        	   System.out.println(datos);
	          
	           }
	           */
        	}  
        	ArrayList <String> componentes = new ArrayList<String>();
        	ArrayList <String> remover = new ArrayList<String>();
        	String procesador = "Procesador:  " + InfoPC.get(1);
        	componentes.add(procesador);
        	remover.add(InfoPC.get(0));
        	String numeroDeNucleos = "Numeor de Nucleos: " + InfoPC.get(3);
        	componentes.add(numeroDeNucleos);
        	remover.add(InfoPC.get(2));
        	String velocidadProcesador = "Velocidad de Procesador: "+ InfoPC.get(5).trim()+"MHz";
        	componentes.add(velocidadProcesador);
        	remover.add(InfoPC.get(4));
        	String sistemaOperativo = "Sistema Operativo: " + InfoPC.get(7);
        	componentes.add(sistemaOperativo);
        	remover.add(InfoPC.get(6));
        	String espacioDeMemoria = "Memoria Total: "+ InfoPC.get(8).trim()+"Gb";
        	componentes.add(espacioDeMemoria);
        	
        	
        	InfoPC.removeAll(remover);
        	
        	oos.writeObject(componentes);
			 
			 // recibo la respuesta (el saludo personalizado)
			 
        	
        	for(String datos : InfoPC)
	           {
	        	   System.out.println(datos);
	          
	           }
        	int score = 0;
        	if((InfoPC.get(0).trim()).equals("AMD Ryzen 5 5600X 6-Core Processor"))
        	{
        		
        		score = score + 5600;
        	}
        	if((InfoPC.get(1).trim()).equals("6"))
        	{
        		
        		score = score + 600;
        	}
        	if((InfoPC.get(2).trim()).equals("3701"))
        	{
        		
        		score = score + 2000;
        	}
        	if((InfoPC.get(3).trim()).equals("Microsoft Windows 10 Home"))
        	{
        		
        		score = score + 10;
        	}
        	if(Integer.parseInt(InfoPC.get(4))>1000)
        	{
        		
        		score = score + 500;
        	}
        	System.out.println(score);
        	oos.writeObject(score);
        	/*
        	 *
        	 StringBuilder sb = new StringBuilder();
             for (String elemento : componentes) {
                 sb.append(elemento).append("\n");
             }

             JOptionPane.showMessageDialog(null, sb.toString());
             */
			 
			 
        	String ret = (String)ois.readObject();
				 
        	// muestro la respuesta que envio el server
        	System.out.println(ret);
        	oos.writeObject(score);
        	
        } 
        	catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        finally
		{
			 if( ois != null ) ois.close();
			 if( oos != null ) oos.close();
			 if( s != null ) s.close();
		}
        
    }
}
