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


## Software required
1.JVM<br>
install instructions:<br>
    Linux: open terminal and type following commands:<br>
    <br>
    sudo apt update && upgrade <br>
    sudo apt install openjdk-11-jdk <br>
    <br>
   
2. Make
__Install instructions:__ <br>
    Linux: enter following command from linux terminal<br>
    <br>
    sudo apt install make
    <br>
3. Git
   __Install istructions:__ <br>
   Linux: Run command: sudo apt install git
   Windows: Navigate to [git](https://git-scm.com/download/win) and follow install instructions there
## How to run the program
The program can be run in different modes, one which is the debug mode that print texts to the console as it solves the algorithm.
Clone the project to your local computer using command:

Step 1: clone the repository to your local machine using command:<br><br>
    git clone [Repository URL]<br><br>
Step 2: Navigate to the target folder with command:<br><br>
    cd Monte-Carlo-Algorithm<br><br>
Step 3: While in this direcoty compile the jave classes by running command:<br><br>
        make<br><br>
step 4: After Compiling, run the progrom using one of the following options:<br><br>
    opition 1: run serial algorithm in test mode- this runs the test algorithm and output Systems properties, command line arguments, and  final results.<br><br>
                __command__ : make run  ARGS="'<rows>' '<cols>' '<xmin>' '<xmax>' '<ymin>' '<ymax>' '<search>' '<mode>' '<parallel>'" <br> 
                These command line arguments are described as follows:<br>
                '__<rows>__':this equals the number of rows of the grid(typically in order of thousands)<br><br>
               '__<cols>__': number od columns of the grid<br>(typically in order of thousands)<br><br>
                '__<xmin>__ 'the minimum x-coordinate where the grid begins<br><br>
                '__<xmax>__' the maximum x-coordinate where the grid ends<br><br>
                '__<ymin>__' the minimum y-coordinates where the grid begins<br><br>
                '__<ymax>__' the maximum y-ccordinate where the grid ends<br><br>
                '__<search>__' defines the search density(typically less than 1!)<br><br>
                '__<mode>__' specifies wherethee the algorithms run in debug mode or not. if set to 1, the algorithms will run in debug mode(outputing texts at each significant step). The defualt is 0 which does not run problems in debug mode <br><br>
                '__<parallel>__' determines if parallel algorithm should be executed. If set to 1, the program will run both serial and parallel algorithms and output their results, together with command line arguments, to the console.<br><br>
                
Option 2: This option runs the program for data collection purposes. It loops a number of times and give algorithms some predefined inputs. It was provided mainly for data collection purposes.<br><br>
        command: make get <br><br>
        This will run the whole algorithms against some defined inputs and write results to text file in "data" folder.<br><br>
        If want to view the results in graphs, run the above command and then the following commands in order: <br><br>
                    1.make get prepare <br> <br>
Option 3: This last option runs a python script and produces a plot of the graphs of the resultes recorded<br><br>
        Command: make plot <br><br>

            
