public class StackAsLinkedList {

    Position root;

    boolean isEmpty(){
        if(root == null){
            return true;
        }else{
            return false;
        }
    }

    public void push(String data) {
        Position newPosition = new Position(data);

        if (root == null) {
            root = newPosition;
        } else {
            Position temp = root;
            root = newPosition;
            newPosition.next = temp;
        }
    }

    public String pop() {
        String popped = null;
        if (root == null) {
            System.out.println("Stack is empty");
        } else {
            popped = root.data;
            root = root.next;
        }
        return popped;
    }

    public String peek() {
        if (root == null) {
            System.out.println("Stack is empty");
            return null;
        } else {
            return root.data;
        }
    }
}


