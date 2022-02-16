import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JScrollPane;

public class GridBagLayoutTest
{

    public static void createPanelUI(Container container) {
        JButton button;
        JTable table;
        JScrollPane scrollPane;
        JRadioButton radio1;
        JRadioButton radio2;
        JRadioButton radio3;
        JTextField input;
        JLabel label;


        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

       
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy = 0;  // нулевая ячейка таблицы по вертикали

        input = new JTextField("", 5);
        constraints.gridwidth = 2;
        constraints.gridy = 5;      // нулевая ячейка таблицы по горизонтали
        container.add(input, constraints);

        label = new JLabel("Input:");
        constraints.gridy = 4;      // нулевая ячейка таблицы по горизонтали
        container.add(label, constraints);


        radio1 = new JRadioButton("Change track");
        constraints.gridx = 0;
        constraints.gridy = 1;      // нулевая ячейка таблицы по горизонтали
        container.add(radio1, constraints);

        radio2 = new JRadioButton("Delete track");
        constraints.gridx = 0;
        constraints.gridy = 2;      // первая ячейка таблицы по горизонтали
        container.add(radio2, constraints);

        radio3 = new JRadioButton("Add track");
        constraints.gridx = 0;
        constraints.gridy = 3;      // вторая ячейка таблицы по горизонтали
        container.add(radio3, constraints);

      
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
        constraints.weightx   = 0.5;
        constraints.gridwidth = 2;    // размер кнопки в две ячейки
        constraints.gridx = 0;    // нулевая ячейка по горизонтали
        constraints.gridy = 0;    // первая ячейка по вертикали

        container.add(table, constraints);

        //scrollPane = new JScrollPane(table);

        //container.add(scrollPane, constraints);
        //container.add(new JScrollPane(table));


        button = new JButton("Press");
        
        constraints.gridwidth = 1;    // размер кнопки в 2 ячейки
        constraints.gridx = 0;    // первая ячейка таблицы по горизонтали
        constraints.gridy = 6;    // вторая ячейка по вертикали
        container.add(button, constraints);

        button = new JButton("Save playlist");
        
        constraints.gridx = 1;    // первая ячейка таблицы по горизонтали
        constraints.gridy = 6;    // вторая ячейка по вертикали
        container.add(button, constraints);

    }

    public static void main(String[] args)
    {
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
