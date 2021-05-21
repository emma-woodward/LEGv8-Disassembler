/**
 * @author Vincent Woodward (https://github.com/vincent-woodward)
 */

package com.company;

/**
 * Label class is a container class used for labels
 */
public class Label {
    /**
     * Name of label (label_x)
     */
    private String name;

    /**
     * Where the label is supposed to go in relation to the program
     */
    private int indexAt;

    /**
     * Label constructor
     *
     * @param name name of the label (label_x)
     * @param indexAt where the label is indexed at in relation to the program
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    public Label(String name, int indexAt) {
        this.name = name;
        this.indexAt = indexAt;
    }

    /**
     * Returns name of label (label_x)
     * @return String label name
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    public String getName() {
        return name;
    }

    /**
     * Returns index of the label in relation to the program
     * @return int index
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    public int getIndex(){ return indexAt; }

    /**
     * Sets the index
     * @param index
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    public void setIndex(int index){
        indexAt = index;
    }

}
