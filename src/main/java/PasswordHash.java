import java.io.IOException;
import java.io.PrintWriter;

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
		response.setContentType("text/html;charset=UTF-8");
		
		String plainPassword = "123456789";

		// シンプルなハッシュ
		String hashed = DigestUtils.sha256Hex(plainPassword);
		System.out.println("SHA256ハッシュ（単純）      : " + hashed);

		// ソルト付きハッシュ（推奨）
		String salt = "randomSalt123";
		String saltedHash = DigestUtils.sha256Hex(plainPassword + salt);
		System.out.println("SHA256ハッシュ（ソルト付き）: " + saltedHash);
		
		PrintWriter out = response.getWriter();
		out.println("<p>パスワード: " + plainPassword + "</p>");
		out.println("<p>SHA256ハッシュ(単純)　　　：" + hashed + "</p>");
		out.println("<p>SHA256ハッシュ(ソルト付き)：" + saltedHash + "</p>");
		
	}
}