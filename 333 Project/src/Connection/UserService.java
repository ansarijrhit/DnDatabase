package Connection;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

public class UserService {
	private static final Random RANDOM = new SecureRandom();
	private static final Base64.Encoder enc = Base64.getEncoder();
	private static final Base64.Decoder dec = Base64.getDecoder();
	private Connection con = null;

	public UserService(Connection con) {
		this.con = con;
	}

	public boolean useApplicationLogins() {
		return true;
	}

	public boolean login(String username, String password) {
		byte[] salt = {};
		String hash = "";
		try  {
			String query = "SELECT PasswordSalt, PasswordHash \nFROM Player\nWHERE Username = ?";
			PreparedStatement login = con.prepareStatement(query);
			login.setString(1, username);
			ResultSet rs = login.executeQuery();
			while (rs.next()) {
				salt = rs.getBytes("PasswordSalt");
				hash = rs.getString("PasswordHash");
				
				if (hashPassword(salt, password).equals(hash)) {
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "Login failed.");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean register(String username, String name, String password) {
		byte[] salt = getNewSalt();
		String hashedPassword = hashPassword(salt, password);
		CallableStatement cs;
		try {
			cs = this.con.prepareCall("{? = call Register(?, ?, ?, ?)}");
			cs.setString(2, username);
			cs.setString(3, name);
			cs.setBytes(4, salt);
			cs.setString(5, hashedPassword);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int returnValue = cs.getInt(1);
			System.out.println(returnValue);
			if (returnValue != 0) {
				JOptionPane.showMessageDialog(null, "Registration failed.");
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Something went wrong.");
			return false;
		}
	}

	public byte[] getNewSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		return salt;
	}

	public String getStringFromBytes(byte[] data) {
		return enc.encodeToString(data);
	}

	public String hashPassword(byte[] salt, String password) {

		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f;
		byte[] hash = null;
		try {
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		}
		return getStringFromBytes(hash);
	}

}
