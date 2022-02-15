package cz.cvut.fel.pjv;

public class NodeImpl implements Node
{
    public Node leftnode;
    public Node rightnode;
    private int value;
    
    @Override
    public Node getLeft() 
    {
        return this.leftnode;
    }
    public NodeImpl(int value)
    {
        this.value = value;
    }
    @Override
    public int getValue() 
    {
        return this.value;
    }
        @Override
    public Node getRight() 
    {
        return this.rightnode;
    }
    
}