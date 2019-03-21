
public class RedBlackBST {
    //Root initialized to nil
    private RedBlackNode nil=new RedBlackNode();
    private RedBlackNode root=nil;
         
    public static void main(String[] args) {
        RedBlackBST tree=new RedBlackBST();
        int a[]={86,108,111,74,99,72,78};
        for(int i=0;i<a.length;i++){
            RedBlackNode temp=new RedBlackNode(a[i]);
            tree.RB_insert(tree,temp);
        }
        inorder(tree,tree.root);
    }
    
    public RedBlackBST(){
        nil.setColor(0);
        root.left= nil;
        root.right=nil;
        root.parent=nil;
    }
    
    private static void inorder(RedBlackBST T,RedBlackNode root){//left-Root-right
        if(!root.equals(T.nil)){
            inorder(T,root.left);
            System.out.println(root.data+" "+root.color);
            inorder(T, root.right);
        }
    }
    private void left_rotate(RedBlackBST T,RedBlackNode x){//x is to be left-rotated
        RedBlackNode y;
        y=x.right;
        x.right=y.left;
        if(!y.left.equals(T.nil))
            y.left.parent=x;
        y.parent=x.parent;
        if(x.parent.equals(T.nil))
            T.root=y;
        else if(x.equals(x.parent.left))
            x.parent.left=y;
        else
            x.parent.right=y;
        y.left=x;
        x.parent=y; 
    }
    private void right_rotate(RedBlackBST T,RedBlackNode y){
        RedBlackNode x;
        x=y.left;
        y.left=x.right;
        if(!x.right.equals(T.nil))
            x.right.parent=y;
        x.parent=y.parent;
        if(y.parent.equals(T.nil))
            T.root=x;
        else if(y.equals(y.parent.left))
            y.parent.left=x;
        else
            y.parent.right=x;
        x.right=y;
        y.parent=x;
    }
    private void RB_insert(RedBlackBST T,RedBlackNode z){
        RedBlackNode y=T.nil;
        RedBlackNode x=T.root;
        while(!x.equals(T.nil)){
            y=x;
            if(z.data<x.data)
                x=x.left;
            else
                x=x.right;
        }
        z.parent=y;
        if(y.equals(T.nil))
            T.root=z;
        else if(z.data<y.data)
            y.left=z;
        else
            y.right=z;
        z.right=z.left=T.nil;
        z.color=RedBlackNode.RED;
        RB_fixup(T,z);
    }
    
    private void RB_fixup(RedBlackBST T,RedBlackNode z){
        RedBlackNode y=null;
        while(z.parent.color== RedBlackNode.RED){ 
            if(z.parent.equals(z.parent.parent.left)){
                y= z.parent.parent.right; //uncle
                if(y.color==RedBlackNode.RED){ //case 1
                    z.parent.color=y.color=RedBlackNode.BLACK;
                    z.parent.parent.color=RedBlackNode.RED;
                    z=z.parent.parent;
                }
                else{
                    if(z.equals(z.parent.right)){ //case 2
                        z=z.parent;
                        left_rotate(T,z);
                    }
                    z.parent.color=RedBlackNode.BLACK; //case 3
                    z.parent.parent.color=RedBlackNode.RED;
                    right_rotate(T,z.parent.parent);
                }
            }
            else{
                y= z.parent.parent.left; //uncle
                if(y.color==RedBlackNode.RED){ //case 1
                    z.parent.color=y.color=RedBlackNode.BLACK;
                    z.parent.parent.color=RedBlackNode.RED;
                    z=z.parent.parent;
                }
                else{
                    if(z.equals(z.parent.left)){ //case 2
                        z=z.parent;
                        right_rotate(T,z);
                    }
                    z.parent.color=RedBlackNode.BLACK; //case 3
                    z.parent.parent.color=RedBlackNode.RED;
                    left_rotate(T,z.parent.parent);
                }   
            }
        }
        T.root.color=RedBlackNode.BLACK;
    }
}
class RedBlackNode{
    public static final int BLACK=0;
    public static final int RED=1;
    RedBlackNode parent;
    RedBlackNode left,right;
    int data;
    int color;
    RedBlackNode(){
        color=RED;
        parent=null;
        left=null;
        right=null;
    }
    RedBlackNode(int key){
        super();
        this.data=key;
    }
    public void setColor(int val){
        if(val==0)
            this.color=BLACK;
        else
            this.color=RED;
    }
}


