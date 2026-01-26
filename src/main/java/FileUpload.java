

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
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class FileUpload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
        String UPLOAD_DIR = "resources";
        
		//文字コード設定
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");

        // 保存先のパスを取得（webapp/uploads）
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + UPLOAD_DIR;
        System.out.println(">>" + uploadFilePath);
    	
    	
        // "uploadFile" は HTML の input name="uploadFile" と一致させる
        Part filePart = request.getPart("uploadFile");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        
        // 保存先のディレクトリ（適宜変更してください）
        String uploadPath = "/resources/";
        
        System.out.println("1:"+uploadPath+fileName); // (1) /resources/xxxx.png
        System.out.println("2:"+getServletContext().getRealPath(uploadPath+fileName)); 
        // (2)  2:U:\objworkspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Kidda-La\resources\スクリーンショット 2025-05-13 10595.png
        
        // アップロードしたファイルをサーバーに保存
        filePart.write(getServletContext().getRealPath(uploadPath + fileName));
        
        response.getWriter().println("ファイルアップロード完了: " + fileName);
        
        System.exit(0);
        
        try {
            Thread.sleep(5000); // 1秒間停止
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // プレビュー表示用JSPにファイル名を渡す
        request.setAttribute("uploadedFileName", "/Kidda-La" + uploadPath + fileName);
        request.getRequestDispatcher("/preview.jsp").forward(request, response);
    }
}
