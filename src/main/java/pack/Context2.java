package pack;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

public class Context2 {
	public static void getAppRoot(HttpServletRequest request) {
		// コンテキストパスを取得
		System.out.println("getContextPath():" + request.getContextPath()); //-> /FileUpload
		System.out.println("getServletPath():" + request.getServletPath()); //-> /FileUpload
		System.out.println("getServletContext():" + request.getServletContext().getRealPath("")); //-> /FileUpload
		System.out.println("getRequestURI() :" + request.getRequestURI()); //-> /FileUpload/FileUpload
		System.out.println("getRequestURL() :" + request.getRequestURL()); //-> http://localhost:8080/FileUpload/FileUpload

	}

	public static void getContextPath(ServletContext servletContext) {
		// ServletContextからコンテキストパスを取得
		System.out.println(">>> " + servletContext.getContextPath()); //-> /FileUpload
	}
}
