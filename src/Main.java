import ADTTree.SonsList.Tree;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        Tree tree2 = new Tree();
        tree = tree.CREATE("label");
        tree2 = tree2.CREATE("label2");
//        Tree.printArray();
        tree = tree.CREATE("label3", tree2);
        tree = tree.CREATE("label4");
//        tree.makeNull();
        System.out.println(tree);
        Tree.printArray();
        symmetricBypass(tree, tree.root());
    }

    public static void symmetricBypass(Tree tree, int root)
    {
        if (root < 0) return;
        int child = tree.leftMostChild(root);
        symmetricBypass(tree, child);
        System.out.println(tree.label(root));
        while (child >= 0) { // todo проверить выводится ли -1 на списке сыновей
            child = tree.rightSibling(child);
            symmetricBypass(tree, child);
        }
    }
}