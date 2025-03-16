<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>오늘뭐함</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <header class="sticky top-0 z-40 w-full border-b bg-white">
        <div class="container mx-auto flex items-center justify-between p-4">
            <div class="flex items-center gap-2">
                <a href="/" class="text-2xl font-bold">오늘뭐함</a>
            </div>
            
            <nav class="flex items-center gap-6">
                <a href="/schedule" class="text-sm font-medium text-gray-700 hover:text-primary">일정</a>
                <a href="/diary" class="text-sm font-medium text-gray-700 hover:text-primary">일기</a>
                <a href="/lotto" class="text-sm font-medium text-gray-700 hover:text-primary">로또</a>
                <a href="/weather" class="text-sm font-medium text-gray-700 hover:text-primary">날씨</a>
                <a href="/restaurants" class="text-sm font-medium text-gray-700 hover:text-primary">밥집</a>
            </nav>
        </div>
    </header>
</body>
</html>
