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
 * FrameEdit.java
 *
 * Created on 01 Nov 09, 22:47:33
 */

package org.ftiuaz.muharram1431.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.ftiuaz.muharram1431.app.dialog.EditBox;
import org.ftiuaz.muharram1431.app.model.SQLModel;
import org.ftiuaz.muharram1431.app.resources.connection.LogFile;
import org.ftiuaz.muharram1431.app.resources.connection.PathConfig;

/**
 *
 * @author maru
 */
public class FrameEdit extends javax.swing.JInternalFrame {
    private SQLModel sqlModel;
    private Statement statement;
    private ResultSet result;
    private EditBox insertData;
    private DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private DefaultListSelectionModel selectionModel = new DefaultListSelectionModel();
    private Vector rowData = new Vector(), columnHeader = new Vector();
    private int columnCount, currentRow = 0;
    private String tableName;
    private HashMap<Integer, String> mapping = new HashMap<Integer, String>();
    private static final int NAV_ALL = 1, NAV_PARTS = 0;

    /** Creates new form FrameEdit */
    public FrameEdit() {
        initComponents();
        sqlModel = new SQLModel(PathConfig.getInstance().getPath());
        sqlModel.connecting();
        selectionModel.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                try {
                    int selectedRow = tableViewer.getSelectedRow();
                    if (selectedRow == -1)
                        selectedRow = 0;
                    result.absolute(selectedRow + 1);
                    setPointer();
                } catch (SQLException ex) {
                    Logger.getLogger(FrameEdit.class.getName()).log(Level.SEVERE, null, ex);
                    LogFile.getInstance().record(LogFile.ERROR_APPS, FrameEdit.class.getName() + ":\n" + ex.getMessage());
                }
            }
        });
        fillModel();
        viewContents();
    }

    private void fillModel() {
        mapping.put(SQLModel.MS_USER, "MS_USER");
        mapping.put(SQLModel.MS_PENYUMBANG, "MS_PENYUMBANG");
        mapping.put(SQLModel.MS_SHADAQAH, "MS_SHADAQAH");
        mapping.put(SQLModel.TR_SHADAQAH, "TR_SHADAQAH");
        for(int i = 1; i <= mapping.size(); i++){
            boxModel.addElement(mapping.get(i));
        }
    }
    
    private void initNav(boolean status, int part) {
        buttonNavFirst.setEnabled(status);
        buttonNavPrev.setEnabled(status);
        if(part == NAV_PARTS){
            buttonNavNext.setEnabled(!status);
            buttonNavLast.setEnabled(!status);
        }else if(part == NAV_ALL){
            buttonNavNext.setEnabled(status);
            buttonNavLast.setEnabled(status);
        }
    }

    private void movePointer(int i) throws SQLException {
        switch(i){
            case 1:
                result.first();
                break;
            case 2:
                result.previous();
                break;
            case 3:
                result.next();
                break;
            case 4:
                result.last();
                break;
        }
        setPointer();
    }

    private void refreshData() {
        try {
            result = statement.executeQuery("SELECT * FROM " + tableName);
            movePointer(1);
        } catch (SQLException ex) {
            Logger.getLogger(FrameEdit.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_APPS, FrameEdit.class.getName() + ":\n" + ex.getMessage());
        }
    }

    private void setPointer() {
        try {
            currentRow = result.getRow();
            if(rowData.size() == 0){
                buttonDelete.setEnabled(false);
                buttonUpdate.setEnabled(false);
            }else{
                buttonDelete.setEnabled(true);
                buttonUpdate.setEnabled(true);
            }
            if(rowData.size() <= 1)
                initNav(false, NAV_ALL);
            else if(currentRow == 1)
                initNav(false, NAV_PARTS);
            else if(currentRow == rowData.size())
                initNav(true, NAV_PARTS);
            else
                initNav(true, NAV_ALL);
            selectionModel.setSelectionInterval(currentRow - 1, currentRow - 1);
            labelState.setText("Pointer pada baris: " + currentRow + " dari " + rowData.size());
        } catch (SQLException ex) {
            Logger.getLogger(FrameEdit.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_APPS, FrameEdit.class.getName() + ":\n" + ex.getMessage());
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar2 = new javax.swing.JToolBar();
        buttonNavFirst = new javax.swing.JButton();
        buttonNavPrev = new javax.swing.JButton();
        buttonNavNext = new javax.swing.JButton();
        buttonNavLast = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        buttonInsert = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jLabel3 = new javax.swing.JLabel();
        pilihTabel = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableViewer = new javax.swing.JTable();
        labelState = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Edit Data");

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        buttonNavFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ftiuaz/muharram1431/app/resources/first.png"))); // NOI18N
        buttonNavFirst.setToolTipText("Pilih data pada baris pertama");
        buttonNavFirst.setFocusable(false);
        buttonNavFirst.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonNavFirst.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonNavFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNavFirstActionPerformed(evt);
            }
        });
        jToolBar2.add(buttonNavFirst);

        buttonNavPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ftiuaz/muharram1431/app/resources/prev.png"))); // NOI18N
        buttonNavPrev.setToolTipText("Pilih data pada baris sebelumnya");
        buttonNavPrev.setFocusable(false);
        buttonNavPrev.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonNavPrev.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonNavPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNavPrevActionPerformed(evt);
            }
        });
        jToolBar2.add(buttonNavPrev);

        buttonNavNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ftiuaz/muharram1431/app/resources/next.png"))); // NOI18N
        buttonNavNext.setToolTipText("Pilih data pada baris selanjutnya");
        buttonNavNext.setFocusable(false);
        buttonNavNext.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonNavNext.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonNavNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNavNextActionPerformed(evt);
            }
        });
        jToolBar2.add(buttonNavNext);

        buttonNavLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ftiuaz/muharram1431/app/resources/last.png"))); // NOI18N
        buttonNavLast.setToolTipText("Pilih data pada baris terakhir");
        buttonNavLast.setFocusable(false);
        buttonNavLast.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonNavLast.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonNavLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNavLastActionPerformed(evt);
            }
        });
        jToolBar2.add(buttonNavLast);
        jToolBar2.add(jSeparator3);

        buttonInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ftiuaz/muharram1431/app/resources/insert.png"))); // NOI18N
        buttonInsert.setToolTipText("Tambah data");
        buttonInsert.setFocusable(false);
        buttonInsert.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonInsert.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInsertActionPerformed(evt);
            }
        });
        jToolBar2.add(buttonInsert);

        buttonUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ftiuaz/muharram1431/app/resources/update.png"))); // NOI18N
        buttonUpdate.setToolTipText("Update data pada field yang sudah dirubah");
        buttonUpdate.setFocusable(false);
        buttonUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonUpdate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });
        jToolBar2.add(buttonUpdate);

        buttonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ftiuaz/muharram1431/app/resources/delete.png"))); // NOI18N
        buttonDelete.setToolTipText("Hapus data pada baris yang dipilih");
        buttonDelete.setFocusable(false);
        buttonDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });
        jToolBar2.add(buttonDelete);
        jToolBar2.add(jSeparator4);

        jLabel3.setText("Pilih tabel: ");
        jToolBar2.add(jLabel3);

        pilihTabel.setModel(boxModel);
        pilihTabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihTabelActionPerformed(evt);
            }
        });
        jToolBar2.add(pilihTabel);

        tableViewer.setModel(tableModel);
        tableViewer.setSelectionModel(selectionModel);
        jScrollPane2.setViewportView(tableViewer);

        labelState.setText("State");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelState, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelState))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pilihTabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihTabelActionPerformed
        tableName = pilihTabel.getSelectedItem().toString().toLowerCase();
        setTitle("Edit Data - " + pilihTabel.getSelectedItem().toString());
        viewContents();
}//GEN-LAST:event_pilihTabelActionPerformed

    private void buttonNavFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNavFirstActionPerformed
        try {
            movePointer(1);
        } catch (SQLException ex) {
            Logger.getLogger(FrameEdit.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_APPS, FrameEdit.class.getName() + ":\n" + ex.getMessage());
        }
    }//GEN-LAST:event_buttonNavFirstActionPerformed

    private void buttonNavPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNavPrevActionPerformed
        try {
            movePointer(2);
        } catch (SQLException ex) {
            Logger.getLogger(FrameEdit.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_APPS, FrameEdit.class.getName() + ":\n" + ex.getMessage());
        }
    }//GEN-LAST:event_buttonNavPrevActionPerformed

    private void buttonNavNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNavNextActionPerformed
        try {
            movePointer(3);
        } catch (SQLException ex) {
            Logger.getLogger(FrameEdit.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_APPS, FrameEdit.class.getName() + ":\n" + ex.getMessage());
        }
    }//GEN-LAST:event_buttonNavNextActionPerformed

    private void buttonNavLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNavLastActionPerformed
        try {
            movePointer(4);
        } catch (SQLException ex) {
            Logger.getLogger(FrameEdit.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_APPS, FrameEdit.class.getName() + ":\n" + ex.getMessage());
        }
    }//GEN-LAST:event_buttonNavLastActionPerformed

    private void buttonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInsertActionPerformed
        insertData = new EditBox();
        insertData.initTabel(columnHeader);
        Vector newRecord = insertData.getRecordData();
        if (newRecord == null) return;
        tableModel.addRow(newRecord);
        try {
            result.moveToInsertRow();
            for (int i = 1; i <= columnCount; i++)
                result.updateObject(i, newRecord.elementAt(i - 1));
            result.insertRow();
            refreshData();
        } catch (SQLException ex) {
            Logger.getLogger(FrameEdit.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_APPS, FrameEdit.class.getName() + ":\n" + ex.getMessage());
        }
    }//GEN-LAST:event_buttonInsertActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        try {
            int row = tableViewer.getSelectedRow();
            for (int i = 1; i <= result.getMetaData().getColumnCount(); i++)
                result.updateObject(i, tableModel.getValueAt(row, i - 1));
            result.updateRow();
            refreshData();
        } catch (SQLException ex) {
            Logger.getLogger(FrameEdit.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_APPS, FrameEdit.class.getName() + ":\n" + ex.getMessage());
        }
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        try {
            result.deleteRow();
            tableModel.removeRow(selectionModel.getLeadSelectionIndex());
            refreshData();
        } catch (SQLException ex) {
            Logger.getLogger(FrameEdit.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_APPS, FrameEdit.class.getName() + ":\n" + ex.getMessage());
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonInsert;
    private javax.swing.JButton buttonNavFirst;
    private javax.swing.JButton buttonNavLast;
    private javax.swing.JButton buttonNavNext;
    private javax.swing.JButton buttonNavPrev;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel labelState;
    private javax.swing.JComboBox pilihTabel;
    private javax.swing.JTable tableViewer;
    // End of variables declaration//GEN-END:variables

    private void viewContents() {
        try {
            statement = sqlModel.getConnection().
                    createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rowData.clear();
            columnHeader.clear();
            result = statement.executeQuery("SELECT * FROM " + tableName);
            columnCount = result.getMetaData().getColumnCount();
            while (result.next()) {
                Vector singleRow = new Vector();
                for (int i = 0; i < columnCount; i++)
                    singleRow.addElement(result.getObject(i + 1));
                rowData.addElement(singleRow);
            }
            for (int i = 1; i <= columnCount; i++)
                columnHeader.addElement(result.getMetaData().getColumnName(i));
            tableModel.setDataVector(rowData, columnHeader);
            movePointer(1);
        } catch (SQLException ex) {
            Logger.getLogger(FrameEdit.class.getName()).log(Level.SEVERE, null, ex);
            LogFile.getInstance().record(LogFile.ERROR_APPS, FrameEdit.class.getName() + ":\n" + ex.getMessage());
        }
    }

}