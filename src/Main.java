import ADTTree.Cursors.Tree;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        Tree tree2 = new Tree();
        tree = tree.CREATE("label");
        tree2 = tree2.CREATE("label2");
//        Tree.printArray();
        tree = tree.CREATE("label3", tree2);
        System.out.println(tree);
        Tree.printArray();
    }
}