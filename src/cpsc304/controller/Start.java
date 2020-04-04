package cpsc304.controller;

import cpsc304.UI.StartWindow;
import cpsc304.delegates.DriverWindowDelegate;
import cpsc304.model.entities.Driver;


public class Start{

    private StartWindow startWindow = null;

    public void start() {
        startWindow = new StartWindow();

    }
}