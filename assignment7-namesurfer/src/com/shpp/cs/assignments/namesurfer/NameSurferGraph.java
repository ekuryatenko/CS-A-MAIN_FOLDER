package com.shpp.cs.assignments.namesurfer;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Iterator;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    private static final String YEAR_LABEL_FONT = "Times New Roman-20";
    private static final String GRAPH_LABEL_FONT = "Book Antiqua Bold-10";
    private ArrayList<NameSurferEntry> entriesBase;
    private ArrayList<Color> graphsColors;
    private int offset_Y = NameSurferConstants.GRAPH_MARGIN_SIZE;
    private double offset_X = NameSurferConstants.GORIZONTAL_MARGIN_SIZE;
    private int avaliableDecades = NameSurferConstants.NDECADES;

    /**
     * Creates a new NameSurferGraph object that displays the data.
     * Adds all necessary items
     */
    public NameSurferGraph() {
        addComponentListener(this);
        entriesBase = new ArrayList<NameSurferEntry>();
        graphsColors = createColorsArray();
    }

    /** Make base of possible colors for graph drawing */
    private ArrayList<Color> createColorsArray() {
        ArrayList<Color> colorsArray = new ArrayList<Color>();
        colorsArray.add(Color.BLUE);
        colorsArray.add(Color.RED);
        colorsArray.add(Color.MAGENTA);
        colorsArray.add(Color.BLACK);
        return colorsArray;
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        entriesBase.clear();
        clearCanvas();
        buildGrid();
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        if(!entriesBase.contains(entry)) {
            entriesBase.add(entry);
        }
    }

    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        clearCanvas();
        buildGrid();
        buildGraphs();
    }

    /** Build supporting coordinate grid */
    private void buildGrid() {
        buildVerticalLines();
        buildHorizontalLines();
    }

    /** Builds all graphs from internal entries base */
    private void buildGraphs() {
        Iterator <NameSurferEntry> itr = entriesBase.iterator();
        while (itr.hasNext()) {
            NameSurferEntry i = itr.next();
            int index = entriesBase.indexOf(i);
            buildSingleGraph(index);
        }
    }

    /** Builds graph for current entry */
    private void buildSingleGraph(int entryIndex) {
        int avaliableDecades = NameSurferConstants.NDECADES;
        /** MAIN BUILD PROCESS */
        LabelBuilder i = new LabelBuilder(entryIndex, 0);
        for (int decade = 1; decade < avaliableDecades; decade++) {
            i = new LabelBuilder(entryIndex, decade);
            CurrentLineBuilder u = new CurrentLineBuilder (entryIndex, (decade - 1), decade);
        }
    }

    /** Remove everything from current canvas */
    private void clearCanvas() {
        this.removeAll();
    }

    /** Build vertical limits for graph drawing */
    private void buildHorizontalLines() {
        double westEdge_X = getCurrentPoint_X(0);
        double eastEdge_X = getWidth();
        double southEdge_Y = getHeight();
        double offset_Y = NameSurferConstants.GRAPH_MARGIN_SIZE;

        GLine topHorLine = new GLine(westEdge_X, offset_Y, eastEdge_X, offset_Y);
        add(topHorLine);
        GLine bottomHorLine = new GLine(westEdge_X, (southEdge_Y - offset_Y), eastEdge_X, (southEdge_Y - offset_Y));
        add(bottomHorLine);

    }

    /** Build x-axis coordinates lines  */
    private void buildVerticalLines() {
        double southEdge_Y = getHeight();
        int graphPoints = NameSurferConstants.NDECADES;

        for (int i = 0; i < graphPoints; i++) {
            double currentLine_X = getCurrentPoint_X(i);

            add(new GLine(currentLine_X, 0, currentLine_X, southEdge_Y));
            buildLinesLabels(currentLine_X, i);
        }
    }

    /** Signs x-axis coordinates */
    private void buildLinesLabels(double currentLine_x, int decade) {
        double southEdge_Y = getHeight();
        int startOfDisplay = NameSurferConstants.START_DECADE;
        int year = startOfDisplay + decade * 10;
        double offset_X = 2;
        double offset_Y = 4;

        GLabel lineDate = new GLabel(String.valueOf(year), (currentLine_x + offset_X), (southEdge_Y - offset_Y));
        lineDate.setFont(YEAR_LABEL_FONT);
        add(lineDate);
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {

    }

    /** Main listener - redraws window if program panel is resized  */
    public void componentResized(ComponentEvent e) {
        clearCanvas();
        update();
    }

    public void componentShown(ComponentEvent e) {

    }

    /** Gives current graphs position */
    public double getCurrentPoint_X(int decade){
        double start_X = (double)(offset_X);
        double graphWidth = getWidth() - (double) (2 * offset_X);
        double pointsStep_X = graphWidth / avaliableDecades;
        return start_X + pointsStep_X * decade;
    }

    /** Gives current graphs position */
    public double getCurrentPoint_Y(int rank){
        double buildRegionHeight = getHeight() - (double) 2 * offset_Y;
        double y;
        if (rank > 0) {
            /** 1-rank is the highest in build region */
            int maxRank = NameSurferConstants.MAX_RANK;
            y = (buildRegionHeight * (rank - 1) / maxRank) + offset_Y;
        }else{
            y = getHeight() - (double) offset_Y;
        }
        return y;
    }

    /** Chooses color for current graph's parts */
    public Color getEntryColor(int entryIndex){
        int colorsNumber = graphsColors.size();
        int thisColor = entryIndex % colorsNumber;
        return graphsColors.get(thisColor);
    }

    /** Provides graphs labels building */
    private class LabelBuilder {
        /** From vertical line to label */
        private double lineOffset = 2;

        public LabelBuilder(int entryIndex, int decade) {
            Color currentColor = getEntryColor(entryIndex);

            /** Get entry data for current entryIndex */
            NameSurferEntry currentEntry = entriesBase.get(entryIndex);
            String name = currentEntry.getName();

            /** Prepares data for current label building  */
            int rank = currentEntry.getRank(decade);
            GLabel graphPointLabel = buildNewLabel(currentColor);

            double label_X = getLabel_X(decade);
            double label_Y = getLabel_Y(rank, graphPointLabel);
            String text = getLabelText(rank, name);
            /** Build current label */
            drawLabel(graphPointLabel, text, label_X, label_Y);
        }

        private void drawLabel(GLabel graphPointLabel, String text, double label_x, double label_y) {
            graphPointLabel.setLabel(text);
            graphPointLabel.setLocation(label_x, label_y);
            add(graphPointLabel);
        }

        /** If current rank is 0 - then *-symbol is added */
        private String getLabelText(int rank, String name) {
            String text;
            if (rank > 0) {
                text = name + " " + rank;
            }else{
                text = name + " *";
            }
            return text;
        }

        private GLabel buildNewLabel(Color currentColor) {
            GLabel i = new GLabel("");
            i.setFont(GRAPH_LABEL_FONT);
            i.setColor(currentColor);
            return i;
        }

        /** Provide label's position  */
        private double getLabel_Y(int rank, GLabel label) {
            double minLabel_Y = (double) offset_Y + label.getAscent();
            double maxLabel_Y = getHeight()- (double) offset_Y - label.getDescent();
            double label_Y = getCurrentPoint_Y(rank);
            if (rank > 0) {
                if(getCurrentPoint_Y(rank) > maxLabel_Y){
                    label_Y = maxLabel_Y;
                }else if(getCurrentPoint_Y(rank) < minLabel_Y){
                    label_Y = minLabel_Y;
                }
            }else{
                label_Y = getHeight() - (double) offset_Y - label.getDescent();
            }
            return label_Y;
        }

        /** Provide label's position  */
        private double getLabel_X(int decade) {
            return (getCurrentPoint_X(decade) + lineOffset);
        }
    }

    /** Provide graphs lines building */
    private class CurrentLineBuilder{
        public CurrentLineBuilder(int entryIndex, int lastDecade, int currentDecade){
            NameSurferEntry entry = entriesBase.get(entryIndex);

            double x1 = getCurrentPoint_X(lastDecade);
            double x2 = getCurrentPoint_X(currentDecade);
            double y1 = getCurrentPoint_Y(entry.getRank(lastDecade));
            double y2 = getCurrentPoint_Y(entry.getRank(currentDecade));

            GLine line = new GLine(x1, y1, x2, y2);
            Color lineColor = getEntryColor(entryIndex);
            line.setColor(lineColor);
            add(line);
        }

    }
}


