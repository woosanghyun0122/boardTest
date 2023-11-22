var login = document.getElementById('login');

login.addEventListener('click',event =>{

    var userid = document.getElementById('userid').value;
    var password = document.getElementById('pw').value;
    fetch(`/api/user/${userid}/${password}`,{
        method: 'GET'
    })
    .then(()=>{
        location.replace('/');
    })
})
