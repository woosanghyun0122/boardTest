var goHomeBtn = document.getElementById('goHome');
var goSaveBtn = document.getElementById('goSave');

if(goSaveBtn){
    goSaveBtn.addEventListener('click',()=>{

        var saveData = {
             title: document.getElementById('title').value,
             content: document.getElementById('content').value,
             writer: document.getElementById('author').value
        }

        fetch('/api/board',{
            method:'POST',
            headers:{
                'Content-Type':'application/json'
            },
            body:JSON.stringify(saveData)
        })
        .then(response =>{
            if(!response.ok){
                alert('게시물 작성에 실패하였습니다.');
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            else{
                alert('게시물 작성을 완료하였습니다.');
                location.replace('/board/main');
            }
        })
        .catch(error =>{
            console.error(error);

        });
    });
}

if(goHomeBtn){

    goHomeBtn.addEventListener('click',()=>{
        location.replace('/');
    });
}
