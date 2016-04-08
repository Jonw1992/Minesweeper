import java.util.Scanner;
import java.io.Serializable;
import java.io.*;



public class MineGrid implements Serializable
{

public int[][] grid;
public boolean [][] revealed,flagged;
public int mines=30,minesx = 0,minesy = 0, gridx=0, gridy=0;

//SETS UP GRID***********************
     public MineGrid(int x,int y) 
	 {	
		setGrid(x);
		setMines(y);
		grid = new int[gridx][gridy];
		revealed = new boolean[gridx][gridy];
		flagged = new boolean[gridx][gridy];
		
		for(int i=0;i<mines;i++)
		{
			minesx=((int)(Math.random() * gridx-1) + 1);
			minesy=((int)(Math.random() * gridy-1) + 1);
			grid[minesx][minesy]=9;
		}
		
		for(int i=0;i<gridx;i++)
		{
			for(int j=0;j<gridy;j++)
			{
				revealed[i][j] = false;
				flagged[i][j] = false;
			}
		}
		
		fillEmpty();	
		
		
	}
//-----------------------------------------	
	public void setGrid(int x)
	{
		gridx =x ;
		gridy = x;
	}
	
	public void setMines(int x)
	{
		mines = x;
	}
//FILL EMPTY GRID CELLS****************	
	public void fillEmpty()
	{
		for(int i=0;i<gridx;i++)
		{
			for(int j=0;j<gridy;j++)
			{
				int neighbor=0;
			
				if(grid[i][j]!=9)
				{
					if((i==0&&j==0))
					{
						if(grid[i+1][j]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i+1][j+1]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i][j+1]==9)
						{
							 neighbor=neighbor+1;						
						}						
					}
					
					else if(i==0&&j==(gridy-1))
					{
						if(grid[i+1][j]==9)
						{
							 neighbor=neighbor+1;
						}
						if(grid[i+1][j-1]==9)
						{
							 neighbor=neighbor+1;
						}
						if(grid[i][j-1]==9)
						{
							 neighbor=neighbor+1;
						}						
					}
					
					else if(i==(gridx-1)&&j==0)
					{
						if(grid[i-1][j]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i-1][j+1]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i][j+1]==9)
						{
							 neighbor=neighbor+1;						
						}						
					}
					
					else if(i==(gridx-1)&&j==(gridy-1))
					{
						if(grid[i-1][j]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i-1][j-1]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i][j-1]==9)
						{
							 neighbor=neighbor+1;						
						}						
					}
				
					else if(i==0&&(j!=0&&j!=(gridy-1)))
					{
						if(grid[i+1][j]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i+1][j-1]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i+1][j+1]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i][j-1]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i][j+1]==9)
						{
							 neighbor=neighbor+1;						
						}						
					}
					
					else if(i==(gridx-1)&&(j!=0&&j!=(gridy-1)))
					{
						if(grid[i-1][j]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i-1][j-1]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i-1][j+1]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i][j-1]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i][j+1]==9)
						{
							 neighbor=neighbor+1;						
						}							
					}
					else if(j==0&&(i!=0&&i!=(gridx-1)))
					{
						if(grid[i-1][j]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i+1][j]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i-1][j+1]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i+1][j+1]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i][j+1]==9)
						{
							 neighbor=neighbor+1;						
						}							
					}
					else if(j==(gridy-1)&&(i!=0&&i!=(gridx-1)))
					{
						if(grid[i-1][j]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i+1][j]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i-1][j-1]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i+1][j-1]==9)
						{
							 neighbor=neighbor+1;						
						}
						if(grid[i][j-1]==9)
						{
							 neighbor=neighbor+1;						
						}							
					}
					else if(i!=0&&i!=(gridx-1)&&j!=0&&j!=(gridy-1))
					{
						if(grid[i-1][j]==9)
						{
							 neighbor=neighbor+1;			
						}
						if(grid[i+1][j]==9)
						{
							 neighbor=neighbor+1;	
						}
						if(grid[i-1][j+1]==9)
						{
							 neighbor=neighbor+1;					
						}
						if(grid[i+1][j+1]==9)
						{
							 neighbor=neighbor+1;					
						}
						if(grid[i][j+1]==9)
						{
							 neighbor=neighbor+1;					
						}
						if(grid[i][j-1]==9)
						{
							 neighbor=neighbor+1;					
						}
						if(grid[i-1][j-1]==9)
						{
							 neighbor=neighbor+1;					
						}
						if(grid[i+1][j-1]==9)
						{
							 neighbor=neighbor+1;						
						}	
					}
					grid[i][j]=neighbor;
				}
			
			}
		}
		
	}
//PLAY VIA COMMANDLINE****************
	public static void playCommandSweeper()
	{	
		Scanner scan = new Scanner(System.in);
		int coordx=0,coordy=0,cmdcount=1;
		boolean bomb=false;
		int[][] userGrid = new int[10][10];
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
					System.out.printf("  _  ");
			}	
			System.out.printf("\n");
		}	
		
		while(Minesweeper.squares.grid[coordx][coordy]!=9 && cmdcount!=0)
		{
			System.out.printf("\nEnter the x coordinate: ");
			coordy = scan.nextInt();
			System.out.printf("\nEnter the y coordinate: ");
			coordx = scan.nextInt();	
			
			userGrid[coordx][coordy]=-1;
		
			cmdcount=0;
			
			if(Minesweeper.squares.grid[coordx][coordy]!=9)
			{

				for(int i=0;i<10;i++)
				{
					for(int j=0;j<10;j++)
					{
						if(userGrid[i][j]>=0)
						{
							System.out.printf("  -  ");
							if(Minesweeper.squares.grid[i][j]!=9)
							{
								cmdcount++;
							}
						}
						else
						{
							if(Minesweeper.squares.grid[i][j]==9)
							{
								System.out.printf("  *  ");
							}
							else
							{
								System.out.printf("  "+Minesweeper.squares.grid[i][j]+"  ");
							}
						}
					}	
					System.out.printf("\n");
				}	
			}
		}
		
		if(Minesweeper.squares.grid[coordx][coordy]==9)
		{
			System.out.printf("BOOM! YOU LOSE!\n");
			for(int i=0;i<10;i++)
			{
				for(int j=0;j<10;j++)
				{
					if(Minesweeper.squares.grid[i][j]==9)
					{
						System.out.printf("  *  ");
					}
					else
					{
						System.out.printf("  "+Minesweeper.squares.grid[i][j]+"  ");
					}
				}	
				System.out.printf("\n");
			} 
			bomb=true;
		}
		
		if(bomb==false && cmdcount==0)
		{
			System.out.println("YOU WON!");
		}
	}
//-----------------------------------------	
}

