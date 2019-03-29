package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso c = new Corso (codins, numeroCrediti, nome, periodoDidattico);
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(c);
			}
			
			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String codins) {
		final String sql = "SELECT * FROM corso WHERE codins LIKE ?";
		int crediti;
		String nome;
		int pd;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			ResultSet rs = st.executeQuery();
			
			if (!rs.next()) {
				return null;
			
			} else {
				crediti = rs.getInt("crediti");
				nome = rs.getString("nome");
				pd= rs.getInt("pd");
				Corso c = new Corso (codins, crediti, nome, pd);
				return c;
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
	}
	}
		

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List <Studente> getStudentiIscrittiAlCorso(Corso corso) {
		final String sql = "SELECT matricola FROM iscrizione WHERE codins LIKE ?";
		List<Studente> studenti = new LinkedList<Studente>();
		int matricola;
		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodIns());
			ResultSet rs = st.executeQuery();
			StudenteDAO dao = new StudenteDAO();
			
			while (rs.next()) {
				matricola = rs.getInt("matricola");
				studenti.add(dao.getStudentePerMatricola(matricola));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Errore DB");
		}
			
		return studenti;
	}

	public List <Corso> getCorsiPerStudente (int matricola) {
		final String sql = "SELECT codins FROM iscrizione WHERE matricola LIKE ?";
		List<Corso> corsi = new LinkedList<Corso>();
		String codins;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				codins = rs.getString("codins");
				corsi.add(this.getCorso(codins));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Errore DB");
		}
			
		return corsi;
	}
	
	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}
}
