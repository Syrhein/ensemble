// common.js
document.addEventListener('DOMContentLoaded', () => {
    const btnSignUp = document.querySelector('.BtnSignUp');
    const btnLogin = document.querySelector('.BtnLogin');
    const iconMy = document.querySelector('.icon_my');

    // 로그인 상태 확인
    fetch('http://localhost:8081/test1/CheckLoginCon') // CheckLoginCon 서블릿 호출
        .then(response => response.json())
        .then(data => {
            const isLoggedIn = data.isLoggedIn;

            // 로그인 상태에 따라 UI 업데이트
            if (btnSignUp && btnLogin) { // 버튼이 존재할 경우에만 처리
                if (isLoggedIn) {
                    btnSignUp.style.display = 'none';
                    btnLogin.style.display = 'none';
                } else {
                    btnSignUp.style.display = 'block';
                    btnLogin.style.display = 'block';
                }
            }

            // My Icon 클릭 이벤트
            if (iconMy) {
                iconMy.addEventListener('click', () => {
                    if (isLoggedIn) {
                        window.location.href = 'MyPage.html';
                    } else {
                        alert('로그인이 필요합니다!');
                        window.location.href = 'login.html';
                    }
                });
            }
        })
        .catch(error => console.error('Error checking login status:', error));
});
