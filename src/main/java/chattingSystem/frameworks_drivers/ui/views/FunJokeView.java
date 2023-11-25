package chattingSystem.frameworks_drivers.ui.views;

import chattingSystem.interface_adapter.controllers.FunJokeController;
import chattingSystem.interface_adapter.state.FunJokeState;
import chattingSystem.interface_adapter.view_models.FunJokeViewManagerModel;
import chattingSystem.interface_adapter.view_models.FunJokeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FunJokeView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Have funny Joke";

    private JTextField cityTextField = new JTextField();

    private JButton funJokeBotton = new JButton("Get a funny joke");

    private JTextArea textDisplayLabel = new JTextArea();

    public FunJokeViewModel funJokeViewModel;

    private final FunJokeViewManagerModel funJokeViewManagerModel;

    public FunJokeController funJokeController;

    private Image backgroundImage;


    public FunJokeView(FunJokeController funJokeController, FunJokeViewModel funJokeViewModel,
                       FunJokeViewManagerModel funJokeViewManagerModel,
                       FunJokeViewManager funJokeViewManager) {
        this.funJokeController = funJokeController;
        this.funJokeViewModel = funJokeViewModel;
        this.funJokeViewManagerModel = funJokeViewManagerModel;
        funJokeViewManager.addView(this, viewName);
        funJokeViewModel.addPropertyChangeListener(this);

        backgroundImage = new ImageIcon("src/main/java/chattingSystem/frameworks_drivers/ui/views/joke.png").getImage();
        setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Funny Jokes");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setForeground(new Color(218, 165, 32));
        titleLabel.setBackground(new Color(220, 220, 220));
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.gridwidth = 2;
        titleConstraints.insets = new Insets(10, 0, 10, 0);
        titleConstraints.anchor = GridBagConstraints.CENTER;
        add(titleLabel, titleConstraints);

        // Get joke guide
        JLabel enterCityLabel = new JLabel("   Guide: Click button to get a joke to spice up chat :D");
        enterCityLabel.setFont(new Font("Arial", Font.BOLD, 15));
        GridBagConstraints enterCityLabelConstraints = new GridBagConstraints();
        enterCityLabelConstraints.gridx = 0;
        enterCityLabelConstraints.gridy = 1;
        enterCityLabelConstraints.insets = new Insets(0, 10, 5, 5);
        add(enterCityLabel, enterCityLabelConstraints);


        // Get Joke Button
        GridBagConstraints funJokeButtonConstraints = new GridBagConstraints();
        funJokeBotton.setBackground(new Color(30, 144, 255));
        funJokeBotton.setForeground(Color.WHITE);
        funJokeBotton.setFocusPainted(false);
        funJokeButtonConstraints.gridx = 0;
        funJokeButtonConstraints.gridy = 2;
        funJokeButtonConstraints.gridwidth = 2;
        funJokeButtonConstraints.insets = new Insets(10, 0, 10, 0);
        funJokeButtonConstraints.anchor = GridBagConstraints.CENTER;
        add(funJokeBotton, funJokeButtonConstraints);

        // Text Display Label (Large JLabel with the shape of a box)
        textDisplayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textDisplayLabel.setLineWrap(true);
        textDisplayLabel.setWrapStyleWord(true);
        textDisplayLabel.setEditable(false);
        GridBagConstraints textDisplayLabelConstraints = new GridBagConstraints();
        textDisplayLabelConstraints.gridx = 0;
        textDisplayLabelConstraints.gridy = 3;
        textDisplayLabelConstraints.gridwidth = 2;
        textDisplayLabelConstraints.weightx = 0.5;
        textDisplayLabelConstraints.weighty = 0.5;
        textDisplayLabelConstraints.fill = GridBagConstraints.BOTH;
        textDisplayLabelConstraints.insets = new Insets(0, 80, 50, 80);
        JScrollPane scrollPane = new JScrollPane(textDisplayLabel);
        add(scrollPane, textDisplayLabelConstraints);


        funJokeBotton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(funJokeBotton)) {
                            FunJokeState state = funJokeViewModel.getState();
                            funJokeController.execute();
                            FunJokeState currentState = funJokeViewModel.getState();
                            String JokeInfoDisplay = currentState.getJokeInfoDisplay();
                            textDisplayLabel.setText(JokeInfoDisplay);
                        }
                    }
                }
        );

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Paint the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        FunJokeState state = (FunJokeState) evt.getNewValue();
        if (state.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
            state.setErrorMessage(null);
        }else{
            // Clear the input fields
            cityTextField.setText("");
            FunJokeState currentState = funJokeViewModel.getState();
            currentState.setJokeInfoDisplay(null);
            funJokeViewModel.setState(currentState);
        }
    }
}


