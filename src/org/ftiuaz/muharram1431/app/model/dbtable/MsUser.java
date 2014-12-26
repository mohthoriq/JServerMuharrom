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
 * Class Model for table ms_user (database)
 */
public class MsUser extends DbTableModel<MsUser> {
    private String idUser, username;

    public MsUser() {
    }

    /**
     * @return the idUser
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public MsUser toModel(ResultSet rs) {
        if(instance == null)
            instance = new MsUser();
        try {
            int fieldCounts = rs.getMetaData().getColumnCount();
            for(int i = 1; i <= fieldCounts; i++){
                if(rs.getMetaData().getColumnName(i).equals("id_user"))
                    instance.setIdUser(rs.getString("id_user"));
                if(rs.getMetaData().getColumnName(i).equals("username"))
                    instance.setUsername(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MsUser.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_MODEL, MsUser.class.getName() + ":\n" + ex.getMessage());
        }
        return instance;
    }
}