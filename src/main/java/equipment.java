import javax.swing.*;

public class equipment extends JFrame{

    private JPanel equipment;
    private JComboBox typeComboBox;
    private JLabel amountLeftLabel;
    private JLabel timeLeftLabel;

    public equipment(String title){
        super(title);
        setContentPane(equipment);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720,420);
        setLocationRelativeTo(null);
        setVisible(true);

        //Adding the options to typeComboBox
        typeComboBox.addItem("Any type");
        typeComboBox.addItem("Equipment");
        typeComboBox.addItem("Feed");
        typeComboBox.addItem("Medicines");
    }

    public static void main(String[] args){
        JFrame equipment = new JFrame("Equipment, Medicines & Feed page");
    }
}
