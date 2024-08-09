function deleteVideo(endpoint, id) {
    if (confirm("Bạn có chắc muốn video này?") === true) {
        fetch(endpoint, {
            method: "delete"
        }).then(res => {
            if (res.status === 204) {
                let d = document.getElementById(`video${id}`);
                d.style.display = "none";
            } else {
                alert("Không thể xóa video!");
            }
        });
    }
};
