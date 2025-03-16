<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div class="container py-8 max-w-screen-lg mx-auto">
    <div class="grid grid-cols-1 gap-6 md:grid-cols-3">
        <div class="md:col-span-2 space-y-6">
            <!-- 오늘 일정 블록 -->
            <div class="bg-white shadow-md rounded-lg p-6">
                <div class="mb-4">
                    <h2 class="text-xl font-semibold">오늘 일정</h2>
                    <p class="text-gray-600">오늘의 중요한 일정</p>
                </div>
                <div class="space-y-2">
                    <div class="flex items-center gap-3">
                        <div class="w-16 text-sm font-medium">09:00</div>
                        <div class="flex-1">아침 미팅</div>
                    </div>
                    <div class="flex items-center gap-3">
                        <div class="w-16 text-sm font-medium">12:30</div>
                        <div class="flex-1">점심 식사</div>
                    </div>
                    <div class="flex items-center gap-3">
                        <div class="w-16 text-sm font-medium">15:00</div>
                        <div class="flex-1">프로젝트 회의</div>
                    </div>
                    <div class="flex items-center gap-3">
                        <div class="w-16 text-sm font-medium">18:30</div>
                        <div class="flex-1">저녁 약속</div>
                    </div>
                </div>
                <div class="mt-4 text-center">
                    <a href="/schedule" class="bg-gray-300 py-2 px-4 rounded-md">일정 관리</a>
                </div>
            </div>

            <!-- 일기 게시판 블록 -->
            <div class="bg-white shadow-md rounded-lg p-6">
                <div class="mb-4">
                    <h2 class="text-xl font-semibold">일기</h2>
                    <p class="text-gray-600">최근 작성한 일기 목록</p>
                </div>
                <div class="space-y-4">
                    <div class="border-b pb-4">
                        <h3 class="font-medium">오늘의 일기 #1</h3>
                        <p class="text-sm text-gray-500">오늘은 정말 좋은 하루였습니다. 날씨도 좋고 기분도 좋았어요.</p>
                        <div class="mt-2 text-xs text-gray-500">2023년 5월 1일</div>
                    </div>
                    <div class="border-b pb-4">
                        <h3 class="font-medium">오늘의 일기 #2</h3>
                        <p class="text-sm text-gray-500">오늘은 정말 좋은 하루였습니다. 날씨도 좋고 기분도 좋았어요.</p>
                        <div class="mt-2 text-xs text-gray-500">2023년 5월 2일</div>
                    </div>
                    <div class="border-b pb-4">
                        <h3 class="font-medium">오늘의 일기 #3</h3>
                        <p class="text-sm text-gray-500">오늘은 정말 좋은 하루였습니다. 날씨도 좋고 기분도 좋았어요.</p>
                        <div class="mt-2 text-xs text-gray-500">2023년 5월 3일</div>
                    </div>
                </div>
                <div class="mt-4 text-center">
                    <a href="/diary" class="bg-gray-300 py-2 px-4 rounded-md">더보기</a>
                </div>
            </div>
        </div>

        <!-- 로그인 블록 -->
        <div class="space-y-6">
            <div class="bg-white shadow-md rounded-lg p-6">
                <h2 class="text-xl font-semibold">로그인</h2>
                <p class="text-gray-600 mb-4">계정에 로그인하세요</p>
                <form class="space-y-4">
                    <input type="email" class="w-full p-2 border rounded-md" placeholder="이메일" />
                    <input type="password" class="w-full p-2 border rounded-md" placeholder="비밀번호" />
                    <button type="submit" class="w-full bg-blue-600 text-white py-2 px-4 rounded-md">로그인</button> <!-- 로그인 버튼 색상 수정 -->
                </form>
                <div class="mt-4 text-center text-sm">
                    계정이 없으신가요? <a href="/register" class="text-blue-600 hover:underline">회원가입</a>
                </div>
            </div>

            <!-- 날씨 위젯 -->
            <div class="bg-white shadow-md rounded-lg p-6">
                <h2 class="text-xl font-semibold">오늘의 날씨</h2>
                <div class="flex items-center justify-between mt-4">
                    <div>
                        <div class="text-2xl font-bold">24°C</div>
                        <div class="text-gray-600">서울, 맑음</div>
                    </div>
                    <div class="text-5xl">☀️</div>
                </div>
            </div>

            <!-- 로또 번호 생성기 -->
            <div class="bg-white shadow-md rounded-lg p-6">
                <h2 class="text-xl font-semibold">로또 번호 생성기</h2>
                <p class="text-gray-600">행운의 번호를 생성해보세요</p>
                <div class="flex justify-between items-center gap-2 mt-4">
                    <div id="lotto-numbers" class="flex gap-2">
                        <!-- 동적으로 생성된 번호가 여기에 표시됩니다. -->
                    </div>
                </div>
                <div class="mt-4 text-center">
                    <button onclick="generateLottoNumbers()" class="bg-green-500 text-white py-2 px-4 rounded-md">번호 생성하기</button> <!-- 로또 번호 버튼 색상 수정 -->
                </div>
            </div>
        </div>
    </div>
</div>

<script>
  // 로또 번호 생성 함수
  function generateLottoNumbers() {
    // 1~45 사이의 랜덤한 숫자 6개 생성
    const numbers = [];
    while (numbers.length < 6) {
      const randomNumber = Math.floor(Math.random() * 45) + 1;
      if (!numbers.includes(randomNumber)) {
        numbers.push(randomNumber);
      }
    }

    // 생성된 번호를 오름차순으로 정렬
    numbers.sort((a, b) => a - b);

    // 번호를 HTML에 표시
    const lottoNumbersContainer = document.getElementById('lotto-numbers');
    lottoNumbersContainer.innerHTML = ''; // 기존 번호를 초기화
    numbers.forEach(num => {
      const numberElement = document.createElement('div');
      numberElement.classList.add('flex', 'h-10', 'w-10', 'items-center', 'justify-center', 'rounded-full', 'bg-green-500', 'text-white');
      numberElement.textContent = num;
      lottoNumbersContainer.appendChild(numberElement);
    });
  }
</script>
