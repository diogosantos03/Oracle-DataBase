package br.com.exampleconection;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class InsertTableCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtCod, txtCidade, txtNome;
	private JButton btnCarregar, btnMostrarTodos;
	private JLabel lblLbnnome, lblCidade, lblCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertTableCliente frame = new InsertTableCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the labels
	 */
	public void labels() {
		lblLbnnome = new JLabel("Nome");
		lblCidade = new JLabel("Cidade");
		lblCodigo = new JLabel("Codigo");
		
		lblLbnnome.setBounds(224, 56, 46, 14);
		contentPane.add(lblLbnnome);
		
		lblCidade.setBounds(357, 56, 46, 14);
		contentPane.add(lblCidade);
		
		lblCodigo.setBounds(76, 56, 46, 14);
		contentPane.add(lblCodigo);
	}
	
	/**
	 * Create the textFilds
	 */
	public void textFilds() {
		txtCod = new JTextField();
		txtNome = new JTextField();
		txtCidade = new JTextField();
		
		txtCod.setBounds(54, 81, 86, 20);
		contentPane.add(txtCod);
		txtCod.setColumns(10);
		
		txtNome.setBounds(197, 81, 86, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtCidade.setBounds(332, 81, 86, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);
	}
	
	/**
	 * Create the buttons
	 */
	public void buttons() {
		btnCarregar = new JButton("Carregar");
		btnCarregar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCarregar.setBounds(194, 199, 101, 23);
		contentPane.add(btnCarregar);
		
		btnMostrarTodos = new JButton("Mostrar Todos");
		btnMostrarTodos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnMostrarTodos.setBounds(184, 328, 132, 23);
		contentPane.add(btnMostrarTodos);
	}
	
	/**
	 * Create the frame.
	 */
	public InsertTableCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		labels();
		textFilds();
		buttons();
		
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "346460");
					PreparedStatement stm = conn.prepareStatement("INSERT INTO CLIENTE(CODIGO, NOME, CIDADE) VALUES (?,?,?)");
					stm.setInt(1, Integer.parseInt(txtCod.getText()));
					stm.setString(2, txtNome.getText());
					stm.setString(3, txtCidade.getText());
		
					stm.executeUpdate();
					
					stm.close();
					
					txtCod.setText("");
					txtNome.setText("");
					txtCidade.setText("");
					
				} catch (SQLException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		
		
		
		btnMostrarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
	}
}
