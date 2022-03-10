import javax.swing.*;

public class Equipment extends JFrame{

    private JPanel equipment;
    private JComboBox typeComboBox;
    private JLabel amountLeftLabel;
    private JLabel timeLeftLabel;

    public Equipment(String title){
        super(title);
        setContentPane(equipment);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
