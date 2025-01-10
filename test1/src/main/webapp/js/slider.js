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
                slideItem.href = `detail.html?musicalId=${item.musicalId}`;
                slideItem.classList.add('slide-item');

                const img = document.createElement('img');
                img.src = item.musicalPoster;
                img.alt = item.musicalTitle;
                slideItem.appendChild(img);

                slideTrack.appendChild(slideItem);
            });

            // 데이터 로드 후 슬라이드 초기화
            updateSlidePosition();
            setInterval(nextSlide, 3000); // 3초마다 다음 슬라이드로 이동
        })
        .catch(error => console.error('Error loading slide data:', error));

    function updateSlidePosition() {
        const slides = Array.from(slideTrack.children); // 슬라이드 목록
        const slideCount = slides.length;
        if (slideCount === 0) return;

        // 모든 슬라이드에서 active 클래스 제거
        slides.forEach(slide => slide.querySelector('img').classList.remove('active'));

        // 현재, 이전, 다음 슬라이드에 클래스 추가
        slides[currentIndex].querySelector('img').classList.add('active'); // 현재 슬라이드
        if (currentIndex > 0) {
            slides[currentIndex - 1].querySelector('img').classList.add('active'); // 이전 슬라이드
        }
        if (currentIndex < slideCount - 1) {
            slides[currentIndex + 1].querySelector('img').classList.add('active'); // 다음 슬라이드
        }

        // 슬라이드 이동
        slideTrack.style.transform = `translateX(-${currentIndex * 240}px)`;
    }

    function nextSlide() {
        const slides = slideTrack.children;
        const slideCount = slides.length;

        currentIndex++;
        if (currentIndex >= slideCount) {
            currentIndex = 0; // 첫 번째 슬라이드로 루프
        }
        updateSlidePosition();
    }
});
