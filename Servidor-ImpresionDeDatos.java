package com.libro.networking;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DemoServer2 extends JFrame {
    private JTextArea textArea;
    
    public DemoServer2() {
        super("Server");
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }

    public void runServer() throws IOException, ClassNotFoundException 
    {
    	
    	ArrayList<String> nombres = new ArrayList<>();
        ArrayList<Integer> puntuaciones = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        int i =1;
        Socket s = null;
        ServerSocket ss = new ServerSocket(3112);
        

        while (true) 
        {
            try {
                System.out.println("Servidor iniciado");
                System.out.println("Esperando...");

                // El ServerSocket me da el Socket
                s = ss.accept();
                ois = new ObjectInputStream(s.getInputStream());
                oos = new ObjectOutputStream(s.getOutputStream());
                String nombre = (String)ois.readObject();
                // Informacion en la consola
                String nom = "Host "+i;
                textArea.append(nom+"\n");
                i++;
                textArea.append(nombre+ " se conecto desde la IP: " + s.getInetAddress()+"\n");

                // Enmascaro la entrada y salida de bytes
                
                // Leo el array que envia el cliente
                @SuppressWarnings("unchecked")
                ArrayList<String> componentes = (ArrayList<String>) ois.readObject();

                // Hacer algo con la lista recibida
                for (String datos : componentes) {
                    System.out.println(datos);
                    // Actualizar el área de texto con los datos recibidos del cliente
                    textArea.append(datos + "\n");
                }
                textArea.append("\n");
               
                
                // Armo el saludo personalizado que le quiero enviar
                String saludo = "Se detecto tu computadora";

                // Envio el saludo al cliente
                oos.writeObject(saludo);
                System.out.println("Saludo enviado...");
                int score = (int)ois.readObject();
               

                nombres.add(nombre);
                puntuaciones.add(score);

                // Ordenar el ranking
                Comparator<Integer> comparador = Collections.reverseOrder();
                Collections.sort(puntuaciones, comparador);

                // Mostrar el ranking
                textArea.append("Ranking Actual: \n");
                for (int j = 0; j < nombres.size(); j++) {
                    textArea.append((j + 1) + ". " + nombres.get(j)+"\n");
                }
                textArea.append("\n");
                textArea.append("\n");

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (oos != null)
                    oos.close();
                if (ois != null)
                    ois.close();
                if (s != null)
                    s.close();
                System.out.println("Conexion cerrada!");
            }
        }
    }
    
    
    public static void main(String[] args) {
        DemoServer2 server = new DemoServer2();
        try {
            server.runServer();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
