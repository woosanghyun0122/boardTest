var goWriteBtn = document.getElementById('goWrite');
var goHomeBtn = document.getElementById('goHome');
var searchBtn = document.getElementById('searchBtn');
var userid = document.getElementById('userid').value;

function moveToBoardView(id) {

    fetch(`/api/board/count/${id}`, {
        method: 'PUT'
    })
    .then(response => {
        if (response.ok) {
            location.href = `/board/view?id=${id}`;
        }
        else if(response.accepted){
            location.href = `/board/view?id=${id}`;
        }
    })
}

document.addEventListener('DOMContentLoaded', function() {
    var titles = document.getElementsByClassName('title');

    for (var i = 0; i < titles.length; i++) {
        titles[i].addEventListener('click', function() {
            var id = this.parentElement.querySelector('.num').value;
            moveToBoardView(id);
        });
    }
});


// JavaScript
if (searchBtn) {
    searchBtn.addEventListener('click', () => {
        var title = document.getElementById('search').value;
        fetch(`/api/board/${title}`, { // 수정: URL에 title을 경로 변수로 추가
            method: 'GET'
        })
        .then(response => {
            if (!response.ok) {
                alert("검색에 실패하였습니다.");
                throw new Error();
            } else {
                location.replace(`/board/search?title=${title}`);
            }
        })
        .catch(error => {
            console.error(error);
        });
    });
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
