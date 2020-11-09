package pathfinding.io;

import pathfinding.domain.Node;

import java.io.*;

/**
 * MapReader reads in a text form map and turns it into a Node[] array.
 */
public class MapReader {

    File file;
    Node[][] nodeArray;
    String filename;

    /**
     * MapReader reads in a text form map and turns it into a Node[] array.
     * @param filename
     * The name of the file to be read.
     * @param mapSize
     * The size of the map as an integer.
     */
    public MapReader(String filename, int mapSize) {
        nodeArray = new Node[mapSize][mapSize];
        file = new File(getClass().getResource(filename).getFile());
    }

    /**
     * Reads the map from a text file and creates a Node array based on it.
     * @return Returns the map as Node[] array.
     * @throws IOException
     */
    public Node[][] createArray() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int index = 0;

        while ((st = br.readLine()) != null) {
            for (int j = 0; j < st.length(); j++) {
                if (st.charAt(j) == '.') {
                    Node newNode = new Node(index, j);
                    nodeArray[index][j] = newNode;
                }
            }

            index++;
        }

        setNeighbors();

        return nodeArray;
    }

    // this is terrible
    // map size shouldn't be hard coded

    /**
     * Sets the neighbors for all nodes. Checks the neighbors in all eight directions
     *  of a two dimensional array and adds them as the node's neighbors.
     */
    //CHECKSTYLE:OFF
    private void setNeighbors() {
        for (int i = 0; i < nodeArray.length; i++) {
            for (int j = 0; j < nodeArray.length; j++) {

                if (nodeArray[i][j] == null) {
                    continue;
                }

                Node[] neighbors = new Node[8];
                int n = 0;

                if (i - 1 >= 0 && nodeArray[i - 1][j] != null) {
                    neighbors[n] = nodeArray[i - 1][j];
                    n++;
                }

                if (i + 1 < 256 && nodeArray[i + 1][j] != null) {
                    neighbors[n] = nodeArray[i + 1][j];
                    n++;
                }

                if (j - 1 >= 0 && nodeArray[i][j - 1] != null) {
                    neighbors[n] = nodeArray[i][j - 1];
                    n++;
                }

                if (j + 1 < 256 && nodeArray[i][j + 1] != null) {
                    neighbors[n] = nodeArray[i][j + 1];
                    n++;
                }

                if (i - 1 >= 0 && j - 1 >= 0 && nodeArray[i - 1][j - 1] != null) {
                    neighbors[n] = nodeArray[i - 1][j - 1];
                    n++;
                }

                if (i - 1 >= 0  && j + 1 < 256 && nodeArray[i - 1][j + 1] != null) {
                    neighbors[n] = nodeArray[i - 1][j + 1];
                    n++;
                }

                if (i + 1 < 256 && j - 1 >= 0 && nodeArray[i + 1][j - 1] != null) {
                    neighbors[n] = nodeArray[i + 1][j - 1];
                    n++;
                }

                if (i + 1 < 256 && j + 1 < 256 && nodeArray[i + 1][j + 1] != null) {
                    neighbors[n] = nodeArray[i + 1][j + 1];
                }

                nodeArray[i][j].setNeighbors(neighbors);
            }
        }
    }
    //CHECKSTYLE:ON
}
