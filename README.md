# Monte-Carlo-Algorithm

# Introductioon
Monte Carlo algorithms use random selection in calculations to complex solve numerical and mathematical problems. Monte Carlo methods is a framework of computational technique that solve methamatical problems using random samples. There are two major statistcal appications of Monte Calo methods: Integration and Optimization.<br>

In this project, Monte Carlo algorithm is used to find the lowest point( local minimum point) of a 2-dimensional fucntion f(x,y) within a specified domain, which is optimization problem. The project implements the algorithm with thought that the function represents the height of terrain(but may be applied to any two-varaible function), and so finds the lowest height in a given area. Since the grid may be very large, the problem definitely needs parallel computation to optimize the computation. <b><br>
The main object of the this project is to measure the performance of parallel implementation of this problem. Traditionally, parallel is normally measures with regards to variable number of processors, with fixed problem size. This approach is importatnt because is determines clearly is the problem should be  parallelized. Moreover, the approach of measure parallel performance also proves, ordisproves theories such as Amdahl's law. However, In project, the parallel performance is measure by fixing the number of processors, and vary the problem size.<br><br>
The project benchmarks to find speed up of the algorithm. By definition, speed-up is time taken by one process divided by time divided by P processes. A speed up of less than zero shows that the sequential implementation of the algoritthm is more efficient and parallelization do not worth it. With this regards, the project implements both sequential and parallel algorithm, so as to accurately find the speed up and determine conditions at which best speed-up is obtained.<br>
# Approach
The project uses two-dimensional array to represent a grid. It is defines by the starting and ending x-and y-coordinates as well as the number of rows present in such a grid. A class that represents a terrain is defined and Search object search the grid, using Monte Carlo approximations (probabilistic) to determine where on the grid is the terran having minimum hieght.<br>

The serial Algoritm uses and array of searches (Search object) to search the array and each object is allowed to search one after the other. This is obviously time consuming considering the possibility of having gigantic gris sizes. At the first, eahc search is given a rondom stating position on the grid(Monte Carlo method), and  evaluates the function(hardcoded terrain function) at that location. Th search object then moves "downhill" by  comparing that height with the height at the neighbouring grid points. This is continues until to more minimum can be found,and the s earch returns the found minimum, as well it's coordinates. To optimiza the problem, the algorithm is implemented such that two search objects do not trace the same route ont the grid.<br>


The parallel implementation of this algorithm is pretty much the same except that multiple search obejct are allowed in the grid. This has potential of race condition where two searches might get to the grid point and change that point at the same time. However, this race condition is ingored in this project because programming to prevent that will make algorithm much slower,and violates initial objective of the experiment. The way that paralellel algorithm works is that, initially it takes the array of searches, and breaks it recursively into two halves, giving each half to another ForkJoin thread until the array size with if within determined sequential cut-off, of which if is the case, seach obejcts in that portion of the search array are allowed into the grid  sequentially.<br>
The algorithms were benchmarked against, grid-size(rows*cols), search_density, and sequential-cut-off., on two different machines(quard-processor and  octa-processor),and the graphs of speed-up are shown below.
![Sequential-cut](https://github.com/Kananelo688/Monte-Carlo-Algorithm/assets/137335001/1a572605-bafa-4b94-8e65-c20ab9904973)
![Grid_Size](https://github.com/Kananelo688/Monte-Carlo-Algorithm/assets/137335001/bf8262a2-f9fa-47f4-87e8-58780d1316ba)

![Speed-up-vs-search-densities](https://github.com/Kananelo688/Monte-Carlo-Algorithm/assets/137335001/4e70ec8b-74fa-4925-a8de-0ee5c3b6faf2)

# Software Required
1. Java Virtual Machine (JVM)
2. Git
3. Make
4. Python3(and some packages)
## How install JVM:
1. Linux: open terminal and type command sudo apt update && apt upgrade && apt install openjdk-11-jdk -y
2. Windows: Navigate to [JVM](https://www.oracle.com/za/java/technologies/downloads/), and download JVM suitable for your Windows system.
## How to install Git
1. Linux: open terminal (ctrl+alt+T) and type command sudo apt install git -y
2. Windows: Navigate to [git](https://www.gnu.org/software/make/), and follow instructions to install make suitable for your windows system.
## How to install python3
 By defualt, JVM comes with python3 already installed. But if that's not the case here is how to install python3<br>
 1. Linux: open terminal and type in command: sudo apt install python3
 2. Windows: Navigate to [python3](https://www.python.org/downloads/windows/), and download and run python installer
## How to install required Python packages
The projects makes use of numpy and matplotlib packages which must be installed manually, to install them, use pip.<br>
### Install Pip
1. Linux: open terminal and run command: sudo apt install pip
2. Windows: open command prompt and run command: curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py
### Install NumPy and Matplotlib
1. Linux( and Window): open terminal(or command prompt in windows) and run command: pip install numpy && pip install matplotlib  

# How to run the program:
1. Clone the repository in your local machine with command: git clone <repository URL><br>
A folder named Monte-Carlo-Algorithm will be create.<br>
2. Still on terminal, change directory to the repository folder and compile run classes: cd ./Monte-Carlo-Algorithm && make
3. Run the program by typing command: make run ARGS="rows cols xmin xmax ymin ymin density mode para"<br>
__rows__ and __cols__: integer that determines the number of rows and columns in the grid, respectively.<br>
__xmin__ and __xmax__: double values that define the minimum and maximum x-coordinates of the grid<br>
__ymin__ and __ymax__: double values that define the minimum and maximum y-coordinates of the grid<br>
__density__: A double that determines the number of searches to be used.( It is typically less than 1)<br>
__mode__: set this to 1 if you want to run in debug mode, otherwise set it to 0.<br>
__para__: set this to 1 if you want to run __BOTH__ parallel and serial algorithms, set it to 0 is you only want to run the serial algoritms
4. Run the following command to run the program for data collection: make get ( this will run all algorithms agaist huge amounts of inputs and will typically take 20-30 minutes to completes, it was provided and is to be used __ONLY__ when you want to benchmark the algorithms.
5. To plot the graphs, run the command: make plot<br>
    N.B: The plots produced will be for the data collected when testing, not from your machine, the program is defined such that is saves files with username(operating system's username), to run plots which include data gathered from your local machine, run command as: make run "username". and replace "username" with the name your computer is using<br>.
            
