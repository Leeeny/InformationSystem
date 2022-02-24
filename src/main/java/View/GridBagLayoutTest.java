package View;

import javax.swing.*;
import java.awt.*;

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
        constraints.gridwidth = 2;// нулевая ячейка таблицы по горизонтали
        container.add(changeButton, constraints);

        deleteButton = new JButton("Delete track");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;// первая ячейка таблицы по горизонтали
        container.add(deleteButton, constraints);

        addButton = new JButton("Add track");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;// вторая ячейка таблицы по горизонтали
        container.add(addButton, constraints);

        String[] columnNames = {
                "Name",
                "Artist",
                "Album",
                "Time"
        };

        String[][] data = {
                {"song1", "artist1", "album1", "100"},
                {"song2", "artist2", "album2", "110"},
                {"song3", "artist3", "album3", "120"},
                {"song4", "artist4", "album4", "130"},
                {"song5", "artist5", "album5", "135"},
                {"song6", "artist6", "album6", "100"},
                {"song7", "artist7", "album7", "125"},
                {"song8", "artist8", "album8", "200"},
                {"song9", "artist9", "album9", "300"},
        };

        table = new JTable(data, columnNames);
        constraints.ipady = 2;   // кнопка высокая
        constraints.weightx = 0.5;
        constraints.gridwidth = 2;    // размер кнопки в две ячейки
        constraints.gridx = 0;    // нулевая ячейка по горизонтали
        constraints.gridy = 0;    // первая ячейка по вертикали

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
            JFrame frame = new JFrame();
            frame.setSize(300, 300);
            frame.setVisible(true);
            JTextField input = new JTextField(4);
        });

        deleteButton.addActionListener(e -> {
            JFrame frame = new JFrame();
            frame.setSize(300, 300);
            frame.setVisible(true);
            JTextField input = new JTextField(4);
        });

        addButton.addActionListener(e -> {
            JFrame frame = new JFrame();
            frame.setSize(300, 300);
            frame.setVisible(true);
            JTextField input = new JTextField(4);
        });
    }

    public static void main(String[] args) {
        // Создание окна
        JFrame frame = new JFrame("GridBagLayoutTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Определить панель содержания
        createPanelUI(frame.getContentPane());

        // Показать окно
        frame.pack();
        frame.setVisible(true);
    }
}