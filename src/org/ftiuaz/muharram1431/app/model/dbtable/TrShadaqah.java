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
public class TrShadaqah extends DbTableModel<TrShadaqah> {
    private int idTransaksi, tahun;
    private MsUser pengentri;
    private int noTransaksi;
    private MsPenyumbang penyumbang;
    private MsShadaqah shadaqah;
    private double jumlah;

    public TrShadaqah() {
    }

    /**
     * @return the idTransaksi
     */
    public int getIdTransaksi() {
        return idTransaksi;
    }

    /**
     * @param idTransaksi the idTransaksi to set
     */
    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
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
    public MsUser getPengentri() {
        return pengentri;
    }

    /**
     * @param pengentri the pengentri to set
     */
    public void setPengentri(MsUser pengentri) {
        this.pengentri = pengentri;
    }

    /**
     * @return the noTransaksi
     */
    public int getNoTransaksi() {
        return noTransaksi;
    }

    /**
     * @param noTransaksi the noTransaksi to set
     */
    public void setNoTransaksi(int noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    /**
     * @return the penyumbang
     */
    public MsPenyumbang getPenyumbang() {
        return penyumbang;
    }

    /**
     * @param penyumbang the penyumbang to set
     */
    public void setPenyumbang(MsPenyumbang penyumbang) {
        this.penyumbang = penyumbang;
    }

    /**
     * @return the shadaqah
     */
    public MsShadaqah getShadaqah() {
        return shadaqah;
    }

    /**
     * @param shadaqah the shadaqah to set
     */
    public void setShadaqah(MsShadaqah shadaqah) {
        this.shadaqah = shadaqah;
    }

    /**
     * @return the jumlah
     */
    public double getJumlah() {
        return jumlah;
    }

    /**
     * @param jumlah the jumlah to set
     */
    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public TrShadaqah toModel(ResultSet rs) {
        if(instance == null)
            instance = new TrShadaqah();
        try {
            int fieldCounts = rs.getMetaData().getColumnCount();
            for(int i = 1; i <= fieldCounts; i++){

                if(rs.getMetaData().getColumnName(i).equals("id_transaksi"))
                    instance.setIdTransaksi(rs.getInt("id_transaksi"));
                if(rs.getMetaData().getColumnName(i).equals("jumlah"))
                    instance.setJumlah(rs.getDouble("jumlah"));
                if(rs.getMetaData().getColumnName(i).equals("no_transaksi"))
                    instance.setNoTransaksi(rs.getInt("no_transaksi"));
                if(rs.getMetaData().getColumnName(i).equals("pengentri")){
                    MsUser user = new MsUser();user.setIdUser(rs.getString("pengentri"));
                    instance.setPengentri(user);
                }if(rs.getMetaData().getColumnName(i).equals("penyumbang")){
                    MsPenyumbang peyumbg = new MsPenyumbang();peyumbg.setIdPenyumbang(rs.getInt("penyumbang"));
                    instance.setPenyumbang(peyumbg);}
                if(rs.getMetaData().getColumnName(i).equals("shadaqah")){
                    MsShadaqah sdq = new MsShadaqah();sdq.setIdShaqadah(rs.getInt("shadaqah"));
                    instance.setShadaqah(sdq);}
                if(rs.getMetaData().getColumnName(i).equals("tahun"))
                    instance.setTahun(rs.getInt("tahun"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(MsUser.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_MODEL, TrShadaqah.class.getName() + ":\n" + ex.getMessage());
        }
        return instance;
    }
}