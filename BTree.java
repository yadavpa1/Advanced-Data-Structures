/* Write a program to implement B-Tree
   Consider key in given sequence for insertion: F,S,Q,K,C,L,H,T,V,W,M,R,N,P,A,B,X,Y,D,Z,E.
   min.degree=2
   min.degree=4
 */

public class BTree {
    private static int t=3;
    private static BNode root=new BNode(t,null);
    public static void main(String[] args) {
        BTree tree= new BTree();
        int a[]= {'F','S','Q','K','C','L','H','T','V','W','M','R','N','P','A','B','X','Y','D','Z','E'};
        //int a[]={10,20,30,40,50,60,70,80,90};
        for(int i=0;i<a.length;i++){
            tree.BTree_insert(tree, a[i]);
        }
        tree.print(root);
    }
    
        public void print(BNode node){
	for(int i = 0; i < node.no_of_keys; i++){
		System.out.print((char)node.keys[i]+" " );//this part prints root node
	}
        if(!node.leaf){//this is called when root is not leaf;
            for(int j = 0; j <= node.no_of_keys ; j++){//in this loop we recurse in preorder fashion				  
		if(node.children[j] != null){ 
                    System.out.println();
                    print(node.children[j]);     
		}
            }
	}
    }
    private void BTree_insert(BTree T,int k){
        BNode r = T.root;
        BNode s = null;
        if ( r.no_of_keys == 2*t -1){ //root node is full
            s = new BNode(t,null);
            T.root = s;
            s.no_of_keys=0;
            s.leaf = false;
            s.children[0]=r;
            BTree_split_child(s,0);
            BTree_insert_nonfull(s,k);
        }
        else{
            BTree_insert_nonfull(r,k);
        }
    }
    
    private void BTree_split_child(BNode x, int i){
        BNode z = new BNode(t,null);
        BNode y = null;
        z.no_of_keys = t-1;
        y = x.children[i];
        z.leaf = y.leaf;
        for(int j = 0; j< t-1; j++){ //shifting keys
            z.keys[j]=y.keys[j+t];
        }
        if(!(z.leaf)){
            for(int j = 0;j< t; j++){ //shifting children pointers
                z.children[j]= y.children[j+t];
            }
        }
        y.no_of_keys = t-1;
        for(int j= x.no_of_keys;j>=i+1; j--){ //pushing key in x requires rearranging child nodes
            x.children[j+1]=x.children[j];
        }
        x.children[i+1]= z;
        for(int j=x.no_of_keys - 1;j>=i;j--){
            x.keys[j+1]=x.keys[j];
        }
        x.no_of_keys = x.no_of_keys +1;
        x.keys[i] = y.keys[t-1];
    }
    
    private void BTree_insert_nonfull(BNode x, int k){
        int i= x.no_of_keys-1;
        if(x.leaf){
            while(i>=0 && k < x.keys[i]){
                x.keys[i+1] = x.keys[i];
                i--;
            }
            x.keys[i+1]=k;
            x.no_of_keys = x.no_of_keys +1;
        }
        else{
            while(i>=0 && k< x.keys[i]){
                i--;
            }
            if(x.children[i+1].no_of_keys == 2*t -1){
                BTree_split_child(x,i+1);
                if(k > x.keys[i+1])
                    i++;
            }
            BTree_insert_nonfull(x.children[i+1],k);
        }
    }
}

class BNode{
    static int t; //min no. of keys
    int no_of_keys;
    boolean leaf;
    int keys[];
    BNode children[]; //array of child references
    BNode parent;
    
    public BNode(int t, BNode parent){
        this.t=t;
        this.parent=parent;
        keys = new int[2*t-1];
        children = new BNode[2*t];
        this.leaf=true; //every node is initially leaf
        this.no_of_keys=0;
    }
    
    public int getValue(int index){ //returns key value at given index
        return keys[index];
    }
    
    public BNode getChild(int index){
        return children[index];
    }
    
}
