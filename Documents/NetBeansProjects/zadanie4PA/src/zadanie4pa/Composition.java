package zadanie4pa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Composition<T> implements Iterable<Executeable<T>>, Executeable<T> {

    @Override
    public T execute(T arg) {
        if (f.size() == 0) {
            return null;
        }
        T outcome = arg;
        for (Executeable<T> function : f) {
            outcome = function.execute(outcome);
        }
        return outcome;
    }

    public Composition(Executeable<T>... functions) {
        Collections.addAll(this.f, functions);
    }

    public void add(Executeable<T> function) {
        this.f.add(function);
    }

    public Composition(List<Executeable<T>> functions) {
        this.f = functions;
    }

    @Override
    public Iterator<Executeable<T>> iterator() {
        return this.f.iterator();
    }
    
    List<Executeable<T>> f = new ArrayList<>();
}