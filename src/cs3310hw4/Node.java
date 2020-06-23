package cs3310hw4;

public class Node 
{
    private int weight, profit, level;
    private double bound;
    
    public Node()
    {}
    
    public Node(Node n)
    {
        this.weight = n.getWeight();
        this.profit = n.getProfit();
        this.level = n.getLevel();
        this.bound = n.getBound();
    }
    
    public void setWeight(int weight)
    {
        this.weight = weight;
    }
    
    public int getWeight()
    {
        return weight;
    }
    
    public void setProfit(int profit)
    {
        this.profit = profit;
    }
    
    public int getProfit()
    {
        return profit;
    }
    
    public void setLevel(int level)
    {
        this.level = level;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public void setBound(double bound)
    {
        this.bound = bound;
    }
    
    public double getBound()
    {
        return bound;
    }
}
