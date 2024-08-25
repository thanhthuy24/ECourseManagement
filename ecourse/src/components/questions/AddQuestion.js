import { useEffect, useState } from "react";
import { authAPIs, endpoints } from "../../configs/APIs";
import { useNavigate, useParams } from "react-router";
import { Button, Form, Spinner } from "react-bootstrap";

const AddQuestion = () => {
    const {courseId} = useParams();
    const [question, setQuestion] = useState('');
    const [tags, setTags] = useState([]);
    const nav = useNavigate();

    const loadAddQuestion = async (e) => {

        e.preventDefault();

        let form = new FormData();
        for (let key in question){
            form.append(key, question[key]);
            console.log(question[key]);
        }

        let res = await authAPIs().post(endpoints['add-question'], form, {
            headers: {
                'Content-Type': "multipart/form-data"
            }
        })

        console.info(res.data);
        if (res.status === 201){
            nav("/");
        }
    }

    const loadTags = async () =>{
        let res = await authAPIs().get(endpoints['tags']);
        setTags(res.data);
    }

    useEffect(() => {
        loadTags();
    }, [])

    const filterTags = tags.find(tag => tag.id >= 4 && tag.id <= 5);

    const change = (e, fields) => {
        setQuestion({...question, [fields]: e.target.value});
    }

    return(
        <>
            <div className="container">
            {question === null ? 
                    <Spinner animation="border" role="status">
                        <span className="visually-hidden">Loading...</span>
                    </Spinner>
                    : <>
                        <Form method="post" onSubmit={loadAddQuestion}>
                            <div className="d-flex" >
                                <Form.Group style={{margin: "10px", width: "500px"}} className="mb-3" controlId="controliInputFirstname">
                                    <Form.Label>question name: </Form.Label>
                                    <Form.Control type="text" placeholder="Enter question name" 
                                    value={question.name}
                                    onChange={e => change(e, "name")}  />
                                </Form.Group>
                            </div>
                            
                            <div className="d-flex" >
                                <Form.Group style={{margin: "10px", width: "500px"}} className="mb-3" controlId="controliInputEmail1">
                                    <Form.Label className="form-label">Tag: </Form.Label>
                                    <Form.Select aria-label="Default select example">
                                        {/* <option value={question.tagId?.name}>{question.tagId?.name}</option> */}
                                        {filterTags ? <>
                                            <option value={filterTags.id}
                                                 onChange={e => change(e, "tagId")}>{filterTags.name}</option>
                                        </> :
                                        <>
                                            <option value="">No option</option>
                                        </>
                                        }
                                    </Form.Select>
                                </Form.Group>
                                <Form.Group style={{ margin: "10px", width: "500px" }} className="mb-3" controlId="controliInputPhoneNumber">
                                    <Form.Label>Assignment: </Form.Label>
                                    <Form.Select aria-label="Default select example">
                                        <option value={question.assignmentId?.name}>Select a course</option>
                                    </Form.Select>
                                </Form.Group>
                            </div>
                            {/* <div className="d-flex">
                                <Form.Group style={{ margin: "10px", width: "500px" }} className="mb-3" controlId="controliInputPhoneNumber">
                                    <Form.Label>Lesson name: </Form.Label>
                                    <Form.Select aria-label="Default select example"
                                        value={question.lesson?.id || ''}
                                        onChange={e => change(e, "lesson")}>
                                        <option value="">Select a lesson</option>
                                        {filteredLessons.map(lesson => (
                                            <option key={lesson.id} value={lesson.id}>{lesson.name}</option>
                                        ))}
                                    </Form.Select>
                                </Form.Group>
                            </div> */}
                            <div className="d-flex" style={{justifyContent: "center", margin: "5px"}}>
                                <Button type="submit">Submit</Button>
                                {/* <Link to="/" className="nav-link" style={{color: "blue", fontWeight: "bold", margin: "3px"}}>Update</Link> */}
                            </div>
                        </Form> 
                        
                    </>    
                    }
            </div>
        </>
    );
}

export default AddQuestion;