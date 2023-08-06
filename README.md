# Monte-Carlo-Algorithm

## Introductioon
Monte Carlo algorithms are use random selection in calculation to solve numerical problems. Monte Carlo methods is a framework of computational technique that solve methamatical problems using random samples. There are two major statistcal appications of Monte Calo methods: Integration and Optimization.<br>

In this project, Monte Carlo algorithm is used to find the lowest point( local minimum point) of a 2-dimensional fucntion f(x,y) within a specified domain. The project implements the algorithm with thought that the function represents the height of terrain(but may be applied to any two-varaible function, and so finds the lowest height in a given area. Since the grid may be very large, the problem definitely needs parallel computation to optimize the computation. Thus, one of the objective of the this project is to compare the performace of the algorithm when implemented in using serial approache or parallelism. 

## Method
To achieve the desired goal with most optimal solution, the program uses probabilistic approach to find the minimum of a fucntion without computing all points in the grid.<br>
At first, a starting point on the grid is chosen at random, and the fucntion is computed at that point. Next, the fucntion is evuluated for few  a nightbouring point( determined by the desired search density), and the te search goes to the point with the minimum value,and then conotinues the process again until to more minimum values can be found, at which the final minimum value is returned.<b>

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

            
