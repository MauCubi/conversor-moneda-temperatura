package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import modelo.Moneda;

import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ConversorMoneda {
	private JTextField fieldCantidad;
	
	private List<Moneda> monedas = new ArrayList<>();
	List<Double> cambio1 = new ArrayList<>();

	public ConversorMoneda(JFrame menuPrincipal) {
		JFrame monedaFrame = new JFrame("Conversor de Moneda");
		monedaFrame.setResizable(false);
		monedaFrame.getContentPane().setBackground(new Color(255, 255, 255));
		monedaFrame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 324, 207);
		panel.setBorder(null);
		monedaFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbMonedaOrigen = new JLabel("Convertir de:");
		lbMonedaOrigen.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lbMonedaOrigen.setBounds(10, 60, 92, 17);
		panel.add(lbMonedaOrigen);
		
		JLabel lbMonedaDestino = new JLabel("A:");
		lbMonedaDestino.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lbMonedaDestino.setBounds(87, 93, 15, 17);
		panel.add(lbMonedaDestino);
		
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
		
		cargarMonedas();
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
		monedaFrame.setSize(358,260);
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    monedaFrame.setLocation(dim.width/2-monedaFrame.getSize().width/2, dim.height/2-monedaFrame.getSize().height/2);	    
	    
	    
	    // Funciones de elementos del frame
	    monedaFrame.addWindowListener(new WindowAdapter() {            

            @Override
            public void windowClosing(WindowEvent e) {                
            	monedaFrame.setVisible(false);
            	monedaFrame.dispose();
            	menuPrincipal.setVisible(true);
                
            }
        });
	    
	    fieldCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(((Character.isDigit(c))||(c==e.VK_PERIOD)||(c==e.VK_BACK_SPACE))&&(fieldCantidad.getText().length()!=20)){
		            int punto=0;
		            
		            if(c==KeyEvent.VK_PERIOD){ 
		                        String s=fieldCantidad.getText();
		                        int dot=s.indexOf('.');
		                        punto=dot;		                        
		                        if(dot!=-1){		                            
		                            e.consume();
		                        }
		                    }
		        }
		        else{   		            
		            e.consume();
		        }
			}
		});
	    
	    btnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fieldCantidad.getText().length() == 0) {
					JOptionPane.showMessageDialog(monedaFrame,
						    "El campo 'Cantidad' no puede estar vacío.",
						    "Alerta",
						    JOptionPane.WARNING_MESSAGE);
				} else { 
				
				Double cantidad = Double.parseDouble(fieldCantidad.getText());
				Moneda origen = (Moneda) cbOrigen.getSelectedItem();
				List<Double> cambio = origen.getCambio(); 
				Moneda destino = (Moneda) cbDestino.getSelectedItem();				
				
				BigDecimal resultado = new BigDecimal(cantidad * cambio.get(monedas.indexOf(destino)));	
				System.out.println(resultado);
				lbConversion.setText(resultado.setScale(2, RoundingMode.UP).toString());
				
			}
			}
		});	    
		  
	    monedaFrame.setVisible(true); 	    

	}
	
	// Funciones de la clase
	
	private void cargarMonedas() {
		List<Double> cambio = new ArrayList<>();
		cambio.add(1.00);
		cambio.add(178.13);
		cambio.add(19.40);
		cambio.add(130.67);
		this.monedas.add(new Moneda("Dolar Estado Unidense", "USD", cambio));
		
		List<Double> cambio2 = new ArrayList<>();
		cambio2.add(0.0056);
		cambio2.add(1.00);		
		cambio2.add(0.11);
		cambio2.add(0.73);
		this.monedas.add(new Moneda("Peso Argentino", "ARS", cambio2));		
		
		List<Double> cambio3 = new ArrayList<>();
		cambio3.add(0.0515);
		cambio3.add(9.20);	
		cambio3.add(1.00);
		cambio3.add(6.74);
		this.monedas.add(new Moneda("Peso Mexicano", "MXN", cambio3));		
		
		List<Double> cambio4 = new ArrayList<>();
		cambio4.add(0.0077);
		cambio4.add(1.37);		
		cambio4.add(0.15);
		cambio4.add(1.00);
		this.monedas.add(new Moneda("Yen", "JPY", cambio4));		
		
	}
	
	private void cargarCombos(JComboBox comboBox, JComboBox comboBox2) {		
		for (Iterator iterator = this.monedas.iterator(); iterator.hasNext();) {
			Moneda moneda = (Moneda) iterator.next();			
			comboBox.addItem(moneda);
			comboBox2.addItem(moneda);			
			
		}
	    
	}
	 
	 
}
