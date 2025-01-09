document.addEventListener('DOMContentLoaded', () => {
    const btnSignUp = document.querySelector('.BtnSignUp');
    const btnLogin = document.querySelector('.BtnLogin');
    const iconMy = document.querySelector('.icon_my');

    // 로그인 상태 확인 함수
    const checkLoginStatus = () => {
        return fetch('http://localhost:8081/test1/CheckLoginCon') // CheckLoginCon 서블릿 호출
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            });
    };

    // 로그인 상태 처리 함수
    const updateUI = (isLoggedIn, error) => {
        if (btnSignUp && btnLogin) {
            if (isLoggedIn) {
                btnSignUp.style.display = 'none';
                btnLogin.style.display = 'none';
            } else {
                btnSignUp.style.display = 'block';
                btnLogin.style.display = 'block';
            }
        }

        if (iconMy) {
            iconMy.addEventListener('click', () => {
                if (isLoggedIn) {
                    window.location.href = 'MyPage.html';
                } else {
                    alert(error || '로그인이 필요합니다!');
                    window.location.href = 'login.html';
                }
            });
        }
    };

    // 로그인 상태 확인 및 UI 업데이트
    checkLoginStatus()
        .then(data => {
            const { isLoggedIn, error } = data;
            updateUI(isLoggedIn, error);
        })
        .catch(error => {
            console.error('Error checking login status:', error);
            alert('네트워크 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
        });
});
