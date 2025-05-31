package com.pmcguire.cmsc335.trafficsim.gui;

import com.pmcguire.cmsc335.trafficsim.Simulator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar
{

    private final Simulator simulator;

    public MenuBar(Simulator sim) {
        simulator = sim;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        return menuBar;
    }

    public JMenu createFileMenu() {
        // Setup File menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.getAccessibleContext().setAccessibleDescription("File");

        // Add File > New submenu
        fileMenu.add(createFileNewMenu());

        // Add File > Exit
        JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_X);
        exit.addActionListener(actionEvent -> System.exit(0));
        fileMenu.add(exit);

        return fileMenu;
    }

    public JMenu createFileNewMenu() {
        // Add  File > New  submenu
        JMenu submenu = new JMenu("New");

        // New Car
        JMenuItem menuItem = new JMenuItem("Car",
                KeyEvent.VK_N);
        menuItem.addActionListener(actionEvent -> simulator.addCar());
        submenu.add(menuItem);

        // New Intersection
        menuItem = new JMenuItem("Intersection");
        menuItem.addActionListener(actionEvent -> simulator.addIntersection());
        submenu.add(menuItem);


        return submenu;
    }


}
