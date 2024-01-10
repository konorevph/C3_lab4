package ADTTree.Cursors;

public class Tree {
    private static class Item {
        private int LMC;
        private String label;
        private int RSL;

        private Item(int RSL, int LMC, String label)
        {
            this.LMC = LMC;
            this.label = label;
            this.RSL = RSL;
        }
    }
    private final static Item[] array;
    private final static int LENGTH = 10;
    private static int SPACE;
    private int root;

    static {
        SPACE = 0;
        array = new Item[LENGTH];
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = new Item(-1,i + 1, "");
        }
        array[array.length - 1] = new Item(-1, -1, "");
    }

    public Tree()
    {
        root = -1;
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
        // Если нет корня
        if (root == -1) return -1;
        // Если ищем сына для корня
        if (n == root) return array[root].LMC;
        // Рекурсивный метод по поиску узла с именем n
        int item = findParent(root, n);
        if (item == -1) return -1;
        return array[n].LMC;
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
        int temp = findParent(root, n);
        if (temp != -1) {
            return array[n].label;
        }
        throw new RuntimeException("No such element in this tree");
    }

    /*
    Создаёт новое дерево с 1 узлом и меткой V
     */
    public Tree CREATE(String V) {
        if (SPACE == -1) return null;
        int newRoot = SPACE;
        SPACE = array[SPACE].LMC;

        array[newRoot].label = V;
        if (root != -1) {
            array[newRoot].LMC = root;
        }
        else {
            array[newRoot].LMC = -1;
        }
        root = newRoot;
        return this;
    }

    /*
    Создаёт корень дерева с меткой V и 1 узел T
     */
    public Tree CREATE(String V, Tree T) {
        // todo Проверить this == T
        if (SPACE == -1) return null;
        if (root == -1) return T.CREATE(V);
        if (T.root == -1) return CREATE(V);

        int newRoot = SPACE;
        SPACE = array[SPACE].LMC;

        array[newRoot].label = V;
        array[newRoot].LMC = root;
        array[root].RSL = T.root;
        root = newRoot;
        T.root = -1;
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
        array[root].label = "";
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
        System.out.println(" # LMC RSL Label");
        for (int i = 0; i < LENGTH; i++){
            System.out.printf("%2d  %2d  %2d %s\n",
                    i,
                    array[i].LMC,
                    array[i].RSL,
                    array[i].label);
        }
        System.out.println();
    }
}
