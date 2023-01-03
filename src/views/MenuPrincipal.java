package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame{
	
	public MenuPrincipal() {
		
		JFrame frame = new JFrame("Programa de Conversion");
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{207, 56, 0, 0};
		gridBagLayout.rowHeights = new int[]{59, 0, 227, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 174, 0));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.gridwidth = 4;
		gbc_panel.ipady = 40;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel titleLabel = new JLabel("Challenge - Conversor");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 29));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		titleLabel.setForeground(new Color(255, 255, 255));
		panel.add(titleLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Eliga Conversor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridwidth = 4;
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{65, 144, 65, 0};
		gbl_panel_1.rowHeights = new int[]{40, 47, 20, 47, 40, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnConversorMoneda = new JButton("Conversor de Moneda");		
		GridBagConstraints gbc_btnConversorMoneda = new GridBagConstraints();
		gbc_btnConversorMoneda.insets = new Insets(0, 0, 5, 5);
		gbc_btnConversorMoneda.fill = GridBagConstraints.BOTH;
		gbc_btnConversorMoneda.gridx = 1;
		gbc_btnConversorMoneda.gridy = 1;
		panel_1.add(btnConversorMoneda, gbc_btnConversorMoneda);
		
		JButton btnConversorTemperatura = new JButton("Conversor de Temperatura");		
		GridBagConstraints gbc_btnConversorTemperatura = new GridBagConstraints();
		gbc_btnConversorTemperatura.fill = GridBagConstraints.VERTICAL;
		gbc_btnConversorTemperatura.insets = new Insets(0, 0, 5, 5);
		gbc_btnConversorTemperatura.gridx = 1;
		gbc_btnConversorTemperatura.gridy = 3;
		panel_1.add(btnConversorTemperatura, gbc_btnConversorTemperatura);
	    frame.setSize(460,341);
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
	    
	    // Evento de botones
	    btnConversorMoneda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConversorMoneda conversorMoneda = new ConversorMoneda(frame);
				frame.dispose();
			}
		});
	    
	    btnConversorTemperatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConversorTemperatura conversorTemperatura = new ConversorTemperatura(frame);
				frame.dispose();
			}
		});
		  
		frame.setVisible(true);
		
		
		
		
	}
	
	
}
