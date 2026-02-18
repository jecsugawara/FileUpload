import jakarta.servlet.http.HttpServletRequest;

public class Context {
	public static void getAppRoot(HttpServletRequest request) {
		// コンテキストパスを取得
		System.out.println("1:getContextPath():" + request.getContextPath()); //-> /FileUpload
		System.out.println("2:getServletPath():" + request.getServletPath()); //-> /FileUpload
		System.out.println("3:getServletContext():" + request.getServletContext()); //-> org.apache.catalina.core.ApplicationContextFacade@3a18669d
		System.out.println("4;getServletContext().getRealPath():" + request.getServletContext().getRealPath("")); //-> Z:\fileupload\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\FileUpload\
		System.out.println("5:getRequestURI() :" + request.getRequestURI());  //-> /FileUpload/FileUpload
		System.out.println("6:getRequestURL() :" + request.getRequestURL());  //-> http://localhost:8080/FileUpload/FileUpload
	}
}
