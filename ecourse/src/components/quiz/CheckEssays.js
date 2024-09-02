import { useContext, useEffect, useState } from "react";
import { Button, Card, Form } from "react-bootstrap";
import { useLocation, useParams } from "react-router";
import { authAPIs, endpoints } from "../../configs/APIs";
import { MyUserContext } from "../../App";
import { ToastContainer, toast, Bounce  } from 'react-toastify';

const CheckEssays = ({u}) => {
    const location = useLocation();
    const questionId = location.state?.questionId;
    const assignmentId = location.state?.assignmentId;

    const user = useContext(MyUserContext);

    const [questions, setQuestions] = useState([]);
    const [essays, setEssays] = useState([]);
    const [score, setScore] = useState({});
    const [scoreDone, setScoreDone] = useState([]);

    const loadEssays = async(assignmentId) => {
        let res = await authAPIs().get(endpoints['essay'](assignmentId));
        setEssays(res.data);
        // console.log(res.data);
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
    
            await authAPIs().post(endpoints['add-score-essay'], scoreEssayData, {
                headers: {
                    'Content-Type':  "application/json"
                }
            })
            toast.success("Completed!");
            // socket.emit("sendNotification", {
            //     senderName: "Giảng viên",
            //     receiverName: essay.userId?.username,
            //     type
            // })
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
            {/* <h1>check {questionId} -- {assignmentId}</h1> */}
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
                            <Form method="post" onSubmit={(e) => addScoreEssay(e, essay)}>
                                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                                    <Form.Label>Score:</Form.Label>
                                    <Form.Control 
                                        type="number" 
                                        placeholder="Enter score" 
                                        value={score[essay.id]?.score || ''}
                                        onChange={e => change(e, "score", essay.id)}
                                        />
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                                    <Form.Label>Feedback for student:</Form.Label>
                                    <Form.Control 
                                        as="textarea" 
                                        rows={3} 
                                        value={score[essay.id]?.feedBack || ''}
                                        onChange={e => change(e, "feedBack", essay.id)}
                                        />
                                </Form.Group>
                                <Button type="submit">Send feedBack</Button>
                            </Form>
                        </Card.Footer>
                    {/* {scoreDone.length > 0 ?
                    <>
                        <Card.Footer>
                            <Form method="post" onSubmit={(e) => addScoreEssay(e, essay)}>
                                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                                    <Form.Label>Score:</Form.Label>
                                    <Form.Control 
                                        type="number" 
                                        placeholder="Enter score" 
                                        value={score[essay.id]?.score || ''}
                                        onChange={e => change(e, "score", essay.id)}
                                        />
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                                    <Form.Label>Feedback for student:</Form.Label>
                                    <Form.Control 
                                        as="textarea" 
                                        rows={3} 
                                        value={score[essay.id]?.feedBack || ''}
                                        onChange={e => change(e, "feedBack", essay.id)}
                                        />
                                </Form.Group>
                                <Button type="submit">Send feedBack</Button>
                            </Form>
                        </Card.Footer>
                    </>    
                    : <>
                        <Card.Footer>
                            <Button>You'd have checked!</Button>
                        </Card.Footer>
                        
                    </>
                    } */}

                    
                </Card>
            ))}
        </div>
        </>
    );
}

export default CheckEssays;