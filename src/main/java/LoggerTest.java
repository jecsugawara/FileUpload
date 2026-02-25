/**
 * Loggerクラスを使ってログファイルにログを記述する
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoggerTest
 */
@WebServlet("/LoggerTest")
public class LoggerTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoggerTest() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String logPath = "C:\\log\\Sample.log";
		
		PrintWriter out = response.getWriter();
		out.println("<h1>Loggerクラスを使ってログファイルにログを出力するサンプル</h1>");
		out.println("<p>logPath: "+logPath + "</p>");
		
        //Loggerクラスのインスタンスを生成する
        try {
			Logger log = Logger.getLogger(FileUpload.class.getName());
			FileHandler fHandler = new FileHandler(logPath, true); //trueは追記モード

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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
