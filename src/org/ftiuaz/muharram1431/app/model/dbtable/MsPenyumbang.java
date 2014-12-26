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
 */
public class MsPenyumbang extends DbTableModel<MsPenyumbang> {
    private int idPenyumbang;
    private String nama, alamat;

    public MsPenyumbang() {
    }

    /**
     * @return the idPenyumbang
     */
    public int getIdPenyumbang() {
        return idPenyumbang;
    }

    /**
     * @param idPenyumbang the idPenyumbang to set
     */
    public void setIdPenyumbang(int idPenyumbang) {
        this.idPenyumbang = idPenyumbang;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public MsPenyumbang toModel(ResultSet rs) {
        if(instance == null)
            instance = new MsPenyumbang();
        try {
            int fieldCounts = rs.getMetaData().getColumnCount();
            for(int i = 1; i <= fieldCounts; i++){
                if(rs.getMetaData().getColumnName(i).equals("id_penyumbang"))
                    instance.setIdPenyumbang(rs.getInt("id_penyumbang"));
                if(rs.getMetaData().getColumnName(i).equals("alamat"))
                    instance.setAlamat(rs.getString("alamat"));
                if(rs.getMetaData().getColumnName(i).equals("nama"))
                    instance.setNama(rs.getString("nama"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MsUser.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_MODEL, MsPenyumbang.class.getName() + ":\n" + ex.getMessage());
        }
        return instance;
    }
}