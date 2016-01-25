package zadanie2pa;

import java.util.Iterator;
import java.util.LinkedList;

public class Tree<T> implements Iterable<Tree<T>> {

    private T value;
    private EnumeratorOrder order;
    private LinkedList<Tree<T>> child;

    public EnumeratorOrder getOrder() {
        return this.order;
    }

    public LinkedList<Tree<T>> getChild() {
        return this.child;
    }

    public Tree(T value, EnumeratorOrder order) {
        this.value = value;
        this.order = order;
        this.child = new LinkedList<>();
    }

    public Tree(T value, EnumeratorOrder order, LinkedList<Tree<T>> child) {
        this.value = value;
        this.child = child;
        setOrder(order);
    }

    public void add(Tree<T> child) {
        child.setOrder(this.order);
        this.child.add(child);
    }

    public void add(T child) {
        this.child.add(new Tree<>(child, this.order));
    }

    public final void setOrder(EnumeratorOrder order) {
        this.order = order;
        this.child.stream().forEach((child) -> {
            child.setOrder(order);
        });
    }

    public LinkedList<T> getAllElements() {
        LinkedList<T> allElements = new LinkedList<>();
        allElements.add(this.value);
        traversing(allElements);
        return allElements;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return pointer.size() > 0;
            }
            @Override
            public Tree<T> next() {
                return pointer.pop();
            }
            private final LinkedList<Tree<T>> pointer = getListOfTrees();
        };
    }

    @Override
    public String toString() {
        return getAllElements().toString();
    }

    private LinkedList<Tree<T>> getListOfTrees() {
        LinkedList<Tree<T>> listOfTrees = new LinkedList<>();
        listOfTrees.add(this);
        nodeTraversing(listOfTrees);
        return listOfTrees;
    }

    private void BFS(LinkedList<T> allElements) {
        this.child.stream().forEach((child) -> {
            allElements.add(child.value);
        });
        this.child.stream().forEach((child) -> {
            child.BFS(allElements);
        });
    }

    private void nodeBFS(LinkedList<Tree<T>> listOfTrees) {
        this.child.stream().forEach((child) -> {
            listOfTrees.add(child);
        });
        this.child.stream().forEach((child) -> {
            child.nodeBFS(listOfTrees);
        });
    }

    private void DFS(LinkedList<T> allElements) {
        this.child.stream().map((child) -> {
            allElements.add(child.value);
            return child;
        }).forEach((child) -> {
            child.DFS(allElements);
        });
    }

    private void nodeDFS(LinkedList<Tree<T>> listOfTrees) {
        this.child.stream().map((child) -> {
            listOfTrees.add(child);
            return child;
        }).forEach((child) -> {
            child.nodeDFS(listOfTrees);
        });
    }

    private void traversing(LinkedList<T> allElements) {
        if (this.order == EnumeratorOrder.BFS) {
            BFS(allElements);
        } else {
            DFS(allElements);
        }
    }

    private void nodeTraversing(LinkedList<Tree<T>> listOfTrees) {
        if (this.order == EnumeratorOrder.BFS) {
            nodeBFS(listOfTrees);
        } else {
            nodeDFS(listOfTrees);
        }
    }
}