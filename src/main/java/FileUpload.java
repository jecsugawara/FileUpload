/**
 * ファイルアップロードのサンプル
 * fileUpload.html -> FileUpload.java -> preview.jsp 
 * <注意>
 * アップロード先のフォルダは絶対パスを指定することでどこにでも保存可能だが、
 * Webサーバーからアクセスできる場所に保存しないと、ブラウザから画像を表示できない。
 * コンテキストパス/image のように保存すること。
 * Eclipseで実行する場合は、request.getServletContext().getRealPath("/image");
 * のようにして、実行時の絶対パスを取得すること。このパスは一時的なもので、
 * デプロイするために変わる可能性があるので本番環境では使用してはいけない。
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/fileupload")
// ファイルサイズ制限などを設定
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50   // 50MB
//  location = "C:/Users/sugawara/Desktop/image/"  // 保存場所 (絶対パスをしてすること)
)
public class FileUpload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

//    	Context.getAppRoot(request);
 //  	Context2.getAppRoot(request);
    	
        String UPLOAD_DIR = "resources";
        
		//文字コード設定
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");

        // 保存先のパスを取得
    	String contextPath = request.getContextPath();    //-> /FileUpload
    	
//    	String path = request.getServletPath();    //-> /fileupload
//		String path = request.getServletContext(); //-> org.apache.catalina.core.ApplicationContextFacade@3a18669d
		String realPath = request.getServletContext().getRealPath(""); //-> Z:\fileupload\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\FileUpload\
//		String path = request.getRequestURI();     //-> /FileUpload/FileUpload
//		String path = request.getRequestURL().toString();     //-> http://localhost:8080/FileUploa0d/FileUpload
//		String path = "Z:\\";  //Javaはどこでもアクセス可能だが、Webサーバーはコンテキストパス以下しかアクセスできない
//		String path = "/opt/tomcat/apache-tomcat-10.1.52/webapps/";

        //Loggerクラスのインスタンスを生成する
        try {
			Logger log = Logger.getLogger(FileUpload.class.getName());
			FileHandler fHandler = new FileHandler("C:\\log\\Sample.log", true); //trueは追記モード

			fHandler.setFormatter(new SimpleFormatter());
			log.addHandler(fHandler);

			//ログをソールに出力する? (これが無くてもコンソールに表示）
			//ConsoleHandler cHandler = new ConsoleHandler();
			//log.addHandler(cHandler);

			// ログレベルの設定（必要に応じて）
			log.setLevel(Level.ALL);

			// 各レベルのログ出力
			log.severe("重大なエラー");
			log.warning("警告");
			log.info("情報メッセージ"); // デフォルトではINFO以上が表示される
			log.fine("普通の情報(fine)");     // 通常は表示されない
			log.finer("詳細の情報(finer)");     // 通常は表示されない
			log.finest("最も詳細の情報(finest)");     // 通常は表示されない
        }catch (IOException e){
        	e.printStackTrace();
        }
        
      /*
        // 本番環境では自身のクラスから@MultipartConfigアノテーションのlocationを取得
        MultipartConfig config = this.getClass().getAnnotation(MultipartConfig.class);
        if (config != null) {
            uploadPath = config.location();     // @MultipartConfigのlocationの値を取得
            System.out.println("保存先パス: " + uploadPath);
        }
      */

        // 保存先のフォルダが無い場合は作成する

        File uploadDir = new File(contextPath + "/" + UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdir(); // フォルダを作成
        }
    	
        // HTTPリクエストからファイル名を取得する。"uploadFile"はHTMLのinput name="uploadFile"と一致させる
        Part filePart = request.getPart("uploadFile");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // アップロードしたファイルをサーバーに保存
        filePart.write(realPath + "\\" + UPLOAD_DIR + "\\" + fileName);
        
        try {
            Thread.sleep(5000); // 5秒間停止
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        

        // プレビュー表示用JSPにアップロードファイル名を渡す
        request.setAttribute("uploadedFileName", contextPath + "/" + UPLOAD_DIR + "/" + fileName);
        request.getRequestDispatcher("/preview.jsp").forward(request, response);
    }
}
