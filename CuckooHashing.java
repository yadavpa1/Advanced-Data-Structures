//Input: 20,50,53,75,100,67,105,3,36,39
//h1(x)=x mod 11
//h2(x)=(key/11)mod 11
public class CuckooHashing {
    static int size=11;
    static Table table[][]=new Table[2][size];
    static Key keys[]=new Key[size];
    
    public static void main(String[] args) {
        Helper h=new Helper();
        int input[]={20,50,53,75,100,67,105,3,36,39,6};
        for(int i=0;i<input.length;i++){
            keys[i]=new Key(input[i],h.hash1(input[i]),h.hash2(input[i]));
            //System.out.println(keys[i]);
        }
        for(int i=0;i<2;i++){
            for(int j=0;j<size;j++){
                table[i][j]=new Table(0);
            }
        }
        cuckoo();
    }
    public static void cuckoo(){
        for(int i=0;i<size;i++){ //start with placing every key at is position as given by h1(x)
            place(keys[i],0,keys[i].x,0);
            printTable();
        }
        //printTable();
    }
    
    public static void place(Key key,int i,int col,int count){
        Key placer=null;
        if(count==size){//if function has been recursively called max number of times, stop & declare cycle.
            System.out.println(key.input+" unpositioned\nCycle present. Rehash");
            return;
        }
        if(table[i][col].val!=0){
            int dis=table[i][col].val;
            table[i][col].val=key.input;
            for(int j=0;j<size;j++){
                if(keys[j].input==dis)
                    placer=keys[j];
            }
            i=(i==0)?1:0;
            if(i==1) col=placer.y;
            else col=placer.x;
            count++;
            place(placer,i,col,count); 
        }
        else{
            table[i][col].val=key.input;
           // System.out.println(key.input);
        }   
    }
    public static void printTable(){
        System.out.println("Final Hash Table:");
        for(int i=0;i<2;i++){
            for(int j=0;j<size;j++){
                System.out.print(table[i][j]+"\t");
            }
            System.out.println("");
        }
    }
}
class Helper{
    public int hash1(int value){
        return value%11;
    }
    
    public int hash2(int value){
        return (value/11)%11;
    }   
}
class Table{
    int val;
    public Table(int val){
        this.val=val;
    }
    public String toString(){
        return val+"";
    }
}
class Key{
    int input;
    int x;
    int y;
    public Key(int input,int x,int y){
        this.input=input;
        this.x=x;
        this.y=y;
    }
    public String toString(){
        return x+"\t"+y;
    }
}