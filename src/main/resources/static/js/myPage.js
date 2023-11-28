var updateBtn = document.getElementById('updateButton');
var deleteBtn = document.getElementById('deleteButton');



if (updateBtn) {
    updateBtn.addEventListener('click', () => {

        var userData ={
                        userid: document.getElementById('userid').value,
                        username: document.getElementById('username').value,
                        password: document.getElementById('password').value,
                        address: document.getElementById('address').value,
                        phone: document.getElementById('phone').value,
                        email: document.getElementById('email').value
                       }
        var id = document.getElementById('id').value;
        fetch(`/api/user/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })
        .then(response => {

            if (!response.ok) {
                alert('회원정보 수정에 실패했습니다.');
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            alert('수정이 완료되었습니다.');
            location.replace("/");
        })
        .catch(error => {
            console.error(error);
        });
    });
}

if(deleteButton){

    deleteButton.addEventListener('click',()=>{

        var userid = document.getElementById('userid').value;
        fetch(`/api/user/${userid}`,{
            method:'DELETE'
        })
        .then(response =>{
            if(response.ok){
                alert("정상적으로 탈퇴되었습니다.");
                location.replace('/logout');
                location.replace('/');
            }
            else{
                alert("회원탈퇴에 실패하였습니다.");
                throw new Error(`HTTP error! Status: ${response.status}`)
            }

        })
        .catch(error =>{
            console.log(error);

        });

    });
}


