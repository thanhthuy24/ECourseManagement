function deleteAssignment(endpoint, id) {
    if (confirm("Bạn có chắc muốn xóa bài tập này?") === true) {
        fetch(endpoint, {
            method: "delete"
        }).then(res => {
            if (res.status === 204) {
                let d = document.getElementById(`assignment${id}`);
                d.style.display = "none";
            } else {
                alert("Không thể xóa bài tập này!");
            }
        });
    }
};

