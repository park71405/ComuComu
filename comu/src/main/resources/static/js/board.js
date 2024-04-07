// 게시글 삭제
const deleteButton = document.getElementById('delete-btn');

if(deleteButton){
    deleteButton.addEventListener('click', event => {
        let no = document.getElementById('board-no').value;
        fetch(`/board/delete/${no}`, {
            method: `DELETE`
        })
        .then(() => {
            alert('삭제가 완료되었습니다.');
            location.replace('/boards');
        });
    });
}

// 게시글 수정
const modifyButton = document.getElementById('modify-btn');

if(modifyButton){
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let no = params.get('no');

        fetch(`/board/update/{no}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
        .then(() => {
            alert('수정되었습니다.');
            location.replace(`/board/${no}`);
        });
    });
}

// 게시글 수정
const createButton = document.getElementById('create-btn');

if(createButton){
    createButton.addEventListener('click', event => {
        fetch("/board/save", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value,
            }),
        }).then(() => {
            alert("등록이 완료되었습니다.");
            location.replace("/boards");
        });
    });
}