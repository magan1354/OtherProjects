import java.util.*;

class node
{		
		// declaring the left and right children of the node.
		node left;
		node right;
		
		// declaring the word and the meaning fields for a node object.
		String word;
		String meaning;
		
		// initializing the fields through a parameterized constructor.
		node(String p, String q)
		{
			this.word =p;
			this.meaning =q;
			
		}
}

class dictionary2final111
{	
	// declaring the root of the dictionary as a global variable.
	static node root=null;
	
	//function for the insertion of words into the dictionary.
	public static void insert(String p,String q)
	{		
			node tr_node=root;// a pointer to keep the track of the current node.
			node pr_node=root;
			if(pr_node!=null)
			{
				if((pr_node.word).equals(p))
				{
					System.out.println();
					System.out.println("duplicate element  found so there is no need to insert that element");
					System.out.println();
					return;
				}
			}
			
			node parent=null;// a pointer to keep the track of the parent of the currently pointed node.
			
			node temp=new node(p,q);// creation of a new node.
			// the word and meaning fields are initialized through the constructor.
			
			if(root==null)
			{	
				// if the root is being inserted, that is the first word is being inserted in the dictionary.
				root=temp;
				return;
				
			}
			
			while(tr_node!=null)// the loop runs until we reach the leaf node.
			{	
				
			    if(p.compareTo(tr_node.word)<0)
				{	
			    	//if the word to be inserted lies left to the word contained in the current node.
				    parent=tr_node;
					tr_node=tr_node.left;
					
				}
			    
			    else 
				{	
			    	//if the word to be inserted lies right to the word contained in the current node.
				    parent=tr_node;
					tr_node=tr_node.right;
					
				}
			    
			}
			
			
			if(parent!=null)
			{	
				//the parent is the leaf node now.
				if(p.compareTo(parent.word)<0)
				{	
					// the word will be inserted as the left child of the parent.
					parent.left=temp;
					
				}
				
				else
				{	
					//the word will be inserted as the right child of the parent.
					parent.right=temp;
					
				}
			}
		
	}
	
	//function to display the words in the dictionary in the alphabetical order.
	public static void inorderTraversal(node m)
	{	
		if(m==null)
	    {	
			//base case for recursive call.
			return;
			
	    }
		
	    else 
		{	
	    	//calling the function recursively on the left sub-tree.
	    	inorderTraversal(m.left);
	    	
	    	//printing the current root node.
			System.out.println("word: "+m.word);
			System.out.println();
			System.out.println("meaning: "+m.meaning);
			
			//calling the function recursively on the right sub-tree.
			inorderTraversal(m.right);
			
		}
	}
	
	// function to search a word in the dictionary.
	public static void findelement(String p)
	{	
		// a pointer to keep the track of the current node which is initialized to the root.
		node tr_node=root;
		
		if(root==null)
		{
			System.out.println("Dictionary is empty ");
			
		}
		
		else
		{	
			//a loop which runs till we find the word in the dictionary or end up traversing without finding the word.
			
			while(tr_node!=null && ((tr_node.word).compareTo(p)!=0))
			{
				if((tr_node.word).compareTo(p) > 0)
				{	
					//the word is searched in the left sub-tree.
					tr_node=tr_node.left;
					
				}
				
				else
				{	
					//the word is searched in the right sub-tree.
					tr_node=tr_node.right;
					
				}
			}
			
			if(tr_node==null)
			{	// if we end up traversing the whole tree without finding the word.
				System.out.println();
				System.out.println("Word not found ");
				
			}
			
			else
			{	
				//the word was found which made the loop break.
				System.out.println();
				System.out.println("Word found");
				System.out.println();
				System.out.println("The meaning of the word is"+"  "+tr_node.meaning);
				
			}
		}
		
	}
	
