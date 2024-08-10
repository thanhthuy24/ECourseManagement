function deleteLesson(endpoint, id) {
    if (confirm("Bạn có chắc muốn xóa bài học này?") === true) {
        fetch(endpoint, {
            method: "delete"
        }).then(res => {
            if (res.status === 204) {
                let d = document.getElementById(`lesson${id}`);
                d.style.display = "none";
            } else {
                alert("Không thể xóa bài học này!");
            }
        });
    }
};
