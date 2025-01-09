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

    // 로그인 상태 처리 함수
    const updateUI = ({ isLoggedIn, error }) => {
        const displayStyle = isLoggedIn ? 'none' : 'block';
        if (btnSignUp) btnSignUp.style.display = displayStyle;
        if (btnLogin) btnLogin.style.display = displayStyle;

        if (iconMy) {
            iconMy.onclick = () => {
                if (isLoggedIn) {
                    window.location.href = 'MyPage.html';
                } else {
                    alert(error || '로그인이 필요합니다!');
                    window.location.href = 'login.html';
                }
            };
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
