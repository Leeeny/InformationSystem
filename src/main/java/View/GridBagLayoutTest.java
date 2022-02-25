package View;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class GridBagLayoutTest {
    public static void createPanelUI(Container container) {
        JButton saveButton;
        JTable table;
        JButton changeButton;
        JButton deleteButton;
        JButton addButton;

        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        container.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy = 0;  // нулевая ячейка таблицы по вертикали

        changeButton = new JButton("Change track");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2; // нулевая ячейка таблицы по горизонтали
        container.add(changeButton, constraints);

        deleteButton = new JButton("Delete track");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // первая ячейка таблицы по горизонтали
        container.add(deleteButton, constraints);

        addButton = new JButton("Add track");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2; // вторая ячейка таблицы по горизонтали
        container.add(addButton, constraints);

        Vector<String> columnNames = new Vector<>();
        columnNames.add("Name");
        columnNames.add("Artist");
        columnNames.add("Album");
        columnNames.add("Time");

        Vector<Vector<String>> info = new Vector<Vector<String>>();
        info.add(new Vector<String>(List.of(new String[]{"song1", "artist1", "album1", "100"})));
        info.add(new Vector<String>(List.of(new String[]{"song2", "artist2", "album2", "200"})));
        info.add(new Vector<String>(List.of(new String[]{"song3", "artist3", "album3", "300"})));
        info.add(new Vector<String>(List.of(new String[]{"song4", "artist4", "album4", "400"})));
        info.add(new Vector<String>(List.of(new String[]{"song5", "artist5", "album5", "500"})));
        info.add(new Vector<String>(List.of(new String[]{"song6", "artist6", "album6", "600"})));


        table = new JTable(info, columnNames);


        constraints.ipady = 2; // кнопка высокая
        constraints.weightx = 0.5;
        constraints.gridwidth = 2; // размер кнопки в две ячейки
        constraints.gridx = 0; // нулевая ячейка по горизонтали
        constraints.gridy = 0; // первая ячейка по вертикали

        //чтобы не было возможности задавать значения таблицы

        container.add(table, constraints);
        saveButton = new JButton("Save playlist");
        constraints.gridy = 6;
        container.add(saveButton, constraints);

        saveButton.addActionListener(e -> {
            String message = "";
            message += "Playlist saved\n";
            JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
        });

        changeButton.addActionListener(e -> {
            JFrame f = new JFrame("Change track");
            f.getContentPane().setLayout(new FlowLayout());
            int row1 = table.getSelectedRows()[0];
            JTextField nameOfSongTF = new JTextField((String) table.getValueAt(row1, 0),10);
            JTextField artistTF = new JTextField((String) table.getValueAt(row1, 1),10);
            JTextField albumTF = new JTextField((String) table.getValueAt(row1, 2),10);
            JTextField timeTF = new JTextField((String) table.getValueAt(row1, 3),10);
            JButton submitButton = new JButton("Submit");
            f.getContentPane().add(nameOfSongTF);
            f.getContentPane().add(artistTF);
            f.getContentPane().add(albumTF);
            f.getContentPane().add(timeTF);
            f.getContentPane().add(submitButton);
            f.pack();
            f.setVisible(true);
            submitButton.addActionListener(y -> {
                //отправлять запрос на сервер
                /*table.setValueAt(nameOfSongTF.getText(), row1, 0);
                table.setValueAt(artistTF.getText(), row1, 1);
                table.setValueAt(albumTF.getText(), row1, 2);
                table.setValueAt(timeTF.getText(), row1, 3);*/
                f.dispose();
            });
        });

        deleteButton.addActionListener(e -> {
            //отправлять запрос на сервер
        });

        addButton.addActionListener(e -> {
            JFrame f = new JFrame("Change track");
            f.getContentPane().setLayout(new FlowLayout());
            JTextField nameOfSongTF = new JTextField("Name of song",10);
            JTextField artistTF = new JTextField("Artist",10);
            JTextField timeTF = new JTextField("Time",10);
            JTextField albumTF = new JTextField("Album",10);
            JButton submitButton = new JButton("Submit");
            f.getContentPane().add(nameOfSongTF);
            f.getContentPane().add(artistTF);
            f.getContentPane().add(timeTF);
            f.getContentPane().add(albumTF);
            f.getContentPane().add(submitButton);
            f.pack();
            f.setVisible(true);
            submitButton.addActionListener(y -> {
                //отправляем запрос на сервер
                f.dispose();
            });
        });
    }

    public static void main(String[] args) {
        // Создание окна
        JFrame frame = new JFrame("Information System!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Определить панель содержания
        createPanelUI(frame.getContentPane());

        // Показать окно
        frame.pack();
        frame.setVisible(true);
    }
}