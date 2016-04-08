import javax.swing.*;

public class Minesweeper
{	
	public static MineGrid squares = new MineGrid(10,30);
	
	public static void main(String[] args)
	{
		new MineFrame();				/*UNCOMMENT TO PLAY VIA GUI*/
		//MineGrid.playCommandSweeper();  /*UNCOMMENT TO PLAY VIA COMMANTLINE*/
	}
}