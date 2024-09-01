import { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import { authAPIs, endpoints } from "../../configs/APIs";
import { Button, Card, CardFooter, Col, Form, Row } from "react-bootstrap";
import { MyUserContext } from "../../App";
import { ToastContainer, toast, Bounce  } from 'react-toastify';
import { format } from "date-fns";
// import { Form } from "react-router-dom";

const Quiz = () => {
    const { assignmentId } = useParams();

    const [questions, setQuestions] = useState([]);
    const [choices, setChoices] = useState({});
    const [correctChoices, setCorrectChoices] = useState({});
    const [selectedId, setSelectedId] = useState({});
    const [assignmentDone, setAssignmentDone] = useState([]);
    const [score, setScore] = useState({});
    const nav = useNavigate();
    
    const user = useContext(MyUserContext);

    const loadQuestions = async () => {
        let res = await authAPIs().get(endpoints['questions'](assignmentId));
        setQuestions(res.data);

        res.data.forEach(async (question) => {
            let choiceRes = await authAPIs().get(endpoints['choices'](question.id));
            setChoices(prev => ({ ...prev, [question.id]: choiceRes.data }));

            let correctChoiceRes = await authAPIs().get(endpoints['correct-choices'](question.id));
            setCorrectChoices(prev => ({ ...prev, [question.id]: correctChoiceRes.data }));
            // console.log(correctChoiceRes.data);
        });
    };

    const loadAssignmentDone = async () => {
        let res = await authAPIs().get(endpoints['userDone'](assignmentId, user.id));
        setAssignmentDone(res.data);
    }

    const loadScore = async () => {
        let res = await authAPIs().get(endpoints['scores'](assignmentId, user.id));
        setScore(res.data);
    }


    const loadAnswer = async (e) => {
        e.preventDefault();
        try {
            // Prepare the array of answers
            const currentDate = new Date().toISOString();
            const answers = Object.entries(selectedId).map(([questionId, choiceId]) => ({
                choiceId: { id: choiceId },
                questionId: { id: questionId },
                userId: { id: user.id },
                createdDate: currentDate,
                assignmentId: {id: assignmentId}
            }));

            // Send the answers to the server
            for (let answer of answers) {
                await authAPIs().post(endpoints['answer'], answer, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
            }

            await authAPIs().post(endpoints['score'](assignmentId, user.id));

            toast.success("Completed!");
            
        } catch (err) {
            toast.error('You had done before!');
        }
    };

    useEffect(() => {
        loadQuestions();
        loadAssignmentDone();
        loadScore();
    }, []);

    const handleChoiceSelect = (questionId, choiceId) => {
        setSelectedId(prevSelectedChoices => ({
            ...prevSelectedChoices,
            [questionId]: choiceId
        }));
    };

    return (
        <div className="container">
            <ToastContainer
                position="top-center"
                autoClose={1500}
                hideProgressBar
                newestOnTop
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
                theme="colored"
                transition= {Bounce}
                />
                <Row>
                    <Col>
                        <Form className="mt-3" method="post" onSubmit={loadAnswer}>
                            {questions.map((question, index) => (
                                <Card key={question.id} style={{ marginBottom: "20px" }}>
                                    <Card.Header className="d-flex justify-content-between">
                                        <div className="div-card-header">
                                            {`Question ${index + 1}: `}
                                            {question.name}
                                        </div>
                                    </Card.Header>
                                    <Card.Body>
                                        {choices[question.id] ? (
                                            <div>
                                                {choices[question.id].map(c => (
                                                    <div key={c.id} className="mb-3 d-flex" style={{ marginLeft: "10px" }}>
                                                        <input
                                                            type="checkbox"
                                                            checked={selectedId[question.id] === c.id}
                                                            onChange={() => handleChoiceSelect(question.id, c.id)}
                                                        />
                                                        {/* <p style={{ margin: "0px", marginLeft: "10px" }}>{c.content}</p> */}
                                                        <p style={{ margin: "0px", marginLeft: "10px" }}>
                                                            {c.content}
                                                        </p>
                                                    </div>
                                                ))}
                                            </div>
                                        ) : (
                                            <p>Loading choices...</p>
                                        )}
                                    </Card.Body>
                                    <Card.Footer>
                                    {assignmentDone.length > 0 ? (
                                        correctChoices[question.id]?.map((correctChoice) => (
                                            <div key={correctChoice.id} style={{ color: 'green', marginLeft: '10px', fontWeight: "bold" }}>
                                                Correct Answer: {correctChoice.content}
                                            </div>
                                        ))
                                    ) : (
                                        <></>
                                    )}
                                    </Card.Footer>
                                </Card>
                            ))}
                            {assignmentDone ? <>
                                <Button disabled type="submit">You'd done all Answers</Button>
                            </> : <>
                                <Button type="submit">Save All Answers</Button>
                            </>}
                            
                        </Form>
                    </Col>
                    <Col className="mt-3">
                        {assignmentDone ? <>
                            {assignmentDone.map(a => 
                                <Card key={a.id} border="danger" style={{ width: '18rem' }}>
                                    <Card.Header>Result</Card.Header>
                                    <Card.Body>
                                        <Card.Title>Answers: {score.score} / {questions.length}</Card.Title>
                                        <Card.Title>
                                            Feedback: {score.feedBack}
                                        </Card.Title>
                                        <Card.Text>
                                            <div className="text-tag font-weight" >Created date: {format(a.createdDate, 'dd/MM/yyyy')}</div>
                                        </Card.Text>
                                    </Card.Body>
                                </Card>
                            )}
                           
                        </> : <>
                            <h1>Chưa có kết quả</h1>
                        </>}
                    </Col>
                </Row>
            
        </div>
    );
};

export default Quiz;
