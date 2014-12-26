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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ftiuaz.muharram1431.app.resources.connection.LogFile;

/**
 *
 * @author maru
 */
public class TransactionModel {
    private Connection connection;
    private Statement statement;
    private ResultSet result;
    //database variable
    private int tahun;
    private String pengentri;
    private int penyumbang;

    public TransactionModel() {
    }

    /**
     *
     * @param nama
     * @param alamat
     */
    public void addDonatur(String nama, String alamat){
        try {
            statement = getConnection().createStatement();
            result = statement.executeQuery("SELECT MAX(id_penyumbang) FROM ms_penyumbang");
            result.next();
            setPenyumbang(result.getInt(1) + 1);
            statement.executeUpdate("INSERT INTO ms_penyumbang VALUES(" + getPenyumbang() + ", '" +
                    nama + "', '" + alamat + "')");
        } catch (SQLException ex) {
            Logger.getLogger(TransactionModel.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_MODEL, TransactionModel.class.getName() + ":\n" + ex.getMessage());
        }
    }

    public int getNextRow(){
        int returnValue = -1;
        try {
            statement = getConnection().createStatement();
            result = statement.executeQuery("SELECT MAX(id_transaksi) FROM tr_shadaqah");
            result.next();
            returnValue = result.getInt(1) + 1;
        } catch (SQLException ex) {
            Logger.getLogger(TransactionModel.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_MODEL, TransactionModel.class.getName() + ":\n" + ex.getMessage());
        }
        return returnValue;
    }

    /**
     * @return the tahun
     */
    public int getTahun() {
        return tahun;
    }

    /**
     * @param tahun the tahun to set
     */
    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    /**
     * @return the pengentri
     */
    public String getPengentri() {
        return pengentri;
    }

    /**
     * @param pengentri the pengentri to set
     */
    public void setPengentri(String pengentri) {
        this.pengentri = pengentri;
    }

    /**
     * @return the penyumbang
     */
    public int getPenyumbang() {
        return penyumbang;
    }

    /**
     * @param penyumbang the penyumbang to set
     */
    public void setPenyumbang(int penyumbang) {
        this.penyumbang = penyumbang;
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}