function deleteTeacher(endpoint, id) {
    if (confirm("Bạn có chắc muốn xóa giáo viên này?") === true) {
        fetch(endpoint, {
            method: "delete"
        }).then(res => {
            if (res.status === 204) {
                let d = document.getElementById(`teachers${id}`);
                d.style.display = "none";
            } else {
                alert("Không thể xóa giáo viên!");
            }
        });
    }
};