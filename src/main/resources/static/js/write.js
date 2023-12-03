var goHomeBtn = document.getElementById('goHome');
var goSaveBtn = document.getElementById('goSave');
var goUpdateBtn = document.getElementById('goUpdate');

if(goUpdateBtn){
    goUpdateBtn.addEventListener('click',()=>{
        var updateData = {
             title: document.getElementById('updateTitle').value,
             content: document.getElementById('updateContent').value,
        }
        var id = document.getElementById('boardId').value
        fetch(`/api/board/${id}`,{
            method: 'PUT',
            headers:{
                'Content-Type':'application/json'
            },
            body:JSON.stringify(updateData)
        })
        .then(response =>{
            if(!response.ok){
                alert("게시물 수정에 실패하였습니다.");
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            else{
                alert("게시물 수정에 성공하였습니다.");
                location.replace("/board/main");
            }

        })

    })
}

if(goSaveBtn){
    goSaveBtn.addEventListener('click',()=>{

        var saveData = {
             title: document.getElementById('newTitle').value,
             content: document.getElementById('newContent').value,
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
