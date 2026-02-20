<!-- 
ファイルアップロードのサンプル
fileUpload.html -> FileUpload.java -> preview.jsp 
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>プレビュー</title>
</head>
<body>
    <h2>アップロードされたファイル: ${uploadedFilePath} </h2>
    
    <!-- EL式でリクエストスコープの値を取得する -->
    <img src="${uploadedFilePath}" alt="Preview" style="max-width:500px;">
        
    <p><a href="fileUpload.html">別のファイルをアップロード</a></p>
</body>
</html>
