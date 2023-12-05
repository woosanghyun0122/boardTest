var updateBtn = document.getElementById('updateBtn');
var deleteBtn = document.getElementById('deleteBtn');
var id = document.getElementById('num').value;
var goHomeBtn = document.getElementById('goHome');

if(updateBtn){

    updateBtn.addEventListener('click',()=>{
        location.replace(`/board/update?id=${id}`);
    })
}

if(goHomeBtn){

    goHomeBtn.addEventListener('click',()=>{
        location.replace('/board/main');
    });
}


if(deleteBtn){

        deleteBtn.addEventListener('click',()=>{

            fetch(`/api/board/${id}`,{
                method:'DELETE'
            })
            .then(response =>{
                if(!response.ok){
                    alert('게시물 삭제에 실패하였습니다.');
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                else{
                    alert('삭제되었습니다.');
                    location.replace('/board/main');
                }
            })
            .catch(error =>{

                console.log('error',error);
            })
        })


}