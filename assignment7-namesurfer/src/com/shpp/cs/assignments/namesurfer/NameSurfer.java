package com.shpp.cs.assignments.namesurfer;

/*
 * File: NameSurfer.java - version 2 - JtextField handler update
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {
    /** Main base of data from file */
    private NameSurferDataBase myBase;
    /** Main input text field  */
    private JTextField text;
    /** Main display process */
    private NameSurferGraph graph;

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        initiateEventSources();
        initiateNameBase();
        initiateGraphsWindow();
    }

    /** Build main display window of program */
    private void initiateGraphsWindow() {
        graph = new NameSurferGraph();
        add(graph);
    }

    /** Get whole data from file to internal program's base */
    private void initiateNameBase() {
        String dataFile = NameSurferConstants.NAMES_DATA_FILE;
        myBase = new NameSurferDataBase(dataFile);
    }

    /** Prepare user interface of programm */
    private void initiateEventSources() {
        JLabel name = new JLabel("Name: ");
        add(name,NORTH);
        /** Main input text field */
        text = new JTextField(20);
        add(text, NORTH);
        /** Such block enables to input text due to enter key pressing
         * It will start drawing of input graph */
        text.addActionListener(this);
        /** Button to clear window from builded graphs */
        JButton clearButton = new JButton("Clear");
        add(clearButton, NORTH);
        /**  Start drawing of input graph */
        JButton graphButton = new JButton("Graph");
        add(graphButton, NORTH);
        /** Connects this event sources with handlers */
        addActionListeners();
    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        /** Handler of Clear button
         * Removes all graphs from programm window */
        if (e.getActionCommand().equals("Clear")) {
            graph.clear();
            /** Handler of Graph button
             * Lunch current name's graph building */
        } else if (e.getActionCommand().equals("Graph")) {
            textFieldProcess();
        } else {
            textFieldProcess();
        }

    }

    /** Graph button main handler method
     * Adds graph for input name into window
     * or does nothing if entered value is fail */
    private void textFieldProcess() {
        String input = text.getText();
        if(!input.equals("")) {
            NameSurferEntry currentEntry = myBase.findEntry(input);
            if (currentEntry == null) {
                text.setText("");
            } else {
                /** Adds data for entered name to NameSurferGraph internal base */
                graph.addEntry(currentEntry);
                /** Redraw all entered graphs  */
                graph.update();
                /** Clears text field from entered value */
                text.setText("");
            }
        }
    }
}
