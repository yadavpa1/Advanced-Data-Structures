
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class Hashing {
    static int size=100;
    public static void main(String[] args) {
        Random rand=new Random();
        LinearTable table=new LinearTable(size);
        int value;
        double loadFactor[]=new double[size];
        double collisions1[]=new double[size];
        double collisions2[]=new double[size];
        double collisions3[]=new double[size];
        for(int i=0;i<size;i++){
            loadFactor[i]=(i+1)/100D;
        }
        Set<Integer> generated=new LinkedHashSet<>();
        while(generated.size()<size){
            value=rand.nextInt(size);
            generated.add(value);
        }
        int i=0;
        for(Integer val:generated){
            System.out.print(val+ " ");
            if(i>0){
                collisions1[i]=collisions1[i-1]+table.insert(val);
                collisions2[i]=collisions2[i-1]+table.insertQuadratic(val);
                collisions3[i]=collisions3[i-1]+table.insertDoubleHashing(val);
            }else{
                collisions1[0]=table.insert(val);
                collisions2[i]=table.insertQuadratic(val);
                collisions3[i]=table.insertDoubleHashing(val);
            }
            i++;   
        }
        table.printHashtable();
        //printComparison(loadFactor,collisions1);
        XYChart chart=QuickChart.getChart("Linear Probing","X","Y","y(x)",loadFactor,collisions1);
        new SwingWrapper(chart).displayChart();
        XYChart chart2=QuickChart.getChart("Quadratic Probing","X","Y","y(x)",loadFactor,collisions2);
        new SwingWrapper(chart2).displayChart();
        XYChart chart3=QuickChart.getChart("Double Hashing","X","Y","y(x)",loadFactor,collisions3);
        new SwingWrapper(chart3).displayChart();
    }
    private static void printComparison(double l[],double c[]){
        System.out.println("\nLoad Factor\t\tNo.of Collisions");
        System.out.println("====================================");
        for(int i=0;i<size;i++){
            System.out.println(l[i]+"  "+c[i]);
        }
    }
    
}
class LinearTable{
    private int size;
    private int keys[];
    private int values[];
    
    public LinearTable(int size){
        this.size=size;
        keys=new int[size];
        values=new int[size];
        for(int i=0;i<size;i++){
            keys[i]=i;
            values[i]=0;
        }  
    }
    public int getSize() //function to get size of hash table
    {
        return size;
    }
    public boolean isFull() //Function to check if hash table is full 
    {
        return size == 100;
    }
    public boolean isEmpty() //Function to check if hash table is empty 
    {
        return getSize() == 0;
    }
    public int get(){//function to get key
        return 0;
    }
    private int hash(int val){
        return val % 15;
    }
    private int hash2(int val){
        return val% 21;
    }
    public int insert(int val){
        int index= hash(val);
        int num_of_collisions=0;
        int i=index;
        do{
            if(values[i]==0){
                values[i]=val;
                if(i==index)
                    return 0;
                else
                    return num_of_collisions;
            }
            i=(i+1)%size;
            num_of_collisions++;
        }while(i!=index);
      return num_of_collisions;
    }
    
    public int insertQuadratic(int val){
        int index=hash(val);
        int num_of_collisions=0;
        int i=index,t=1;
        do{
            if(values[i]==0){
                values[i]=val;
                if(i==index)
                    return 0;
                else
                    return num_of_collisions;
            }
            i=(i+(t*t))%size;
            t++;
            num_of_collisions++;
        }while(i!=index);
      return num_of_collisions;
    }
    
    public int insertDoubleHashing(int val){
        int index=hash(val);
        int num_of_collisions=0;
        int i=index,t=1;
        do{
            if(values[i]==0){
                values[i]=val;
                if(i==index)
                    return 0;
                else
                    return num_of_collisions;
            }
            i=(i+(t*hash2(val)))%size;
            t++;
            num_of_collisions++;
        }while(i!=index);
      return num_of_collisions;
    }
    
    public void printHashtable(){
        System.out.println("Hash Table:");
        for(int i=0;i<size;i++){
            System.out.println(keys[i]+" "+ values[i]);
        }
    }
}
