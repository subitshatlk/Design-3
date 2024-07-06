//Approach - we can use a doubly LL to keep track of the order of operations done. 
//most recent operations will be added to head and least recently used are at the end. 
//Use a hashmap to keep track of the key and the Node so that we dont have to take O(n) to search for a node in LL. 
//Hashmap for O(1) lookup. 
//Time Complexity - O(1)
//Space - O(n)
class LRUCache {
    class Node{
        int key, value;
        Node prev, next;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    Node head;
    Node tail;
    HashMap<Integer,Node> map;
    int capacity;

    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;
    }
    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;

    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            node.value = value;
            return;
        }
        if(map.size() == capacity){
            Node tailprev = tail.prev;
            removeNode(tailprev);
            map.remove(tailprev.key);

        }
        Node node = new Node(key,value);
        addToHead(node);
        map.put(key,node);
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */