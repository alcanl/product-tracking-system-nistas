package com.alcanl.app;

import com.alcanl.app.application.gui.MainForm;
import com.alcanl.app.global.PreLaunchBuilder;

import javax.swing.*;

class Application {
    public static void run(String[] args)
    {
        new PreLaunchBuilder().copyFiles().setDbDirectory();
        SwingUtilities.invokeLater(MainForm::new);
    }
}
