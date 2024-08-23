import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { authAPIs, endpoints } from "../../configs/APIs";

const Assignments = () => {
    const { courseId } = useParams();
    const [assignments, setAssignments] = useState([]);

    // const loadAssignments = async() => {
    //     let res = await authAPIs().get(endpoints['assignment-by-course'](courseId));
    //     setAssignments(res.data);
    //     // console.log(res.data);
    // } 

    // useEffect(() => {
    //     loadAssignments();
    // }, [courseId]);

    return(
        <>
            <h1>{courseId}</h1>
        </>
    );
}

export default Assignments;