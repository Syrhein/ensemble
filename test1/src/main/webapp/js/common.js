document.addEventListener('DOMContentLoaded', () => {
    const btnSignUp = document.querySelector('.BtnSignUp');
    const btnLogin = document.querySelector('.BtnLogin');
    const iconMy = document.querySelector('.icon_my');

    // 로그인 상태 확인 함수
    const checkLoginStatus = () => {
        return fetch('http://localhost:8081/test1/CheckLoginCon')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            });
    };

    // 로그아웃 버튼 생성 함수
    const createLogoutButton = () => {
        const logoutBtn = document.createElement('div');
        logoutBtn.classList.add('BtnLogout'); // 로그아웃 버튼 클래스 추가
        logoutBtn.innerHTML = '<div class="Logout">로그아웃</div>';
        
        // 기존 버튼과 동일한 스타일 적용 (로그인 + 회원가입 버튼 크기)
        logoutBtn.style.width = `${btnSignUp.offsetWidth + btnLogin.offsetWidth}px`;
        logoutBtn.style.height = btnSignUp.offsetHeight + 'px';

        // 클릭 이벤트 추가
        logoutBtn.addEventListener('click', () => {
            fetch('http://localhost:8081/test1/LogoutCon', { method: 'POST' })
                .then(response => {
                    if (response.ok) {
                        alert('로그아웃 되었습니다.');
                        window.location.href = 'Main.html'; // 메인 페이지로 리다이렉트
                    } else {
                        alert('로그아웃에 실패했습니다.');
                    }
                })
                .catch(error => {
                    console.error('Error during logout:', error);
                    alert('네트워크 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
                });
        });

        // "로그아웃" 버튼 추가
        const buttonsContainer = btnSignUp.parentElement;
        buttonsContainer.replaceChild(logoutBtn, btnSignUp);
        buttonsContainer.replaceChild(logoutBtn, btnLogin);
    };

    // 로그인 상태 처리 함수
    const updateUI = ({ isLoggedIn, error }) => {
        if (isLoggedIn) {
            if (btnSignUp) btnSignUp.style.display = 'none';
            if (btnLogin) btnLogin.style.display = 'none';

            // 로그아웃 버튼 표시
            createLogoutButton();
        } else {
            if (btnSignUp) btnSignUp.style.display = 'block';
            if (btnLogin) btnLogin.style.display = 'block';

            if (iconMy) {
                iconMy.onclick = () => {
                    alert(error || '로그인이 필요합니다!');
                    window.location.href = 'login.html';
                };
            }
        }
    };

    // 로그인 상태 확인 및 UI 초기화
    checkLoginStatus()
        .then(data => updateUI(data))
        .catch(error => {
            console.error('Error checking login status:', error);
            alert('네트워크 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
            updateUI({ isLoggedIn: false, error: '네트워크 오류' });
        });
});
