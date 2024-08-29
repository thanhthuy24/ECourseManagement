import { useState } from "react";
import { useParams } from "react-router";

const Essay = () => {
    const { assignmentId } = useParams();

    const [questions, setQuestions] = useState([]);
    return(
        <>
            
        </>
    );
}

export default Essay;