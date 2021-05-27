package SummerFire3972.Models;

import java.util.List;
import SummerFire3972.Models.DataObject.*;
import SummerFire3972.Models.DataObject.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.sql.*;
import java.time.LocalDate;
import javax.sql.DataSource;

public class DBManager {
	private DataSource ds;
	
//	enum Type {
//		FOOD("Food"), ENTERTAINMENT("Entertainment"), UTILITY("Other"), OTHER("Other");
//		
//		private String value;
//		private Type (String value) {
//			this.value = value;
//		}
//	}

	public DBManager() {
		ds = DBCPDataSourceFactory.getDataSource();
	}
	
	public ObservableList<SingleExpense> queryAll() {

		// creating empty observable list that will be returned at the end
		ObservableList<SingleExpense> ret = FXCollections.observableArrayList();
		
		// define a query that will be executed right after
		String query = """
				select Date, Amount, Type
				from Expense
				group by Date, Amount, Type
				order by Date desc;
				""";

		// connect to the database, execute the query, and store all the query detail to the observable list
		try (
				Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement();
			) {
			
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				ret.add(new SingleExpense(rs.getDate("Date").toString(), rs.getDouble("Amount"), rs.getString("Type")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		// return
		return ret;
	}
	
	public ObservableList<Expense> queryAllDetailed() {
		
		ObservableList<Expense> ret = FXCollections.observableArrayList();
		String query = """
				select Date, Amount, Type, ExpenseID
				from Expense
				group by Date, Amount, Type, ExpenseID
				order by Date desc;
				""";

		try (
				Connection conn =ds.getConnection();
				Statement stmt = conn.createStatement();
			) {
			
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				ret.add(new Expense(rs.getDate("Date").toString(), rs.getDouble("Amount"), rs.getString("Type"), rs.getString("ExpenseID")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return ret;
	}
	
	public ObservableList<Expense> queryAllDetailedThisMonth() {
		ObservableList<Expense> ret = FXCollections.observableArrayList();
		LocalDate date = LocalDate.now();
		String preparedQuery = """
				select *
				from Expense
				where year(Date) = ? and month(Date) = ?
				order by Date desc;
				""";
		
		try (
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(preparedQuery);
			) {
			stmt.setInt(1, date.getYear());
			stmt.setInt(2, date.getMonthValue());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ret.add(new Expense(rs.getString("Date"), rs.getDouble("Amount"), rs.getString("Type"), rs.getString("ExpenseID")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}

	public ObservableList<MonthlyExpense> queryTotal() {

		ObservableList<MonthlyExpense> ret = FXCollections.observableArrayList();
		String query = """
				select date_format(Date, '%Y-%m') as YearMonth, sum(Amount) as Sum
				from Expense
				group by date_format(Date, '%Y-%m')
				order by date_format(Date, '%Y-%m') desc;
				""";

		try (
				Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement();
			) {
			
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				ret.add(new MonthlyExpense(rs.getString("YearMonth"), rs.getDouble("Sum")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public ObservableList<MonthlyExpense> queryByType(Type t) {
		
//		Type t;
//		if ((t = Type.getType(type)) == null)
//			return null;
		
//		boolean typeCorrect = false;
//		for (Type t : Type.values()) {
//			if (t.value.equalsIgnoreCase(type)) {
//				typeCorrect = true;
//				break;
//			}
//		}
//		if (!typeCorrect)
//			return null;
		
		ObservableList<MonthlyExpense> ret = FXCollections.observableArrayList();
		String preparedQuery = """
				select date_format(Date, '%Y-%m') as YearMonth, sum(Amount) as Sum
				from Expense
				where Type=?
				group by date_format(Date, '%Y-%m')
				order by date_format(Date, '%Y-%m') desc;
				""";
		
		try (
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(preparedQuery);
			){
			
			stmt.setString(1, t.value);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ret.add(new MonthlyExpense(rs.getString("YearMonth"), rs.getDouble("Sum")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public String insertValue(LocalDate date, String type, double amount) {
		
		Type t;
		if ((t = Type.getType(type)) == null)
			return null;
		
//		boolean typeCorrect = false;
//		for (Type t : Type.values()) {
//			if (t.value.equalsIgnoreCase(type)) {
//				typeCorrect = true;
//				break;
//			}
//		}
//		if (!typeCorrect)
//			return null;
		
		String id = UUID.randomUUID().toString();
		String preparedStatement = "insert into Expense(ExpenseID, Date, Type, Amount) values (?, ?, ?, ?)";
		
		try (
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(preparedStatement);
			) {
			
			conn.setAutoCommit(false);
			
			stmt.setString(1, id);
			stmt.setDate(2, java.sql.Date.valueOf(date));
			stmt.setString(3, type);
			stmt.setDouble(4, amount);
			
			stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public boolean deleteValue(String id) {		
		String preparedStatement = "delete from Expense where ExpenseID=?;";
		
		try (
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(preparedStatement);
			) {
			
			conn.setAutoCommit(false);
			
			stmt.setString(1, id);
			
			stmt.executeUpdate();
			conn.commit();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
