package io;

import domain.Node;

import java.io.*;


public class MapReader {

    File file;
    String[] stringArray;
    Node[][] nodeArray;

    // map size as a parameter?
    public MapReader(String pathToMap) {
        file = new File(pathToMap);
        stringArray= new String[256];
        nodeArray = new Node[256][256];
    }

    public void createArrays() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int index = 0;

        while ((st = br.readLine()) != null) {
            stringArray[index] = st;

            for (int j = 0; j < st.length(); j++) {
                Node newNode = new Node(index, j);
                newNode.setABlock(st.charAt(j) != '.');
                nodeArray[index][j] = newNode;
            }

            index++;
        }

        setNeighbors();
    }

    // this is terrible
    private void setNeighbors() {
        for (int i = 0; i < nodeArray.length; i++) {
            for (int j = 0; j < nodeArray.length; j++) {
                Node[] neighbors = new Node[8];
                int n = 0;

                if (i - 1 >= 0 && !nodeArray[i - 1][j].isABlock()) {
                    neighbors[n] = nodeArray[i - 1][j];
                    n++;
                }

                if (i + 1 < 256 && !nodeArray[i + 1][j].isABlock()) {
                    neighbors[n] = nodeArray[i + 1][j];
                    n++;
                }

                if (j - 1 >= 0 && !nodeArray[i][j - 1].isABlock()) {
                    neighbors[n] = nodeArray[i][j - 1];
                    n++;
                }

                if (j + 1 < 256 && !nodeArray[i][j + 1].isABlock()) {
                    neighbors[n] = nodeArray[i][j + 1];
                    n++;
                }

                if (i - 1 >= 0 && j - 1 >= 0 && !nodeArray[i - 1][j - 1].isABlock()) {
                    neighbors[n] = nodeArray[i - 1][j - 1];
                    n++;
                }

                if (i - 1 >= 0  && j + 1 < 256 && !nodeArray[i - 1][j + 1].isABlock()) {
                    neighbors[n] = nodeArray[i - 1][j + 1];
                    n++;
                }

                if (i + 1 < 256 && j - 1 >= 0 && !nodeArray[i + 1][j - 1].isABlock()) {
                    neighbors[n] = nodeArray[i + 1][j - 1];
                    n++;
                }

                if (i + 1 < 256 && j + 1 < 256 && !nodeArray[i + 1][j + 1].isABlock()) {
                    neighbors[n] = nodeArray[i + 1][j + 1];
                }

                nodeArray[i][j].setNeighbors(neighbors);
            }
        }
    }

    public String[] getStringArray() {
        return stringArray;
    }

    public Node[][] getNodeArray() {
        return nodeArray;
    }
}
