import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Gui extends JFrame{
//	JLabel kindL;
//	JLabel nameL;
//	JLabel colorL;
//	JLabel ageL;
//	JLabel oldnameL;
//	JLabel newnameL;
//	JLabel newcolorL;
//	JLabel newageL;
//	JLabel searchnameL;
	PetShop petShop;
	JTextField kindT;
	JTextField nameT;
	JTextField colorT;
	JTextField ageT;
	JTextField oldnameT;
	JTextField newnameT;
	JTextField newcolorT;
	JTextField newageT;
	JTextField searchnameT;
	JButton addButton;
	JButton reviseButton;
	JButton searchButton;
	JButton deleteButton;
	JButton saveButton;
	JButton autosaveButton;
	
	Gui(){
		super("Welcome to PetShop");
		petShop = new PetShop();
		this.frameInitial();	   //窗口初始化
		this.setBackground();		//设置背景图片
		this.createLayout();		//实现布局管理
		this.eventProcess();		//实现时间处理
		this.frameLast();			//窗口重绘
	}
	/*设置事件监听器*/
	void eventProcess() {
		addButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					addButtonProcess();
				}
			}
				);
		reviseButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					reviseButtonProcess();
				}
			}
				);
		searchButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					searchButtonProcess();
				}
			}
				);
		deleteButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					deleteButtonProcess();
				}
			}
				);
		saveButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					saveButtonProcess();
				}
			}
				);
		autosaveButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					autosaveButtonProcess();
				}
			}
				);
	}
	/*实现添加宠物的事件处理*/
	void addButtonProcess() {
		try{
			String kind = kindT.getText().toString();
			String name = nameT.getText().toString();
			int age = Integer.parseInt(ageT.getText());
			String color = colorT.getText().toString();
			petShop.addPet(kind, name,  color, age);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "add pet happen exception","Result", JOptionPane.ERROR_MESSAGE);
			System.out.println("add pet happen exception");
		}
