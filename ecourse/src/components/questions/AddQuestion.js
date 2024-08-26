import { useEffect, useState } from "react";
import { authAPIs, endpoints } from "../../configs/APIs";
import { useNavigate, useParams } from "react-router";
import { Button, Form, Spinner } from "react-bootstrap";

const AddQuestion = () => {
    const {assignmentId} = useParams();
    const [assignments, setAssignments] = useState(null);
    const [question, setQuestion] = useState({ name: '', assignmentId: null, tagId: null });
    const [tags, setTags] = useState([]);
    const nav = useNavigate();

    const loadAddQuestion = async (e) => {

        e.preventDefault();

        const questionData = {
            name: question.name,
            assignmentId: { id: assignmentId },
            tagId: parseInt(question.tagId)
        };

        let res = await authAPIs().post(endpoints['add-question'], questionData, {
            headers: {
                'Content-Type':  "application/json"
            }
        })
        const url = endpoints['questions'](assignmentId);
        nav(url);
    }

    const loadAssignments = async (assignmentId) => {
        let res = await authAPIs().get(endpoints['assignment'](assignmentId));
        setAssignments(res.data);
        console.info(res.data);
    } 

    const loadTags = async () =>{
        let res = await authAPIs().get(endpoints['tags']);
        setTags(res.data);
    }

    useEffect(() => {
        loadTags();
        loadAssignments(assignmentId);
    }, [assignmentId])

    const filterTags = tags.filter(tag => tag.id >= 4 && tag.id <= 5);

    const change = (e, fields) => {
        setQuestion({...question, [fields]: e.target.value});
    }

    return(
        <>
            <div className="container">
                <Form method="post" onSubmit={loadAddQuestion}>
                    <div className="d-flex" >
                        <Form.Group style={{margin: "10px", width: "500px"}} className="mb-3" controlId="controliInputFirstname">
                            <Form.Label>question name: </Form.Label>
                            <Form.Control type="text" placeholder="Enter question name" 
                            value={question.name}
                            onChange={e => change(e, "name")}  />
                        </Form.Group>
                    </div>
                    <div>
                    <Form.Group style={{margin: "10px", width: "500px"}} className="mb-3" controlId="controliInputEmail1">
                        <Form.Label className="form-label">Tag: </Form.Label>
                        <Form.Select aria-label="Default select example" onChange={e => change(e, "tagId")}>
                            {filterTags.map(tag => (
                                <option key={tag.id} value={tag.id}>{tag.name}</option>
                            ))}
                        </Form.Select>
                    </Form.Group>
                    </div>
                    <div>
                        <Form.Group style={{margin: "10px", width: "500px"}} className="mb-3" controlId="controliInputEmail1">
                            <Form.Label className="form-label">Assignment: </Form.Label>
                            <Form.Control
                                type="text"
                                value={assignments ? assignments.name : 'Loading...'}
                                readOnly
                            />
                        </Form.Group>
                    </div>
                    <div>
                    <Button type="submit">Submit</Button>
                    </div>
                </Form>
            </div>
        </>
    );
}

export default AddQuestion;