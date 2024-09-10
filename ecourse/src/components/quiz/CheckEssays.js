import { useEffect, useState } from "react";
import { Button, Card, Col, Form, Row } from "react-bootstrap";
import { useLocation } from "react-router";
import { authAPIs, endpoints } from "../../configs/APIs";
import { ToastContainer, toast, Bounce  } from 'react-toastify';

const CheckEssays = ({u}) => {
    const location = useLocation();
    const assignmentId = location.state?.assignmentId;

    const [questions, setQuestions] = useState([]);
    const [essays, setEssays] = useState([]);
    const [score, setScore] = useState({});
    const [scoreDone, setScoreDone] = useState([]);
    const [notic, setNotic] = useState({});

    const loadEssays = async(assignmentId) => {
        let res = await authAPIs().get(endpoints['essay'](assignmentId));
        setEssays(res.data);
    }

    const loadScoreDone = async (essay) => {
        let res = await authAPIs().get(endpoints['scores'](assignmentId, essay.userId?.id));
        setScoreDone(res.data);
        console.log(res.data);
    }

    const loadQuestions = async (assignmentId) => {
        let res = await authAPIs().get(endpoints['questions'](assignmentId));
        setQuestions(res.data);
    };

    const addScoreEssay = async (e, essay, type) => {
        try {
            e.preventDefault();

            const scoreData = score[essay.id] || {};
            const scoreEssayData = {
                score: scoreData.score,
                feedBack: scoreData.feedBack,
                userId: {id: essay.userId?.id},
                assignmentId: {id: assignmentId}
            };

            const noticData = {
                title: "Bài tập đã có điểm!!",
                message: "Giảng viên đã nhận xét bài của bạn! Check ngay nhé!",
                userId: {id: essay.userId?.id},
            };
    
            await authAPIs().post(endpoints['add-score-essay'], scoreEssayData, {
                headers: {
                    'Content-Type':  "application/json"
                }
            })

            await authAPIs().post(endpoints['send-notic'], noticData, {
                headers: {
                    'Content-Type':  "application/json"
                }
            })

            toast.success("Completed!");
        } catch (err) {
            toast.error('You had done before!');
        }
        
    }

    useEffect(() => {
        essays.forEach(essay => {
            loadScoreDone(essay);
        });
    }, [essays]);

    useEffect(() => {
        loadQuestions(assignmentId);
        loadEssays(assignmentId);
    }, [assignmentId])

    const change = (e, field, essayId) => {
        setScore(prevScores => ({
            ...prevScores,
            [essayId]: {
                ...prevScores[essayId],
                [field]: e.target.value
            }
        }));
    }

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
                    {essays.map((essay, index) => (
                <Card className="mt-3" key={essay.id} style={{ marginBottom: "20px" }}>
                    <Card.Header className="d-flex justify-content-between">
                        <div className="div-card-header">
                            {essay.questionId?.name} - {essay.assignmentId?.name}
                        </div>
                    </Card.Header>
                    <Card.Body>
                        <Card.Title>User: {essay.userId?.username}</Card.Title>
                        <Card.Title>Assignment name: {essay.assignmentId?.name}</Card.Title>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                            <Form.Label>Answer:</Form.Label>
                            <Form.Control 
                                as="textarea" 
                                value={essay.content}
                                rows={3} 
                                disabled/>
                        </Form.Group>
                    </Card.Body>
                    <Card.Footer>
                    
                     </Card.Footer>
                   
                </Card>
            ))}
                    </Col>
                    <Col>
                        {essays.length > 0 && (<>
                            <Form method="post" onSubmit={(e) => addScoreEssay(e, essays[0])}>
                                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                                    <Form.Label>Score:</Form.Label>
                                    <Form.Control 
                                        type="number" 
                                        placeholder="Enter score" 
                                        value={score[essays[0].id]?.score || scoreDone.score}
                                        onChange={e => change(e, "score", essays[0].id)}
                                        />
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                                    <Form.Label>Feedback for student:</Form.Label>
                                    <Form.Control 
                                        as="textarea" 
                                        rows={3} 
                                        value={score[essays[0].id]?.feedBack || scoreDone.feedBack}
                                        onChange={e => change(e, "feedBack", essays[0].id)}
                                        />
                                </Form.Group>
                                {scoreDone ? (<>
                                    <Button type="submit" disabled>You'd checked!!</Button>
                                </>) : (<>
                                    <Button type="submit">Send feedBack</Button>
                                </>)}
                                
                            </Form>
                        </>)}
                    </Col>
                </Row>
                
            {/* {scoreDone ? (
            <Form method="post" onSubmit={(e) => addScoreEssay(e)}>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                    <Form.Label>Score:</Form.Label>
                    <Form.Control 
                        type="number" 
                        placeholder="Enter score" 
                        value={scoreDone.score || ''}
                        // onChange={e => change(e, "score", essay.id)}
                        />
                </Form.Group>
                <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                    <Form.Label>Feedback for student:</Form.Label>
                    <Form.Control 
                        as="textarea" 
                        rows={3} 
                        value={scoreDone.feedBack || ''}
                        // onChange={e => change(e, "feedBack", essay.id)}
                        />
                </Form.Group>
                <Button disabled type="submit">You'd checked!</Button>
            </Form>):( <></> )
            } */}
        </div>
        </>
    );
}

export default CheckEssays;