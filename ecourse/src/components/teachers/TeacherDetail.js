import { useState } from "react";
import { useParams } from "react-router";

const TeacherDetail = () => {
    const [teacher, setTeacher] = useState([]);
    const { id } = useParams();
    return (
        <>
            <div className="container">
                <h3>hi</h3>
                <h1>Detail Page for ID: {id}</h1>
            </div>
        </>
    );
}

export default TeacherDetail;