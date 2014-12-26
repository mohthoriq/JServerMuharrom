/*
 * This source code was authorized by TomsProject.
 * Copyright (C) 2009 TomsProject.

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.ftiuaz.muharram1431.app.resources.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohammad Thoriq
 * @author maru
 */
public class ConnectionFactory{
    private static String driver = null;
    private static String url = null;
	private static String user = null;
	private static String password = null;

    private static Connection con = null;

    private ConnectionFactory() {}

    public static Connection getConnection(Properties prop) throws SQLException{
        String singletone = prop.getProperty("database.singletone");
        if (singletone.equalsIgnoreCase("true")) {
            if((con==null))con = connProcess(prop);
		}else{
            con = connProcess(prop);
        }
        return con;
    }

    private static Connection connProcess(Properties prop) throws SQLException{
        Connection connProcess = null;
        try {
            driver = prop.getProperty("database.driver");
            url = prop.getProperty("database.url");
            user = prop.getProperty("database.username");
            password = prop.getProperty("database.password");
            Class.forName(driver); // use GOF Facory design pattern
            connProcess = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_RESOURCES, ConnectionFactory.class.getName() + ":\n" + ex.getMessage());
        } 
        return connProcess;
    }
}