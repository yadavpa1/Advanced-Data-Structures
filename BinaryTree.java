
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {
    Node root;
    public static void main(String[] args) {
        int a[]={1,2,3,4,0,5,6};
        BinaryTree t=new BinaryTree();
        t.root=createTree(a,t.root,0);
        inorder(t.root);
        System.out.println("");
        preorder(t.root);
        System.out.println("");
        postorder(t.root);
        System.out.println("\nLevel order traversal:");
        levelorder(t.root);
        System.out.println("Inorder iterative:");
        inorder_iterative(t.root);
        System.out.println("Pre-order iterative");
        preorder_iterative(t.root);
        System.out.println("Height of binary tree:"+ (findheight(t.root)-1));
        System.out.println("Total no. of leaf nodes:"+getLeafCount(t.root));
        System.out.println("Maximum element:"+findmax(t.root));
       // System.out.println("Minimum element in binary tree:"+findmin(t.root));
       // System.out.println("Total no.of non-leaf nodes:"+getNonLeafCount(t.root));
        
        
    }
    public static Node createTree(int arr[],Node root,int i){
        if(i<arr.length){
            Node temp= new Node(arr[i]);
            root=temp;
            root.left= createTree(arr,root.left,2*i+1); //inserting left child
            root.right=createTree(arr,root.right,2*i+2); //inserting right child
        }        
        return root;
    }
    
    private static void inorder(Node root){//left-Root-right
        if(root!=null){
            inorder(root.left);
            if(root.data!=0)
                System.out.print(root.data+" ");
            inorder(root.right);
        }
    }
    
    public static void preorder(Node root){//root-left-right
        if(root!=null){
            if(root.data!=0)
                System.out.print(root.data+" ");
            preorder(root.left);
            preorder(root.right);
        }
    }
    
    public static void postorder(Node root){//left-right-root
        if(root!=null){
            postorder(root.left);
            postorder(root.right);
            if(root.data!=0)
                System.out.print(root.data+" ");
        }
    }
    
    public static int findheight(Node root){ //computes the height of the tree: longest path from root to farthest leaf node
        if(root==null)
            return 0;
        int lheight= findheight(root.left);
        int rheight=findheight(root.right);
        if(lheight<= rheight)
            return rheight+1;
        else
            return lheight+1;
    }
    
    public static int getLeafCount(Node root){
        if(root==null)
            return 0;
        if(root.left==null && root.right==null)
            return 1;
        return getLeafCount(root.left)+ getLeafCount(root.right);
        
    }
    
    public static int findmax(Node root){
        if(root==null)
            return 0;
        int val=root.data;
        int leftval=findmax(root.left);
        int rightval=findmax(root.right);
        if(val<leftval)
            val=leftval;
        if(val<rightval)
            val=rightval;
        return val;
    }
    
    public static void levelorder(Node root){
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node node=q.poll();
            if(node.data!=0)
                System.out.println(node.data);
            if(node.left!=null)
                q.add(node.left);
            if(node.right!=null)
                q.add(node.right);
        }  
    }
    
    public static void inorder_iterative(Node root){
        Stack<Node> stack=new Stack();
        if(root==null)
            return;
        Node curr=root;
        while(!stack.isEmpty()||curr!=null){
            while(curr!=null){
                stack.push(curr);
                curr=curr.left;                
            }
            Node popped_item=stack.pop();
            System.out.println(popped_item.data);
            curr=popped_item.right;       
        }
    }   
    
    public static void preorder_iterative(Node root){
        Stack<Node> stack=new Stack<>();
        if(root==null)
            return;
        Node curr=root;
        while(!stack.isEmpty()||curr!=null){
            while(curr!=null){
                if(curr.data!=0)
                    System.out.println(curr.data);
                stack.push(curr);
                curr=curr.left;
            }
            Node popped_item=stack.pop();
            curr=popped_item.right;
        }
    }
}
class Node{
  Node left;
  Node right;
  int data;
  Node(int data){
      this.data=data;
      this.left=null;
      this.right=null;
  }   
}
//write a program to compute load factor and no.of collisions required 
//in long random sequence using linear probing,quadratic probing and random probing.
//Plot the graph between load factor and no. of collisions.