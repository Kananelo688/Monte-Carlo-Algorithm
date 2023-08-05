package MonteCarloMini;
import java.io.*;
import java.util.Random;

/**
 *A DataCollector object is an object that is generally designed to collect data the experiment using system
 * generated inputs. It generates inputs( large set of inputs) and calls the main method and experiments the algorithm's performances.
 * The object creates a set of inputs. The parallel algorithm are evaluated for
 * several number of sequential cutoffs, for comparison purposes.
 *
 * */
public final class DataCollector {
    /**
     * Specify values that will be used to benchmark the algorithms for speedup against grid-size, for fixed search density and sequential cut-off
     * */
    private static  final int[] grows={100,500,1000,1500,2000,3000,4000,5000,6000,7000,8000,9000};
    /**
     * Specify values fo search densities that will be used to benchmark the algorithms for  different search densities for fixed
     * grid-size and sequential cut-off;
     * */


    private static final double  [] densities={0.01,0.02,0.03,0.05,0.1,0.2,0.3,0.4,0.5,1,2,3,4,5,6};
    /**
     *
     * Specify values for sequential cut-offs to benchmark algorithms against sequential cut-offs. Parallel algorithms are highly
     * depended on the sequential cut-off
     * */
    private static final int [] cut_offs={1,10,100,1000,1500,1000,2000,3000,5000,6000,7000,8000};
    /**
     * Specify default sequential cut-off
     * */
    private static final double DEFAULT_CUT_OFF=100;
     /**
      * Specify default rows and columns (to make grid size of at most 800 000, to gather for density of 6,
      * which will give large number of searches (avoid heap overflow)
      * */
     private static final int DEFAULT_ROWS=4000,DEFAULT_COLS=200;
     /**
      * Default search density
      * */
     private static final double DEFAULT_DENSITY=0.1;

     public  static PrintWriter DENSITY;
    public  static PrintWriter GRID_SIZE;
    public  static PrintWriter CUT_OFFS;
    public static boolean DensityBenchMark=false;
    public static boolean CutoffBenchMark=false;
    public static boolean gridBenchMark=false;
     /**
      * Invokes main method of the MonteMinimization class and passes appropriate arguments for benchmarking
      * **/

     public static void main(String[] args) throws FileNotFoundException {
         System.out.println("Collecting data...");
         //Collecting data for speedup against grid-sze for fixed search density and sequential cuf-off
         System.out.println("Benchmarking  against search densities...");
         for(int i=0;i< densities.length;i++){
             DensityBenchMark=true;
             String filename="data/Search_density_data_"+Runtime.getRuntime().availableProcessors()+"Core.txt";
             if(DENSITY==null){
                 DENSITY=new PrintWriter(new FileOutputStream(filename));
             }
             String[] argum={String.valueOf(DEFAULT_ROWS),String.valueOf(DEFAULT_COLS),"-2000","2000","-100","100",
                     String.valueOf(densities[i]),"0","1"};
             DENSITY.print(densities[i]+" ");
             MonteCarloMinimization.main(argum);
         }
         DENSITY.close();
         System.out.println("Done.");
         System.out.println("BenchMarking for speedup against grid-size, for fixed search density and sequential cut-off....");
         Random rand=new Random();
         for(int i=0;i< grows.length;i++){
             DensityBenchMark=false;gridBenchMark=true;
             String filename="data/Grid_Size_data_"+Runtime.getRuntime().availableProcessors()+"Core.txt";
             if(GRID_SIZE==null){
                 GRID_SIZE=new PrintWriter(new FileOutputStream(filename));
             }
             int rows=grows[rand.nextInt(grows.length)], cols=grows[rand.nextInt(grows.length)];
             String[] argum={String.valueOf(rows),String.valueOf(cols),
                     "-2000","2000","-100","100",
                     String.valueOf(DEFAULT_DENSITY),"0","1"};
             GRID_SIZE.print(rows*cols+" ");
             MonteCarloMinimization.main(argum);
         }
         GRID_SIZE.close();
         System.out.println("Benchmarking for speedup against sequential cut-offs....");
         for(int i=0;i< cut_offs.length;i++){
             DensityBenchMark=false;gridBenchMark=false;CutoffBenchMark=true;
             String filename="data/cutoff_data_"+Runtime.getRuntime().availableProcessors()+"Core.txt";
             if(CUT_OFFS==null){
                 CUT_OFFS=new PrintWriter(new FileOutputStream(filename));
             }
             int rows=grows[rand.nextInt(grows.length)], cols=grows[rand.nextInt(grows.length)];
             String[] argum={String.valueOf(DEFAULT_ROWS),String.valueOf(DEFAULT_COLS),
                     "-2000","2000","-100","100",
                     String.valueOf(10),"0","1"};
             CUT_OFFS.print(cut_offs[i]+" ");
             MonteCarloMinimizationParallel.SEQUENTIAL_CUTOFF=cut_offs[i];
             MonteCarloMinimization.main(argum);
         }
         CUT_OFFS.close();
         System.out.println("BenchMarking completed....");
     }
}
