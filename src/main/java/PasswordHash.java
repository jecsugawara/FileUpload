import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

@WebServlet("/PasswordHash")
public class PasswordHash extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String plainPassword = "123456789";

		// シンプルなハッシュ
		String hashed = DigestUtils.sha256Hex(plainPassword);
		System.out.println("ハッシュ（単純）: " + hashed);

		// ソルト付きハッシュ（推奨）
		String salt = "randomSalt123";
		String saltedHash = DigestUtils.sha256Hex(plainPassword + salt);
		System.out.println("ハッシュ（ソルト付き）: " + saltedHash);
	}
}