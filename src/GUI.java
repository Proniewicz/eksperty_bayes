import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;

public class GUI extends JPanel
        implements ItemListener {
    private final List<String> nodeNames;
    JCheckBox chinButton;
    JCheckBox glassesButton;
    JCheckBox hairButton;
    JCheckBox teethButton;

    private Map<String, List<JCheckBox>> options = new HashMap<>();
    private Map<String, String> selectedOptions = new HashMap<>();

    StringBuffer choices;

    public GUI() {
        super(new GridLayout(2, 2));

        Bayes bayes = new Bayes();

        JPanel checkPanel = new JPanel(new GridLayout(5, 3));

        nodeNames = Arrays.asList("gitara_elektryczna", "gitara_basowa", "gitara_akustyczna", "harmonijka", "banjo", "skrzypce", "harfa", "pianino", "saksofon", "trabka", "kontrabas", "wokal", "taneczny");

        for (String s : nodeNames) {
            JCheckBox jCheckBoxYes = new JCheckBox(s + " tak");
            jCheckBoxYes.setHorizontalTextPosition(SwingConstants.LEFT);
            JCheckBox jCheckBoxNo = new JCheckBox("nie");
            jCheckBoxNo.setHorizontalTextPosition(SwingConstants.LEFT);
            ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
            checkBoxes.add(jCheckBoxYes);
            checkBoxes.add(jCheckBoxNo);
            options.put(s, checkBoxes);
            checkPanel.add(jCheckBoxYes);
            checkPanel.add(jCheckBoxNo);
        }

        JLabel label = new JLabel();


        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("");
                selectedOptions.clear();
                getCheckBoxResult();
                label.setText("<html>" + bayes.getResult(selectedOptions) + "</html>");
            }
        });

        add(checkPanel);
        add(submit);
        add(new JPanel());
        add(label);

        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Bayes music");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new GUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    public void getCheckBoxResult() {
        options.forEach((option, checkBoxes) -> putValueToMap(option, checkBoxes));

    }

    private void putValueToMap(String option, List<JCheckBox> checkBoxes) {
        if (checkBoxes.get(0).isSelected()) {
            selectedOptions.put(option, "tak");
        } else if (checkBoxes.get(1).isSelected()) {
            selectedOptions.put(option, "nie");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}