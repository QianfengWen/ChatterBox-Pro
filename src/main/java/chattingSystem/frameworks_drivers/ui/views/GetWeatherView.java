package chattingSystem.frameworks_drivers.ui.views;

import chattingSystem.interface_adapter.controllers.GetWeatherController;
import chattingSystem.interface_adapter.state.GetWeatherState;
import chattingSystem.interface_adapter.state.SignupState;
import chattingSystem.interface_adapter.view_models.GetWeatherViewManagerModel;
import chattingSystem.interface_adapter.view_models.GetWeatherViewModel;
import chattingSystem.interface_adapter.view_models.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GetWeatherView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Get Weather";
    private JTextField cityTextField = new JTextField();
    private JButton getWeatherBotton = new JButton("Get Weather");
    private JTextArea textDisplayLabel = new JTextArea();
    public GetWeatherViewModel getWeatherViewModel;
    private final GetWeatherViewManagerModel getWeatherViewManagerModel;
    public GetWeatherController getWeatherController;
    private Image backgroundImage;

    public GetWeatherView(GetWeatherController getWeatherController, GetWeatherViewModel getWeatherViewModel,
                          GetWeatherViewManagerModel getWeatherViewManagerModel,
                          GetWeatherViewManager getWeatherViewManager) {
        this.getWeatherController = getWeatherController;
        this.getWeatherViewModel = getWeatherViewModel;
        this.getWeatherViewManagerModel = getWeatherViewManagerModel;
        getWeatherViewManager.addView(this, viewName);
        getWeatherViewModel.addPropertyChangeListener(this);

        // get the address of the background image
        backgroundImage = new ImageIcon("src/main/java/chattingSystem/frameworks_drivers/ui/views/weatherimage.png").getImage();
        setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Weather Report");
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

        // Enter City Label
        JLabel enterCityLabel = new JLabel("Enter City: ");
        enterCityLabel.setFont(new Font("Arial", Font.BOLD, 15));
        GridBagConstraints enterCityLabelConstraints = new GridBagConstraints();
        enterCityLabelConstraints.gridx = 0;
        enterCityLabelConstraints.gridy = 1;
        enterCityLabelConstraints.insets = new Insets(0, 10, 5, 5);
        add(enterCityLabel, enterCityLabelConstraints);

        // City TextField
        GridBagConstraints cityTextFieldConstraints = new GridBagConstraints();
        cityTextFieldConstraints.gridx = 1;
        cityTextFieldConstraints.gridy = 1;
        cityTextFieldConstraints.weightx = 1.0;
        cityTextFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        cityTextFieldConstraints.insets = new Insets(0, 0, 5, 10);
        add(cityTextField, cityTextFieldConstraints);

        // Get Weather Button
        GridBagConstraints getWeatherButtonConstraints = new GridBagConstraints();
        getWeatherBotton.setBackground(new Color(30, 144, 255));
        getWeatherBotton.setForeground(Color.WHITE);
        getWeatherBotton.setFocusPainted(false);
        getWeatherButtonConstraints.gridx = 0;
        getWeatherButtonConstraints.gridy = 2;
        getWeatherButtonConstraints.gridwidth = 2;
        getWeatherButtonConstraints.insets = new Insets(10, 0, 10, 0);
        getWeatherButtonConstraints.anchor = GridBagConstraints.CENTER;
        getWeatherBotton.setOpaque(true);
        getWeatherBotton.setBorderPainted(false);
        add(getWeatherBotton, getWeatherButtonConstraints);

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

        // Action listener for the Get Weather Button
        cityTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        GetWeatherState currentState = getWeatherViewModel.getState();
                        String cityLocation = cityTextField.getText() + e.getKeyChar();
                        currentState.setCityLocation(cityLocation);
                        getWeatherViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        getWeatherBotton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(getWeatherBotton)) {
                            GetWeatherState oldState = getWeatherViewModel.getState();
                            getWeatherController.execute(
                                    oldState.getCityLocation()
                            );
                            GetWeatherState currentState = getWeatherViewModel.getState();
                            String weatherInfoDisplay = currentState.getWeatherInfoDisplay();
                            textDisplayLabel.setText(weatherInfoDisplay);
                            cityTextField.setText("");
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
        GetWeatherState state = (GetWeatherState) evt.getNewValue();
        if (state.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
            state.setErrorMessage(null);
        }else{
                // Clear the input fields
            cityTextField.setText("");
            GetWeatherState currentState = getWeatherViewModel.getState();
            currentState.setCityLocation("");
            currentState.setWeatherInfoDisplay(null);
            getWeatherViewModel.setState(currentState);
            }
        }
}

