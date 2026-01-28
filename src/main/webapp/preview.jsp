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
    <h2>アップロードされたファイル: ${uploadedFileName}</h2>
    
    <!-- プレビュー表示: コンテキストパス/resources/ファイル名 
                    (例) /Kidda-La/resources/xxxx.png
    -->
    <img src="${uploadedFileName}" alt="Preview" style="max-width:500px;">
    
    <p><a href="fileUpload.html">別のファイルをアップロード</a></p>
</body>
</html>
