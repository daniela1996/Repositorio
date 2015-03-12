import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Shell extends JFrame{
	
	//elementos gráficos
	JTextField comando_txt;
	JButton ejecutar_btn,limpiar_btn;
	JTextArea resultado_txtA;
	JScrollPane barra;

	//oyente de click de botón
	ActionListener ejecutar_ev;

	public Shell(){
		setSize(700,600);
		setTitle(System.getProperty("os.name"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void graficos(){
		getContentPane().setLayout(null);
		
		//cuadro de texto
		comando_txt = new JTextField();
		comando_txt.setBounds(50,50,250,30);
		add(comando_txt
		);
		//botón para ejecutar comando
		
		ejecutar_btn = new JButton("Ejecutar");
		ejecutar_btn.setBounds(330,50,120,30);
		add(ejecutar_btn);
		ejecutar_btn.addActionListener(ejecutar_ev);
		
		limpiar_btn = new JButton("Limpiar");
		limpiar_btn.setBounds(470,50,120,30);
		add(limpiar_btn);
		limpiar_btn.addActionListener(ejecutar_ev);

		//área de texto
		
		resultado_txtA = new JTextArea();
		resultado_txtA.setBounds(50,130,600,370);
		resultado_txtA.setBackground(Color.BLACK);
		resultado_txtA.setForeground(Color.MAGENTA);
		//scroll pane
		barra = new JScrollPane(resultado_txtA);
		barra.setBounds(50,120,500,400);
		add(barra);
		//
		setVisible(true);
	}

	private void acciones(){
		ejecutar_ev = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==ejecutar_btn){
				ejecutar();
			}
			if(e.getSource()==limpiar_btn){
				resultado_txtA.setText("");
			}
			}
		};
	}

	private void ejecutar(){

		Process proceso; 
		InputStream is_in;
		String s_aux;
		BufferedReader br;

		try
		{
			proceso = Runtime.getRuntime().exec(comando_txt.getText());
			is_in=proceso.getInputStream();
			br=new BufferedReader (new InputStreamReader (is_in));
			s_aux = br.readLine();
            while (s_aux!=null)
            {
            	resultado_txtA.setText(resultado_txtA.getText()+s_aux+"\n");
                s_aux = br.readLine();
            } 
		}
		catch(Exception e)
		{
			e.getMessage();
		}


	}

	public static void main(String args[]){
		Shell ventana = new Shell();
		ventana.acciones();	
		ventana.graficos();	
	}

}