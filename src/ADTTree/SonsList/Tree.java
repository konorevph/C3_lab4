package ADTTree.SonsList;

public class Tree {
    private static class Item {
        private String label;
        private Son son;
        private int next;
        private Item(int next, String label)
        {
            this.next = next;
            this.label = label;
        }
    }

    private static class Son {
        private int alias;
        private Son next;
        private Son(int alias, Son next) {
            this.alias = alias;
            this.next = next;
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
            array[i] = new Item(i + 1, "");
        }
        array[array.length - 1] = new Item(-1, "");
    }

    public Tree()
    {
        root = -1;
    }

    private int findParent(int root, int n) {
        Son son = array[root].son;

        while (son != null) {
            if (son.alias == n) return root;

            int res = findParent(son.alias, root);
            if (res != -1)
                return res;
            son = son.next;
        }
        return -1;
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
        if (root == -1) return -1;
        if (root == n) return array[root].son.alias;
        int item = findParent(root, n);
        if (item == -1) return -1;
        return array[n].son.alias;
    }

    /*
    Возвращает правого брата узла n
     */
    public int rightSibling(int n) {
        int item = findParent(root, n);
        if (item != -1 && array[item].son.next != null) {
            return array[item].son.next.alias;
        }
        return -1;
    }

    /*
    Возвращает метку узла
     */
    public String label(int n) {
        if (n == root) return array[n].label;
        int item = findParent(root, n);
        if (item != -1) {
            return array[n].label;
        }
        throw new RuntimeException("No such element in this tree");
    }

    /*
    Создаёт новое дерево с 1 узлом и меткой V
     */
    public Tree CREATE(String V) {
        if (SPACE == -1) return null;
        if (root != -1) {
            array[SPACE].son = new Son(root, null);
        }
        array[SPACE].label = V;
        root = SPACE;
        SPACE = array[SPACE].next;
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

        array[SPACE].label = V;
        array[SPACE].son = new Son(root, new Son(T.root, null));
        root = SPACE;
        SPACE = array[SPACE].next;

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
        Son child = array[root].son;
        while (child != null) {
            clearBranch(child.alias);
            child = child.next;
        }

        array[root].next = SPACE;
        array[root].label = "";
        SPACE = root;
    }

    public static void printArray(){ // вывод в виде массива
        System.out.println(" # Next Label     List of sons");
        for (int i = 0; i < LENGTH; i ++){
            System.out.printf("%2d   %2d %-10s", i, array[i].next, array[i].label);
            Son son = array[i].son;
            System.out.print('[');
            while (son != null) {
                System.out.printf("%2d ", son.alias);
                son = son.next;
            }
            System.out.println(']');
        }
        System.out.println();
    }
}
