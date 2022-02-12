import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleGUI extends JFrame {

    private JButton button = new JButton("press");
    private JButton button2 = new JButton("save on disk");
    private JTextField input = new JTextField("", 5);
    private JLabel label = new JLabel("Input:");
    private JRadioButton radio1 = new JRadioButton("Change track");
    private JRadioButton radio2 = new JRadioButton("Delete track");
    private JRadioButton radio3 = new JRadioButton("Add track");


    private JCheckBox check = new JCheckBox("Check", false);

    public SimpleGUI() {
        super("Music player");
        this.setBounds(300, 300, 1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        JTable table = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);

        this.getContentPane().add(scrollPane);
        this.setPreferredSize(new Dimension(1000, 500));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    Container container = this.getContentPane();
    container.setLayout(new GridLayout(3,3, 3, 3));
    container.add(label);
    container.add(input);

    ButtonGroup group = new ButtonGroup();
    group.add(radio1);
    group.add(radio2);
    group.add(radio3);
    container.add(radio1);
    radio1.setSelected(true);
    container.add(radio2);
    radio2.setSelected(true);
    container.add(radio3);
    container.add(check);
    button.addActionListener(new ButtonEventListener());
    container.add(button);
    container.add(button2);


    }
    class ButtonEventListener<message> implements ActionListener {
        public void actionPerformed (ActionEvent e) {

        String message = "";
        message += "Button was pressed\n";
        message += "Playlist saved\n";
        message += "Text is"+input.getText()+"\n";
        message += (radio1.isSelected() ? "Radio #1" : "Radio #2") + "is selected!\n";
        message += (radio2.isSelected() ? "Radio #2" : "Radio #3") + "is selected!\n";
        message += "Checkbox is"+((check.isSelected()) ? "checked" : "unchecked");
        JOptionPane.showMessageDialog(null, message,"Output",JOptionPane.PLAIN_MESSAGE);
    }

    }
}
