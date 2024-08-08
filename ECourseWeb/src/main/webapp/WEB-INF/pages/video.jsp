<%-- 
    Document   : video
    Created on : Aug 8, 2024, 8:51:41 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section>
    <form:form method="post" enctype="multipart/form-data" 
               style="margin-left: 20%" action="${action}" modelAttribute="video">

        <div style="background-color: white">
            <form:input path="file" type="file" accept="*" multiple="multiple"
                        class="form-control form-input" id="file" name="file" />

            <div class="mb-3 mt-3">
                <label for="name" class="form-label label-input">Course description:</label>
                <form:input path="name" type="text" class="form-control form-input"  id="name" placeholder="type description..." name="name" />
            </div>
            
            <button class="btn btn-success" type="submit">
                Add course
            </button>

            <c:forEach items="${videos}" var="c" >
                <p id="${c.id}">
                    ${c.name}
                </p>
            </c:forEach>
        </div>
    </div>
</form:form>

</section>


<style>
    .hidden {
        display: none;
    }
    #drop-zone {
        width: 20%;
        height: 200px;
        border: 2px dashed #ccc;
        border-radius: 4px;
        text-align: center;
        line-height: 200px;
        color: #ccc;
        margin: 10px;
    }
    #drop-zone.hover {
        border-color: #333;
        color: #333;
    }
</style>

<script>
    const dropZone = document.getElementById('drop-zone');
    const fileInput = document.getElementById('fileInput');

    dropZone.addEventListener('dragover', (e) => {
        e.preventDefault();
        dropZone.classList.add('hover');
    });

    dropZone.addEventListener('dragleave', () => {
        dropZone.classList.remove('hover');
    });

    dropZone.addEventListener('drop', (e) => {
        e.preventDefault();
        dropZone.classList.remove('hover');
        const files = e.dataTransfer.files;
        uploadFiles(files);
    });

    dropZone.addEventListener('click', () => {
        fileInput.click();
    });

    fileInput.addEventListener('change', () => {
        const files = fileInput.files;
        uploadFiles(files);
    });

    function uploadFiles(files) {
    const formData = new FormData();
            for (let i = 0; i < files.length; i++) {
    formData.append('file', files[i]);
    }

//        fetch('/videos', {
//            method: 'POST',
//            body: formData
//        }).then(response => response.text())
//                .then(result => alert('Upload successful: ' + result))
//                .catch(error => alert('Upload failed: ' + error));
//    }
</script>
