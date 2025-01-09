document.addEventListener('DOMContentLoaded', () => {
    const slideTrack = document.querySelector('.slide-track');
    let currentIndex = 0; // 첫 번째 이미지부터 시작 (인덱스는 0부터 시작)

    // 서버에서 슬라이드 데이터 로드
    fetch('http://localhost:8081/test1/SlideDataServlet')
        .then(response => response.json())
        .then(data => {
            slideTrack.innerHTML = ''; // 기존 슬라이드 초기화

            data.forEach(item => {
                const img = document.createElement('img');
                img.src = item.musicalPoster;
                img.alt = item.musicalTitle || "슬라이드 이미지"; // alt 속성 추가
                slideTrack.appendChild(img);
            });

            // 데이터 로드 후 슬라이드 초기화
            updateSlidePosition();
            setInterval(nextSlide, 3000);
        })
        .catch(error => console.error('Error loading slide data:', error));

    // 슬라이드 동작 초기화
    function updateSlidePosition() {
        const slides = slideTrack.children;
        const slideCount = slides.length; // 슬라이드의 총 개수 계산
        if (slideCount === 0) return; // 슬라이드가 없으면 동작 중지

        slideTrack.style.transform = `translateX(-${currentIndex * 240}px)`; // 한 슬라이드의 너비: 240px

        Array.from(slides).forEach((slide, index) => {
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
