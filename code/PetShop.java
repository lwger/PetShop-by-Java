import java.util.*;
import java.io.*;
interface Pet {
	String getname();

	void setname(String name);

	String getcolor();

	void setcolor(String color);

	int getage();

	void setage(int age);

	int getfirendly();

	void setfirendly(int firendly);
}

public class PetShop {
	ArrayList<Pet> list = new ArrayList<Pet>();
	/*添加宠物*/
	boolean addPet(String kind, String name, String color, int age) {
		if (kind.equals("Cat")) {
			list.add(new Cat(name, color, age));
		} else if (kind.equals("Dog")) {
			list.add(new Dog(name, color, age));
		} else if (kind.equals("OtherPet")) {
			list.add(new OtherPet(name, color, age));
		} else {
//			return false;
			list.add(new OtherPet(name, color, age));
		}
		return true;
	}

	boolean isPet(String name) {
		for (Pet p : list) {
			if (p.getname().equals(name)) {
				return true;
			}
		}
		return false;
	}
	/*查找宠物*/
	Pet searchPet(String name) {
		for (Pet p : list) {
			if (p.getname().equals(name)) {
				return p;
			}
		}
		return list.get(0);
	}

	boolean revisePet(String oldname, String newName, String color, int age) {
		Pet pet = searchPet(oldname);
		pet.setname(newName);
		pet.setcolor(color);
		pet.setage(age);
		return true;
	}
	/*修改宠物*/
	boolean deletePet(String name) {
		Pet pet = searchPet(name);
		list.remove(pet);
		return true;
	}
	/*将宠物信息写入文件*/
	boolean writePetToFile() {
		try {
			File file = new File("./src/file/petInformation.txt");
			RandomAccessFile pointer=new RandomAccessFile(file, "rw");
			//将写文件指针移到文件尾。
			pointer.seek(pointer.length());
			for (Pet p : list) {
				pointer.writeBytes(this.petInformation(p));
			}
			pointer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	String petInformation(Pet pet) {
		return pet.getname()+" "+pet.getcolor()+" "+pet.getage()+"\n";
	}
	
	void deBug() {
		for(Pet p : list) {
			System.out.println(p.getname()+" "+p.getcolor()+" "+p.getage());
		}
		System.out.println();
	}

}

class Cat implements Pet {

	private String name = "name";
	private String color = "color";
	private int age = 5;
	private int firendly = 5; // 1-10
	private int cuteLevel = 5; // 1-10

	Cat(String name, String color, int age) {
		this.name = name;
		this.color = color;
		this.age = age;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getcolor() {
		return color;
	}

	public void setcolor(String color) {
		this.color = color;
	}

	public int getage() {
		return age;
	}

	public void setage(int age) {
		this.age = age;
	}

	public int getfirendly() {
		return firendly;
	}

	public void setfirendly(int firendly) {
		this.firendly = firendly;
	}

	public int getcuteLevel() {
		return cuteLevel;
	}

	public void setcuteLevel(int cuteLevel) {
		this.cuteLevel = cuteLevel;
	}

	public void climb() {
		System.out.println("The cat can climb tree");
	}
}

class Dog implements Pet {
	private String name = "name";
	private String color = "color";
	private int age = 5;
	private int violenceLevel = 5;
	private int firendly = 5; // 1-10

	Dog(String name, String color, int age) {
		this.name = name;
		this.color = color;
		this.age = age;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getcolor() {
		return color;
	}

	public void setcolor(String color) {
		this.color = color;
	}

	public int getage() {
		return age;
	}

	public void setage(int age) {
		this.age = age;
	}

	public int getfirendly() {
		return firendly;
	}

	public void setfirendly(int firendly) {
		this.firendly = firendly;
	}

	public void lookforHouse() {
		System.out.println("The dog can look for the house");
	}

	public int getviolenceLevel() {
		return violenceLevel;
	}

	public void setviolenceLevel(int violenceLevel) {
		this.violenceLevel = violenceLevel;
	}

}

class OtherPet implements Pet {
	private String name = "name";
	private String color = "color";
	private int age = 5;
	private int level = 5;
	private int firendly = 5; // 1-10

	OtherPet(String name, String color, int age) {
		this.name = name;
		this.color = color;
		this.age = age;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getcolor() {
		return color;
	}

	public void setcolor(String color) {
		this.color = color;
	}

	public int getage() {
		return age;
	}

	public void setage(int age) {
		this.age = age;
	}

	public int getfirendly() {
		return firendly;
	}

	public void setfirendly(int firendly) {
		this.firendly = firendly;
	}

	public void action() {
		System.out.println("The special action");
	}

	public int getlevel() {
		return level;
	}

	public void setlevel(int level) {
		this.level = level;
	}
}
