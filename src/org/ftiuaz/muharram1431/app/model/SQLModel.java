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

package org.ftiuaz.muharram1431.app.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ftiuaz.muharram1431.app.model.dbtable.DbTableModel;
import org.ftiuaz.muharram1431.app.model.dbtable.MsPenyumbang;
import org.ftiuaz.muharram1431.app.model.dbtable.MsShadaqah;
import org.ftiuaz.muharram1431.app.model.dbtable.MsUser;
import org.ftiuaz.muharram1431.app.model.dbtable.TrShadaqah;
import org.ftiuaz.muharram1431.app.resources.connection.ConnectionFactory;
import org.ftiuaz.muharram1431.app.resources.connection.LogFile;

/**
 *
 * @author maru
 */
public class SQLModel{
    private String resultSQL, pathConfig;
    private static Properties properties;
    private Connection connection;
    private Statement statement;
    private int dbModel;
    public static final int MS_USER = 1, MS_SHADAQAH = 2, MS_PENYUMBANG = 3,
            TR_SHADAQAH = 4;

    public SQLModel(String pathConfig) {
        this.pathConfig = pathConfig;
        setConfiguration(this.pathConfig);
    }

    /**
     * connect to database
     */
    public void connecting(){
        try {
            connection = ConnectionFactory.getConnection(properties);
        } catch (SQLException ex) {
            Logger.getLogger(SQLModel.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_MODEL, SQLModel.class.getName() + ":\n" + ex.getMessage());
        }
    }

    /**
     * disconnect from database
     */
    public void disconnecting(){
        try {
            ConnectionFactory.getConnection(properties).close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLModel.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_MODEL, SQLModel.class.getName() + ":\n" + ex.getMessage());
        }
    }

    public void setPathConfig(String pathConfig) {
        this.pathConfig = pathConfig;
    }

    public void commandQuery(String data) {
        try {
            statement = getConnection().createStatement();
            statement.executeUpdate(data);
            resultSQL = "Query berhasil diproses";
        } catch (SQLException ex) {
            resultSQL = "Query tidak berhasil diproses";
            Logger.getLogger(SQLModel.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_MODEL, SQLModel.class.getName() + ":\n" + ex.getMessage());
        }
    }

    public List<Object> requestQuery(String data) {
        List<Object> list = null;
        try {
            list = new ArrayList<Object>();
            statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery(data);
            DbTableModel model = null;
            while (rs.next()) {
                switch(dbModel){
                    case MS_USER:
                        model = new MsUser();
                        model = (DbTableModel) model.toModel(rs);
                        break;
                    case MS_PENYUMBANG:
                        model = new MsPenyumbang();
                        model = (DbTableModel) model.toModel(rs);
                        break;
                    case MS_SHADAQAH:
                        model = new MsShadaqah();
                        model = (DbTableModel) model.toModel(rs);
                        break;
                    case TR_SHADAQAH:
                        model = new TrShadaqah();
                        model = (DbTableModel) model.toModel(rs);
                        break;
                }
                if(model != null)
                    list.add(model);
            }
            resultSQL = "Query berhasil diproses";
        } catch (SQLException ex) {
            resultSQL = "Query tidak berhasil diproses";
            Logger.getLogger(SQLModel.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_MODEL, SQLModel.class.getName() + ":\n" + ex.getMessage());
        }
        return list;
    }

    private void setConfiguration(String path) {
        if(properties == null)
            properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException ex) {
            Logger.getLogger(SQLModel.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_MODEL, SQLModel.class.getName() + ":\n" + ex.getMessage());
        }
    }

    /**
     * @return the resultSQL
     */
    public String getResultSQL() {
        return resultSQL;
    }

    /**
     * @param dbModel the dbModel to set
     */
    public void setDbModel(int dbModel) {
        this.dbModel = dbModel;
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }
}