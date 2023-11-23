var login = document.getElementById('login');

login.addEventListener('click',event =>{

    var userid = document.getElementById('userid').value;
    var password = document.getElementById('pw').value;
    fetch(`/api/user/${userid}/${password}`,{
        method: 'GET'
    })
    .then(response =>{
        if(!response.ok){
            alert("아이디와 비밀번호를 확인하세요");
        }
    })
    .then(()=>{
        location.replace('/');
    })
})
