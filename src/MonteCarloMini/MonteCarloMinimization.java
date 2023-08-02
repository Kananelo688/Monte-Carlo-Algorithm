package MonteCarloMini;
/* Serial  program to use Monte Carlo method to 
 * locate a minimum in a function
 * This is the reference sequential version (Do not modify this code)
 * Michelle Kuttel 2023, University of Cape Town
 * Adapted from "Hill Climbing with Montecarlo"
 * EduHPC'22 Peachy Assignment" 
 * developed by Arturo Gonzalez Escribano  (Universidad de Valladolid 2021/2022)
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

class MonteCarloMinimization{
	static boolean DEBUG=false;
	static long startTime = 0;
	static long endTime = 0;
	//timers - note milliseconds
	private static void tick()
	{
		startTime = System.currentTimeMillis();
	}
	private static void tock()
	{
		endTime=System.currentTimeMillis(); 
	}
	
    public static void main(String[] args)  {

    	int rows, columns; //grid size
    	double xmin, xmax, ymin, ymax; //x and y terrain limits
    	TerrainArea terrain;  //object to store the heights and grid points visited by searches
    	double searches_density;	// Density - number of Monte Carlo  searches per grid position - usually less than 1!

     	int num_searches;		// Number of searches
    	Search [] searches;		// Array of searches
    	Random rand = new Random();  //the random number generator
    	
    	if (args.length<7) {
    		System.out.println("Incorrect number of command line arguments provided.");   	
    		System.exit(0);
    	}else if(args.length>7 && args[7].equalsIgnoreCase("1"))
		{
			DEBUG=true;
		}
    	/* Read argument values */
    	rows =Integer.parseInt( args[0] );
    	columns = Integer.parseInt( args[1] );
    	xmin = Double.parseDouble(args[2] );
    	xmax = Double.parseDouble(args[3] );
    	ymin = Double.parseDouble(args[4] );
    	ymax = Double.parseDouble(args[5] );
    	searches_density = Double.parseDouble(args[6]);
    	if(DEBUG)
		{
    		/* Print arguments */
    		System.out.printf("Arguments, Rows: %d, Columns: %d\n", rows, columns);
    		System.out.printf("Arguments, x_range: ( %f, %f ), y_range( %f, %f )\n", xmin, xmax, ymin, ymax );
    		System.out.printf("Arguments, searches_density: %f\n", searches_density );
    		System.out.printf("\n");
    	}
    	
    	// Initialize
		if(DEBUG)
		{System.out.println("Initialising the searches...");}
    	terrain = new TerrainArea(rows, columns, xmin,xmax,ymin,ymax);
    	num_searches = (int)( rows * columns * searches_density );
    	searches= new Search [num_searches];
    	for (int i=0;i<num_searches;i++) 
    		searches[i]=new Search(i+1, rand.nextInt(rows),rand.nextInt(columns),terrain);
      	if(DEBUG)
		  {
    		/* Print initial values */
    		System.out.printf("Done.\nNumber searches: %d\n", num_searches);
    		terrain.print_heights();
    	}System.out.println("Running serial algorithm...");
    	if(DEBUG) System.out.println("Start timing...");
    	//start timer
    	tick();
    	
    	//all searches
    	int min=Integer.MAX_VALUE;
    	int local_min=Integer.MAX_VALUE;
    	int finder =-1;
    	for  (int i=0;i<num_searches;i++) {
    		local_min=searches[i].find_valleys();
    		if((!searches[i].isStopped())&&(local_min<min)) { //don't look at  those who stopped because hit exisiting path
    			min=local_min;
    			finder=i; //keep track of who found it
    		}
    		if(DEBUG) System.out.println("Search "+searches[i].getID()+" finished at  "+local_min + " in " +searches[i].getSteps());
    	}
   		//end timer
   		tock();
   		System.out.println("Done.");
		   long serialTime=endTime-startTime;// time taken by serial algorithm in milliseconds
		   long parallelTime; // time taken by parallel algorithm in milliseconds
    	if(DEBUG) {
			System.out.println("Stop timer.");
    		/* print final state */
    		terrain.print_heights();
    		terrain.print_visited();
    	}
    	if(args.length>8 && args[8].equalsIgnoreCase("1")){
			System.out.println("Running parallel algorithm...");
			if(DEBUG) System.out.println("Start timer....");
			terrain.reset(); // reset the
			resetTime(); // reset the timer
			tick();
			ForkJoinPool pool= ForkJoinPool.commonPool();
			MonteCarloMinimizationParallel original= new MonteCarloMinimizationParallel(searches);
			ArrayList<Double> result= pool.invoke(original);
			tock();
			if(DEBUG) System.out.println("Stop timer.");
			System.out.println("Done.");
			parallelTime=endTime-startTime;
			System.out.print("Run parameters:\n");
			System.out.printf("\t Rows: %d, Columns: %d\n", rows, columns);
			System.out.printf("\t x: [%f, %f], y: [%f, %f]\n", xmin, xmax, ymin, ymax );
			System.out.printf("\t Search density: %f (%d searches)\n", searches_density,num_searches );
			System.out.println("*************Results from Parallel Solution************");
			System.out.println("Time: "+parallelTime+" ms");
			int tmp=terrain.getGrid_points_visited();
			System.out.printf("Grid points visited: %d  (%2.0f%s)\n",tmp,(tmp/(rows*columns*1.0))*100.0, "%");
			tmp=terrain.getGrid_points_evaluated();
			System.out.printf("Grid points evaluated: %d  (%2.0f%s)\n",tmp,
					(tmp/(rows*columns*1.0))*100.0, "%");
			System.out.printf("Global minimum: %.1f at x=%.1f y=%.1f\n\n",
					result.get(0), result.get(1),result.get(2));
		}
		if (!(args.length>8 && args[8].equalsIgnoreCase("1"))){
			System.out.print("Run parameters:\n");
			System.out.printf("\t Rows: %d, Columns: %d\n", rows, columns);
			System.out.printf("\t x: [%f, %f], y: [%f, %f]\n", xmin, xmax, ymin, ymax );
			System.out.printf("\t Search density: %f (%d searches)\n", searches_density,num_searches );
			System.out.println("Results of serial computation");
		}
		/*  Total computation time */
		System.out.println("********************Results from Serial Solution************");
		System.out.printf("Time: %d ms\n",serialTime);
		int tmp=terrain.getGrid_points_visited();
		System.out.printf("Grid points visited: %d  (%2.0f%s)\n",tmp,(tmp/(rows*columns*1.0))*100.0, "%");
		tmp=terrain.getGrid_points_evaluated();
		System.out.printf("Grid points evaluated: %d  (%2.0f%s)\n",tmp,(tmp/(rows*columns*1.0))*100.0, "%");
	
		/* Results*/
		System.out.printf("Global minimum: %d at x=%.1f y=%.1f\n\n",
				min, terrain.getXcoord(searches[finder].getPos_row()),
				terrain.getYcoord(searches[finder].getPos_col()) );
    }
	private static void resetTime()
	{
		startTime=0;
		endTime=0;
	}
}
