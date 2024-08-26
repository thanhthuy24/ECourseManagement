import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import { authAPIs, endpoints } from "../../configs/APIs";
import { Button, Card } from "react-bootstrap";
import { format } from "date-fns";

import './styleAssignments.css';

const Assignments = () => {
    const { courseId } = useParams();
    const [assignments, setAssignments] = useState([]);
    const nav = useNavigate();

    const loadAssignments = async (courseId) => {
        let res = await authAPIs().get(endpoints['assignment-by-course'](courseId));
        setAssignments(res.data);
    } 

    useEffect(() => {
        loadAssignments(courseId);
    }, [courseId]);

    const handleClick = (assignmentId) => {
        const url = endpoints['questions'](assignmentId);
        nav(url);
    }

    const handleAddQuestion = (assignmentId) => {
        // const url = endpoints['add-question'](assignmentId);
        nav(`/questions/assignments/${assignmentId}`);
    }

    return(
        <>
            {/* <h1>{courseId} 5455</h1> */}
            <div className="container mt-3">
                {assignments.map((assignment) => (
                    <Card style={{marginBottom: "20px"}}>
                        <Card.Header className="d-flex justify-content-between">
                            <div
                                className="div-card-header">
                                {/* style={{margin: "10px", padding: "10px", fontWeight: "bold"}}> */}
                                Assignment name: {assignment.name}
                            </div>
                            
                            <Button onClick={() => handleClick(assignment.id)}>List questions</Button>
                            </Card.Header>
                        <Card.Body>
                            <div className="d-flex justify-content-between">
                                <Card.Title className="mb-3">Course: {assignment.courseId?.name}</Card.Title>
                                <Card.Title>{assignment.lessonId?.name}</Card.Title>
                            </div>
                            
                            <div>
                                <p className="text-tag font-weight">Type: {assignment.tagId?.name}</p>
                            </div>
                        </Card.Body>
                        <Card.Footer className="text-muted">
                            <div>
                                <div className="text-tag font-weight" >Created date: {format(assignment.createdDate, 'dd/MM/yyyy')}</div>
                                <div className="text-deadline font-weight">Deadline: {format(assignment.dueDate, 'dd/MM/yyyy')}</div>
                            </div>
                            <Button onClick={() => handleAddQuestion(assignment.id)}>Add question</Button>
                        </Card.Footer>
                        </Card>
                    ))}
            </div>
           
        </>
    );
}

export default Assignments;