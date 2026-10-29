import java.util.*;

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    // Map original nodes to their cloned counterparts to prevent infinite loops and handle cycles
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // If the node has already been cloned, return the clone
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a clone for the current node
        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode);

        // Recursively clone all neighbors
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }
}