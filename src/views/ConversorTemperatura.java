package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import modelo.Temperatura;

public class ConversorTemperatura {
	private JTextField fieldCantidad;
	
	private List<Temperatura> monedas = new ArrayList<>();
	List<Double> cambio1 = new ArrayList<>();

	public ConversorTemperatura(JFrame menuPrincipal) {
		JFrame temperaturaFrame = new JFrame("Conversor de Temperatura");
		temperaturaFrame.setResizable(false);
		temperaturaFrame.getContentPane().setBackground(new Color(255, 255, 255));
		temperaturaFrame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 324, 207);
		panel.setBorder(null);
		temperaturaFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbTemperaturaOrigen = new JLabel("Convertir de:");
		lbTemperaturaOrigen.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lbTemperaturaOrigen.setBounds(10, 60, 92, 17);
		panel.add(lbTemperaturaOrigen);
		
		JLabel lbTemperaturaDestino = new JLabel("A:");
		lbTemperaturaDestino.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lbTemperaturaDestino.setBounds(87, 93, 15, 17);
		panel.add(lbTemperaturaDestino);
		
		JComboBox cbOrigen = new JComboBox();
		cbOrigen.setBackground(new Color(255, 255, 255));
		cbOrigen.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbOrigen.setBounds(112, 58, 202, 22);
		panel.add(cbOrigen);
		
		JComboBox cbDestino = new JComboBox();
		cbDestino.setBackground(new Color(255, 255, 255));
		cbDestino.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbDestino.setBounds(112, 91, 202, 22);		
		panel.add(cbDestino);
		
		cargarTemperaturas();
		cargarCombos(cbOrigen, cbDestino);
		
		JLabel lbCantidad = new JLabel("Cantidad:");
		lbCantidad.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lbCantidad.setBounds(34, 27, 71, 17);
		panel.add(lbCantidad);
		
		fieldCantidad = new JTextField();	
		fieldCantidad.setBorder(new LineBorder(new Color(171, 173, 179)));
		fieldCantidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldCantidad.setBounds(112, 27, 202, 20);
		panel.add(fieldCantidad);
		fieldCantidad.setColumns(10);
		
		JButton btnConvertir = new JButton("Convertir");
		btnConvertir.setFont(new Font("SansSerif", Font.PLAIN, 16));		
		btnConvertir.setBounds(97, 136, 124, 29);
		panel.add(btnConvertir);
		
		JLabel lbResultado = new JLabel("Resultado:");
		lbResultado.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lbResultado.setBounds(10, 176, 77, 17);
		panel.add(lbResultado);
		
		JLabel lbConversion = new JLabel("");
		lbConversion.setFont(new Font("SansSerif", Font.BOLD, 16));
		lbConversion.setBounds(95, 176, 219, 17);
		panel.add(lbConversion);
		temperaturaFrame.setSize(358,260);
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    temperaturaFrame.setLocation(dim.width/2-temperaturaFrame.getSize().width/2, dim.height/2-temperaturaFrame.getSize().height/2);	    
	    
	    
	    // Funciones de elementos del frame
	    temperaturaFrame.addWindowListener(new WindowAdapter() {            

            @Override
            public void windowClosing(WindowEvent e) {                
            	temperaturaFrame.setVisible(false);
            	temperaturaFrame.dispose();
            	menuPrincipal.setVisible(true);
                
            }
        });
	    
	    fieldCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(((Character.isDigit(c))||(c==e.VK_PERIOD)||(c==e.VK_BACK_SPACE)||(c==e.VK_MINUS))&&(fieldCantidad.getText().length()!=15)){
		            int punto=0;
		            if(c==KeyEvent.VK_PERIOD){ 
		                        String s=fieldCantidad.getText();
		                        int dot=s.indexOf('.');
		                        punto=dot;		                        
		                        if(dot!=-1){		                            
		                            e.consume();
		                        }
		                    }
		            if(c==KeyEvent.VK_MINUS){ 
                        String s=fieldCantidad.getText();
                        int minus=s.indexOf('-');
                        punto=minus;		                        
                        if(minus!=-1 && fieldCantidad.getText().length()>0){		                            
                            e.consume();
                        }
                    }
		            
		        }
		        else{   		            
		            e.consume();
		        }			}
		});
	    
	    btnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (fieldCantidad.getText().length() == 0) {
					JOptionPane.showMessageDialog(temperaturaFrame,
						    "El campo 'Cantidad' no puede estar vacío.",
						    "Alerta",
						    JOptionPane.WARNING_MESSAGE);
				} else { 
					Double cantidad = Double.parseDouble(fieldCantidad.getText());
					Temperatura origen = (Temperatura) cbOrigen.getSelectedItem();				
					Temperatura destino = (Temperatura) cbDestino.getSelectedItem();	
					Double resultado = 0.0;
					
					switch (origen.getAbreviatura()) {
					case "C":
						switch (destino.getAbreviatura()) {
						case "K":
							resultado = cantidad + 273.15;
							break;
						case "F":
							resultado = (cantidad * 1.8) + 32;
							break;	
						case "C":
							resultado = cantidad;
							break;
						default:
							break;
						}
						break;
					case "F":
						switch (destino.getAbreviatura()) {
						case "K":
							resultado = (cantidad + 459.67) * 5/9;
							break;
						case "C":
							resultado = (cantidad - 32) / 1.8;
							break;	
						case "F":
							resultado = cantidad;
							break;
	
						default:
							break;
						}
						break;
					case "K":
						switch (destino.getAbreviatura()) {
						case "F":
							resultado = 1.8 * (cantidad - 273.15) + 32;
							break;
						case "C":
							resultado = cantidad - 273.15;
							break;	
						case "K":
							resultado = cantidad;
							break;
	
						default:
							break;
						}
						break;
					default:
						break;
					}
					
					BigDecimal resultadoFinal = new BigDecimal(resultado);
					lbConversion.setText(resultadoFinal.setScale(2, RoundingMode.UP).toString());			
				}
				
			}
		});	    
		  
	    temperaturaFrame.setVisible(true); 	    

	}
	
	// Funciones de la clase
	
	private void cargarTemperaturas() {		
		this.monedas.add(new Temperatura("Celcius", "C"));	
		
		this.monedas.add(new Temperatura("Fahrenheit", "F"));	
		
		this.monedas.add(new Temperatura("Kelvin", "K"));	
		
	}
	
	private void cargarCombos(JComboBox comboBox, JComboBox comboBox2) {		
		for (Iterator iterator = this.monedas.iterator(); iterator.hasNext();) {
			Temperatura moneda = (Temperatura) iterator.next();			
			comboBox.addItem(moneda);
			comboBox2.addItem(moneda);			
		}	    
	}

}
