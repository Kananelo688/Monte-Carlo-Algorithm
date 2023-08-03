package MonteCarloMini;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 *A DataCollector object is an object that is generally designed to collect data the experiment using system
 * generated inputs. It generates inputs( large set of inputs) and calls the main method and experiments the algorithm's performances.
 * The object creates a set of inputs. The parallel algorithm are evaluated for
 * several number of sequential cutoffs, for comparison purposes.
 *
 * */
public final class DataCollector {
    private static final int[] cutOffs={100,500,1000,1500,2000,4000,5000};//sequential cut-offs


    private static final int[] rows={1000,2000,2500,5000,6000,7000,8000,5500,6500,9000};
    private static final double DEFAULT_SEARCH_DENSITY=0.1; // default search density for experimental purposes

    public static final PrintWriter writer;

    static {
        try {
            writer = new PrintWriter(new FileOutputStream("data/data.txt",true));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static Random random=new Random();

    private static int[] starts={-1000,-500,0,500,500,9000,7000,0,5000,2500};

    public static void main(String[] args){
        DataCollector.writer.printf("System properties:\nOS Name:\t%s\nVersion:\t+%s\nArchitecture:\t%s\nProcessors:\t%d\n",
                System.getProperty("os.name"),System.getProperty("os.version"),
                System.getProperty("os.arch"),Runtime.getRuntime().availableProcessors());
        for(int i=0;i< cutOffs.length;i++){
            System.out.printf("Experimenting with sequential cut-off of %d...\n",cutOffs[i]);
            MonteCarloMinimizationParallel.SEQUENTIAL_CUTOFF=cutOffs[i];
            writer.printf("******************Results with sequential cut-off of %d ******************************\n",
                    MonteCarloMinimizationParallel.SEQUENTIAL_CUTOFF);
            for(int j=0;j<rows.length;j++){
                //obtain number of rows and columns randomly from the rows array
                int rowsNum=rows[random.nextInt(rows.length)], colNum=rows[random.nextInt(rows.length)];
                int xStart=starts[random.nextInt(rows.length)],
                        yStart=(starts[random.nextInt(rows.length)]),
                        xEnd=starts[random.nextInt(rows.length)],
                        yEnd=starts[random.nextInt(rows.length)];
                //check if xStart is actually less than xEnd
                if(xStart>xEnd){
                    int temp=xStart;
                    xStart=xEnd;
                    xEnd=temp;
                }
                if(yStart>yEnd){
                    int temp=yStart;
                    yStart=yEnd;
                    yEnd=temp;
                }
                String[] arguments= {String.valueOf(rowsNum),
                        String.valueOf(colNum),String.valueOf(xStart),String.valueOf(xEnd),
                        String.valueOf(yStart),String.valueOf(yEnd),
                        String.valueOf(DEFAULT_SEARCH_DENSITY),"0","1"};
                MonteCarloMinimization.main(arguments);
            }
        }
        //close the file after writing to it
        writer.close();


    }
}
