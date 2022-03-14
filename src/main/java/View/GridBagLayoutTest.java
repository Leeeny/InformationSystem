package View;

import Controller.FileController;
import Controller.net.Connection;
import Model.Style;
import Model.Track;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class GridBagLayoutTest extends JFrame {
    private String trackID;
    private String[] uuids;
    private Track track;
    private Vector<Vector<String>> info;
    private JTable table;
    private JButton changeButton;
    private JButton deleteButton;
    private JButton addButton;

    public JTable getTable() {
        return table;
    }

    public JButton getChangeButton() {
        return changeButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    //HashMap<String, LinkedHashMap<String, LinkedHashMap<String, Object>>> gettingHash;
    //DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public GridBagLayoutTest( DataOutputStream dataOutputStream) {
        super("Information System!");
        //this.gettingHash = gettingHash;
        // Создание окна
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Определить панель содержания
        //createPanelUI(this.getContentPane(), gettingHash);

        // Показать окно
        this.pack();
        this.setVisible(true);
        this.dataOutputStream = dataOutputStream;

    }

    public String getTrackID() {
        return trackID;
    }

    public void setTrackID(String trackID) {
        this.trackID = trackID;
    }

    public GridBagLayoutTest() throws HeadlessException {
        //this.choose = -1;
    }

    public String[] getUuids() {
        return uuids;
    }

    public void setUuids(String[] uuids) {
        this.uuids = uuids;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Vector<Vector<String>> getInfo(HashMap<String, LinkedHashMap<String, LinkedHashMap<String, Object>>> gettingHash) {

        Vector<Vector<String>> info = new Vector<>();
        uuids = gettingHash.keySet().toArray(new String[0]);
        for (String uuid : gettingHash.keySet()) {
            LinkedHashMap<String, LinkedHashMap<String, Object>> keys = gettingHash.get(uuid);
            Vector<String> vector = new Vector<String>(List.of(new String[]{
                    String.valueOf(keys.get("nameOfTrack")),
                    String.valueOf(keys.get("artist")),
                    String.valueOf(keys.get("album")),
                    String.valueOf(keys.get("style").get("nameOfStyle")),
                    String.valueOf(keys.get("time"))
            }));

            info.add(vector);
        }
        return info;
    }

    public void updatePanelUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Определить панель содержания
        //createPanelUI(this.getContentPane(), gettingHash);

        // Показать окно
        this.pack();
        this.setVisible(true);
    }
    public void createPanelUI(HashMap<String, LinkedHashMap<String, LinkedHashMap<String, Object>>> gettingHash) {

        getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy = 0;  // нулевая ячейка таблицы по вертикали

        changeButton = new JButton("Change track");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2; // нулевая ячейка таблицы по горизонтали
        getContentPane().add(changeButton, constraints);

        deleteButton = new JButton("Delete track");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // первая ячейка таблицы по горизонтали
        getContentPane().add(deleteButton, constraints);

        addButton = new JButton("Add track");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2; // вторая ячейка таблицы по горизонтали
        getContentPane().add(addButton, constraints);

        Vector<String> columnNames = new Vector<>();
        columnNames.add("Name");
        columnNames.add("Artist");
        columnNames.add("Album");
        columnNames.add("Style");
        columnNames.add("Time");

        info = getInfo(gettingHash);

        table = new JTable(info, columnNames);
        constraints.ipady = 2; // кнопка высокая
        constraints.weightx = 0.5;
        constraints.gridwidth = 2; // размер кнопки в две ячейки
        constraints.gridx = 0; // нулевая ячейка по горизонтали
        constraints.gridy = 0; // первая ячейка по вертикали

        //чтобы не было возможности задавать значения таблицы

        getContentPane().add(table, constraints);


        TableModel tableModel = table.getModel();
        DefaultTableModel defaultTableModel = (DefaultTableModel) tableModel;


        //изменить трек
        changeButton.addActionListener(e -> {
            JFrame f = new JFrame("Change track");
            f.getContentPane().setLayout(new FlowLayout());
            int row1 = table.getSelectedRows()[0];
            JTextField nameOfSongTF = new JTextField((String) table.getValueAt(row1, 0), 10);
            JTextField artistTF = new JTextField((String) table.getValueAt(row1, 1), 10);
            JTextField albumTF = new JTextField((String) table.getValueAt(row1, 2), 10);
            JTextField styleTF = new JTextField((String) table.getValueAt(row1, 3), 10);
            JTextField timeTF = new JTextField((String) table.getValueAt(row1, 4), 10);
            //добавить имя стиля!
            JButton submitButton = new JButton("Submit");
            f.getContentPane().add(nameOfSongTF);
            f.getContentPane().add(artistTF);
            f.getContentPane().add(albumTF);
            f.getContentPane().add(styleTF);
            f.getContentPane().add(timeTF);

            f.getContentPane().add(submitButton);
            f.pack();
            f.setVisible(true);
            submitButton.addActionListener(y -> {
                //функция поиска через row1 для hashMap
                trackID = uuids[row1];
                track = new Track(nameOfSongTF.getText(), artistTF.getText(), albumTF.getText(), Long.parseLong(timeTF.getText()), new Style(styleTF.getText()));

                //info.set(row1, new Vector<String>(List.of(nameOfSongTF.getText(), artistTF.getText(), albumTF.getText(), styleTF.getText(), timeTF.getText())));
                //choose = 1;
                String toSend = "1";
                String trackId = getTrackID();
                toSend += "@" + trackId + "@" + FileController.TrackToJSON(track);
                //choose = -1;
                try {
                    Connection.send(dataOutputStream, toSend);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                tableModel.setValueAt(nameOfSongTF.getText(), row1, 0);
                tableModel.setValueAt(artistTF.getText(), row1, 1);
                tableModel.setValueAt(albumTF.getText(), row1, 2);
                tableModel.setValueAt(styleTF.getText(), row1, 3);
                tableModel.setValueAt(timeTF.getText(), row1, 4);
                f.dispose();
            });
        });

        //удалить
        deleteButton.addActionListener(e -> {
            int row1 = table.getSelectedRows()[0];
            trackID = uuids[row1];
            String toSend = "2";
            toSend += "@" + trackID;
            try {
                Connection.send(dataOutputStream, toSend);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
           // tableModel.removeRow()
            //отправлять запрос на сервер
            //функция поиска через row1 для hashMap
        });

        //добавить новый
        addButton.addActionListener(e -> {
            JFrame f = new JFrame("Add new track");
            f.getContentPane().setLayout(new FlowLayout());
            int row1 = table.getSelectedRows()[0];
            JTextField nameOfSongTF = new JTextField("Name of song", 10);
            JTextField artistTF = new JTextField("Artist", 10);
            JTextField timeTF = new JTextField("Time", 10);
            JTextField styleTF = new JTextField("Style", 10);
            JTextField albumTF = new JTextField("Album", 10);
            JButton submitButton = new JButton("Submit");
            f.getContentPane().add(nameOfSongTF);
            f.getContentPane().add(artistTF);
            f.getContentPane().add(albumTF);
            f.getContentPane().add(styleTF);
            f.getContentPane().add(timeTF);

            f.getContentPane().add(submitButton);
            f.pack();
            f.setVisible(true);
            submitButton.addActionListener(y -> {
                //отправляем запрос на сервер
                //функция обновления через row1 для hashMap
                trackID = uuids[row1];
                track = new Track(nameOfSongTF.getText(), artistTF.getText(), albumTF.getText(), Long.parseLong(timeTF.getText()), new Style(styleTF.getText()));
                String toSend = "3";
                toSend += "@" + track.getTrackId().toString() + "@" + FileController.TrackToJSON(track);
                try {
                    Connection.send(dataOutputStream, toSend);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                //table.re
                f.dispose();
            });
        });
    }

    public void runView(HashMap<String, LinkedHashMap<String, LinkedHashMap<String, Object>>> gettingHash) {
        // Создание окна
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Определить панель содержания
        createPanelUI(gettingHash);

        // Показать окно
        this.pack();
        this.setVisible(true);
    }
}