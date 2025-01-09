document.addEventListener('DOMContentLoaded', function () {
    const calendarEl = document.getElementById('calendar');
    const calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: ['dayGrid'],
        initialView: 'dayGridMonth',
        events: fetchCalendarEvents,
        eventRender: renderEvent,
    });
    calendar.render();
});

function fetchCalendarEvents(fetchInfo, successCallback, failureCallback) {
    // 로그인 여부는 common.js에서 이미 확인되었으므로, 캘린더 데이터만 가져옵니다.
    fetch('/calendar')
        .then(response => response.json())
        .then(successCallback)
        .catch(failureCallback);
}

function renderEvent(info) {
    const eventEl = info.el;
    const img = document.createElement('img');
    img.src = info.event.extendedProps.posterUrl;
    img.alt = "포스터 이미지";
    img.className = 'poster-image';
    eventEl.innerHTML = '';
    eventEl.appendChild(img);
}
