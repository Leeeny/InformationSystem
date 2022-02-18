import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JScrollPane;

public class GridBagLayoutTest
{



    public static void createPanelUI(Container container) {
        JButton button;
        JButton button1;

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

        // По умолчанию натуральная высота, максимальная ширина
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy = 0;  // нулевая ячейка таблицы по вертикали

        input = new JTextField("", 5);
        constraints.gridwidth = 2;
        constraints.gridy = 5;      
        container.add(input, constraints);

        label = new JLabel("Input:");
        constraints.gridy = 4;      
        container.add(label, constraints);


        radio1 = new JRadioButton("Change track");
        constraints.gridx = 0;
        constraints.gridy = 1;      
        container.add(radio1, constraints);
        //radio1.addActionListener(new ButtonEventListener());


        radio2 = new JRadioButton("Delete track");
        constraints.gridx = 0;
        constraints.gridy = 2;      
        container.add(radio2, constraints);

        radio3 = new JRadioButton("Add track");
        constraints.gridx = 0;
        constraints.gridy = 3;     
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


        button1 = new JButton("Press");
        //constraints.ipady     = 0;    // установить первоначальный размер кнопки
        //constraints.weighty   = 1.0;  // установить отступ
        // установить кнопку в конец окна
        //constraints.anchor    = GridBagConstraints.PAGE_END;
        //constraints.insets    = new Insets(5, 5, 5, 5);  // граница ячейки по Y
        constraints.gridwidth = 1;    // размер кнопки в 2 ячейки
        constraints.gridx = 0;    // первая ячейка таблицы по горизонтали
        constraints.gridy = 6;    //  ячейка по вертикали
        container.add(button1, constraints);

        button= new JButton("Save playlist");
        //constraints.ipady     = 0;    
        //constraints.weighty   = 1.0;  
        // установить кнопку в конец окна
        //constraints.anchor    = GridBagConstraints.PAGE_END;
        //constraints.insets    = new Insets(5, 0, 0, 0);  // граница ячейки по Y
        //constraints.gridwidth = 2;    // размер кнопки в 2 ячейки
        constraints.gridx = 1;    
        constraints.gridy = 6;    
        container.add(button, constraints);

        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "";
                message += "Button was pressed\n";

                JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
            }
        });
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "";
                message += "Playlist saved\n";

                JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
            }
        });
        radio1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "";
                message += "Track changed\n";

                JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
            }
        });
        radio2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "";
                message += "Track deleted\n";

                JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
            }
        });
        radio3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "";
                message += "Track added\n";

                JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
            }
        });
        input.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "";
                message += "track is "+input.getText()+"\n";

                JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
            }
        });


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


