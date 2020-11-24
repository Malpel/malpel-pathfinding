package pathfinding.io;

import pathfinding.domain.Node;
import pathfinding.domain.NodeMap;
import pathfinding.domain.List;

import java.io.*;


/**
 * MapReader reads in a text form map and turns it into a Node[] array.
 */
public class MapReader {

    File file;
    NodeMap nodeMap;
    String filename;
    int mapSize;

    /**
     * MapReader reads in a text form map and turns it into a Node[] array.
     * @param filename
     * The name of the file to be read.
     * @param mapSize
     * The size of the map as an integer.
     */
    public MapReader(String filename, int mapSize) {
        nodeMap = new NodeMap(mapSize, mapSize);
        file = new File(getClass().getResource(filename).getFile());
        this.mapSize = mapSize;
    }

    /**
     * Reads the map from a text file and creates a Node array based on it.
     * @return Returns the map as Node[] array.
     * @throws IOException
     */
    public NodeMap createNodeMap() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int index = 0;

        while ((st = br.readLine()) != null) {
            for (int j = 0; j < st.length(); j++) {
                if (st.charAt(j) == '.') {
                    Node newNode = new Node(index, j);
                    nodeMap.addNode(index, j, newNode);
                }
            }

            index++;
        }

        setNeighbors();

        return nodeMap;
    }

    /**
     * Sets the neighbors for all nodes. Checks the neighbors in all eight directions
     *  of a two dimensional array and adds them as the node's neighbors.
     */
    //CHECKSTYLE:OFF
    private void setNeighbors() {
        for (int i = 0; i < nodeMap.getHeight(); i++) {
            for (int j = 0; j < nodeMap.getWidth(); j++) {

                if (nodeMap.getNode(i, j) == null) {
                    continue;
                }

                List neighbors = new List();

                if (nodeMap.isAccessible(i - 1, j)) {
                    neighbors.add(nodeMap.getNode(i - 1, j));
                }

                if (nodeMap.isAccessible(i + 1, j)) {
                    neighbors.add(nodeMap.getNode(i + 1, j));
                }

                if (nodeMap.isAccessible(i, j - 1)) {
                    neighbors.add(nodeMap.getNode(i, j - 1));
                }

                if (nodeMap.isAccessible(i, j + 1)) {
                    neighbors.add(nodeMap.getNode(i, j + 1));
                }

                if (nodeMap.isAccessible(i - 1, j - 1)) {
                    neighbors.add(nodeMap.getNode(i - 1, j - 1));
                }

                if (nodeMap.isAccessible(i - 1, j + 1)) {
                    neighbors.add(nodeMap.getNode(i - 1, j + 1));
                }

                if (nodeMap.isAccessible(i + 1, j - 1)) {
                    neighbors.add(nodeMap.getNode(i + 1, j - 1));
                }

                if (nodeMap.isAccessible(i + 1, j + 1)) {
                    neighbors.add(nodeMap.getNode(i + 1, j + 1));
                }

                nodeMap.getNode(i, j).setNeighbors(neighbors);
            }
        }
    }
    //CHECKSTYLE:ON
}
