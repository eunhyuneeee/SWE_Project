import java.sql.*;

public class StudentInfoDB extends StudentInfoSystem{
	
	String db_id ;
	String db_name;
	String db_depart;
	String db_pnum ;
	
	
	public StudentInfoDB(String id, String name, String depart, String pnum){
		db_id = id;
		db_name = name;
		db_depart = depart;
		db_pnum = pnum;
	}
	
	// �й��� �����Ѱ� �ְų� , �����Է�ĭ�� �ϳ��� �Է����� �ʴ� ��� ����x 
	public boolean info_add(){
				
		try {
			state.executeUpdate("insert into student_info(Id, Name, Department, Phone_num) values('"+db_id+"','"+db_name+"','"+db_depart+"','"+db_pnum+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;	
	}

}
