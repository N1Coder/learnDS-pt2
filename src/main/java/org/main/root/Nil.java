package org.main.root;

public class Nil extends Node {
    public Nil() {
        super(0);
        this.setColor(Color.BLACK);
        this.setLeft(this);
        this.setRight(this);
        this.setParent(null);
    }
}
