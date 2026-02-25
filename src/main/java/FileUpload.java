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
    maxRequestSize = 1024 * 1024 * 50,   // 50MB
    location = "Z:\\fileupload\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\FileUpload"  // 保存場所 (絶対パスをしてすること)
)
public class FileUpload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
   	
        String UPLOAD_DIR = "resources";
        
		//文字コード設定
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");

        // 保存先のパスを取得
		String realPath = request.getServletContext().getRealPath(""); //-> Z:\fileupload\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\FileUpload\
//		String realPath = "Z:\\";  //Javaはどこでもアクセス可能だが、Webサーバーはコンテキストパス以下しかアクセスできない
    
        // 本番環境では自身のクラスから@MultipartConfigアノテーションのlocationを取得
        String location = "";
		MultipartConfig config = this.getClass().getAnnotation(MultipartConfig.class);
        if (config != null) {
            location = config.location();     // @MultipartConfigのlocationの値を取得
        }     

        // 保存先のフォルダが無い場合は作成する
        File uploadDir = new File(realPath + "\\" + UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdir(); // フォルダを作成
        }
        
        // HTTPリクエストからファイル名を取得する。"uploadFile"はHTMLのinput name="uploadFile"と一致させる
        Part filePart = request.getPart("uploadFile");

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        //アップロードしたファイルの保存場所は、(1)か(2)のどちらかで指定すること。
        //(1) request.getServletContext().getRealPath("")で取得したパスにアップロードしたファイルを保存する場合
        filePart.write(realPath + UPLOAD_DIR + "\\" + fileName);   //Z:\fileupload\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\FileUpload\resources
  
        //(2) @MultipartConfigアノテーションのlocationで取得したパスにアップロードしたファイルを保存する場合
        //    こちらの場合は、@MultiPartConfigのlocationの記述は不要である。
        //filePart.write(location + "\\" + UPLOAD_DIR + "\\" + fileName);   //Z:\fileupload\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\FileUpload\resources
        
        try {
            Thread.sleep(5000); // 5秒間停止
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // アップロードファイル名をプレビュー表示用JSPのリクエストスコープに保存する
        request.setAttribute("uploadedFilePath", request.getContextPath() + "/" + UPLOAD_DIR + "/" + fileName);
        request.setAttribute("uploadedRealPath", realPath + UPLOAD_DIR + "\\" + fileName);

        request.getRequestDispatcher("/preview.jsp").forward(request, response);
    }
}
