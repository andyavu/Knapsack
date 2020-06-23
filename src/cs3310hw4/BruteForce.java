package cs3310hw4;

public class BruteForce 
{
    private int weights[], profits[], maxProfit, nodes, temp[][], solution[][], finalSolution[], negative = -1;
    
    public BruteForce(int numItems, int maxWeight)
    {
        weights = new int[numItems + 1];
        profits = new int[numItems + 1];
        maxProfit = 0;
        nodes = 1;
        temp = new int[numItems + 1][maxWeight + 1];
        solution = new int[numItems + 1][maxWeight + 1];
        finalSolution = new int[numItems + 1];
    }
    
    public void knapsack(int maxWeight, int weights[], int profits[], int numItems)
    {
        for(int i = 1; i <= numItems; i++)
        {
            this.weights[i] = weights[i - 1];
            this.profits[i] = profits[i - 1];
        }
        
        for(int i = 1; i <= numItems; i++)
        {
            for(int j = 0; j <= maxWeight; j++)
            {
                int m1 = temp[i - 1][j];
                int m2 = negative;
                
                if(j >= this.weights[i])
                {
                    m2 = temp[i - 1][j - this.weights[i]] + this.profits[i];
                    nodes++;
                }
                temp[i][j] = Math.max(m1, m2);
                solution[i][j] = m2 > m1 ? 1 : 0;
            }
        }
        
        for(int n = numItems, w = maxWeight; n > 0; n--)
        {
            if(solution[n][w] != 0)
            {
                finalSolution[n] = 1;
                w -= this.weights[n];
            }
            else
            {
                finalSolution[n] = 0;
            }
        }
    }
    
    public void printSolution()
    {
        System.out.println("Knapsack contains:");
        for(int i = 1; i < finalSolution.length; i++)
        {
            if(finalSolution[i] == 1)
            {
                System.out.println("- Item " + i + " with weight " + weights[i] + " and profit " + profits[i]);
            }
        }
        System.out.println("Knapsack does not contain:");
        for(int i = 1; i < finalSolution.length; i++)
        {
            if(finalSolution[i] != 1)
            {
                System.out.println("- Item " + i + " with weight " + weights[i] + " and profit " + profits[i]);
            }
        }
    }
    
    public int getProfit()
    {
        for(int i = 1; i < finalSolution.length; i++)
        {
            if(finalSolution[i] == 1)
            {
                maxProfit += profits[i];
            }
        }
        return maxProfit;
    }
    
    public int getNodes()
    {
        return nodes;
    }
}
