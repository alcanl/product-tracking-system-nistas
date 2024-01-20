package com.alcanl.app;

import com.alcanl.app.application.gui.MainForm;

import javax.swing.*;

class Application {
    public static void run(String[] args)
    {
        SwingUtilities.invokeLater(MainForm::new);
    }
}
