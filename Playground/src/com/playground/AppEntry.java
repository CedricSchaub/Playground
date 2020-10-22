package com.playground;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.playground.net.MathExpressionTokenizer;
import com.playground.net.Notation;
import com.playground.net.PostfixNotation;

public class AppEntry {

	public static void print(String msg) {
		System.out.println(msg);
	}

	public void writeToXml() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("Artikel.xml"));
			writer.write("<artikel>\n");
			writer.write("<artikelnummer>100</artikelnummer>\n");
			writer.write("<artikelbezeichnung>Logitech G403</artikelbezeichnung>\n");
			writer.write("</artikel>\n");
			writer.write("<artikel>\n");
			writer.write("<artikelnummer>300</artikelnummer>\n");
			writer.write("<artikelbezeichnung>Razor Huntsman</artikelbezeichnung>\n");
			writer.write("</artikel>\n");
			writer.close();
		} catch (IOException ioex) {
			print("Ups something went wrong");
		}
	}

	public void readFromXML() throws ParserConfigurationException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newDefaultInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		try {
			Document document = documentBuilder.parse("Artikel.xml");
			var artikel = document.getElementsByTagName("artikel");
			for (int i = 0; i < artikel.getLength(); i++) {
				var item = artikel.item(i);
				var childNodes = item.getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {
					print("Nodename: " + childNodes.item(j).getNodeName());
					print(", Nodevalue: " + childNodes.item(j).getNodeValue());
					print(childNodes.item(j).getTextContent());
				}
			}
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean insertArtikel(Statement sqlStatement, int artikelnr, String name, String beschreibung,
			float preis) throws SQLException {
		String insert_artikel_sql_template = "INSERT INTO Artikel (artikelnr, name, beschreibung, preis) VALUES (%d, '%s', '%s', %.3f)";
		String sql = String.format(Locale.US, insert_artikel_sql_template, artikelnr, name, beschreibung, preis);
		return sqlStatement.execute(sql);
	}

	private static void dbProgramm() throws ClassNotFoundException {

		Class.forName("org.h2.Driver");
		try {
			Connection connection = DriverManager.getConnection("jdbc:h2:./h2Test", "", "");
			Statement sqlStatement = connection.createStatement();

			String create_artikel_table_sql = "CREATE TABLE Artikel(" + "artikelnr INTEGER Primary Key Not Null, "
					+ "name VARCHAR(50), " + "beschreibung VARCHAR(255), " + "preis DECIMAL(9,3))";
			String drop_artikel_table_sql = "DROP TABLE Artikel";

			String insert_artikel_sql = "INSERT INTO Artikel (artikelnr, name, beschreibung, preis) VALUES (%d, '%s', '%s', %.3f)";
			// qlStatement.execute(drop_artikel_table_sql);
			sqlStatement.execute(create_artikel_table_sql);

			while (JOptionPane.showConfirmDialog(null, "Noch einen Artikel eingeben?", "Artikel eingabe",
					JOptionPane.YES_NO_OPTION) != JOptionPane.NO_OPTION) {

				int artikel = Integer
						.parseInt(JOptionPane.showInputDialog(null, "Artikelnummer", "", JOptionPane.QUESTION_MESSAGE));
				String name = JOptionPane.showInputDialog(null, "Name", "", JOptionPane.QUESTION_MESSAGE);
				String beschreibung = JOptionPane.showInputDialog(null, "Beschreibung", "",
						JOptionPane.QUESTION_MESSAGE);
				float preis = Float.parseFloat(
						JOptionPane.showInputDialog(null, "Preis", "", JOptionPane.QUESTION_MESSAGE).replace(',', '.'));

				boolean result = insertArtikel(sqlStatement, artikel, name, beschreibung, preis);

			}

			ResultSet rs = sqlStatement.executeQuery("Select * from Artikel");

			while (rs.next()) {
				String name = rs.getString("name");
				System.out.println(name);
			}

			sqlStatement.execute(drop_artikel_table_sql);
			if (connection != null) {
				connection.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		
		// 00000000 00000000 00000000 10000000
		Notation notation = new PostfixNotation();
		String expression = "1 + 3 * 9 / 2 + 4";
		MathExpressionTokenizer tokenizer = new MathExpressionTokenizer(expression);
		
		String postfix = notation.getRepresentation(tokenizer);
		
		System.out.println("Original expression: " + expression);
		System.out.println("Postfix expression: " + postfix);
		
		
		
	}
}
