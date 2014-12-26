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

/**
 *
 * @author maru
 */
public interface RemoteClient extends Remote {
    /**
     * 
     * @param host
     * @param user
     * @throws java.rmi.RemoteException
     */
    void register(String host, String user) throws RemoteException;
    /**
     * 
     * @param host
     * @return
     * @throws java.rmi.RemoteException
     */
    boolean isValid(String host) throws RemoteException;
}