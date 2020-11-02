
public class Main {

    public static void main(String[] args) throws Exception {

        MapReader mapReader = new MapReader("/home/malpel/Projects/malpel-pathfinding/src/main/resources/Paris_0_256.map");
        mapReader.createArrays();

        String[] stringMap = mapReader.getStringArray();
        Node[][] nodeMap = mapReader.getNodeArray();
        
    }

}