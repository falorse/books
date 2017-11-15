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
        if (top_ == null) {
            return null;
        }
        return top_.data_;
    }

    String showStack(Node n) {
        if (n == null) {
            n = top_;
        }
        if (top_ == null) {
            return "";
        }

        if (n.next_ != null) {
            return showStack(n.next_) + String.valueOf(n.data_);
        } else {
            return String.valueOf(n.data_);
        }
    }
}

class Queue {

    Node first_, last_;

    void enqueue(Object item) {
        if (first_ == null) {
            last_ = new Node(item);
            first_ = last_;
        } else {
            last_.next_ = new Node(item);
            last_ = last_.next_;
        }
    }

    void enqueue(Node item) {
        if (first_ == null) {
            last_ = item;
            first_ = last_;
        } else {
            last_.next_ = item;
            last_ = last_.next_;
        }
    }

    Object dequeue() {
        if (first_ != null) {
            Object item = first_.data_;
            first_ = first_.next_;
            return item;
        }
        return null;
    }

    String showQueue() {
        String r = "";
        Node n = first_;
        while (n != null) {
            r += String.valueOf(n.data_);
            n = n.next_;
        }
        return r;
    }
}

class ThreeStacks {

    int[] topNums_;
    int[] stackSizes_;
    int[] stackPiles_;
    int size_;
    Node[] nodes_;

    public ThreeStacks(int size) {
        size_ = size > 96 ? size : 96;
        topNums_ = new int[3];
        stackSizes_ = new int[3];
        stackPiles_ = new int[3];
        for (int i = 0; i < 3; i++) {
            topNums_[i] = i * 4;
            stackSizes_[i] = 4;
            stackPiles_[i] = 0;
        }
        nodes_ = new Node[size_];
    }

    void push(Object item, int num) throws Exception {
        boolean canPush = true;
        if (stackSizes_[num] == stackPiles_[num]) {
            canPush = resize(num);
        }
        if (canPush) {
            nodes_[topNums_[num]] = new Node(item);
            stackPiles_[num]++;
            topNums_[num]++;
        } else {
            throw new Exception("out of size");
        }
    }

    //リサイズできたらtrueを返す
    boolean resize(int num) {
        int newSize = stackSizes_[num] * 2;
        int allSize = 0;
        for (int i = 0; i < 3; i++) {
            if (i == num) {
                allSize += newSize;
                continue;
            }
            allSize += stackSizes_[i];
        }
        //リサイズするための容量がない場合
        if (allSize > size_) {
            return false;
        } else {
            //ある場合
            switch (num) {
                case 0:
                    for(int i = size_ - 1; i > newSize - 1; i--){
                        nodes_[i] = nodes_[i - stackSizes_[0]];
                    }
                    topNums_[1] += stackSizes_[0];
                    topNums_[2] += stackSizes_[0];
                    stackSizes_[0] *= 2;
                    break;
                case 1:
                    for(int i = size_ - 1; i > newSize + stackSizes_[0] - 1 ; i--){
                        nodes_[i] = nodes_[i - stackSizes_[1]];
                    }
                    topNums_[2] += stackSizes_[1];
                    stackSizes_[1] *= 2;
                    break;
                default:
                    stackSizes_[2] *= 2;
                    break;
            }
        }
        return true;
    }
    
    Object pop(int num){
        if(stackPiles_[num] == 0) return null;
        stackPiles_[num]--;
        topNums_[num]--;
        return nodes_[topNums_[num]].data_;
    }
    
    Object peek(int num){
        return nodes_[topNums_[num] - 1].data_;
    }
}

public class Chapter3 {

    public Chapter3() {
    }

}
