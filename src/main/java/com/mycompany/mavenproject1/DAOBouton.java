/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import simplejdbc.DAOException;

/**
 *
 * @author morga
 */
public class DAOBouton {
    	protected final DataSource myDataSource;

	/**
	 *
	 * @param dataSource la source de données à utiliser
	 */
	public DAOBouton(DataSource dataSource) {
		this.myDataSource = dataSource;
	}
    
        public List<String> diffState() throws DAOException{
            List<String> resultat = new LinkedList<>();
            String sql ="SELECT DISTINCT STATE FROM CUSTOMER";
            
            try (Connection connection = myDataSource.getConnection();
			Statement stmt = connection.createStatement()) {
			try (ResultSet rs = stmt.executeQuery(sql)) {
				while (rs.next()) { // Tant qu'il y a des enregistrements
					resultat.add(rs.getString("state"));
				}
			}
		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}

            
            return resultat;
        }
}
