package Pi4Chi;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainUI extends JFrame {
	private static final long serialVersionUID = -3113946090871808260L;
	private JPanel contentPane;
	private JTextField textFieldDestinationIP;
	private JTextField textFieldDestinationPort;
	private JLabel lblAnalogInValue;
	private JLabel lblStatusValue;
	private JLabel lblPeriod;
	private JTextField textFieldPeriod;

	public MainUI() {
		setTitle("Chi @ Pi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 207);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblDestinationIP = new JLabel("Destination IP :");
		GridBagConstraints gbc_lblDestinationIP = new GridBagConstraints();
		gbc_lblDestinationIP.insets = new Insets(0, 0, 5, 5);
		gbc_lblDestinationIP.anchor = GridBagConstraints.EAST;
		gbc_lblDestinationIP.gridx = 0;
		gbc_lblDestinationIP.gridy = 0;
		contentPane.add(lblDestinationIP, gbc_lblDestinationIP);
		
		textFieldDestinationIP = new JTextField();
		GridBagConstraints gbc_textFieldDestinationIP = new GridBagConstraints();
		gbc_textFieldDestinationIP.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDestinationIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDestinationIP.gridx = 1;
		gbc_textFieldDestinationIP.gridy = 0;
		contentPane.add(textFieldDestinationIP, gbc_textFieldDestinationIP);
		textFieldDestinationIP.setColumns(10);
		
		JLabel lblDestinationPort = new JLabel("Destination Port :");
		GridBagConstraints gbc_lblDestinationPort = new GridBagConstraints();
		gbc_lblDestinationPort.anchor = GridBagConstraints.EAST;
		gbc_lblDestinationPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblDestinationPort.gridx = 0;
		gbc_lblDestinationPort.gridy = 1;
		contentPane.add(lblDestinationPort, gbc_lblDestinationPort);
		
		textFieldDestinationPort = new JTextField();
		textFieldDestinationPort.setText("40000");
		GridBagConstraints gbc_textFieldDestinationPort = new GridBagConstraints();
		gbc_textFieldDestinationPort.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDestinationPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDestinationPort.gridx = 1;
		gbc_textFieldDestinationPort.gridy = 1;
		contentPane.add(textFieldDestinationPort, gbc_textFieldDestinationPort);
		textFieldDestinationPort.setColumns(10);
		
		lblPeriod = new JLabel("Period :");
		GridBagConstraints gbc_lblPeriod = new GridBagConstraints();
		gbc_lblPeriod.anchor = GridBagConstraints.EAST;
		gbc_lblPeriod.insets = new Insets(0, 0, 5, 5);
		gbc_lblPeriod.gridx = 0;
		gbc_lblPeriod.gridy = 2;
		contentPane.add(lblPeriod, gbc_lblPeriod);
		
		textFieldPeriod = new JTextField();
		textFieldPeriod.setText("1");
		GridBagConstraints gbc_textFieldPeriod = new GridBagConstraints();
		gbc_textFieldPeriod.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPeriod.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPeriod.gridx = 1;
		gbc_textFieldPeriod.gridy = 2;
		contentPane.add(textFieldPeriod, gbc_textFieldPeriod);
		textFieldPeriod.setColumns(10);
		
		JLabel lblAnalogIn = new JLabel("Analog In :");
		lblAnalogIn.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblAnalogIn = new GridBagConstraints();
		gbc_lblAnalogIn.fill = GridBagConstraints.BOTH;
		gbc_lblAnalogIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnalogIn.gridx = 0;
		gbc_lblAnalogIn.gridy = 3;
		contentPane.add(lblAnalogIn, gbc_lblAnalogIn);
		
		lblAnalogInValue = new JLabel("0");
		GridBagConstraints gbc_lblAnalogInValue = new GridBagConstraints();
		gbc_lblAnalogInValue.fill = GridBagConstraints.VERTICAL;
		gbc_lblAnalogInValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblAnalogInValue.anchor = GridBagConstraints.WEST;
		gbc_lblAnalogInValue.gridx = 1;
		gbc_lblAnalogInValue.gridy = 3;
		contentPane.add(lblAnalogInValue, gbc_lblAnalogInValue);
		
		JLabel lblStatus = new JLabel("Status :");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.fill = GridBagConstraints.VERTICAL;
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 4;
		contentPane.add(lblStatus, gbc_lblStatus);
		
		lblStatusValue = new JLabel("OFF");
		GridBagConstraints gbc_lblStatusValue = new GridBagConstraints();
		gbc_lblStatusValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatusValue.fill = GridBagConstraints.VERTICAL;
		gbc_lblStatusValue.anchor = GridBagConstraints.WEST;
		gbc_lblStatusValue.gridx = 1;
		gbc_lblStatusValue.gridy = 4;
		contentPane.add(lblStatusValue, gbc_lblStatusValue);
		
		JButton btnStartButton = new JButton("Start");
		btnStartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnStartButton.setEnabled(false);
				System.out.println("Disabled Button");
				Pi4Chi.sendLEDData();
				System.out.println("SEND LED DATA");
				Pi4Chi.startMonitor();
				System.out.println("START ANALOG IN");
				Pi4Chi.startListen();
				System.out.println("START LISTENING");
			}
		});
		GridBagConstraints gbc_btnStartButton = new GridBagConstraints();
		gbc_btnStartButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStartButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnStartButton.gridx = 0;
		gbc_btnStartButton.gridy = 6;
		contentPane.add(btnStartButton, gbc_btnStartButton);
	}
	
	public void setAnalogValue(double d) {
		lblAnalogInValue.setText(String.format("%.4f",d));
	}
	
	public void setStatus (String s) {
		lblStatusValue.setText(s);
	}
	public String getDestinationAddress() {
		return textFieldDestinationIP.getText();
	}
	
	public int getDestinationPort() {
		return Integer.parseInt(textFieldDestinationPort.getText());
	}
	
	public int getPeriod() {
		return Integer.parseInt(textFieldPeriod.getText());
	}
	
}
