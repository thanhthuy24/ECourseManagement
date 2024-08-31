import { useContext, useEffect, useState } from "react";
import { ToastContainer, Bounce, toast  } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate, useParams } from "react-router";
import { Button, Card, Col, Form, Row } from "react-bootstrap";
import { MyUserContext } from "../../App";
import { authAPIs, endpoints } from "../../configs/APIs";
import { format } from "date-fns";

const Essay = () => {
    const { assignmentId } = useParams();

    const [questions, setQuestions] = useState([]);
    const [assignmentDone, setAssignmentDone] = useState([]);
    const [essay, setEssay] = useState({});
    const [score, setScore] = useState({});
    const [essayDone, setEssayDone] = useState([]);
    const nav = useNavigate();

    const user = useContext(MyUserContext);

    const loadQuestions = async () => {
        let res = await authAPIs().get(endpoints['questions'](assignmentId));
        setQuestions(res.data);
        // console.log(res.data);
    };

    const loadAssignmentDone = async () => {
        let res = await authAPIs().get(endpoints['userDone'](assignmentId, user.id));
        setAssignmentDone(res.data);
        // console.log(res.data);
    }

    const loadScore = async () => {
        let res = await authAPIs().get(endpoints['scores'](assignmentId, user.id));
        setScore(res.data);
        console.log(res.data);
    }
    
    const loadEssayDone = async () => {
        let res = await authAPIs().get(endpoints['essay-user-done'](user.id));
        setEssayDone(res.data);
    }

    const saveEssay = async (e) => {
        try {
            e.preventDefault();

            const essaysData = Object.entries(essay).map(([questionId, content]) => ({
                content: content,
                userId: { id: user.id },
                assignmentId: { id: assignmentId },
                questionId: { id: questionId }
            }));
            
            // Post each essay to the server
            for (let essayData of essaysData) {
                await authAPIs().post(endpoints['add-essay'], essayData, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
            }
       
            toast.success("Completed!");
        } catch (err) {
            toast.error('You had done before!');
        }
    }

    const change = (e, field) => {
        setEssay({...essay, [field]: e.target.value});
    }
    
    useEffect(() => {
        loadQuestions();
        loadScore();
        loadAssignmentDone();
        loadEssayDone();
    }, []);

    return(
        <>
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
                        <Form className="mt-3" method="post" onSubmit={saveEssay}>
                            {questions.map((question, index) => (
                                <Card key={question.id} style={{ marginBottom: "20px" }}>
                                    <Card.Header className="d-flex justify-content-between">
                                        <div className="div-card-header">
                                            {`Question ${index + 1}: `}
                                            {question.name}
                                        </div>
                                    </Card.Header>
                                    <Card.Body>
                                    <Form.Group className="mb-3">
                                        <Form.Label>Answer here:</Form.Label>
                                        <Form.Control
                                                as="textarea"
                                                rows={3}
                                                // value={essay[question.id] || ''} // Bind the textarea value to the corresponding question ID
                                                onChange={e => change(e, question.id)} // Update the essay state with the question ID
                                                type="text"
                                                value={essayDone.find(e => e.questionId?.id === question.id)?.content || essay[question.id] || ''} 
                                            />
                                    </Form.Group>
                                    </Card.Body>
                                </Card>
                            ))}
                            {assignmentDone.length > 0 ? <>
                                <Button disabled type="submit">You'd done all Answers</Button>
                            </> : <>
                                <Button type="submit">Save All Answers</Button>
                            </>}
                            
                        </Form>
                    </Col>
                    <Col className="mt-3">
                        {assignmentDone.length > 0 ? (
                            assignmentDone.map(a => 
                                <Card border="danger" style={{ width: '18rem' }}>
                                    <Card.Header>Result</Card.Header>
                                    <Card.Body>
                                        <Card.Title>Score: {score.score}</Card.Title>
                                        <Card.Title>
                                            Feedback: {score.feedBack}
                                        </Card.Title>
                                        <Card.Text>
                                            <div className="text-tag font-weight" >Created date: {format(a.createdDate, 'dd/MM/yyyy')}</div>
                                        </Card.Text>
                                    </Card.Body>
                                </Card>
                            )
                        ) : (
                            <h1>No Results</h1>
                        )}
                    </Col>
                </Row>
            </div>
        </>
    );
}

export default Essay;