//		petShop.addPet("Cat", "lee", "red", 3);
//		petShop.addPet("Dog", "tu", "black", 5);
//		petShop.addPet("Other", "song", "blue", 7);
		petShop.deBug();
	}
	/*实现修改宠物的事件处理*/
	void reviseButtonProcess() {
		try {
			String oldname = oldnameT.getText().toString();
			String newname = newnameT.getText().toString();
			int newage = Integer.parseInt(newageT.getText());
			String newcolor = newcolorT.getText().toString();
			if(petShop.isPet(oldname)) {
				petShop.revisePet(oldname,newname,newcolor,newage);
			}
			else {
				JOptionPane.showMessageDialog(null, "This pet isn't exist!", "Result", JOptionPane.ERROR_MESSAGE);
				System.out.println("This pet is not exist!");
			}
			petShop.deBug();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "revise pet happen exception", "Result",JOptionPane.ERROR_MESSAGE);
			System.out.println("revise pet happen exception");
		}
		
	}
	/*实现查找宠物的事件处理*/
	void searchButtonProcess() {
		try {
			String searchname = searchnameT.getText().toString();
			if(petShop.isPet(searchname)) {
				Pet pet = petShop.searchPet(searchname);
				String information = new String("name:"+pet.getname()+" color:"+pet.getcolor()+" age: "+pet.getage());
				JOptionPane.showMessageDialog(null, information, "Search Result", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "This pet isn't exist!", "Search Result", JOptionPane.ERROR_MESSAGE);
			}
			petShop.deBug();
		}
		catch(Exception e) {
			System.out.println("search pet happen exception");
		}
	}
	/*实现删除宠物的事件处理*/
	void deleteButtonProcess() {
		try {
			String searchname = searchnameT.getText().toString();
			if(petShop.isPet(searchname)) {
				petShop.deletePet(searchname);
				JOptionPane.showMessageDialog(null, "Delete successful!", "Result", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "This pet isn't exist!", "Result", JOptionPane.ERROR_MESSAGE);
			}
			petShop.deBug();
		}
		catch(Exception e) {
			System.out.println("search pet happen exception");
		}
	}
	/*实现保存宠物的事件处理*/
	void saveButtonProcess() {
		try {
			petShop.writePetToFile();
			JOptionPane.showMessageDialog(null, "save successful!", "Result", JOptionPane.ERROR_MESSAGE);
			petShop.deBug();
		}
		catch(Exception e) {
			System.out.println("save happen exception");
		}
	}
	/*实现自动保存宠物的事件处理*/
	void autosaveButtonProcess() {
		try {
	        new Thread() {
	            public void run() {
	            	while(true) {
	            		try {
	            			Thread.sleep(1000*60);
	            			petShop.writePetToFile();
	            			JOptionPane.showMessageDialog(null, "save successful!", "Result", JOptionPane.ERROR_MESSAGE);
	            			petShop.deBug();
	            		}
	            		catch(InterruptedException e) {
	            			JOptionPane.showMessageDialog(null, "thread.sleep exception", "Result", JOptionPane.ERROR_MESSAGE);
	            		}
	            	}
	            }
	        }.start();
		}
		catch(Exception e) {
			System.out.println("save happen exception");
		}
	}
	void frameInitial() {
		ImageIcon frameIcon = new ImageIcon("./src/images/frameIcon.png");
		this.setIconImage(frameIcon.getImage());
		this.setSize(1000,700);
		this.setLocation(250,50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void frameLast() {
		this.setVisible(true);
	}
	void setBackground() {
        JPanel ctPanel = (JPanel)this.getContentPane();
        ctPanel.setOpaque(false);
        ImageIcon background = new ImageIcon("./src/images/background.jpg");
        JLabel bkLabel = new JLabel(background);
        bkLabel.setBounds(0, 0,background.getIconWidth(), background.getIconHeight());
//      this.setSize(background.getIconWidth(), background.getIconHeight());
        this.getLayeredPane().add(bkLabel,Integer.MIN_VALUE);

	}
	void createLayout() {
        JPanel jp1 = new JPanel(new FlowLayout());
        JPanel jp2 = new JPanel(new FlowLayout());
        JPanel jp3 = new JPanel(new FlowLayout());
        JPanel jp4 = new JPanel(new FlowLayout());
        JPanel mainjp = new JPanel(new GridLayout(4,1));
        mainjp.add(jp1);
        mainjp.add(jp2);
        mainjp.add(jp3);
        mainjp.add(jp4);
        jp1.add(kindT = new JTextField("kind"));
        jp1.add(nameT = new JTextField("name"));     
        jp1.add(colorT = new JTextField("color"));
        jp1.add(ageT = new JTextField("age"));
        jp1.add(addButton = new JButton("Add"));
        kindT.setPreferredSize(new Dimension(150,40));
        nameT.setPreferredSize(new Dimension(150,40));
        ageT.setPreferredSize(new Dimension(150,40));
        colorT.setPreferredSize(new Dimension(150,40));
        addButton.setPreferredSize(new Dimension(100,40));
        kindT.setFont(new  java.awt.Font("微软楷体",  1,  20)); //设置字体样式
        nameT.setFont(new  java.awt.Font("微软楷体",  1,  20)); //设置字体样式
        ageT.setFont(new  java.awt.Font("微软楷体",  1,  20)); //设置字体样式
        colorT.setFont(new  java.awt.Font("微软楷体",  1,  20)); //设置字体样式
        addButton.setFont(new  java.awt.Font("微软楷体",  1,  20)); //设置字体样式
      
        jp2.add(oldnameT = new JTextField("oldname"));
        jp2.add(newnameT = new JTextField("newname"));
        jp2.add(newcolorT = new JTextField("newcolor"));
        jp2.add(newageT = new JTextField("newage"));
        jp2.add(reviseButton = new JButton("Revise"));
        oldnameT.setPreferredSize(new Dimension(150,40));
        newnameT.setPreferredSize(new Dimension(150,40));
        newcolorT.setPreferredSize(new Dimension(150,40));
        newageT.setPreferredSize(new Dimension(150,40));
        reviseButton.setPreferredSize(new Dimension(100,40));
        oldnameT.setFont(new  java.awt.Font("微软楷体",  1,  20)); //设置字体样式
        newnameT.setFont(new  java.awt.Font("微软楷体",  1,  20)); //设置字体样式
        newcolorT.setFont(new  java.awt.Font("微软楷体",  1,  20)); //设置字体样式
        newageT.setFont(new  java.awt.Font("微软楷体",  1,  20)); //设置字体样式
        reviseButton.setFont(new  java.awt.Font("微软楷体",  1,  20)); //设置字体样式

        jp3.add(searchnameT = new JTextField("name"));
        jp3.add(searchButton= new JButton("Search"));
        jp3.add(deleteButton= new JButton("Delete"));
        searchnameT.setPreferredSize(new Dimension(150,40));
        searchButton.setPreferredSize(new Dimension(150,40));
        deleteButton.setPreferredSize(new Dimension(150,40));
        searchnameT.setFont(new  java.awt.Font("微软楷体",  1,  20)); //设置字体样式
        searchButton.setFont(new  java.awt.Font("微软楷体",  1,  20)); //设置字体样式
        deleteButton.setFont(new  java.awt.Font("微软楷体",  1,  20)); //设置字体样式
                
        jp4.add(saveButton= new JButton("Save"));
        jp4.add(autosaveButton= new JButton("Autosave"));
        saveButton.setPreferredSize(new Dimension(150,40));
        autosaveButton.setPreferredSize(new Dimension(150,40));
        saveButton.setFont(new  java.awt.Font("微软楷体",  1,  20)); //设置字体样式
        autosaveButton.setFont(new  java.awt.Font("微软楷体",  1,  20)); //设置字体样式
        
        Container container = this.getContentPane();
        container.add(mainjp);

	}
	public static void main(String[] str){
//		System.out.println("hhh");
		new Gui();	

	}
}




































































