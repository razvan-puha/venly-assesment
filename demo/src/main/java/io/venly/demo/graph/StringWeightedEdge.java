package io.venly.demo.graph;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jgrapht.graph.DefaultEdge;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class StringWeightedEdge extends DefaultEdge {
    String relation;

    @Override
    public String toString() {
        return String.format("%s ==(%s)=> %s", getSource(), relation, getTarget());
    }
}