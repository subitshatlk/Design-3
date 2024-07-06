/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
//  TC -  O(1) for next() hasNext() method - O(N) where N is the total number of elements (both integers and lists)
//  SC - O(D), where D is the maximum depth of nesting in the input.
//  Approach - Using the java iterator and stack.
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> s;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        s.push(nestedList.iterator());
        
        
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
        
    }

    @Override
    public boolean hasNext() {
        while(!s.isEmpty()){
            if(!s.peek().hasNext()){
                s.pop();
            }
            else if((nextEl = s.peek().next()).isInteger()){
                return true;
            }
            else{
                s.push(nextEl.getList().iterator());
            }
        }

        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
