package trees;

import java.util.LinkedList;
import java.util.List;

public class Tree {
    private List<Tree> leaves = new LinkedList<Tree>();
    private Tree parent = null;
    private String data;

    public Tree(String data, Tree parent) {
        this.data = data;
        this.parent = parent;
    }
}