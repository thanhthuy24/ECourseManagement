function deleteCourse(endpoint, id) {
    if (confirm("Bạn có chắc muốn xóa khóa học này?") === true) {
        fetch(endpoint, {
            method: "delete"
        }).then(res => {
            if (res.status === 204) {
                let d = document.getElementById(`course${id}`);
                d.style.display = "none";
            } else {
                alert("Không thể xóa khóa học này!");
            }
        });
    }
};
