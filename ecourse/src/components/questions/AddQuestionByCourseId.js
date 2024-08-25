import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { authAPIs, endpoints } from "../../configs/APIs";
import { Button, Form } from "react-bootstrap";

const AddQuestionByCourseId = () => {
    const {courseId} = useParams();

    const [courses, setCourses] = useState([]);
    const [assignments, setAssignments] = useState([]);
    const [question, setQuestion] = useState({ name: '', assignmentId: null, tagId: null });

    const [tags, setTags] = useState([]);
    
    const loadCourses = async () =>{
        let res = await authAPIs().get(endpoints['courses']);
        setCourses(res.data);
    }

    const loadAssignments = async (courseId) => {
        let res = await authAPIs().get(endpoints['assignment-by-course'](courseId));
        setAssignments(res.data);
    } 

    const loadTags = async () =>{
        let res = await authAPIs().get(endpoints['tags']);
        setTags(res.data);
    }

    useEffect(() => {
        loadCourses();
        loadAssignments(courseId);
    }, [courseId]);

    const change = (e, fields) => {
        setQuestion({...question, [fields]: e.target.value});
    }

    const loadAddQuestion = async (e) => {

        e.preventDefault();

        // let form = new FormData();
        // for (let key in question){
        //     form.append(key, question[key]);
        //     console.log(question[key]);
        // }

        const assignmentObj = assignments.find(a => a.id === parseInt(question.assignmentId));
    
        const questionData = {
            name: question.name,
            assignmentId: assignmentObj, // Send the entire object
            tagId: parseInt(question.tagId)
        };

        let res = await authAPIs().post(endpoints['add-question'], questionData, {
            headers: {
                'Content-Type':  "application/json"
            }
        })
        // "application/json"multipart/form-data

        console.info(res.data);
    }

    useEffect(() => {
        loadTags();
    }, [])

    const filterTags = tags.filter(tag => tag.id >= 4 && tag.id <= 5);

    return (
        <>
            <h1>hiii {courseId}</h1>

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
                            {filterTags.length > 0 ? filterTags.map(tag => (
                                <option key={tag.id} value={tag.id}>{tag.name}</option>
                            )) : <option value="">No option</option>}
                        </Form.Select>
                    </Form.Group>
                    </div>
                    <div>
                        <Form.Group style={{margin: "10px", width: "500px"}} className="mb-3" controlId="controliInputEmail1">
                            <Form.Label className="form-label">Assignment: </Form.Label>
                            <Form.Select aria-label="Default select example" onChange={e => change(e, "assignmentId")}>
                                {assignments.length > 0 ? assignments.map(assignment => (
                                    <option key={assignment.id} value={assignment.id}>{assignment.name}</option>
                                )) : <option value="">No option</option>}
                            </Form.Select>
                        </Form.Group>
                    </div>
                    <div>
                    <Button type="submit">Submit</Button>
                    </div>
                </Form>
                
            </div>
            
            {/* {question ? <>
                <h1>{question.id}</h1>
            </> : <>
                <h1>ui</h1>
            </>} */}

        </>
    );
}

export default AddQuestionByCourseId;