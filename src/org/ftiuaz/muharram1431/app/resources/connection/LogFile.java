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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maru
 */
public class LogFile {
    private static LogFile instance;
    private String path;
    private static File file;
    private RandomAccessFile saveLog;
    public static final int ERROR_MODEL = 1, ERROR_RESOURCES = 2, ERROR_APPS = 3;

    private LogFile() {}

    /**
     * @return the instance
     */
    public static LogFile getInstance() {
        if(instance == null)
            instance = new LogFile();
        return instance;
    }

    private String initPath(int type){
        switch(type){
            case ERROR_MODEL:
                path="model_log.txt";
                break;
            case ERROR_RESOURCES:
                path="resources_log.txt";
                break;
            case ERROR_APPS:
                path="apps_log.txt";
                break;
        }
        file = new File(path);
        return null;
    }

    public void record(int type, String msg) {
        initPath(type);
        try {
            saveLog = new RandomAccessFile(file, "rw");
            if(saveLog.length() != 0)
                saveLog.seek(saveLog.length());
            saveLog.writeUTF(new SimpleDateFormat("yyyy.MM.dd 'at' hh:mm:ss").format(new Date()) + ":\n" + msg + "\n");
            saveLog.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LogFile.class.getName()).log(Level.SEVERE, null, ex);
            record(LogFile.ERROR_RESOURCES, LogFile.class.getName() + ":\n" + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(LogFile.class.getName()).log(Level.SEVERE, null, ex);
            record(LogFile.ERROR_RESOURCES, LogFile.class.getName() + ":\n" + ex.getMessage());
        }
    }
}