package cs3310hw4;

import java.util.*;

public class CS3310Hw4 
{
    public static void main(String[] args) 
    {
        Scanner userInput = new Scanner(System.in);
        
        System.out.print("Enter number of items available: ");
        int numItems = userInput.nextInt();
        
        int weights[] = new int[numItems];
        int profits[] = new int[numItems];
        
        for(int i = 0; i < numItems; i++)
        {            
            System.out.print("Enter the weight of item " + (i + 1) + ": ");
            int weight = userInput.nextInt();
            weights[i] = weight;
            
            System.out.print("Enter the profit of item " + (i + 1) + ": ");
            int profit = userInput.nextInt();
            profits[i] = profit;
        }
        
        System.out.print("Enter the maximum weight the bag can hold: ");
        int maxWeight = userInput.nextInt();
        
        BruteForce brute = new BruteForce(numItems, maxWeight);
        Backtracking backtrack = new Backtracking(numItems);
        BranchAndBound branch = new BranchAndBound(numItems);
        
        System.out.println("\n     Brute-Force");
        System.out.println("--------------------------------------------");
        brute.knapsack(maxWeight, weights, profits, numItems);
        brute.printSolution();
        System.out.println("Maximum profit: " + brute.getProfit());
        System.out.println("Nodes checked: " + brute.getNodes());
        
        System.out.println("\n     Backtracking");
        System.out.println("--------------------------------------------");
        backtrack.knapsack(maxWeight, weights, profits, numItems, 0);
        backtrack.printSolution(weights, profits);
        System.out.println("Maximum profit: " + backtrack.getProfit());
        System.out.println("Nodes checked: " + backtrack.getNodes());
        
        System.out.println("\n     Branch-and-Bound");
        System.out.println("--------------------------------------------");
        branch.knapsack(maxWeight, weights, profits, numItems);
        backtrack.printSolution(weights, profits);
        System.out.println("Maximum profit: " + branch.getProfit());
        System.out.println("Nodes checked: " + branch.getNodes());
    }
}
