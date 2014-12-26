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

package org.ftiuaz.muharram1431.app.model.dbtable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ftiuaz.muharram1431.app.resources.connection.LogFile;

/**
 *
 * @author maru
 * Class Model for table ms_shadaqah (database)
 */
public class MsShadaqah extends DbTableModel<MsShadaqah> {
    private int idShaqadah;
    private String jenisShadaqah, satuan;
    private boolean type;

    public MsShadaqah() {
    }

    /**
     * @return the idShaqadah
     */
    public int getIdShaqadah() {
        return idShaqadah;
    }

    /**
     * @param idShaqadah the idShaqadah to set
     */
    public void setIdShaqadah(int idShaqadah) {
        this.idShaqadah = idShaqadah;
    }

    /**
     * @return the jenisShadaqah
     */
    public String getJenisShadaqah() {
        return jenisShadaqah;
    }

    /**
     * @param jenisShadaqah the jenisShadaqah to set
     */
    public void setJenisShadaqah(String jenisShadaqah) {
        this.jenisShadaqah = jenisShadaqah;
    }

    /**
     * @return the satuan
     */
    public String getSatuan() {
        return satuan;
    }

    /**
     * @param satuan the satuan to set
     */
    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    /**
     * @return the type
     */
    public boolean isTipe() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setTipe(boolean type) {
        this.type = type;
    }

    public MsShadaqah toModel(ResultSet rs) {
        if(instance == null)
            instance = new MsShadaqah();
        try {
            int fieldCounts = rs.getMetaData().getColumnCount();
            for(int i = 1; i <= fieldCounts; i++){
                if(rs.getMetaData().getColumnName(i).equals("id_shadaqah"))
                    instance.setIdShaqadah(rs.getInt("id_shadaqah"));
                if(rs.getMetaData().getColumnName(i).equals("jenis_shadaqah"))
                    instance.setJenisShadaqah(rs.getString("jenis_shadaqah"));
                if(rs.getMetaData().getColumnName(i).equals("satuan"))
                    instance.setSatuan(rs.getString("satuan"));
                if(rs.getMetaData().getColumnName(i).equals("tipe"))
                    instance.setTipe(rs.getBoolean("tipe"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MsUser.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_MODEL, MsShadaqah.class.getName() + ":\n" + ex.getMessage());
        }
        return instance;
    }
}