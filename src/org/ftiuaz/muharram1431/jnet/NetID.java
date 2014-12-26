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

/*
 * To detect and get the network card ID (MAC Address) from host
 */

package org.ftiuaz.muharram1431.jnet;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.ftiuaz.muharram1431.app.resources.connection.LogFile;

/**
 *
 * @author maru
 */
public class NetID {
    private InetAddress address; // IP host
    private String host;
    private Socket socket;
    private boolean error = false;
    private String returnValue;

    public NetID(Socket socket) {
        this.socket = socket;
    }

    public NetID() {
    }

    public NetID(String host) {
        this.host = host;
    }

    /**
     * @return the host via IP
     */
    public String getHost() {
        setAddress();
        if(!error)
            returnValue = address.getHostName();
        return returnValue;
    }
    
    /**
     * @return the host via IP
     */
    public String getHostViaIP(){
        setAddress();
        if(!error)
            returnValue = address.getHostAddress();
        return returnValue;
    }

    /**
     * @return the host via MAC
     */
    public String getHostViaMAC() {
        returnValue = "";
        try {
            setAddress();
            if(!error){
                // network interface (network card ID)
                NetworkInterface networkInterface = NetworkInterface.getByInetAddress(address);
                if(networkInterface != null){
                    byte[] mac = networkInterface.getHardwareAddress();
                    if(mac != null){
                        for(int i = 0; i < mac.length; i++)
                            returnValue += String.format("%02X%s", mac[i],
                                    (i < mac.length - 1)? "-" : "");
                    }else
                        returnValue = "Host [" + host + "] tidak ada atau" +
                                " tidak dapat diakses";
                }else
                    returnValue = "Network Interface untuk host [" + host + "] tidak" +
                            " ditemukan";
            }
        } catch (SocketException ex) {
            error = true;
            returnValue = ex.getMessage();
            LogFile.getInstance().record(LogFile.ERROR_RESOURCES, NetID.class.getName() + ":\n" + ex.getMessage());
        }
        return returnValue.trim();
    }

    private void setAddress() {
        if (socket == null) {
            try {
                address = (host != null) ? InetAddress.getByName(host) : InetAddress.getLocalHost();
            } catch (UnknownHostException ex) {
                error = true;
                returnValue = ex.getMessage() + " tidak dapat ditemukan";
                LogFile.getInstance().record(LogFile.ERROR_RESOURCES, NetID.class.getName() + ":\n" + ex.getMessage());
            }
        } else {
            address = socket.getInetAddress();
        }
    }

    /**
     * @return the error
     */
    public boolean isError() {
        return error;
    }
}