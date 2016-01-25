
import java.util.LinkedList;
import org.junit.Test;
import org.junit.Assert;
import zadanie2pa.EnumeratorOrder;
import zadanie2pa.Tree;

public class TreeTest {

    @Test
    public void enumeratorValidation() {
        Tree<Integer> stree = new Tree<>(5, EnumeratorOrder.BFS);
        stree.add(1);
        stree.add(2);
        Tree<Integer> tree = new Tree<>(7, EnumeratorOrder.BFS);
        tree.add(stree);
        tree.add(10);
        tree.add(15);
        LinkedList<Integer> allElements = tree.getAllElements();
        boolean outcome = false;
        for (Integer elm : allElements) {
            if (elm % 2 == 0) {
                if (elm == 10) {
                    outcome = true;
                } else {
                    break;
                }
            }
        }
        Assert.assertTrue(outcome);
        int bfs[] = new int[]{7, 5, 10, 15, 1, 2};
        Object[] mBFS = tree.getAllElements().toArray();
        Assert.assertEquals(bfs.length, mBFS.length);
        for (int i = 0; i < bfs.length; ++i) {
            Assert.assertEquals(bfs[i], (int) mBFS[i]);
        }
        tree.setOrder(EnumeratorOrder.DFS);
        outcome = false;
        for (Integer elm : allElements) {
            if (elm % 2 == 0) {
                if (elm == 2) {
                    outcome = true;
                } else {
                    break;
                }
            }
        }
        int dfs[] = new int[]{7, 5, 1, 2, 10, 15};
        Object[] mDFS = tree.getAllElements().toArray();
        Assert.assertEquals(dfs.length, mDFS.length);
        for (int i = 0; i < bfs.length; ++i) {
            Assert.assertEquals(dfs[i], (int) mDFS[i]);
        }
    }

    @Test
    public void enumerateWithNoChildren() {
        Tree<Integer> tree = new Tree<>(7, EnumeratorOrder.DFS);
        Assert.assertEquals(7, (int) tree.getAllElements().getFirst());
        tree.setOrder(EnumeratorOrder.BFS);
        Assert.assertEquals(7, (int) tree.getAllElements().getFirst());
        Tree<Integer> stree = new Tree<>(5, EnumeratorOrder.BFS);
        tree.add(stree);
        Assert.assertEquals(5, (int) tree.getAllElements().getLast());
        tree.setOrder(EnumeratorOrder.DFS);
        Assert.assertEquals(5, (int) tree.getAllElements().getLast());
    }

    @Test
    public void orderPropertyValidation() {
        Tree<Integer> stree = new Tree<>(5, EnumeratorOrder.DFS);
        stree.add(1);
        stree.add(2);
        Tree<Integer> tree = new Tree<>(7, EnumeratorOrder.BFS);
        tree.add(stree);
        Assert.assertEquals(EnumeratorOrder.BFS, stree.getOrder());
        for (Tree<Integer> child : stree.getChild()) {
            Assert.assertEquals(EnumeratorOrder.BFS, child.getOrder());
        }
    }
}