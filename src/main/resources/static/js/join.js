var duplicateBtn = document.getElementById('duplicateCheck');
var saveBtn = document.getElementById('joinButton');



if(duplicateBtn){

    duplicateBtn.addEventListener('click',()=>{
        var userid = document.getElementById('userid').value;
        fetch(`/api/user/${userid}`,{
            method:'GET'
        })
        .then(response =>{
            if(!response.ok){
                alert("이미 등록된 아이디가 있습니다.");
                document.getElementById('userid').style.color='red';
                document.getElementById('userid').style.fontWeight='900';
            }
            else{
                alert("사용 가능한 아이디 입니다.");
                document.getElementById('userid').style.color='black';
                 document.getElementById('userid').style.fontWeight='0';
            }
        })
    })

}

if (saveBtn) {
    saveBtn.addEventListener('click', () => {
        var password = document.getElementById('password').value;
        var pwCheck = document.getElementById('pwCheck').value;

        var userData ={
                        userid: document.getElementById('userid').value,
                        password: document.getElementById('password').value,
                        username: document.getElementById('username').value,
                        address: document.getElementById('address').value,
                        phone: document.getElementById('phone').value,
                        email: document.getElementById('email').value
                       }

        fetch('/api/user', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })
        .then(response => {
            if (password !== pwCheck) {
                alert("비밀번호가 일치하지 않습니다.");
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            if (!response.ok) {
                alert('회원가입에 실패했습니다.');
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            alert('회원가입이 완료되었습니다.');
            location.replace("/login");
        })
        .catch(error => {
            console.error(error);
        });
    });
}


