package com.shpp.cs.assignments.namesurfer;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {
    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        StringTokenizer st = new StringTokenizer(line," ");
        this.name = st.nextToken();
        for(int decade = 0; decade < 12; decade++) {
            this.ranksTable[decade] = Integer.parseInt(st.nextToken());
        }
    }
    /** Name value from current entry */
    private String name;
    /** Ranks array from current entry */
    private int[] ranksTable = new int[12];
    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return ranksTable[decade];
    }
    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    @Override
    public String toString() {
        String out = name + " [";
        for(int i = 0; i < (ranksTable.length - 1) ; i++){
            out += (ranksTable[i] + " ");
        }
        out += ranksTable[(ranksTable.length - 1)] + "]";
        return out;
    }
}

