import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class StudentInfoDB {
	
	
	Connection con = null;
	java.sql.Statement st = null;
	ResultSet rs = null;
	int r = 0;
	
	public void dataBase() {
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://" + StudentInfoSystem._host + ":" + StudentInfoSystem._port + "/" + StudentInfoSystem._database;
		String username = StudentInfoSystem._user;
		String password = StudentInfoSystem._password;
		
		try {
			Class.forName(jdbcDriver).newInstance();
			con = (Connection) DriverManager.getConnection(dbUrl,username,password);
			st = con.createStatement();
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void add(){				
		
		String id = StudentInfoSystem.input_id.getText().trim();	
		try { 
			String str = "select Id from student_info where Id LIKE '"+id+"' ";
			rs = st.executeQuery(str);
			
			if(rs.next()) {
				StudentInfoSystem.display.append("�ߺ��� �й��Դϴ�. �ٽ� �Է��ϼ���.");
			}		
		} catch (SQLException e) {
            e.printStackTrace();
        }	
		
		String name = StudentInfoSystem.input_name.getText().trim();
		String depart = StudentInfoSystem.input_depart.getText().trim();
		String pnum = StudentInfoSystem.input_pnum.getText().trim();
		
		try {       
            String sql2 = "insert into student_info values" + "('"+id+"','"+name+"','"+depart+"','"+pnum+"')";
            st.executeUpdate(sql2); 
            StudentInfoSystem.display.append("=============================================================" +"\n");
            StudentInfoSystem.display.append(" \t     �л� ������ �߰��Ǿ����ϴ�.\n ");
            StudentInfoSystem.display.append(" ============================================================="+"\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }	
	}
	
	/*
	public void dataSearch(){
		System.out.print("å ������ �Է����ּ���: ");
		String searchtitle = scan.nextLine();
		
		try {
			String showData = " select * from book WHERE title LIKE '%"+searchtitle+"%' ";
			rs = st.executeQuery(showData);
			
			if(rs.next()) {
				rs=st.getResultSet();
				
				System.out.println("[search result]");
				rs.beforeFirst();
				
				System.out.println("____________________________________________________________");
				System.out.println(String.format("  %s  ", "book_number") + "|" 
				                 + String.format(" %20s ", "title") + "|"  
				                 + String.format(" %20s ", "publisher"));
				System.out.println("____________________________________________________________");				
				while(rs.next()) {					
					System.out.println(String.format("     %5s     ", rs.getString(1)) + "|" 
	                 		 + String.format(" %20s ",  rs.getString(2)) + "|"  
	                         + String.format(" %20s ", rs.getString(3)));
				}
				System.out.println("____________________________________________________________");	
				} else { 
					System.out.print("�˻� ����� �����ϴ�.If you want to go back to menu press1 => ");
					int rg = scan.nextInt();
					switch (rg) {
					case 1:
						mg.Manage();
						break;
					default:
						System.out.println("Insert is wrong.");
						break;
					}
					}
			
			System.out.println("");
			
			System.out.print("���ϴ�å��ȣ���Է��ϼ���: ");
			String searchbn = scan.nextLine();
			
			try {
				String search = " select * from book WHERE book_number LIKE '"+searchbn+"' ";
				rs=st.executeQuery(search);
				
				if(rs.next()) {
					rs=st.getResultSet();
					
					System.out.println("[search result]");
					rs.beforeFirst();
					System.out.println("________________________________________________________________________________________________________________________");
					System.out.println(String.format("  %s  ", "book_number") + "|" 
					                 + String.format(" %20s ", "title") + "|"  
					                 + String.format(" %20s ", "writer") + "|" 
					                 + String.format(" %20s ", "publisher") + "|"
					                 + String.format(" %10s ", "borrow ����") + "|"
					                 + String.format(" %20s ", "borrowed Id"));
					System.out.println("________________________________________________________________________________________________________________________");
					String can = "O";
					while(rs.next()) {						
						if(rs.getString(5)!=null) {
							can = "X";
						} 
						
						System.out.println(String.format("     %5s     ", rs.getString(1)) + "|" 
		                 		 + String.format(" %20s ",  rs.getString(2)) + "|"  
		                         + String.format(" %20s ", rs.getString(3)) + "|"  
		                         + String.format(" %20s ", rs.getString(4)) + "|"
		                         + String.format(" %10s ", can) + "|"
		                         + String.format(" %20s ", rs.getString(5)));
						}
					System.out.println("________________________________________________________________________________________________________________________");
					} else { 
						System.out.print("�߸��� å ��ȣ�Դϴ�. �޴��� ���ư��� ������ 1�� �Է��ϼ��� => ");
						int rg = scan.nextInt();
						switch (rg) {
						case 1:
							mg.Manage();
							break;
						default:
							System.out.println("Insert is wrong.");
							break;
						}
						}
				} catch(Exception e) {
					System.out.println("Data�� �����ϴ�.");
					return;
				}
			} catch(Exception e) {
				System.out.println("Data�� �����ϴ�.");
				return;
			}
	}
	
	public void dataDelete(){ 
		System.out.print("������ å ������ �Է��ϼ���: ");
		String searchtitle = scan.nextLine();
		
		try {
			String showData = " select * from book WHERE title LIKE '%"+searchtitle+"%' ";
			rs = st.executeQuery(showData);
			
			if(rs.next()) {
				rs=st.getResultSet();
				
				System.out.println("[search result]");
				rs.beforeFirst();
				System.out.println("________________________________________________________________________________________________________________________");
				System.out.println(String.format("  %s  ", "book_number") + "|" 
				                 + String.format(" %20s ", "title") + "|"  
				                 + String.format(" %20s ", "writer") + "|" 
				                 + String.format(" %20s ", "publisher") + "|"
				                 + String.format(" %10s ", "borrow ����") + "|"
				                 + String.format(" %20s ", "borrowed Id"));
				System.out.println("________________________________________________________________________________________________________________________");
				String can = "O";
				while(rs.next()) {						
					if(rs.getString(5)!=null) {
						can = "X";
					} 
					
					System.out.println(String.format("     %5s     ", rs.getString(1)) + "|" 
	                 		 + String.format(" %20s ",  rs.getString(2)) + "|"  
	                         + String.format(" %20s ", rs.getString(3)) + "|"  
	                         + String.format(" %20s ", rs.getString(4)) + "|"
	                         + String.format(" %10s ", can) + "|"
	                         + String.format(" %20s ", rs.getString(5)));
					}
				System.out.println("________________________________________________________________________________________________________________________");
				} else { 
					System.out.print("�˻� ����� �����ϴ�. �޴��� ���ư��� ������ 1�� �Է��ϼ��� => ");
					int rg = scan.nextInt();
					switch (rg) {
					case 1:
						mg.Manage();
						break;
					default:
						System.out.println("Insert is wrong.");
						break;
					}
					}
			
			System.out.println("");
				
			System.out.print("������ å ��ȣ�� �Է��ϼ���: ");
			String delbook = scan.nextLine();
			
			try {
				String delete = "delete from book where book_number = '"+delbook+"'";
				r = st.executeUpdate(delete);
				
					
				if(r!=0) { 
					System.out.println("���� �Ϸ�"); 
					} else { 
						System.out.print("���� ����. �޴��� ���ư��� ������ 1�� �Է��ϼ��� => ");
						int rg = scan.nextInt();
						switch (rg) {
						case 1:
							mg.Manage();
							break;
						default:
							System.out.println("Insert is wrong.");
							break;
						}
						}
				} catch(Exception e) {
					System.out.println("Data�� �����ϴ�.");
					return;
					}
			} catch(Exception e) {
				System.out.println("Data�� �����ϴ�.");
				return;
				}
		}

	public void dataChange(){
		System.out.print("������Ʈ�ϰ� ���� å ������ �Է��ϼ��� :");
		String searchtitle = scan.nextLine();
		
		try {
			String showData = " select * from book WHERE title LIKE '%"+searchtitle+"%' ";
			rs = st.executeQuery(showData);
			
			if(rs.next()) {
				rs=st.getResultSet();
				
				System.out.println("[search result]");
				rs.beforeFirst();
				System.out.println("________________________________________________________________________________________________________________________");
				System.out.println(String.format("  %s  ", "book_number") + "|" 
				                 + String.format(" %20s ", "title") + "|"  
				                 + String.format(" %20s ", "writer") + "|" 
				                 + String.format(" %20s ", "publisher") + "|"
				                 + String.format(" %10s ", "borrow ����") + "|"
				                 + String.format(" %20s ", "borrowed Id"));
				System.out.println("________________________________________________________________________________________________________________________");
				String can = "O";
				while(rs.next()) {						
					if(rs.getString(5)!=null) {
						can = "X";
					} 
					
					System.out.println(String.format("     %5s     ", rs.getString(1)) + "|" 
	                 		 + String.format(" %20s ",  rs.getString(2)) + "|"  
	                         + String.format(" %20s ", rs.getString(3)) + "|"  
	                         + String.format(" %20s ", rs.getString(4)) + "|"
	                         + String.format(" %10s ", can) + "|"
	                         + String.format(" %20s ", rs.getString(5)));
					}
				System.out.println("________________________________________________________________________________________________________________________");
				} else { 
					System.out.print("�˻� ����� �����ϴ�. �޴��� ���ư��� ������ 1�� �Է��ϼ��� => ");
					int rg = scan.nextInt();
					switch (rg) {
					case 1:
						mg.Manage();
						break;
					default:
						System.out.println("Insert is wrong.");
						break;
					}
					}
			
			System.out.println("");
			
			System.out.print("������Ʈ�� å ��ȣ�� �Է��ϼ���: ");
			String upbook = scan.nextLine();
				
			try
			{
				String showData4 = " select * from book WHERE book_number LIKE '"+upbook+"' ";
				rs = st.executeQuery(showData4);
				
				if(rs.next()) {
					rs=st.getResultSet();
					
					System.out.println("[update�� å ����]");
					rs.beforeFirst();
					while(rs.next()) {
						System.out.print(rs.getString(1)+" "); //System.out.print("å ��ȣ : ");
						System.out.print(rs.getString(2)+" "); //System.out.print("å ���� : ");
						System.out.print(rs.getString(3)+" "); //System.out.print("���� : ");
						System.out.print(rs.getString(4)+" "); //System.out.print("���ǻ� : ");
						System.out.println(rs.getString(5));   //System.out.print("���� �л� ID : ");
						}
					
					System.out.println("1.å ���� ���� | 2.���� ���� | 3.���ǻ� ����");
					System.out.print("���� : ");
					int upnum = scan.nextInt();
					scan.nextLine();
					
					if(upnum==1) { // å ���� ���� 
						
						System.out.print("���ο� �̸��� �Է��ϼ���: ");
						String uptitle = scan.nextLine();
						String updata = " update book set title='"+uptitle+"' where book_number LIKE '"+upbook+"' ";
						st.executeUpdate(updata);
						
						if(st.executeUpdate(updata)>0) { 
							System.out.println("������Ʈ �Ϸ�"); 
							} else { 
								System.out.print("������Ʈ ����. �޴��� ���ư��� ������ 1�� �Է��ϼ��� => ");
								int rg = scan.nextInt();
								switch (rg) {
								case 1:
									mg.Manage();
									break;
								default:
									System.out.println("Insert is wrong.");
									break;
								}
								}
						
						String showData3 = " select * from book WHERE book_number LIKE '"+upbook+"' ";
						rs=st.executeQuery(showData3);
						
						if(rs!=null) {
							rs=st.getResultSet();
						}
						
						System.out.println("[update result]");
						while(rs.next()) {
							System.out.print(rs.getString(1)+" "); //System.out.print("å ��ȣ : ");
							System.out.print(rs.getString(2)+" "); //System.out.print("å ���� : ");
							System.out.print(rs.getString(3)+" "); //System.out.print("���� : ");
							System.out.print(rs.getString(4)+" "); //System.out.print("���ǻ� : ");
							System.out.println(rs.getString(5));   //System.out.print("���� �л� ID : ");
							}
						} else if(upnum==2) { // ���� ����
							
							System.out.print("���ο� ���ڸ� �Է��ϼ���: ");
							String upwriter = scan.nextLine();
							String updata = " update book set writer='"+upwriter+"' where book_number LIKE '"+upbook+"' ";
							st.executeUpdate(updata);
							
							if(st.executeUpdate(updata)>0) { 
								System.out.println("������Ʈ �Ϸ�"); 
								} else { 
									System.out.print("������Ʈ ����. �޴��� ���ư��� ������ 1�� �Է��ϼ��� => ");
									int rg = scan.nextInt();
									switch (rg) {
									case 1:
										mg.Manage();
										break;
									default:
										System.out.println("Insert is wrong.");
										break;
									}
									}
							
							String showData3 = " select * from book WHERE book_number LIKE '"+upbook+"' ";
							rs=st.executeQuery(showData3);
							
							if(rs!=null) {
								rs=st.getResultSet();
							}
							
							System.out.println("[update result]");
							while(rs.next()) {
								System.out.print(rs.getString(1)+" "); //System.out.print("å ��ȣ : ");
								System.out.print(rs.getString(2)+" "); //System.out.print("å ���� : ");
								System.out.print(rs.getString(3)+" "); //System.out.print("���� : ");
								System.out.print(rs.getString(4)+" "); //System.out.print("���ǻ� : ");
								System.out.println(rs.getString(5));   //System.out.print("���� �л� ID : ");
								}
						} else if(upnum==3) {
							
							System.out.print("���ο� ���ǻ縦 �Է��ϼ���: ");
							String uppublisher  = scan.nextLine();
							String updata = " update book set publisher='"+uppublisher +"' where book_number LIKE '"+upbook+"' ";
							st.executeUpdate(updata);
							
							if(st.executeUpdate(updata)>0) { 
								System.out.println("������Ʈ �Ϸ�"); 
								} else { 
									System.out.print("������Ʈ ����. �޴��� ���ư��� ������ 1�� �Է��ϼ��� => ");
									int rg = scan.nextInt();
									switch (rg) {
									case 1:
										mg.Manage();
										break;
									default:
										System.out.println("Insert is wrong.");
										break;
									}
									}
							
							String showData3 = " select * from book WHERE book_number LIKE '"+upbook+"' ";
							rs=st.executeQuery(showData3);
							
							if(rs!=null) {
								rs=st.getResultSet();
							}
							
							System.out.println("[update result]");
							while(rs.next()) {
								System.out.print(rs.getString(1)+" "); //System.out.print("å ��ȣ : ");
								System.out.print(rs.getString(2)+" "); //System.out.print("å ���� : ");
								System.out.print(rs.getString(3)+" "); //System.out.print("���� : ");
								System.out.print(rs.getString(4)+" "); //System.out.print("���ǻ� : ");
								System.out.println(rs.getString(5));   //System.out.print("���� �л� ID : ");
								}
							}
					} else { 
						System.out.print("�߸��� å ��ȣ�Դϴ�. �޴��� ���ư��� ������ 1�� �Է��ϼ��� => ");
						int rg = scan.nextInt();
						switch (rg) {
						case 1:
							mg.Manage();
							break;
						default:
							System.out.println("Insert is wrong.");
							break;
						}
						}
				} catch(SQLException e) {
					System.out.println("Data�� �����ϴ�.");
					return;
					}
			} catch(Exception e) {
				System.out.println("Data�� �����ϴ�.");
				return;
				}
		}

 */
	}
