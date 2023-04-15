//Dai, Li. “Liliarizona/Hushgame.” GitHub, 14 May 2017, github.com/liliarizona/Hushgame/blob/master/hushgame.java. 
// Parts of code from author above, also cited in PC and raed me 
import java.io.*;
import java.util.Random;

public class CIS129_FinalProject_CharlieHood {

	public static void main(String[] args) throws Exception {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		Random rand = new Random();
		
		int mrows = 5;
		int mcols = 5;
		
		char[][] board = new char[mrows][mcols];
		boolean[][] isHidden = new boolean[mrows][mcols];
		
		
		
		int players = 0;
		players = numInput(2, 4, "Hush, Hush Kleine Hexe memory board game \nHow many players? (2-4)");
		
		fillArray(board, isHidden);
	    printArray(board,isHidden);
	    
	    int round = 0;
	    int die;
	    boolean win = false;
	    
	    	while (!win) {
	    		round = round +1;
	    		int playerNum;
	    		for(playerNum=1; playerNum<players + 1; playerNum++) {
	    			
	    			if(!win) {
	    				System.out.println("Player " + playerNum + "'s turn, Round " + round + "!\n");
	    			}
	    		
	    		
	    		boolean roll = true;
	    		while(roll & (!win)) {
	    			System.out.println("press enter to roll the die");
	    			String dieRoll;
	    			dieRoll= reader.readLine();
	    			die = rand.nextInt(6);
	    			
	    			
	    			if(die==0) {
	    				int shuffle;
	    				System.out.println("You rolled the shuffle play!");
	    				shuffle = numInput(2,5, "How many times would you like to shuffle the board? (2-5)");
	    				
	    					
	    				for (int i =1; i < shuffle; i++) {
	    						System.out.println("shuffle number " + i + " out of " + i);
	    						 int firstCol=0, secondCol=0;
	                             firstCol=numInput(1, 5, "input the first column of the shuffler(1-5)")-1;
	                             secondCol=firstCol;
	                             while(secondCol==firstCol)
	                             {
	                                  secondCol=numInput(1, 5, "input the second column of the shuffler(1-5)")-1;
	                                  if(secondCol==firstCol){
	                                    System.out.println("Invalid value, please enter a colomn different from the first column chosen");
	                                  }
	                             }
	                             ShuffleBoard(board,firstCol,secondCol);
	                          }
	                          System.out.println("Done shuffling, next player's turn.");
	                          roll=false;
	    		     }else{
	                     int inputCol=0;
	                     inputCol=numInput(1, 5, "Player " + playerNum + " rolled a " + GetRollColor(die) + "\nChoose which column has color "+ GetRollColor(die)+", please enter 1-5")-1;
	                     
	                     
	                     if(checkwitch(board,GetWitchColor(die),inputCol)){
	                         System.out.println("you guessed right!! "+ GetWitchColor(die)+" move one block forward and you can roll again for another turn!");
	                         
	                         //move one block
	                         int myRow;
	                         myRow=FindRow(board,inputCol);
	                         
	                         board[myRow][inputCol]=0;
	                         isHidden[myRow][inputCol]=false;
	                         board[myRow-1][inputCol]=(char) die;
	                         isHidden[myRow-1][inputCol]=false;
	                         printArray(board,isHidden);
	                         isHidden[myRow-1][inputCol]=true;
	                         
	                         //check if there is a winner
	                         win=Winner(board);
	                         if(win){
	                           System.out.println("\nCongrats, player "+playerNum+" won!!!");
	                           printFinalArray(board, isHidden);
	                         }
	                       }else{
	                          System.out.println("Sorry, "+GetRollColor(die)+" is not in column "+ (inputCol+1)+".");
	                          System.out.println("Next players turn!\n");
	                          roll=false;
	                          printArray(board,isHidden);
	    					}
	    		     }
	    		}
	    		}
	    	}
	    	}
	    	
	
		
	
	
	
	
