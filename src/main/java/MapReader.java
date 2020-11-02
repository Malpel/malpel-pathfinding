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
                Node newNode = new Node();
                newNode.setABlock(st.charAt(j) != '.');
                nodeArray[index][j] = newNode;
            }

            index++;
        }

        createNodes();
    }

    // this is terrible
    private void createNodes() {
        for (int i = 0; i < nodeArray.length; i++) {
            for (int j = 0; j < nodeArray.length; j++) {
                Node[] neighbors = new Node[8];
                int n = 0;

                if (i - 1 >= 0 && !nodeArray[i - 1][j].isABlock()) {
                    neighbors[n] = nodeArray[i - 1][j];
                    n++;
                } else if (i + 1 < 256 && !nodeArray[i + 1][j].isABlock()) {
                    neighbors[n] = nodeArray[i + 1][j];
                    n++;
                } else if (j - 1 >= 0 && !nodeArray[i][j - 1].isABlock()) {
                    neighbors[n] = nodeArray[i][j - 1];
                    n++;
                } else if (j + 1 < 256 && !nodeArray[i][j + 1].isABlock()) {
                    neighbors[n] = nodeArray[i][j + 1];
                    n++;
                } else if (i - 1 >= 0 && j - 1 >= 0 && !nodeArray[i - 1][j - 1].isABlock()) {
                    neighbors[n] = nodeArray[i - 1][j - 1];
                    n++;
                } else if (i - 1 >= 0  && j + 1 < 256 && !nodeArray[i - 1][j + 1].isABlock()) {
                    neighbors[n] = nodeArray[i - 1][j + 1];
                    n++;
                } else if (i + 1 < 256 && j - 1 >= 0 && !nodeArray[i + 1][j - 1].isABlock()) {
                    neighbors[n] = nodeArray[i + 1][j - 1];
                    n++;
                } else if (i + 1 < 256 && j + 1 < 256 && !nodeArray[i + 1][j + 1].isABlock()) {
                    neighbors[n] = nodeArray[i + 1][j + 1];
                    n++;
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
