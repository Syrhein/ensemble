document.addEventListener('DOMContentLoaded', function () {
    const calendarEl = document.getElementById('calendar');

    if (!calendarEl) {
        console.error('캘린더 DOM 요소를 찾을 수 없습니다.');
        return;
    }

    try {
        const calendar = new FullCalendar.Calendar(calendarEl, {
            plugins: ['dayGrid'],
            initialView: 'dayGridMonth',
            events: (fetchInfo, successCallback, failureCallback) => {
                // 호출 경로를 서버의 절대 경로로 설정
                fetch('http://localhost:8081/test1/CalendarServlet')
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`HTTP error! status: ${response.status}`);
                        }
                        return response.json();
                    })
                    .then(events => {
                        // 성공적으로 데이터를 가져온 경우 콜백 호출
                        successCallback(events);
                    })
                    .catch(error => {
                        console.error('캘린더 이벤트 데이터를 가져오는 데 실패했습니다:', error);
                        failureCallback(error); // 에러 콜백 호출
                    });
            },
            eventContent: function (info) {
                const eventEl = document.createElement('div');
                const img = document.createElement('img');
                img.src = info.event.extendedProps.posterUrl || './img/default-poster.jpg'; // 기본 이미지 처리
                img.alt = "포스터 이미지";
                img.className = 'poster-image';
                eventEl.appendChild(img);
                return { domNodes: [eventEl] };
            },
        });

        // 캘린더 렌더링
        calendar.render();
    } catch (error) {
        console.error('캘린더 초기화 중 오류가 발생했습니다:', error);
    }
});
