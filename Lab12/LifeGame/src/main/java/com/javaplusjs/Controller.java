package com.javaplusjs;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Controller extends JDialog {

    private List algorithmsList;

    public Controller() throws IOException {
        start();
    }

    private void start() {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            engine.eval(new FileReader("/home/barankevycha/Desktop/Pwr_sem6/Java/myJavaProjects/LifeGame/jsLogic/game-life.js"));
            GameOfLife automate = new GameOfLife(readMapa(), engine);
            setVisible(false);
            JDialog controller = new MainWindow(this, automate);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Problem while running automate\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }


    private int[][] readMapa() throws IOException {
        FileReader fileReader = new FileReader("/home/barankevycha/Desktop/Pwr_sem6/Java/myJavaProjects/LifeGame/initGamefile/mapa.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        int size = 28;
        int[][] result = new int[size][size];
        int i = 0;
        while (line != null) {
            int j = 0;
            String[] elements = line.split(" ");
            for (String element : elements) {
                result[i][j] = Integer.parseInt(element);
                j++;
                if (j == size) {
                    break;
                }
            }
            i++;
            line = bufferedReader.readLine();

            if (i == size) {
                break;
            }
        }
        return result;
    }



    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
    }

}
