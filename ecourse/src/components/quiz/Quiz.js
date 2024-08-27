import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router";
import { authAPIs, endpoints } from "../../configs/APIs";
import { Button, Card, Form } from "react-bootstrap";
import { MyUserContext } from "../../App";
// import { Form } from "react-router-dom";

const Quiz = () => {
    const { assignmentId } = useParams();

    const [questions, setQuestions] = useState([]);
    const [choices, setChoices] = useState({});
    const [selectedId, setSelectedId] = useState({});
    
    const user = useContext(MyUserContext);

    const loadQuestions = async () => {
        let res = await authAPIs().get(endpoints['questions'](assignmentId));
        setQuestions(res.data);

        res.data.forEach(async (question) => {
            let choiceRes = await authAPIs().get(endpoints['choices'](question.id));
            setChoices(prev => ({ ...prev, [question.id]: choiceRes.data }));
        });
    };

    const loadAnswer = async (e) => {
        e.preventDefault();
        try {
            // Prepare the array of answers
            const currentDate = new Date().toISOString();
            const answers = Object.entries(selectedId).map(([questionId, choiceId]) => ({
                choiceId: { id: choiceId },
                questionId: { id: questionId },
                userId: { id: user.id },
                createdDate: currentDate
            }));

            // Send the answers to the server
            for (let answer of answers) {
                await authAPIs().post(endpoints['answer'], answer, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
            }

            // console.info('Answers submitted:', res.data);
            alert('Answers saved successfully!');
        } catch (err) {
            console.error('Error saving answers:', err);
            alert('Failed to save answers.');
        }
    };

    useEffect(() => {
        loadQuestions();
    }, [assignmentId]);

    const handleChoiceSelect = (questionId, choiceId) => {
        setSelectedId(prevSelectedChoices => ({
            ...prevSelectedChoices,
            [questionId]: choiceId
        }));
    };

    return (
        <div className="container">
            <h1>Quiz for Assignment {assignmentId}</h1>
            <Form method="post" onSubmit={loadAnswer}>
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
                                            <p style={{ margin: "0px", marginLeft: "10px" }}>{c.content}</p>
                                        </div>
                                    ))}
                                </div>
                            ) : (
                                <p>Loading choices...</p>
                            )}
                        </Card.Body>
                    </Card>
                ))}
                <Button type="submit">Save All Answers</Button>
            </Form>
        </div>
    );
};

export default Quiz;
