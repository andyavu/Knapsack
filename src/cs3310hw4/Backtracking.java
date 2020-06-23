package cs3310hw4;

public class Backtracking 
{
    private int maxProfit, nodes, solution[], finalSolution[];
    
    public Backtracking(int numItems)
    {
        maxProfit = 0;
        nodes = 1;
        solution = new int[numItems];
        finalSolution = new int[numItems];
    }
    
    public void knapsack(int maxWeight, int weights[], int profits[], int numItems, int index)
    {
        solution[index] = -1;
        int n = weights.length;
        while(solution[index] < 1)
        {
            solution[index] = solution[index] + 1;
            
            if((sum(index, weights) <= maxWeight) && (index == n - 1))
            {
                update(maxWeight, weights, profits, index);
                nodes++; // nodes visited
            }
            else if(index < n - 1)
            {
                knapsack(maxWeight, weights, profits, numItems, index + 1);
            }
//            nodes++; // total nodes
        }
    }
    
    public int sum(int index, int weights[])
    {
        int sum = 0;
        for(int i = 0; i < solution.length; i++)
        {
            sum += solution[i] * weights[i];
        }
        return sum;
    }
    
    public void update(int maxWeight, int weights[], int profits[], int index)
    {
        int totalValue = 0;
        
        for(int i = 0; i < weights.length; i++)
        {
            if(solution[i] == 1)
            {
                totalValue += profits[i];
            }
        }
        
        if(totalValue > maxProfit)
        {
            for(int i = 0; i < weights.length; i++)
            {
                finalSolution[i] = solution[i];
            }
            maxProfit = totalValue;
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
