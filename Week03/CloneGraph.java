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
    // HashMap to store values of nodes to their new copies
    public Map<Integer, Node> nodeCopyMap = new HashMap<>();

    // Recursive function to explore all nodes and create copies
    public Node DFS_copy(Node node) {
        // Base case when node has been created - just return it
        if (nodeCopyMap.containsKey(node.val)) {
            return nodeCopyMap.get(node.val);
        } else {
            // If node has not been created, create it, copy its location to the hashmap,
            // and explore its neighbors. Add those new nodes to current node's neighbors as well.
            Node copyNode = new Node(node.val);
            nodeCopyMap.put(node.val, copyNode);
            for (Node neighbor : node.neighbors) {
                copyNode.neighbors.add(DFS_copy(neighbor));
            }
            return copyNode;
        }
    }

    public Node cloneGraph(Node node) {
        /*
        Call DFS on the head of the list.
        At each call of DFS, if the node has already been copied, return the copied node
        If the node has not been copied, create a copy and store a pointer to it.
            For all neighbors of the node, call DFS on them, which also creates a copy
            Add the new node to the current copy's list of neighbors
        return the copy of the node.
        Since the graph is connected, all nodes and neighbors should be copied and added
        The final node to be returned will be the head
        */

        // If node is uninitialized, return null
        if (Objects.isNull(node)) {
            return node;
        }
        return DFS_copy(node);
    }
}