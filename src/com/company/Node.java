package com.company;

import java.util.List;
import java.util.ArrayList;

public class Node<T> {
    Node<T> parent;
    List<Node<T>> children;
    T value;

    public Node(T _value) {
        value = _value;
        children = new ArrayList<Node<T>>();
    }

    Node<T> addChild(Node<T> child) {
        if (child != null) {
            children.add(child);
            child.parent = this;
            return child;
        }
        else
            return null;
    }

    void removeChild(Node<T> child) {
        if (child != null && children.contains(child)) {
            child.parent = null;
            children.remove(child);
        }
    }

    List<T> getPathToRoot(List<T> valuesSoFar) {
        if (valuesSoFar == null) {
            valuesSoFar = new ArrayList<T>();
        }

        valuesSoFar.add(value); // add this node's value to list

        if (parent != null) {
            return parent.getPathToRoot(valuesSoFar);
        }
        else {
            return valuesSoFar;
        }
    }
}