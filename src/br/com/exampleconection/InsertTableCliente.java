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
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class InsertTableCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtCod;
	private JTextField txtNome;
	private JTextField txtCidade;
	private JButton btnMostrarTodos;

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
	 * Create the frame.
	 */
	public InsertTableCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLbnnome = new JLabel("Nome");
		lblLbnnome.setBounds(224, 56, 46, 14);
		contentPane.add(lblLbnnome);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(357, 56, 46, 14);
		contentPane.add(lblCidade);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(76, 56, 46, 14);
		contentPane.add(lblCodigo);
		
		txtCod = new JTextField();
		txtCod.setBounds(54, 81, 86, 20);
		contentPane.add(txtCod);
		txtCod.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setBounds(197, 81, 86, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(332, 81, 86, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);
		//==========================================================
		
		JButton btnCarregar = new JButton("Carregar");
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
					
				} catch (SQLException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		btnCarregar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCarregar.setBounds(194, 199, 101, 23);
		contentPane.add(btnCarregar);
		
		btnMostrarTodos = new JButton("Mostrar Todos");
		btnMostrarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnMostrarTodos.setBounds(184, 328, 132, 23);
		contentPane.add(btnMostrarTodos);
	}
}
