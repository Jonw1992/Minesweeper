import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JFileChooser;

public class MineFrame extends JFrame
{
	public MinePanel panel = new MinePanel();
	private JFileChooser save = new JFileChooser();
	private FileMenuItem savegame = new FileMenuItem("Save");
	private FileMenuItem loadgame = new FileMenuItem("Load");
	private FileMenuItem quitgame = new FileMenuItem("Quit");
	private FileMenuItem easy = new FileMenuItem("Easy");
	private FileMenuItem medium = new FileMenuItem("Medium");
	private FileMenuItem hard = new FileMenuItem("Hard");
	private FileMenuItem overkill = new FileMenuItem("Overkill");
	private int screenx = 1000;
	private int screeny = 1000;
	
	public MineFrame()
	{
		setTitle("Minesweeper");
		setSize(screenx,screeny);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu subMenu = new JMenu("New");
		mb.add(fileMenu);
		MenuHandler mh = new MenuHandler();
		easy.addActionListener(mh);
		medium.addActionListener(mh);
		hard.addActionListener(mh);
		overkill.addActionListener(mh);
		savegame.addActionListener(mh);
		loadgame.addActionListener(mh);
		quitgame.addActionListener(mh);
		fileMenu.add(subMenu);
		fileMenu.add(savegame);
		fileMenu.add(loadgame);
		fileMenu.add(quitgame);
		subMenu.add(easy);
		subMenu.add(medium);
		subMenu.add(hard);
		subMenu.add(overkill);
		save.setCurrentDirectory(new File(System.getProperty("user.home")));
		this.setJMenuBar(mb);

		add(panel);
		setVisible(true);	
	}
	
	public FileMenuItem getFileMenuItem(String name)
	{
		if(name=="easy")
		{return(easy);}
		if(name=="medium")
		{return(medium);}
		if(name=="hard")
		{return(hard);}
		if(name=="overkill")
		{return(overkill);}
		if(name=="save")
		{return(savegame);}
		if(name=="load")
		{return(loadgame);}
		else
		{return(quitgame);}
	}
	
	public class FileMenuItem extends JMenuItem
	{
		public FileMenuItem(String label)
		{
			super(label);
		}
	}	
	
	private class MenuHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==getFileMenuItem("easy"))
			{
				Minesweeper.squares = new MineGrid(5,10);
				remove(panel);
				panel = new MinePanel();
				add(panel);
				setVisible(true);
			}
			if(e.getSource()==getFileMenuItem("medium"))
			{
				Minesweeper.squares = new MineGrid(10,30);
				remove(panel);
				panel = new MinePanel();
				add(panel);
				setVisible(true);			
			}
			if(e.getSource()==getFileMenuItem("hard"))
			{
				Minesweeper.squares = new MineGrid(15,90);
				remove(panel);
				panel = new MinePanel();
				add(panel);
				setVisible(true);		
			}
			if(e.getSource()==getFileMenuItem("overkill"))
			{
				Minesweeper.squares = new MineGrid(20,250);
				remove(panel);
				panel = new MinePanel();
				add(panel);
				setVisible(true);				
			}
			if(e.getSource()==getFileMenuItem("save"))
			{
				saveFile();
			
			}
			if(e.getSource()==getFileMenuItem("load"))
			{
				loadFile();
				remove(panel);
				panel = new MinePanel();
				add(panel);
				setVisible(true);
			}
			if(e.getSource()==getFileMenuItem("quit"))
			{
				System.exit(0);
			}			
		}
	}
	
	public void saveFile()
	{
		int result = save.showSaveDialog(null);
		String filename = "";
		
		if(result == JFileChooser.APPROVE_OPTION) 
		{
			filename = save.getSelectedFile().getName();
		}

		try 
		{
			 ObjectOutputStream out =
			 new ObjectOutputStream(new FileOutputStream(filename+""));
			 out.writeObject(Minesweeper.squares);
		} 
		catch (IOException x) 
		{
			System.out.println("Error reading file or no file selected.");	
		}		 
	}
		
	public void loadFile()
	{
		int result = save.showOpenDialog(null);
		String filename = "";
		if(result == JFileChooser.APPROVE_OPTION) 
		{
			filename = save.getSelectedFile().getName();
		}
		try 
		{
			ObjectInputStream in =
			new ObjectInputStream(new FileInputStream(filename+""));
			Minesweeper.squares = (MineGrid)in.readObject();
		} 
		 
		 catch(IOException x) 
		{
			System.out.println("Error reading file or no file selected.");
		}
		catch(ClassNotFoundException x) 
		{
			System.out.println("File does not contain the right object.");
		}			
	}
}
	



		
	
