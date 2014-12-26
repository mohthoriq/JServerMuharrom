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

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import org.ftiuaz.muharram1431.app.model.dbtable.MsPenyumbang;
import org.ftiuaz.muharram1431.app.model.dbtable.TrShadaqah;
import org.ftiuaz.muharram1431.app.model.remote.DataTransaksi;
import org.ftiuaz.muharram1431.app.model.remote.RemoteQuery;
import org.ftiuaz.muharram1431.app.resources.connection.PathConfig;

/**
 *
 * @author maru
 */
public class ClientGrant extends UnicastRemoteObject implements RemoteQuery {
    private SQLModel sqlModel = new SQLModel(PathConfig.getInstance().getPath());
    private List list;
    private DataTransaksi transaksi = new DataTransaksi();

    public ClientGrant() throws RemoteException {
        sqlModel.connecting();
    }

    public DataTransaksi getData(int no_transaksi, String client) {
        sqlModel.setDbModel(SQLModel.TR_SHADAQAH);
        list = sqlModel.requestQuery("SELECT id_transaksi, penyumbang, shadaqah, jumlah FROM tr_shadaqah WHERE no_transaksi = "
                + no_transaksi + " AND pengentri = '" + client + "'");
        if(list.size() == 0)
            return null;
        else{
            TrShadaqah shadaqah = (TrShadaqah) list.get(0);
            transaksi.setIdTransaksi(shadaqah.getIdTransaksi());
            transaksi.setIdPenyumbang(shadaqah.getPenyumbang().getIdPenyumbang());
            transaksi.setShadaqah(shadaqah.getShadaqah().getIdShaqadah());
            transaksi.setJumlah(shadaqah.getJumlah());
            //donatur
            sqlModel.setDbModel(SQLModel.MS_PENYUMBANG);
            list = sqlModel.requestQuery("SELECT * FROM ms_penyumbang WHERE id_penyumbang = "
                + transaksi.getIdPenyumbang());
            if(list.size() == 0)
                return null;
            else{
                MsPenyumbang penyumbang = (MsPenyumbang) list.get(0);
                transaksi.setIdPenyumbang(penyumbang.getIdPenyumbang());
                transaksi.setNama(penyumbang.getNama());
                transaksi.setAlamat(penyumbang.getAlamat());
                return transaksi;
            }
        }
    }

    public boolean updateData(DataTransaksi shadaqah) {
        if(updateDonatur(shadaqah.getIdPenyumbang(), shadaqah.getNama(), shadaqah.getAlamat())){
            if(updateShadaqah(shadaqah.getIdTransaksi(), shadaqah.getIdPenyumbang(), shadaqah.getShadaqah(), shadaqah.getJumlah()))
                return true;
        }
        return false;
    }

    private boolean updateDonatur(int idPenyumbang, String nama, String alamat) {
//        System.out.println("Id: " + idPenyumbang + ", nama: " + nama + ", alamat: "
//                + alamat);
        sqlModel.commandQuery("UPDATE ms_penyumbang SET nama = '" + nama + "', alamat = '"
                + alamat + "' WHERE id_penyumbang = " + idPenyumbang);
        return (sqlModel.getResultSQL().equals("Query berhasil diproses"))?true : false;
    }

    private boolean updateShadaqah(int idTransaksi, int idPenyumbang, int shadaqah, double jumlah) {
//        System.out.println("Id: " + idTransaksi + ", donatur: " + idPenyumbang +
//                ", shadaqah: " + shadaqah + ", jumlah: " + jumlah);
        sqlModel.commandQuery("UPDATE tr_shadaqah SET shadaqah = " + shadaqah +
                ", jumlah = " + jumlah + " WHERE id_transaksi = " + idTransaksi
                + " AND penyumbang = " + idPenyumbang);
        return (sqlModel.getResultSQL().equals("Query berhasil diproses"))?true : false;
    }
}