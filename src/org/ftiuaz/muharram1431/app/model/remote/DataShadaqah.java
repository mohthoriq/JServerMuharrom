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

import java.io.Serializable;

/**
 *
 * @author maru
 */
public class DataShadaqah implements Serializable {
    public static final boolean BARANG = true;
    public static final boolean UANG = false;
    
    private int idShaqadah;
    private String jenisShadaqah, satuan;
    private boolean type;

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
    public boolean isType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(boolean type) {
        this.type = type;
    }
}