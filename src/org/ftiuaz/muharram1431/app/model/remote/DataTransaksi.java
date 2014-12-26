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
public class DataTransaksi implements Serializable {
    //id
    private int idPenyumbang, idTransaksi;
    private int shadaqah;
    private String nama, alamat;
    private double jumlah;

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
     * @return the shadaqah
     */
    public int getShadaqah() {
        return shadaqah;
    }

    /**
     * @param shadaqah the shadaqah to set
     */
    public void setShadaqah(int shadaqah) {
        this.shadaqah = shadaqah;
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
}