document.addEventListener('DOMContentLoaded', () => {
    const slideTrack = document.querySelector('.slide-track');
    let currentIndex = 2; // 3번째 이미지부터 시작 (인덱스는 0부터 시작)

    // 서버에서 슬라이드 데이터 로드
    fetch('http://localhost:8081/test1/SlideDataServlet')
        .then(response => response.json())
        .then(data => {
            slideTrack.innerHTML = ''; // 기존 슬라이드 초기화

            data.forEach(item => {
                const img = document.createElement('img');
                img.src = item.musicalPoster;
                img.alt = item.musicalTitle;
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
        slideTrack.style.transform = `translateX(-${(currentIndex - 2) * 240}px)`;

        Array.from(slides).forEach((slide, index) => {
            slide.style.transform = index === currentIndex ? 'scale(1.2)' : 'scale(1)';
            slide.style.opacity = index === currentIndex ? '1' : (index === currentIndex - 1 || index === currentIndex + 1 ? '0.5' : '0');
        });
    }

    function nextSlide() {
        const slides = slideTrack.children;
        currentIndex++;
        if (currentIndex >= slides.length) {
            currentIndex = 2; // 다시 3번째 이미지로 루프
        }
        updateSlidePosition();
    }
});
