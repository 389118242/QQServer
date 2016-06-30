package qq.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseUtil {
	private static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qqdb?useUnicode=true&characterEncoding=utf8",
					"root", "niit");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	private static void close(Connection con, Statement smt) {
		try {
			smt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void close(Connection con, Statement smt, ResultSet rs) {
		try {
			rs.close();
			smt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void setParam(PreparedStatement ps, Object[] param) {
		try {
			if (null != param) {
				for (int i = 0; i < param.length; i++) {
					ps.setObject(i + 1, param[i]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static <T> List<T> query(Class<T> c, String sql, Object[] param) {
		List<T> result = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			setParam(ps, param);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int colnum = md.getColumnCount();
			Map<String, Object> m = new HashMap<String, Object>();
			while (rs.next()) {
				for (int i = 0; i < colnum; i++) {
					m.put(md.getColumnName(i + 1).toLowerCase(), rs.getObject(i + 1));
				}
				result.add(getObject(c, m));
			}
			close(con, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static int updateData(String sql, Object[] param) {
		int result = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			setParam(ps, param);
			result = ps.executeUpdate();
			close(con, ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static <T> T getObject(Class<T> c, Map<String, Object> m) {
		T result = null;
		try {
			Constructor<T> con = c.getDeclaredConstructor(new Class[] { Map.class });
			result = con.newInstance(new Object[] { m });
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}

}
