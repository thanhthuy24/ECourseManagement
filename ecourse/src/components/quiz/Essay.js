import { useContext, useEffect, useState } from "react";
import { ToastContainer, Bounce, toast  } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useParams } from "react-router";
import { Button, Card, Col, Form, Row } from "react-bootstrap";
import { MyUserContext } from "../../App";
import { authAPIs, endpoints } from "../../configs/APIs";

const Essay = () => {
    const { assignmentId } = useParams();

    const [questions, setQuestions] = useState([]);
    const [assignmentDone, setAssignmentDone] = useState([]);
    const [essay, setEssay] = useState({});

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
        // setScore(res.data);
    }

    const saveEssay = async (e) => {
        try {
            e.preventDefault();

            const esssayData = {
                content: essay.content,
                assignmentId: { id: assignmentId },
                questionId: { id: questions.id } 
            };
    
            let res = await authAPIs().post(endpoints['add-essay'], esssayData, {
                headers: {
                    'Content-Type': "application/json"
                }
            });
       
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
                                            id="content" 
                                            name="content" 
                                            as="textarea"
                                            rows={3}
                                            value={essay.content || ''} // Provide a default empty string if content is undefined
                                            onChange={e => change(e, "content")}
                                            type="text"
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
                                    {/* Render assignmentDone content */}
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