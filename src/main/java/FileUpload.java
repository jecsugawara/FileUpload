/**
 * ファイルアップロードのサンプル
 * fileUpload.html -> FileUpload.java -> preview.jsp 
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

@WebServlet("/FileUpload")
// ファイルサイズ制限などを設定
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class FileUpload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
        String UPLOAD_DIR = "resources";
        
		//文字コード設定
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");

        // 保存先のパスを取得
        String applicationPath = request.getServletContext().getRealPath("");
//    	String applicationPath = "Z:\\";
    	String uploadFilePath = applicationPath + UPLOAD_DIR + "\\"; 

// 10.64.144.101のTomcatのパス
//    	String applicationPath = "/opt/tomcat/apache-tomcat-10.1.52/webapps/";
//    	String uploadFilePath = applicationPath + UPLOAD_DIR + "/"; 
    	
        System.out.println(">>" + uploadFilePath);

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
        
        // 保存先のフォルダが無い場合は作成する
        File uploadDir = new File(uploadFilePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir(); // フォルダを作成
        }
    	
        // "uploadFile" は HTML の input name="uploadFile" と一致させる
        Part filePart = request.getPart("uploadFile");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        
        // アップロードしたファイルをサーバーに保存
        filePart.write(uploadFilePath + fileName);
        
        try {
            Thread.sleep(5000); // 5秒間停止
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // プレビュー表示用JSPにファイル名を渡す
        request.setAttribute("uploadedFileName", "/FileUpload/" + UPLOAD_DIR + "/" + fileName);
//      request.setAttribute("uploadedFileName", "/" + UPLOAD_DIR + "/" + fileName);
        request.getRequestDispatcher("/preview.jsp").forward(request, response);
    }
}
