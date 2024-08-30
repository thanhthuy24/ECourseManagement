import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import { authAPIs, endpoints } from "../../configs/APIs";
import { Button, Card } from "react-bootstrap";

const Questions = () => {
    const { assignmentId } = useParams();
    const [questions, setQuestions] = useState([]);
    const [choices, setChoices] = useState({});

    const nav = useNavigate();

    const loadQuestions = async () => {
        let res = await authAPIs().get(endpoints['questions'](assignmentId));
        setQuestions(res.data);
        console.log(res.data);
    }

    const loadChoices = async(questionId) => {
        let res = await authAPIs().get(endpoints['choices'](questionId));
        setChoices(prev => ({ ...prev, [questionId]: res.data }));
    }

    const handleAddChoice = (questionId) => {
        const url = endpoints['add-choice'];
        // nav(url);
        nav(url, { state: { questionId } });
    }

    useEffect(() => {
        loadQuestions();
    }, [assignmentId]);

    return(
        <>
            <div className="container mt-3">
                
                {questions.map((question) => (
                    <Card key={question.id} style={{marginBottom: "20px"}}>
                        <Card.Header className="d-flex justify-content-between">
                            <div
                                className="div-card-header">
                                {question.name}
                            </div>
                            <div>
                                <Button onClick={() => handleAddChoice(question.id)} className="button-update-choices">Update Choices</Button>
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
                                <Button onClick={() => loadChoices(question.id)} className="button-update-choices">Show choices</Button>
                                {choices[question.id] ? (
                                    <ul>
                                        {choices[question.id].map(c => (
                                            <li>{c.content}</li>
                                        ))}
                                    </ul>
                                ) : (
                                    <p></p>
                                )}
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