	//function to delete a word from the dictionary.
	public static void delete(String s)
	{	
		node current=root;
		node parent=root;
		boolean left=false;
		if(root==null)
		{
			System.out.println();
			System.out.println("Dictionary empty no deletion possible");
			return;
		}
		else
		{
			while(current!=null && (s.compareTo(current.word)!=0) )
			{
				parent=current;
				if(s.compareTo(current.word)<0)
				{	left=true;
					current=current.left;
				}
				
				else
				{	left=false;
					current=current.right;
				}
			}
			
			if(current==null)
			{
				System.out.println();
				System.out.println("word not found in the dictionary");
			}
			else
			{
				if(current==root)
				{
					if(root.left==null && root.right==null)
					{
						root=null;//case1
					}
					else if(root.left!=null && root.right==null)
					{
						root=root.left;//case2
					}
					else if(root.left==null && root.right!=null)
					{
						root=root.right;//case3
					}
					else
					{
						//case when root has 2 children and root is to be deleted
					}
				}
				
				else
				{
					if(current.left==null && current.right==null)
					{
						if(left==true)
						{
							parent.left=null;
						}
						else
						{
							parent.right=null;
						}
					}
					
					else if(current.left!=null && current.right==null)
					{
						if(left==true)
						{
							parent.left=current.left;
						}
						else
						{
							parent.right=current.left;
						}
					}
					
					else if(current.right!=null && current.left==null)
					{
						if(left==true)
						{
							parent.left=current.right;
						}
						else
						{
							parent.right=current.right;
						}
					}
					else
					{
						//when current has 2 children.
						node right_sub=current.right;
						node traverse=right_sub;
						node parent2=right_sub;
						while(traverse.left!=null)
						{	parent2=traverse;
						    
							traverse=traverse.left;
						}
						
						if(traverse.right==null && parent2!=traverse)
						{	parent2.left=null;
							traverse.left=current.left;
							traverse.right=current.right;
							if(left==true)
							{
								parent.left=traverse;
							}
							else
							{
								parent.right=traverse;
							}
							current.right=null;
							current.left=null;
							current=null;
						}
						else if(traverse.right==null && parent2==traverse)
						{
							traverse.left=current.left;
							if(left==true)
							{
								parent.left=traverse;
							}
							else
							{
								parent.right=traverse;
							}
							current.left=null;
							current.right=null;
							current=null;
						}
						
						else if(traverse.right!=null && parent2!=traverse)
						{
							//when successor has a right child.
							parent2.left=traverse.right;
							traverse.left=current.left;
							traverse.right=current.right;
							if(left==true)
							{
								parent.left=traverse;
							}
							else
							{
								parent.right=traverse;
							}
							current.right=null;
							current.left=null;
							current=null;
							
						}
						else
						{
							traverse.left=current.left;
							if(left==true)
							{
								parent.left=traverse;
								
							}
							else
							{
								parent.right=traverse;
							}
							current.left=null;
							current.right=null;
							current=null;
						}
					}
				}
			}
			
		}
	}
	public static void main(String args[]) throws Exception
	{
		System.out.println("This is a project made by"+" "+"1.Manraj"+":101610052"+" "+"2.Madanjeet"+":101603176"+" "+"3.Maganjot"+":101603179"+" "+"4.Manish"+":101603183"+" "+"5.Madhav"+":101603177");
		System.out.println();
		System.out.println("All are of batch:COE13");
		System.out.println();
		System.out.println("welcome to our own hand made dictionary");
		System.out.println();
		Scanner sc=new Scanner(System.in);
		int choice;
		outer:
		while(true){
		inner:
		do{
			System.out.println();
			System.out.println("Hello everyone,i am dictionary and i am here to perform following operation");
			 System.out.println();
			System.out.println("1.Insertion in dictionary");
			System.out.println();
			System.out.println("2.Deletion in dictionary");
			System.out.println();
			System.out.println("3.Searching in dictionary");
			System.out.println();
			System.out.println("4.Traversal in dictionary");
			System.out.println();
			System.out.println("5.Exit");
			System.out.println();
			System.out.println("Enter your own choice");
			System.out.println();
			choice=sc.nextInt();
		
			
		}
		while(choice<1||choice>5);
		System.out.println("\n");
				
			switch(choice){
				case 1:
				System.out.println("word to insert");
				String a=sc.nextLine();// extra line introduced.
				String pp=sc.nextLine();
				String ppp=pp.substring(0,1).toUpperCase();
				String word=ppp +pp.substring(1).toLowerCase();
				System.out.println("meaning");
				String mm=sc.nextLine();
				String mmm=mm.substring(0,1).toUpperCase();
				String meaning=mmm+mm.substring(1).toLowerCase();
				insert(word,meaning);
				break;
				case 2:
				System.out.println("enter the word to delete");
				String garbage=sc.nextLine();
				String ss=sc.nextLine();
				String sss=ss.substring(0,1).toUpperCase();
				String s=sss + ss.substring(1).toLowerCase();
				System.out.println("Are you sure? Y/N");
				char check=sc.next().charAt(0);
				if(check=='Y')
				{
					delete(s);
				}
				else
				{
					System.out.println("process Terminated!");
				}
				break;
				case 3:
				System.out.println("enter the word to be searched for");
				String m=sc.nextLine();//extra line introduced.
				String ww=sc.nextLine();
				String www=ww.substring(0,1).toUpperCase();
				String search_word=www + ww.substring(1).toLowerCase();
				if(root==null)
				{
					System.out.println("There is no word in the dictionary yet.");
				}
				else
				{
					findelement(search_word);
				}
				break;
				case 4:
				inorderTraversal(root);
				break;
				case 5:
				System.exit(0);
				default:
				System.out.println("you have entered wrong option\n");
				break;
			}
		}
	}
}
