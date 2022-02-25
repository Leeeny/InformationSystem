package View;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class Main extends JFrame {

    private JTable m_simpleTable;
    private SimpleTableModel m_simpleTableModel;


    class SimpleTableModel extends AbstractTableModel {
        public String[] m_colNames = {
                "Name",
                "Artist",
                "Album",
                "Time"
        };

        public Class[] m_colTypes = {
                String.class, String.class, String.class, Long.class
        };

        Vector<Data> m_macDataVector;

        public SimpleTableModel(Vector<Data> macDataVector) {
            super();
            m_macDataVector = macDataVector;
        }

        public int getColumnCount() {
            return m_colNames.length;
        }

        public int getRowCount() {
            return m_macDataVector.size();
        }

        public void setValueAt(Object value, int row, int col) {
            Data macData = (m_macDataVector.elementAt(row));
            fireTableCellUpdated(row, col);
            switch (col) {
                case 0 -> macData.setName((String) value);
                case 1 -> macData.setArtist((String) value);
                case 2 -> macData.setAlbum((String) value);
                case 3 -> macData.setTime((Long) value);
            }
        }

        public String getColumnName(int col) {
            return m_colNames[col];
        }

        public Class getColumnClass(int col) {
            return m_colTypes[col];
        }

        public boolean isCellEditable(int row, int col) {
            return true;
        }

        public Object getValueAt(int row, int col) {
            Data macData = (m_macDataVector.elementAt(row));

            return switch (col) {
                case 0 -> macData.getName();
                case 1 -> macData.getArtist();
                case 2 -> macData.getAlbum();
                case 3 -> macData.getTime();
                default -> "";
            };
        }
    }

    class Data {

        private String name;

        private String artist;

        private String album;

        private Long time;

        public Data() {
        }

        public Data(String name, String artist, String album, Long time) {
            this.name = name;
            this.artist = artist;
            this.album = album;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }

        public Long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }
    }

    public Main() {
        Vector<Data> dummyMacData = new Vector<>(10, 10);
        dummyMacData.addElement(new Data("Do I Wanna Know?", "Arctic Monkeys", "Album", 1023L));
        dummyMacData.addElement(new Data("Evil Twin", "Arctic Monkeys", "Album", 348L));
        dummyMacData.addElement(new Data("фдфдф", "выввав", "Album", 328L));
        dummyMacData.addElement(new Data("Evil Twin", "Arctic Monkeys", "Album", 10L));
        dummyMacData.addElement(new Data("Heat Waves", "Glass Animals", "Album", 1024L));

        m_simpleTableModel = new SimpleTableModel(dummyMacData);
        m_simpleTable = new JTable(m_simpleTableModel);
        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(m_simpleTable);
        JButton submitButton = new JButton("Submit");
        JButton deleteButton = new JButton("Delete");

        getContentPane().add(scrollPane);
        getContentPane().add(submitButton);
        getContentPane().add(deleteButton);
        submitButton.addActionListener(e -> {
            System.out.println(Arrays.toString(m_simpleTable.getSelectedRows()));
        });

        deleteButton.addActionListener(e -> {

        });
    }

    public static void main(String[] arg) {
        Main m = new Main();

        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setVisible(true);
        m.setSize(new Dimension(600, 300));
        m.validate();
    }
}