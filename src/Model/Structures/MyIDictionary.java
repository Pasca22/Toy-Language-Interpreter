package Model.Structures;

import Model.Values.IValue;

public interface MyIDictionary<K, V> {


    boolean isDefined(K id);

    V lookup(K id);

    void update(K id, V val);

}
