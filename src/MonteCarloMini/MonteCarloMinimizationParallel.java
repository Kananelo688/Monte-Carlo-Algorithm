package MonteCarloMini;

import java.util.concurrent.*;
import java.util.ArrayList;

/**
 * MonteCarloMinimizationParallel is the implementation of the Monte Carlo algorithm for optimized computation of the
 *  minimum of a two-dimensional function. The class extends java.util.concurrent.RecursiveTask, because each thread is expected to return
 *  its results, being te global minimum of the function as well as the coordinates where that occurs.
 *  <p>
 *
 *  </p>
 *  <p>
 *      The approach used here is that, Because of possibility of large number of searches, the recursive algorithm is implemented such that
 *      it recursively divides the Search array into two, keeping one half, while given one half to the other thread.
 *      The main thread (thread with original problem), sets the starting and ending point to equal full array, then subsequent threads
 *       divide it from that point on wards
 *
 *  </p>
 * @author chabeli
 */
 public final class MonteCarloMinimizationParallel extends RecursiveTask<ArrayList<Double>>{
    /**
    Represents the searchers of the Parallel solution. This will be used by all threads.
    */
    private static Search[] searchers;
    /**
      Represents the sequential cut-off
    */
    private static final int SEQUENTIAL_CUTOFF=10000;//value will be changed for experimenting
    /**
      Starting index on search array where this thread process 
    */
    private int startX;
    /**
      Ending Index of the searches array where this thread processes
    */
    private int endX;
    /**
      Creates the main thread with the original size of the Search objects.

     The subsequent searches will use the same array and, so this constructor accepts those as argument and set static members.
      The <code>startX</code> is initiated to 0 and <code>endX</code> is initiated to length of the searches
    @param search a Search array which holds objects that search the TerrainArea
    */
    public MonteCarloMinimizationParallel(Search[] search){
      searchers=search;
      startX=0;
      endX=search.length;
    }
    /**
     * Creates a thread that searches the TerrainARea object suing Search objects in the <code>searcher</code> array
     *
     * @param startX and integr that represents the starting index of the array where this thread wil be processing
     * */
    private MonteCarloMinimizationParallel(int startX,int endX){
        this.startX=startX;
        this.endX=endX;
    }

    /**
     * Checks to see if the array size ahd reduced below the minimum and computes the problem directly or divide the array
     * recursively into halves
     * */
    public ArrayList<Double> compute()
    {

     if(endX-startX<=SEQUENTIAL_CUTOFF){
         return computeDirectly();
     }
     int mid=(endX+startX)/2;
     //Thread that search the TerrainArea using Search object on the first half
     MonteCarloMinimizationParallel thread1= new MonteCarloMinimizationParallel(startX,mid);
        //Thread that search the TerrainArea using Search object on the second half
     MonteCarloMinimizationParallel thread2= new MonteCarloMinimizationParallel(mid,endX);
     //give first half to other thread to computer
        thread1.fork();
        ArrayList<Double> here=thread2.compute();//compute the other half in this thread
        //wait for the other thread to finish
        ArrayList<Double> other=thread1.join();
        //check which found the minimum and return it
        if(here.get(0)<other.get(0)){
            return here;
        }
        return other;
    }
    /**
     * Performs the serial computation when the array size has been reduced within acceptable limits.
     * */
    private ArrayList<Double> computeDirectly()
    {
        ArrayList<Double> arrayList=new ArrayList<>(3);
        int min=Integer.MAX_VALUE;
        int local_min=Integer.MAX_VALUE;
        int finder =-1;
        for  (int i=startX;i<endX;i++) {
            searchers[i].reset();//reset the step to that it can be used by parallel Algorithm
            local_min=searchers[i].find_valleys();
            if((!searchers[i].isStopped())&&(local_min<min))
            { //don't look at  those who stopped because hit exisiting path
                min=local_min;
                finder=i; //keep track of who found it
            }
            if(MonteCarloMinimization.DEBUG) System.out.println("Search "+searchers[i].getID()+" finished at  "
                    +local_min + " in " +searchers[i].getSteps());
        }
        double xCoord=0.0;
        double yCoord=0.0;
        if(finder==searchers.length){
            xCoord=searchers[finder-1].getTerrain().getXcoord(searchers[finder-1].getPos_row());
            yCoord=searchers[finder-1].getTerrain().getYcoord(searchers[finder-1].getPos_col());
        }
        else if(finder!=-1){
            xCoord=searchers[finder].getTerrain().getXcoord(searchers[finder].getPos_row());
            yCoord=searchers[finder].getTerrain().getYcoord(searchers[finder].getPos_col());
        }
        arrayList.add((double) min);arrayList.add(xCoord);arrayList.add(yCoord);
        return arrayList;
    }
    
 } 
