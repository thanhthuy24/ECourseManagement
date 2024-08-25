import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import { authAPIs, endpoints } from "../../configs/APIs";
import { Button, Card } from "react-bootstrap";

const Questions = () => {
    const { assignmentId } = useParams();
    const { courseId } = useParams();
    const [questions, setQuestions] = useState([]);
    const nav = useNavigate();

    const loadQuestions = async () => {
        let res = await authAPIs().get(endpoints['questions'](assignmentId));
        setQuestions(res.data);
    }

    const handleAddQuestion = () => {
        const url = endpoints['add-question'];
        nav(url);
    }

    useEffect(() => {
        loadQuestions();
    }, [assignmentId]);

    return(
        <>
            <h1>hghghg {courseId}</h1>

            <div className="container mt-3">
                <div>
                    <Button onClick={() => handleAddQuestion()}>Add question</Button>
                </div>
                {questions.map((question) => (
                    <Card style={{marginBottom: "20px"}}>
                        <Card.Header className="d-flex justify-content-between">
                            <div
                                className="div-card-header">
                                {/* style={{margin: "10px", padding: "10px", fontWeight: "bold"}}> */}
                                {question.name}
                            </div>
                            <div>
                                <Button className="button-update-choices">Update Choices</Button>
                            </div>
                            </Card.Header>
                        <Card.Body>
                            <div className="d-flex justify-content-between">
                                <Card.Title>Type: {question.tagId?.name}</Card.Title>
                            </div>
                            <div>
                                <p className="text-tag font-weight">Assignment name: {question.assignmentId?.name}</p>
                            </div>

                            {question.tagId?.name === "Quiz" ? <>
                                <p>Các lựa chọn: </p>
                            </> :
                            <>
                                <p>.</p>
                            </>}

                        </Card.Body>
                        <Card.Footer className="text-muted">
                            <div>Số người đã hoàn thành: </div>
                        </Card.Footer>
                        </Card>
                    ))}
            </div>
           

        </>
    );
}

export default Questions;