	   public static void fillArray(char[][] board, boolean[][] isHidden){
		      int i, j;
		     for (i = 0; i<board.length ; i++ ) { 
		       for(j = 0; j <board[0].length; j++) { 
		    	   board[i][j] = 0;
		    	   isHidden[i][j]=false;
		       }
		     }
		     
		     board[board.length-1][0]=1;
		     isHidden[board.length-1][0]=true;
		     board[board.length-1][1]=2;
		     isHidden[board.length-1][1]=true;
		     board[board.length-1][2]=3;
		     isHidden[board.length-1][2]=true;
		     board[board.length-1][3]=4;
		     isHidden[board.length-1][3]=true;
		     board[board.length-1][4]=5;
		     isHidden[board.length-1][4]=true;
		     
		    }

	
    public static void ShuffleBoard(char[][] board, int fcol,int scol){
        int frow,srow;
        frow=FindRow(board, fcol);
        srow=FindRow(board, scol);
        char temp;
        temp=board[frow][fcol];
        board[frow][fcol]=board[srow][scol];
        board[srow][scol]=temp;
     }
    
    public static int FindRow(char[][] board, int col){
        int i;
        for (i = 0; i<board.length ; i++ ) { 
         if(board[i][col]>0){ 
           return i;
         }
        }
        return i;
      }
    
    public static void printArray(char[][] board, boolean[][] isHidden) {
        int i, j;
        
        for (i = 0; i<board.length ; i++ ) { 
          System.out.print((i+1)+" ");
          for(j = 0; j <board[0].length; j++) { 
            if(isHidden[i][j]){
              System.out.print( hideWitch(board[i][j]) + " ");
            } else {
              System.out.print( GetWitchColor(board[i][j]) + " ");
            }
          }
          System.out.println();
        }
        System.out.print("  ");
        for(i=0;i<board.length;i++)
        {
          System.out.print((i+1)+" ");
        }
        System.out.println();
      }
	public static int numInput(int min, int max, String msg) {
		   InputStreamReader input = new InputStreamReader(System.in);
		   BufferedReader reader = new BufferedReader(input); 
		   
		   int out = -1;
		   while(out<min | out>max) {
			   System.out.println(msg);
			   try {
			   out = Integer.parseInt(reader.readLine());
			   } catch (Throwable t) {
				   System.out.println("Error, enter an number");
			   }
		   }
		   return out;
	}
	   public static String GetWitchColor(int a){
		      
		      if (a==0)
		        return "*";
		      else if (a==1)
		        return "G";        
		      else if (a==2)
		        return "R";   
		      else if (a==3)
		        return "B";         
		      else if (a==4)
		        return "O";   
		      else if (a==5)
		        return "Y"; 
		      else
		        return " ";

		    }
	   public static String GetRollColor(int a){
		      
		      if (a==0)
		        return "*";
		      else if (a==1)
		        return "Green";        
		      else if (a==2)
		        return "Red";   
		      else if (a==3)
		        return "Blue";         
		      else if (a==4)
		        return "Orange";   
		      else if (a==5)
		        return "Yellow"; 
		      else
		        return " ";

		    }
	    public static String hideWitch(int a){
	        if(a==0)
	          return "*";   
	        else  
	          return "X";
	        
	      }
	   public static boolean checkwitch(char[][] board, String color, int column){
		      boolean hat=false;
		      int i=0;
		      while(i<board[0].length && !hat) {
		        if(GetWitchColor(board[i][column]).equals(color)){ 
		        	hat=true;
		        }
		        i++;
		      }
		      return hat;
		    }
	   public static boolean Winner(char[][] board){
		      boolean iswinner=false;
		      int i = 0;
		      while (i <board[0].length && !iswinner) {
		         if(board[0][i] > 0){
		           iswinner=true;
		         }
		         else i++;
		      }
		      return iswinner;
		   }
	   
	   public static void printFinalArray(char[][] board, boolean[][] isHidden) {
	        int i, j;
	        
	        for (i = 0; i<board.length ; i++ ) { 
	          System.out.print((i+1)+" ");
	          for(j = 0; j <board[0].length; j++) { 
	            if(isHidden[i][j]){
	              System.out.print( showWitch(board[i][j]) + " ");
	            } else {
	              System.out.print( GetWitchColor(board[i][j]) + " ");
	            }
	          }
	          System.out.println();
	        }
	        System.out.print("  ");
	        for(i=0;i<board.length;i++)
	        {
	          System.out.print((i+1)+" ");
	        }
	        System.out.println();
	      }
	   public static String showWitch(int a){
	        if(a==0)
	          return "*";   
	        else if (a==1)  
	          return "G";
	        else if (a==2)
	        	return "R";
	        else if (a==3)
	        	return "B";
	        else if (a==4)
	        	return "O";
	        else if (a==5)
	        	return "Y";
	        else 
	        	return " ";
	        
	      }
	}
