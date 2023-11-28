var goWriteBtn = document.getElementById('goWrite');
var goHomeBtn = document.getElementById('goHome');
var searchBtn = document.getElementById('searchBtn');
var userid = document.getElementById('userid').value;

if(searchBtn){
    searchBtn.addEventListener('click',()=>{

        var title = document.getElementById('search').value;
        console.log(title);
        fetch(`/api/board?title=${title}`,{
            method: 'GET'
        })
        .then(response =>{
            if(!response.ok){
                alert("검색에 실패하였습니다.");
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            else{
                location.replace(`/board/search?title=${title}`);
            }

        })
        .catch(error =>{
            console.error(error);
        })
    })
}


if(goHomeBtn){
    goHomeBtn.addEventListener('click',()=>{
        location.replace('/');
    });
}

if(goWriteBtn){
    goWriteBtn.addEventListener('click',()=>{
        if(userid == null || userid ==''){
            alert('로그인이 필요합니다.');
            location.replace('/login');
        }
        else{
                location.replace(`/board/write?userid=${userid}`);
            }
        })

}
