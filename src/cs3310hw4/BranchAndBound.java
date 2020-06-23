package cs3310hw4;

import java.util.*;

public class BranchAndBound 
{
    LinkedList<Node> pq;
    private int maxProfit, nodes, solution[], finalSolution[];
    
    public BranchAndBound(int numItems)
    {
        pq = new LinkedList();
        maxProfit = 0;
        nodes = 1;
        solution = new int[numItems];
        finalSolution = new int[numItems];
    }
    
    public void knapsack(int maxWeight, int weights[], int profits[], int numItems)
    {   
        Node u = new Node(), v = new Node();
        
        v.setLevel(-1);
        v.setProfit(0);
        v.setWeight(0);
        
        pq.offer(new Node(v));
        
        while(!pq.isEmpty())
        {
            v = pq.poll();
            
            if(v.getLevel() == -1)
            {
                u.setLevel(0);
            }
            else if(v.getLevel() != (numItems - 1))
            {
                u.setLevel(v.getLevel() + 1);
            }
            
            u.setWeight(v.getWeight() + weights[u.getLevel()]);
            u.setProfit(v.getProfit() + profits[u.getLevel()]);
            
            double bound = bound(u, maxWeight, numItems, weights, profits);
            u.setBound(bound);
            
            if((u.getWeight() <= maxWeight) && (u.getProfit() > maxProfit))
            {
                maxProfit = u.getProfit();
            }
            
            if(u.getBound() > maxProfit)
            {
                pq.offer(new Node(u));
                nodes++;
            }
            
            u.setWeight(v.getWeight());
            u.setProfit(v.getProfit());
            
            if(u.getBound() > maxProfit)
            {
                pq.offer(new Node(u));
                nodes++;
            }
//            nodes++;
        }
    }
    
    public double bound(Node u, int W, int n, int w[], int p[])
    {
        int j = 0, k = 0, weight;
        double profit;
        
        if(u.getWeight() >= W)
        {
            return 0;
        }
        else
        {
            profit = u.getProfit();
            j = u.getLevel() + 1;
            weight = u.getWeight();
            
            while((j < n) && (weight + w[j]) <= W)
            {
                weight += w[j];
                profit += p[j];
                j++;
            }
            
            k = j;
            
            if(k < n)
            {
                profit = profit + (W - weight) * (p[k] / w[k]);
            }
            return profit;
        }
    }
    
    public void printSolution(int weights[], int profits[])
    {
        System.out.println("Knapsack contains:");
        for(int i = 0; i < finalSolution.length; i++)
        {
            if(finalSolution[i] == 1)
            {
                System.out.println("- Item " + (i + 1) + " with weight " + weights[i] + " and profit " + profits[i]);
            }
        }
        System.out.println("Knapsack does not contain:");
        for(int i = 0; i < finalSolution.length; i++)
        {
            if(finalSolution[i] != 1)
            {
                System.out.println("- Item " + (i + 1) + " with weight " + weights[i] + " and profit " + profits[i]);
            }
        }
    }
    
    public int getProfit()
    {
        return maxProfit;
    }
    
    public int getNodes()
    {
        return nodes;
    }
}
