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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.ftiuaz.muharram1431.app.model.dbtable.MsShadaqah;
import org.ftiuaz.muharram1431.app.model.remote.DataShadaqah;
import org.ftiuaz.muharram1431.app.model.remote.RemoteTransaction;
import org.ftiuaz.muharram1431.app.resources.connection.PathConfig;

/**
 *
 * @author maru
 */
public class Transactions extends UnicastRemoteObject implements RemoteTransaction {
    private SQLModel sqlModel = new SQLModel(PathConfig.getInstance().getPath());
    private TransactionModel transactionModel = new TransactionModel();
    private List list;

    public Transactions() throws RemoteException {
        sqlModel.connecting();
        transactionModel.setConnection(sqlModel.getConnection());
        transactionModel.setTahun(1431);
    }

    public boolean isAvailable(int noTransaction) throws RemoteException {
        sqlModel.setDbModel(SQLModel.TR_SHADAQAH);
        list = sqlModel.requestQuery("SELECT no_transaksi FROM tr_shadaqah WHERE no_transaksi = " +
                noTransaction);
        if(list.size() == 0)
            return true;
        else
            return false;
    }

    public Map<Integer, DataShadaqah> getDetailShadaqah() throws RemoteException {
        sqlModel.setDbModel(SQLModel.MS_SHADAQAH);
        list = sqlModel.requestQuery("SELECT * FROM ms_shadaqah");
        Map<Integer, DataShadaqah> mapping = new HashMap();
        DataShadaqah shadaqah;
        MsShadaqah ms;
        for (Object satuan : list) {
            ms = (MsShadaqah) satuan;
            shadaqah = new DataShadaqah();
            shadaqah.setIdShaqadah(ms.getIdShaqadah());
            shadaqah.setJenisShadaqah(ms.getJenisShadaqah());
            shadaqah.setSatuan(ms.getSatuan());
            shadaqah.setType(ms.isTipe());
            mapping.put(shadaqah.getIdShaqadah(), shadaqah);
        }
        return mapping;
    }

    public void setPengentri(String host) throws RemoteException {
        transactionModel.setPengentri(host);
    }

    public boolean insertData(int noTransaction, String donatur, String alamat, int shadaqah, double jumlah) throws RemoteException {
        transactionModel.addDonatur(donatur, alamat);
        if(transactionModel.getNextRow() != -1){
            sqlModel.commandQuery("INSERT INTO tr_shadaqah VALUES(" + transactionModel.getNextRow() + ", " +
                    transactionModel.getTahun() + ", '" + transactionModel.getPengentri() +
                    "', " + noTransaction + ", " + transactionModel.getPenyumbang() + ", " +
                    shadaqah + ", " + jumlah + ")");
            return (sqlModel.getResultSQL().equals("Query berhasil diproses"))?true : false;
        }else
            return false;
    }
}