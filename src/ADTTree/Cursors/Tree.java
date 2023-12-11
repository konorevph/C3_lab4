package ADTTree.Cursors;

public class Tree {
    private static class Item {
        private int LMC;
        private String label;
        private int RSL;

        private Item(int LMC)
        {
            this.LMC = LMC;
            label = "";
            RSL = -1;
        }
    }
    private final static Item[] array;
    private static int SPACE;
    private int root;

    static {
        array = new Item[100];
        SPACE = 0;
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = new Item(i + 1);
        }
        array[array.length - 1] = new Item(-1);
    }

    private int findParent(int root, int n) {
        if (root == n) return root;

        int res = -1, tmp = root;



        return res;
    }

    /*
    Возвращает родителя узла n
     */
    public int parent(int n) {
        int i = root;
        while (i != -1) {

        }
    }

    /*
    Возвращает левого сына узла n
     */
    public int leftMostChild(int n) {
        return 0;
    }

    /*
    Возвращает правого брата узла n
     */
    public int rightSibling(int n) {
        return 0;
    }

    /*
    Возвращает метку узла
     */
    public String label(int n) {
        return null;
    }

    /*
    Создаёт новое дерево с 1 узлом и меткой V
     */
    public void CREATE(String V) {
        root = SPACE;
        SPACE = array[SPACE].LMC;
    }

    /*
    Создаёт корень дерева с меткой V и 1 узел T
     */
    public void CREATE(String V, Tree T) {

    }

    /*
    Создаёт корень дерева с меткой V и 2 узла T1 и T2
     */
    public void CREATE(String V, Tree T1, Tree T2) {

    }

    /*
    Возвращает корень дерева
     */
    public int root() {
        return 0;
    }

    /*
    Обнуляет дерево
     */
    public void makeNull() {

    }
}
