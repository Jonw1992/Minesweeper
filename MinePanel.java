import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class MinePanel extends JPanel
{
	private JButton[][] button=new JButton[Minesweeper.squares.gridx][Minesweeper.squares.gridy];;
	private JPanel grids = new JPanel();
	private JButton reset = new JButton("MINESWEEPER!");
	
	public MinePanel()
	{
		GridLayout panelgrid = new GridLayout(Minesweeper.squares.gridx,Minesweeper.squares.gridy,0,0);
		GridLayout maingrid = new GridLayout(2,0,0,0);
		setLayout(maingrid);
		reset.addMouseListener(new MouseHandler());
		grids.setLayout(panelgrid);
		add(reset);
		add(grids);
		setButtonGrid();
		refreshButtons();
	}
	
	public void setButtonGrid()
	{
		for(int i=0;i<Minesweeper.squares.gridx;i++)
		{
			for(int j=0;j<Minesweeper.squares.gridy;j++)
			{
				button[i][j]= new JButton("");
				button[i][j].setText("");
				button[i][j].addMouseListener(new MouseHandler());
				grids.add(button[i][j]);
			}
		}	
	}
	

		public void refreshButtons()
		{
			for(int i=0;i<Minesweeper.squares.gridx;i++)
			{
				for(int j=0;j<Minesweeper.squares.gridy;j++)
				{
					if(Minesweeper.squares.revealed[i][j]==false && Minesweeper.squares.flagged[i][j]==false)
					{
						button[i][j].setText("");
					}
					if(Minesweeper.squares.revealed[i][j]==true)
					{
						if(Minesweeper.squares.grid[i][j]==9)
						{
							button[i][j].setText("*");
							reset.setText("GAME OVER! CLICK TO RESET THE MINEFIELD!");
						}
						else
						{
							button[i][j].setText(""+Minesweeper.squares.grid[i][j]);
						}
					}				
					if(Minesweeper.squares.flagged[i][j]==true)
					{
						button[i][j].setText("F");
					}
				}
			}
		}	
	
	
	public void resetGrid(int x, int y)
	{
		Minesweeper.squares = new MineGrid(x,y);
		refreshButtons();
		reset.setText("MINESWEEPER!");
	}
	
	public class MouseHandler extends MouseAdapter
	{
		public void mousePressed(MouseEvent e)
		{
			int x=0,y=0;
			boolean test=false;
					
			if((JButton)e.getSource() == reset)
			{ 
				resetGrid(Minesweeper.squares.gridx,Minesweeper.squares.mines);
			}			
						
			for(int i=0; i<Minesweeper.squares.gridx; i++)
			{ 
				for(int j=0;j<Minesweeper.squares.gridy;j++)
				{
					if((JButton)e.getSource() == button[i][j])
					{ 
						x=i;
						y=j;
						test=true;
					}
				} 
			}
			
			if(e.getButton()==MouseEvent.BUTTON1)
			{
				if(button[x][y].getText()!="F" && (JButton)e.getSource() != reset )
				{
				Minesweeper.squares.revealed[x][y]=true;
				refreshButtons();					
				}
				
				if(Minesweeper.squares.grid[x][y]==9 && button[x][y].getText()!="F")
				{
					for(int k=0;k<Minesweeper.squares.gridx;k++)
					{
						for(int l=0;l<Minesweeper.squares.gridy;l++)
						{
							Minesweeper.squares.revealed[k][l]=true;
						}
					}
					refreshButtons();
				}	
				checkWin();	
			}
			else if(e.getButton()==MouseEvent.BUTTON3)
			{
				if(Minesweeper.squares.revealed[x][y]!=true && Minesweeper.squares.flagged[x][y]!=true)
				{
					Minesweeper.squares.flagged[x][y]=true;
					refreshButtons();
				}
				else
				{
					Minesweeper.squares.flagged[x][y]=false;
					refreshButtons();
				}
				checkWin();
			}
		}
	}
	
	public void checkWin()
	{
		int count=0;
		boolean falseflag=false;
				
		 for(int m=0;m<Minesweeper.squares.gridx;m++)
		 {
			for(int n=0;n<Minesweeper.squares.gridy;n++)
			{
				if(button[m][n].getText()!="" && button[m][n].getText()!="*")
				{	
					count++;
				}
				if(button[m][n].getText()=="F")
				{
					if(Minesweeper.squares.grid[m][n]!=9)
					{
						falseflag=true;
					}
				}
			}
		 }
				 
		if(count>((Minesweeper.squares.gridx*Minesweeper.squares.gridy)-1) && falseflag==false)
		{
				reset.setText("YOU WON! CLICK TO RESET THE MINEFIELD");
				System.out.println("YOU WON!");
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setVisible(true);
	}	
}



