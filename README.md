# Monte-Carlo-Algorithm

## Introductioon
Monte Carlo algorithms are use random selection in calculation to solve numerical problems. Monte Carlo methods is a framework of computational technique that solve methamatical problems using random samples. There are two major statistcal appications of Monte Calo methods: Integration and Optimization.<br>

In this project, Monte Carlo algorithm is used to find the lowest point( local minimum point) of a 2-dimensional fucntion f(x,y) within a specified domain. The project implements the algorithm with thought that the function represents the height of terrain(but may be applied to any two-varaible function, and so finds the lowest height in a given area. Since the grid may be very large, the problem definitely needs parallel computation to optimize the computation. Thus, one of the objective of the this project is to compare the performace of the algorithm when implemented in using serial approache or parallelism. 

## Method
To achieve the desired goal with most optimal solution, the program uses probabilistic approach to find the minimum of a fucntion without computing all points in the grid.<br>
At first, a starting point on the grid is chosen at random, and the fucntion is computed at that point. Next, the fucntion is evuluated for few  a nightbouring point( determined by the desired search density), and the te search goes to the point with the minimum value,and then conotinues the process again until to more minimum values can be found, at which the final minimum value is returned.<b>
The flow chat of the algorithm is shown in the figure below.
## How to run the program
// TO DO
