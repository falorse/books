/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codinginterviewjava;

import java.util.Hashtable;
import java.lang.Exception;

/**
 *
 * @author fukuda
 */
class Stack {
    Node top_;

    Object pop() {
        if (top_ != null) {
            Object item = top_.data_;
            top_ = top_.next_;
            return item;
        }
        return null;
    }

    void push(Object item) {
        Node t = new Node(item);
        t.next_ = top_;
        top_ = t;
    }

    Object peek() {
        return top_.data_;
    }
}

class Queue{
    Node first_, last_;
    
    void enqueue(Object item){
        if(first_ == null){
            last_ = new Node(item);
            first_ = last_;
        }else{
            last_.next_ = new Node(item);
            last_ = last_.next_;
        }
    }
    
    Object dequeue(){
        if(first_ != null){
            Object item = first_.data_;
            first_ = first_.next_;
            return item;
        }
        return null;
    }
}

public class Chapter3 {

}
