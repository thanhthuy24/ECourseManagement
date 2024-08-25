import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import APIs, { authAPIs, endpoints } from "../../configs/APIs";
import { Button, Form, Spinner } from "react-bootstrap";
import { format } from "date-fns";
import { Link } from "react-router-dom";

const AssignmentUpdate = () => {
    const {assignmentId} = useParams();
    const nav = useNavigate();

    const [assignment, setAssignment] = useState({});
    const [tags, setTags] = useState([]);
    const [courses, setCourses] = useState([]);
    const [lessons, setLessons] = useState([]);

    const [filteredLessons, setFilteredLessons] = useState([]);
    const [selectedCourseId, setSelectedCourseId] = useState('');

    const loadAssignment = async () => {
        let res = await authAPIs().get(endpoints['assignment'](assignmentId));
        setAssignment(res.data);
    }

    const uploadAssignment = async (e) => {
        e.preventDefault();

        let form = new FormData();
        for (let key in assignment){
            form.append(key, assignment[key]);
            console.log(assignment[key]);
        }

        let res = await authAPIs().post(endpoints['assignment'](assignmentId), form, {
            headers: {
                'Content-Type': "multipart/form-data"
            }
        });
        console.info(res.data);
        if (res.status === 201){
            nav("/");
        }
    }

    const loadTags = async () =>{
        let res = await authAPIs().get(endpoints['tags']);
        setTags(res.data);
    }

    const loadCourses = async () =>{
        let res = await authAPIs().get(endpoints['courses']);
        setCourses(res.data);
    }

    const loadLessons = async () => {
        let res = await authAPIs().get(endpoints['get-all-lessons']);
        setLessons(res.data);
    }

    const change = (e, field) => {
        setAssignment({...assignment, [field]: e.target.value});
    }

    useEffect(() => {
        loadAssignment();
        loadTags();
        loadCourses();
        loadLessons();
    }, [assignmentId])

    const filterTags = tags.find(tag => tag.id >= 4 && tag.id <= 5);

    useEffect(() => {
        if (selectedCourseId) {
            const filtered = lessons.filter(lesson => lesson.courseId === selectedCourseId);
            setFilteredLessons(filtered);
        } else {
            setFilteredLessons([]);
        }
    }, [selectedCourseId, lessons]);
    return (
        <>
            <h1>{assignmentId}</h1>
            <div className="container">
            {assignment === null ? 
                    <Spinner animation="border" role="status">
                        <span className="visually-hidden">Loading...</span>
                    </Spinner>
                    : <>
                        <Form method="post" onSubmit={uploadAssignment}>
                            <div className="d-flex" >
                                <Form.Group style={{margin: "10px", width: "500px"}} className="mb-3" controlId="controliInputFirstname">
                                    <Form.Label>Assignment name: </Form.Label>
                                    <Form.Control type="text" placeholder="Enter assignment name" 
                                    value={assignment.name}
                                    onChange={e => change(e, "name")}  />
                                </Form.Group>
                            </div>
                            
                            <div className="d-flex" >
                                <Form.Group style={{margin: "10px", width: "500px"}} className="mb-3" controlId="controliInputEmail1">
                                    <Form.Label className="form-label">Tag: </Form.Label>
                                    <Form.Select aria-label="Default select example">
                                        <option value={assignment.tag?.name}>{assignment.tag?.name}</option>
                                        {filterTags ? <>
                                            <option value={filterTags.id}
                                                 onChange={e => change(e, "tag")}>{filterTags.name}</option>
                                        </> :
                                        <>
                                            <option value="">No option</option>
                                        </>
                                        }
                                    </Form.Select>
                                </Form.Group>
                                <Form.Group style={{ margin: "10px", width: "500px" }} className="mb-3" controlId="controliInputPhoneNumber">
                                    <Form.Label>Course name: </Form.Label>
                                    <Form.Select aria-label="Default select example"
                                        value={selectedCourseId}
                                        onChange={e => setSelectedCourseId(e.target.value)}>
                                        <option value="">Select a course</option>
                                        {courses.map(course => (
                                            <option key={course.id} value={course.id}>{course.name}</option>
                                        ))}
                                    </Form.Select>
                                </Form.Group>
                            </div>
                            <div className="d-flex">
                                <Form.Group style={{ margin: "10px", width: "500px" }} className="mb-3" controlId="controliInputPhoneNumber">
                                    <Form.Label>Lesson name: </Form.Label>
                                    <Form.Select aria-label="Default select example"
                                        value={assignment.lesson?.id || ''}
                                        onChange={e => change(e, "lesson")}>
                                        <option value="">Select a lesson</option>
                                        {filteredLessons.map(lesson => (
                                            <option key={lesson.id} value={lesson.id}>{lesson.name}</option>
                                        ))}
                                    </Form.Select>
                                </Form.Group>
                            </div>
                            <div className="d-flex" style={{justifyContent: "center", margin: "5px"}}>
                                <Button type="submit">Submit</Button>
                                <Link to="/" className="nav-link" style={{color: "blue", fontWeight: "bold", margin: "3px"}}>Update</Link>
                            </div>
                        </Form> 
                        
                    </>    
                    }
            </div>
            
        </>
    );
}

export default AssignmentUpdate;