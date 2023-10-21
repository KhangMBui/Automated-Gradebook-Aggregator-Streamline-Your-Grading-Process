import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;
/**
 * Class for student with constructor, getter, and setter functions
 * @author khang
 *
 */
public class Student {
	private String Name;
	private String ID;
	private List<String> Total;
	private String finalGrade;
	private List<String> grades;
	//Constructor
	public Student() {
		Name = "";
		ID = "";
		grades = new ArrayList<>();
		Total = new ArrayList<>();
		finalGrade = "";
	}
	//Set name
	public void setName(String name) {
		this.Name = name;
	}
	//Set ID
	public void setID(String id) {
		this.ID = id;
	}
	//Get name
	public String getName() {
		return Name;
	}
	//Get ID
	public String getID() {
		return ID;
	}
	//Get the list of total score
	public List<String> getTotal() {
		return Total;
	}
	//Set the list of total score
	public void setTotal(List<String> total) {
		this.Total = total;
	}
	//Set final grade
	public void setFinalGrade(String FinalGrade) {
		this.finalGrade = FinalGrade;
	}
	//Get final grade
	public String getFinalGrade() {
		return finalGrade;
	}
	//Set the list of grade
	public void setGrade(List<String> secondList) {
		for (int i = 0; i<secondList.size(); i++) {
			this.grades.set(i, secondList.get(i));
		}
	}
	//Get the list of grade
	public List<String> getGrade() {
		return this.grades;
	}
}
