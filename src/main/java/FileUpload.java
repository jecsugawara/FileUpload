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

@WebServlet("/FileUpload")
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
		//文字コード設定
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");

        String uploadPath = "";

        // Eclipse開発環境での保存先のパスを取得
        uploadPath = request.getServletContext().getRealPath("/image/");
        
      /*
        // 本番環境では自身のクラスから@MultipartConfigアノテーションのlocationを取得
        MultipartConfig config = this.getClass().getAnnotation(MultipartConfig.class);
        if (config != null) {
            uploadPath = config.location();     // @MultipartConfigのlocationの値を取得
            System.out.println("保存先パス: " + uploadPath);
        }
      */

        // 保存先のフォルダが無い場合は作成する
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir(); // フォルダを作成
        }
    	
        // HTTPリクエストからファイル名を取得する。"uploadFile"はHTMLのinput name="uploadFile"と一致させる
        Part filePart = request.getPart("uploadFile");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        
        // アップロードしたファイルをサーバーに保存(@MultipartConfigのlocationに保存する場合)
        //filePart.write(fileName);						

        // アップロードしたファイルをサーバーに保存(Eclipse実行環境の場合)
        String uploadFilePath = uploadPath + fileName;
        filePart.write(uploadFilePath); 	
        System.out.println("保存先パス:" + uploadFilePath);
        
        try {
            Thread.sleep(5000); // 5秒間停止
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // プレビュー表示用JSPにファイル名を渡す
//      request.setAttribute("uploadedFileName", "/Kidda-La/image/" + fileName);   //これもOK
        request.setAttribute("uploadedFileName", "image/" + fileName);
        request.getRequestDispatcher("/preview.jsp").forward(request, response);
    }
}
