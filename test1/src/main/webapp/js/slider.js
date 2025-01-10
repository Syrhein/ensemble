document.addEventListener('DOMContentLoaded', () => {
    const slideTrack = document.querySelector('.slide-track');
    let currentIndex = 0; // 첫 번째 이미지부터 시작 (인덱스는 0부터 시작)

    // 서버에서 슬라이드 데이터 로드
    fetch('http://localhost:8081/test1/SlideDataServlet')
        .then(response => response.json())
        .then(data => {
            slideTrack.innerHTML = ''; // 기존 슬라이드 초기화

            // 슬라이드 데이터 동적 생성
            data.forEach(item => {
                const slideItem = document.createElement('a');
                slideItem.href = `detail.html?musicalId=${item.musicalId}`; // 상세 페이지로 연결
                slideItem.classList.add('slide-item'); // 스타일 적용을 위해 클래스 추가

                const img = document.createElement('img');
                img.src = item.musicalPoster; // 포스터 이미지
                img.alt = item.musicalTitle; // 이미지 대체 텍스트
                slideItem.appendChild(img);

                slideTrack.appendChild(slideItem);
            });

            // 데이터 로드 후 슬라이드 초기화
            updateSlidePosition();
            setInterval(nextSlide, 3000); // 3초마다 다음 슬라이드로 이동
        })
        .catch(error => console.error('Error loading slide data:', error));

    // 슬라이드 동작 초기화
    function updateSlidePosition() {
        const slides = Array.from(slideTrack.children); // 슬라이드 목록
        const slideCount = slides.length; // 슬라이드의 총 개수 계산
        if (slideCount === 0) return; // 슬라이드가 없으면 동작 중지

        // 슬라이드 이동 (한 슬라이드의 너비: 240px)
        slideTrack.style.transform = `translateX(-${currentIndex * 240}px)`;

        // 현재 슬라이드 강조
        slides.forEach((slide, index) => {
            slide.style.transform = index === currentIndex ? 'scale(1.2)' : 'scale(1)';
            slide.style.opacity = index === currentIndex ? '1' : '0.5';
        });
    }

    function nextSlide() {
        const slides = slideTrack.children;
        const slideCount = slides.length; // 슬라이드의 총 개수 계산

        currentIndex++;
        if (currentIndex >= slideCount) {
            currentIndex = 0; // 첫 번째 슬라이드로 루프
        }
        updateSlidePosition();
    }
});
