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
    private final static int LENGTH = 100;
    private static int SPACE;
    private int root = -1;

    static {
        array = new Item[LENGTH];
        SPACE = 0;
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = new Item(i + 1);
        }
        array[array.length - 1] = new Item(-1);
    }

    /*
    Возвращает родителя узла n
     */
    public int parent(int n) {
        return findParent(root, n);
    }

    /*
    Возвращает левого сына узла n
     */
    public int leftMostChild(int n) {
        int item = findParent(root, n);
        if (item == -1) return -1;
        return array[item].LMC;
    }

    /*
    Возвращает правого брата узла n
     */
    public int rightSibling(int n) {
        int item = findParent(root, n);
        if (item == -1) return -1;
        item = array[item].LMC;
        while (item != n) {
            item = array[item].RSL;
        }
        return array[item].RSL;
    }

    /*
    Возвращает метку узла
     */
    public String label(int n) {
        if (n==root) return array[n].label;
        int temp = findParent(n, root);
        if (temp != -1) {
            return array[n].label;
        }
        else throw new RuntimeException("No such element in this tree");
    }

    /*
    Создаёт новое дерево с 1 узлом и меткой V
     */
    public Tree CREATE(String V) {
        if (SPACE == -1) return null;
        array[SPACE].label = V;
        if (root != -1) {
            array[SPACE].LMC = root;
            root = SPACE;
            SPACE = array[SPACE].LMC;
        }
        else {
            root = SPACE;
            SPACE = array[SPACE].LMC;
            array[root].LMC  = -1;
        }
        return this;
    }

    /*
    Создаёт корень дерева с меткой V и 1 узел T
     */
    public Tree CREATE(String V, Tree T) {
        if (SPACE == -1) return null;
        if (root == -1) return T.CREATE(V);
        if (T.root == -1) return CREATE(V);
        array[SPACE].label = V;
        array[SPACE].LMC = root;
        array[root].RSL = T.root;
        root = SPACE;
        T.root = -1;
        SPACE = array[SPACE].LMC;
        return this;
    }

    /*
    Возвращает корень дерева
     */
    public int root() {
        return root;
    }

    /*
    Обнуляет дерево
     */
    public void makeNull() {
        if (root == -1) return;
        clearBranch(root);
        root = -1;
    }

    private void clearBranch(int root) {
        int child = array[root].LMC;
        while (child != -1) {
            clearBranch(child);
            child = array[child].RSL;
        }

        array[root].LMC = SPACE;
        array[root].RSL = -1;
        array[root].label = null;
        SPACE = root;
    }

    private int findParent(int root, int n) {
        int child = array[root].LMC;
        while (child != -1) {
            if (child == n) return root;
            int res = findParent(child, n);
            if (res != -1)
                return res;
            child = array[child].RSL;
        }
        return -1;
    }

    public static void printArray(){ // вывод в виде массива
        for (int i = 0; i < LENGTH; i++){
            System.out.println(i + ": " +
                    array[i].LMC + " " +
                    array[i].label + " " +
                    array[i].RSL);
        }
        System.out.println();
    }
}
