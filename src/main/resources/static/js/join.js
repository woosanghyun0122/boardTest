var duplicateBtn = document.getElementById('duplicateCheck');
var saveBtn = document.getElementById('joinButton');

var userData ={
                userid: document.getElementById('userid'),
                password: document.getElementById('password'),
                username: document.getElementById('username'),
                address: document.getElementById('address'),
                phone: document.getElementById('phone'),
                email: document.getElementById('email')
               }


if(saveBtn){

    duplicateBtn.addEventListener('click',()=>{

            fetch('/api/user',{
                method: 'POST'
                headers:{
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            })
            .then(response =>{
                if(response != ok){
                    alert('회원가입에 실패했습니다.');
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }

            })
            .then(data=>{
                alert('회원가입이 완료되었습니다.');
                location.replace("/login");
            })
            .catch(error =>{
                console.error('Error':error);

            })
    })
}

