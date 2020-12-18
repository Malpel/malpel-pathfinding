package pathfinding.io;

import pathfinding.domain.Node;
import pathfinding.domain.NodeMap;
import pathfinding.domain.List;

import java.io.*;


/**
 * MapReader reads in a text form map and turns it into a Node[] array.
 */
public class MapReader {

    public MapReader() {

    }

    /**
     * Reads the map from a text file and creates a Node array based on it.
     * MapReader reads in a text form map and turns it into a Node[] array.
     * @param filename
     * The name of the file to be read.
     * @param mapSize
     * The size of the map as an integer.
     * @return Returns the map as Node[] array.
     * @throws IOException
     * If file not found.
     */
    public NodeMap createNodeMap(String filename, int mapSize) throws IOException {
        NodeMap nodeMap = new NodeMap(mapSize, mapSize);
        File file = new File(getClass().getResource(filename).getFile());
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

        setNeighbors(nodeMap);

        return nodeMap;
    }

    /**
     * Sets the neighbors for all nodes. Checks the neighbors in all eight directions
     *  of a two dimensional array and adds them as the node's neighbors.
     */
    //CHECKSTYLE:OFF
    private void setNeighbors(NodeMap nodeMap) {
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

                /*
                *   diagonals are checked this way to prevent corner cutting almost entirely
                 */
                if (nodeMap.isAccessible(i - 1, j - 1)) {
                    if (nodeMap.isAccessible(i - 1, j) && nodeMap.isAccessible(i, j - 1)) {
                        neighbors.add(nodeMap.getNode(i - 1, j - 1));
                    }
                }

                if (nodeMap.isAccessible(i - 1, j + 1)) {
                    if (nodeMap.isAccessible(i - 1, j) && nodeMap.isAccessible(i, j + 1)) {
                        neighbors.add(nodeMap.getNode(i - 1, j + 1));
                    }
                }

                if (nodeMap.isAccessible(i + 1, j - 1)) {
                    if (nodeMap.isAccessible(i + 1, j) && nodeMap.isAccessible(i, j - 1)) {
                        neighbors.add(nodeMap.getNode(i + 1, j - 1));
                    }
                }

                if (nodeMap.isAccessible(i + 1, j + 1)) {
                    if (nodeMap.isAccessible(i + 1, j) && nodeMap.isAccessible(i, j + 1)) {
                        neighbors.add(nodeMap.getNode(i + 1, j + 1));
                    }
                }

                nodeMap.getNode(i, j).setNeighbors(neighbors);
            }
        }
    }
    //CHECKSTYLE:ON
}
