import { useEffect, useState } from "react";
import { useLocation, useParams } from "react-router";
import { authAPIs, endpoints } from "../../configs/APIs";
import { Button, Form } from "react-bootstrap";

const UpdateChoices = () => {
    const location = useLocation();
    const questionId = location.state?.questionId;

    const [question, setQuestion] = useState(null);
    const [choice, setChoice] = useState({content: '', isCorrect: false, questionId: null });

    const loadQuestion = async (questionId) => {
        let res = await authAPIs().get(endpoints['question'](questionId));
        setQuestion(res.data);    
        // console.log(res.data);
    }

    useEffect(() => {
        loadQuestion(questionId);
    }, [questionId]);

    const loadChoice = async(e) => {
        e.preventDefault();

        const choiceData = {
            content: choice.content,
            isCorrect: isChecked,
            questionId: {id: questionId}
        };

        let res = await authAPIs().post(endpoints['add-choice'], choiceData, {
            headers: {
                'Content-Type':  "application/json"
            }
        })
        console.info(choiceData);

    }

    const change = (e, fields) => {
        setChoice({...choice, [fields]: e.target.value});
    }

    // const handleCheck = (e) => {
    //     const isChecked = e.target.checked;
    //     setChoice(prevChoice => ({ ...prevChoice, isCorrect: isChecked }));
    //     console.log("Checkbox changed:", isChecked);
    // }

    const [isChecked, setIsChecked] = useState(false);
    const handleCheckboxChange = () => {
        setIsChecked(!isChecked);
        console.log(!isChecked); // In ra giá trị của isChecked (true hoặc false)
      };



    return(
        <>
            <div className="container">
            <div className="container">
                <Form method="post" onSubmit={loadChoice}>
                    <div className="d-flex" >
                        <Form.Group style={{margin: "10px", width: "500px"}} className="mb-3" controlId="controliInputFirstname">
                            <Form.Label>Content: </Form.Label>
                            <Form.Control type="text" placeholder="Enter question name" 
                            value={choice.content}
                            onChange={e => change(e, "content")}  />
                        </Form.Group>
                    </div>
                    <div className="mb-3 d-flex" style={{marginLeft: "10px"}}>
                        <input
                        type="checkbox"
                        checked={isChecked}
                        onChange={handleCheckboxChange}
                        />
                        <p style={{ margin: "0px", marginLeft:"10px"}}>Câu trả lời đúng</p>
                    </div>
                    <div>
                    <Form.Group style={{margin: "10px", width: "500px"}} className="mb-3" controlId="controliInputEmail1">
                            <Form.Label className="form-label">Question: </Form.Label>
                            <Form.Control
                                type="text"
                                value={question ? question.name : 'Loading...'}
                                readOnly
                            />
                        </Form.Group>
                    </div>
                    <div>
                    <Button type="submit">Submit</Button>
                    </div>
                </Form>

             
            </div>
            </div>
        </>
    );
}

export default UpdateChoices;