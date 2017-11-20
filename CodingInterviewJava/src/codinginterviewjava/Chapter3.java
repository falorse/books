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

    Stack() {
        System.out.println("stack");
        top_ = null;
    }

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
        if (top_ == null) {
            top_ = t;
        } else {
            t.next_ = top_;
            top_ = t;
        }
    }

    Object peek() {
        if (top_ == null) {
            return null;
        }
        return top_.data_;
    }

    boolean isEmpty() {
        return top_ == null;
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

    String showStack(){
        return showStack(top_);
    }
    
    Object peekAt(int num) {
        Node node = top_;
        while (num != 0 && node.next_ != null) {
            node = node.next_;
            num--;
        }
        return node.data_;
    }
}

class StackHasMin extends Stack {

    Stack minValueStack_;

    public StackHasMin() {
        minValueStack_ = new Stack();
    }

    void push(Object item) {
        Node node = new Node(item);
        if (top_ == null) {
            top_ = node;
            minValueStack_.push(node.data_);
        } else {
            node.next_ = top_;
            top_ = node;
            if ((int) node.data_ <= (int) minValueStack_.peek()) {
                minValueStack_.push(node.data_);
            }
        }
    }

    Object pop() {
        Node node = top_;
        if ((int) top_.data_ == (int) minValueStack_.peek()) {
            minValueStack_.pop();
        }
        top_ = node.next_;
        return node;
    }

    Object min() {
        return minValueStack_.peek();
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
                    for (int i = size_ - 1; i > newSize - 1; i--) {
                        nodes_[i] = nodes_[i - stackSizes_[0]];
                    }
                    topNums_[1] += stackSizes_[0];
                    topNums_[2] += stackSizes_[0];
                    stackSizes_[0] *= 2;
                    break;
                case 1:
                    for (int i = size_ - 1; i > newSize + stackSizes_[0] - 1; i--) {
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

    Object pop(int num) {
        if (stackPiles_[num] == 0) {
            return null;
        }
        stackPiles_[num]--;
        topNums_[num]--;
        return nodes_[topNums_[num]].data_;
    }

    Object peek(int num) {
        return nodes_[topNums_[num] - 1].data_;
    }
}

class SetOfStacks {

    Stack stacks_;
    int count_;
    int limit_;

    public SetOfStacks(int limit) throws Exception {
        if (limit <= 0) {
            throw new Exception();
        }
        stacks_ = new Stack();
        stacks_.push(new Stack());
        count_ = 0;
        limit_ = limit;
    }

    public void push(Object item) {
        if (count_ != limit_) {
            count_++;
        } else {
            stacks_.push(new Stack());
            count_ = 1;
        }
        ((Stack) stacks_.peek()).push(item);
    }

    public Object pop() {
        if (stacks_.isEmpty()) {
            count_ = 0;
            return null;
        } else if (((Stack) stacks_.peek()).isEmpty()) {
            stacks_.pop();
            count_ = limit_;
        }
        count_--;
        if (stacks_.isEmpty()) {
            count_ = 0;
            stacks_.push(new Stack());
            return null;
        }
        return ((Stack) stacks_.peek()).pop();
    }

    public Object peek() {
        if (stacks_.isEmpty()) {
            return null;
        }
        if (((Stack) stacks_.peek()).isEmpty()) {
            stacks_.pop();
            count_ = limit_;
        }
        return ((Stack) stacks_.peek()).peek();
    }

    public boolean isEmpty() {
        if (stacks_.peek() == null) {
            return true;
        } else if (((Stack) stacks_.peek()).isEmpty()) {
            return true;
        }
        return false;
    }

    public Object popAt(int num) {
        if (num == 0) {
            if (((Stack) (stacks_.peek())).isEmpty()) {
                return null;
            }
            return this.pop();
        }
        return ((Stack) (stacks_.peekAt(num))).pop();
    }
}

public class Chapter3 {

    public Chapter3() {
    }

    public static void main(String[] args) {
        System.out.println(hanoiTower(10));
    }

    static int hanoiTower(int n) {
        int count = 0;
        Stack stacks[] = new Stack[3];

        for (int i = 0; i < 3; i++) {
            stacks[i] = new Stack();
        }

        for (int i = n; i > 0; i--) {
            stacks[0].push(i);
        }

        boolean pushed;
        int preCircle = n + 1;
        while (true) {
            for (int i = 0; i < 3; i++) {
                int circle;
                if(stacks[i].isEmpty() ||  (circle = (int) (stacks[i].peek())) == preCircle)
                    continue;
                pushed = false;
                for (int j = (i + 1) % 3 ; j != i; j++) {
                    if (stacks[j].isEmpty() || circle < (int) (stacks[j].peek())) {
                        stacks[j].push(stacks[i].pop());
                        pushed = true;
                        preCircle = circle;
                        break;
                    }
                    if(j == 2)
                        j -= 3;
                }
                if (pushed) {
                    break;
                }
            }
            count++;
            System.out.println(stacks[0].showStack());
            System.out.println(stacks[1].showStack());
            System.out.println(stacks[2].showStack() + "\n/////////////");
            if (stacks[0].isEmpty() && stacks[1].isEmpty()) {
                break;
            }
        }
        return count;
    }
}
