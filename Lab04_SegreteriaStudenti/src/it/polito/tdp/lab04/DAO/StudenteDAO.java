package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getStudentePerMatricola (int matricola) {
		
		final String sql = "SELECT cognome, nome, cds FROM studente WHERE matricola LIKE ?";
		String nome;
		String cognome;
		String codCorso;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			if (!rs.next()) {
				return null;
			
			} else {
				cognome = rs.getString("cognome");
				nome = rs.getString("nome");
				codCorso = rs.getString("CDS");
				Studente s = new Studente (matricola, cognome, nome, codCorso);
				return s;
			}
	}    catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
	}
	}
}
