import { useParams } from "react-router";

const CourseDetail = () => {
    const { id } = useParams();
    return (
        <>
            <h3>{id}</h3>
        </>
    );
}

export default CourseDetail;