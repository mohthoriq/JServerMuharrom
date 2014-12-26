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

package org.ftiuaz.muharram1431.app.model.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 *
 * @author maru
 */
public interface RemoteTransaction extends Remote {
    /**
     *
     * @param noTransaction
     * @return
     * @throws java.rmi.RemoteException
     * Check transaction number (noTransaction), is available or not?
     */
    boolean isAvailable(int noTransaction) throws RemoteException;
    /**
     * 
     * @return
     * @throws java.rmi.RemoteException
     */
    Map<Integer, DataShadaqah> getDetailShadaqah() throws RemoteException;
    /**
     * 
     * @param host
     * @throws java.rmi.RemoteException
     */
    void setPengentri(String host) throws RemoteException;
    /**
     * 
     * @param noTransaction
     * @param donatur
     * @param alamat
     * @param shadaqah
     * @param jumlah
     * @return
     * @throws java.rmi.RemoteException
     */
    boolean insertData(int noTransaction, String donatur, String alamat, int shadaqah, double jumlah) throws RemoteException;
}