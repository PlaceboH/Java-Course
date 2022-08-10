package com.javaplusjs;

import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class MainWindow extends JDialog{
    private static final int CELL_SIZE = 25;
    private static final int MARGIN = 0;
    private static final Color[] COLORS = {Color.green, Color.black};

    private final GameOfLife gameOfLife;
    private JPanel[][] cells;
    private final JPanel rootPanel;
    private final Timer timer;
    private final JDialog parent;

    public MainWindow(JDialog parent, GameOfLife gameOfLife) {
        super();
        this.parent = parent;
        this.gameOfLife = gameOfLife;

        pack();
        setSize(calcSize());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        timer = new Timer(400, e -> step());

        rootPanel = new JPanel(null);
        add(rootPanel);
        menu();
        initCells();

        setModal(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                parent.setVisible(true);
            }
        });
    }

    private Dimension calcSize() {
        int size = gameOfLife.getSize() * CELL_SIZE +  (gameOfLife.getSize() - 1) + 14;
        return new Dimension(size, size);
    }

    private void initCells() {
        cells = new JPanel[gameOfLife.getSize()][gameOfLife.getSize()];
        for (int i = 0; i < gameOfLife.getSize(); i++) {
            for (int j = 0; j < gameOfLife.getSize(); j++) {

                JPanel panel = new JPanel();
                panel.setSize(CELL_SIZE, CELL_SIZE);
                panel.setLocation(j * CELL_SIZE , i * CELL_SIZE);
                panel.setBackground(COLORS[gameOfLife.getState()[i][j]]);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                rootPanel.add(panel);
                cells[i][j] = panel;
            }
        }
        updateSate();
    }

    private void updateSate() {
        for (int i = 0; i < gameOfLife.getSize(); i++) {
            for (int j = 0; j < gameOfLife.getSize(); j++) {
                cells[i][j].setBackground(COLORS[gameOfLife.getState()[i][j]]);
            }
        }
    }

    private void menu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem run = new JMenuItem("Run");
        JMenuItem stop = new JMenuItem("Stop");
        JMenuItem step = new JMenuItem("Step");

        run.addActionListener(e -> timer.start());
        stop.addActionListener(e -> timer.stop());
        step.addActionListener(e -> step());

        menu.add(run);
        menu.add(stop);
        menu.add(step);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void step() {
        try {
            gameOfLife.runStep();
        } catch (ScriptException | NoSuchMethodException ex) {
            JOptionPane.showMessageDialog(this, "Error while running step\n" + ex.getMessage());
            System.exit(-1);
        }
        updateSate();
    }

}
