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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.ftiuaz.muharram1431.app.model.remote.RemoteClient;
import org.ftiuaz.muharram1431.app.resources.connection.PathConfig;

/**
 *
 * @author maru
 */
public class Clients extends UnicastRemoteObject implements RemoteClient {

    private List hostClient = new ArrayList();
    //Enlist host clients
    private HashMap<String, String> enlistClients = new HashMap<String, String>();

    public Clients() throws RemoteException {
    }

    public void register(String host, String user) throws RemoteException {
        //store key (host) to List
        if(!enlistClients.containsKey(host)){
            hostClient.add(host);
            setEnlistClients(host, user);
        }
    }

    public boolean isValid(String host) throws RemoteException {
        SQLModel sqlModel = new SQLModel(PathConfig.getInstance().getPath());
        sqlModel.connecting();
        sqlModel.setDbModel(SQLModel.MS_USER);
        List list = sqlModel.requestQuery("SELECT id_user FROM ms_user WHERE id_user = '" +
                host + "'");
        if(list.size() != 0)
            return true;
        else
            return false;
    }

    public int sumClients(){
        SQLModel sqlModel = new SQLModel(PathConfig.getInstance().getPath());
        sqlModel.connecting();
        sqlModel.setDbModel(SQLModel.MS_USER);
        List list = sqlModel.requestQuery("SELECT id_user FROM ms_user");
        return list.size();
    }

    /**
     *
     * @param key represented MAC address client
     * @return
     */
    public String getEnlistClients(String key) {
        return enlistClients.get(key);
    }

    /**
     * 
     * @param key represented MAC address client
     * @param value represented username or hostname
     */
    public void setEnlistClients(String key, String value) {
        enlistClients.put(key, value);
    }

    /**
     * @return the hostClient
     */
    public List getHostClient() {
        return hostClient;
    }
}