
function goUpdate(id){
    location.replace(`/board/update?id=${id}`);
}


function goDelete(id){

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


}