package cz.cvut.fel.pjv;


public class TreeImpl implements Tree{
        
        int[] values;
        Node root;
        
        @Override
	public void setTree(int[] values)
        {
            this.values = values;
            if(!(values.length > 0)) this.root = null;
            else this.root = makeTree(0, values.length - 1);
        }
        
        public Node makeTree(int start, int end)
        {
            if(start != end){
                int mid = (-start + end + 1)/2 + start;
                NodeImpl node = new NodeImpl(this.values[mid]);
                node.leftnode = makeTree(start, mid - 1);
                if((mid + 1) <= end) node.rightnode = makeTree(mid + 1, end);
                else node.rightnode = null;
                return node;
            }
            else return new NodeImpl(this.values[start]);
        }
        
        private String Convert(Node node, int depth)
        {
            String result = "";
            for (int i = 0; i < depth; i++) {
                result += " ";
            }
            result += "- " + Integer.toString(node.getValue()) + "\n";
            if(!(node.getLeft() == null)) 
            {
                result = result + Convert(node.getLeft(), depth+1);
            }
            if(!(node.getRight() == null)) 
            {
                result = result + Convert(node.getRight(), depth+1);
            }
            return result;
        }
  
        @Override
	public Node getRoot()
        {
            return this.root;
        }
        
        @Override
	public String toString()
        {  
            String result = "";
            if(!(this.root == null))
            {
                result += "- " + Integer.toString(this.root.getValue()) + "\n";
                if(!(this.root.getLeft() == null)) result = result + Convert(this.root.getLeft(), 1);
                if(!(this.root.getRight() == null)) result = result + Convert(this.root.getRight(), 1);
            }
            return result;
        }
}