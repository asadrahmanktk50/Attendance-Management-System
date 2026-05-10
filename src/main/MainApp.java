package main;

import util.DatabaseInitializer;
import ui.LoginFrame;

public class MainApp {

    public static void main(String[] args) {

        DatabaseInitializer.initialize();

        new LoginFrame();
    }
}