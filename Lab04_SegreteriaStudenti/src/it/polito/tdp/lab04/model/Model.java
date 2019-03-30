package it.polito.tdp.lab04.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	List<Corso> corsi;
	
	public List<Corso> getAllCorsi () {
		CorsoDAO dao = new CorsoDAO();
		corsi = dao.getTuttiICorsi();
		Collections.sort(corsi);
		return corsi;
		
		}


	public Studente getStudenteByMatricola (int matricola) {
		StudenteDAO dao = new StudenteDAO();
		Studente s = dao.getStudentePerMatricola(matricola);
		return s;	
	}
	
	
	public List<Studente> getStudentiPerCorso(Corso corso) {
		CorsoDAO dao = new CorsoDAO();
		return dao.getStudentiIscrittiAlCorso(corso);
	}


	public List<Corso> getCorsiByStudente(int matricola) {
		CorsoDAO dao = new CorsoDAO();
		return dao.getCorsiPerStudente(matricola);
	}
	
	public Boolean isStudenteIscrittoA (int matricola, String codins) {
		CorsoDAO dao = new CorsoDAO();
		StudenteDAO daos = new StudenteDAO();
		List <Studente> ltemp = dao.getStudentiIscrittiAlCorso(dao.getCorso(codins));
		if (ltemp == null) {
			return null;
		}
		Studente s = daos.getStudentePerMatricola(matricola);
		return ltemp.contains(s);
	}
	
}


