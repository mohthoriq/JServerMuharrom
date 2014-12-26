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

package org.ftiuaz.muharram1431.app.resources.connection;

/**
 *
 * @author maru
 */
public class PathConfig {
    private static PathConfig instance;
    private static String path;

    private PathConfig() {
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param aPath the path to set
     */
    public void setPath(String aPath) {
        path = aPath;
    }

    /**
     * @return the instance
     */
    public static PathConfig getInstance() {
        if(instance == null)
            instance = new PathConfig();
        return instance;
    }
}