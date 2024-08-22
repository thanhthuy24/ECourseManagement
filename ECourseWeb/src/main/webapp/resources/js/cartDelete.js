function deleteCart(endpoint, id) {
    if (confirm("Bạn có chắc muốn xóa mặt hàng này?") === true) {
        fetch(endpoint, {
            method: "delete"
        }).then(res => {
            if (res.status === 204) {
                let d = document.getElementById(`cart${id}`);
                d.style.display = "none";
            } else {
                alert("Không thể xóa mặt hàng này!");
            }
        });
    }
};

