import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import { authAPIs, endpoints } from "../../configs/APIs";
import { Button, Card } from "react-bootstrap";
import { differenceInMilliseconds, format, subHours } from "date-fns";

import './styleAssignments.css';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBell } from "@fortawesome/free-solid-svg-icons";
import { toast } from "react-toastify";

const Assignments = () => {
    const { courseId } = useParams();
    const [assignments, setAssignments] = useState([]);
    const [countUserDone, setCountUserDone] = useState({});
    const [enrollment, setEnrollment] = useState([]);
    const nav = useNavigate();

    const loadEnrollment = async () => {
        let res = await authAPIs().get(endpoints['enrollment'](courseId));
        setEnrollment(res.data);
    }

    const addNotic = async (e, assignment) => {
        try {
            e.preventDefault();

            for (let student of enrollment) {
                const noticData = {
                    title: "Hạn nộp bài tập",
                    message: `Bài tập ${assignment.name} trong khóa học ${assignment.courseId?.name} sắp hết hạn! Làm ngay nào!`,
                    userId: { id: student.userId?.id }
                };
    
                await authAPIs().post(endpoints['send-notic'], noticData, {
                    headers: {
                        'Content-Type': "application/json"
                    }
                });
            }

            // toast.success("Notification successful!");
        } catch (err) {
            toast.error('You had done before!');
        }
        
    }

    // const addNotic = async (e, assignment) => {
    //     try {
    //         e.preventDefault();
    
    //         const now = new Date();
    //         const dueDate = new Date(assignment.dueDate);

    //         // Tính toán thời gian cần hiển thị thông báo (1 giờ trước dueDate)
    //         const notificationTime = subHours(dueDate, 1); // Trừ đi 1 tiếng từ dueDate
    //         const timeUntilNotification = differenceInMilliseconds(notificationTime, now);
    
    //         if (timeUntilNotification > 0) {
    
    //             setTimeout(async () => {
    //                 for (let student of enrollment) {
    //                     const noticData = {
    //                         title: "Hạn nộp bài tập",
    //                         message: `Bài tập ${assignment.name} trong khóa học ${assignment.courseId?.name} sắp hết hạn! Làm ngay nào!`,
    //                         userId: { id: student.userId?.id }
    //                     };
    
    //                     await authAPIs().post(endpoints['send-notic'], noticData, {
    //                         headers: {
    //                             'Content-Type': "application/json"
    //                         }
    //                     });
    //                 }
    //                 toast.success("Notifications sent successfully!");
    //             }, timeUntilNotification);
    //         } else {
    //             toast.warning("Thời gian thông báo đã qua.");
    //         }
    
    //     } catch (err) {
    //         toast.error('An error occurred while sending notifications!');
    //     }
    // }
    

    const loadAssignments = async (courseId) => {
        let res = await authAPIs().get(endpoints['assignment-by-course'](courseId));
        setAssignments(res.data);
        // console.log(res.data);
        for (let assignment of res.data) {
            loadUserDone(assignment.id);
        }
    } 

    const loadUserDone = async (assignmentId) => {
        let res = await authAPIs().get(endpoints['count-userDone'](assignmentId));
        setCountUserDone(prevState => ({
            ...prevState,
            [assignmentId]: res.data // assuming res.data is the count
        }));
    }

    useEffect(() => {
        loadAssignments(courseId);
        loadEnrollment();
    }, [courseId]);

    const handleClick = (assignmentId) => {
        const url = endpoints['questions'](assignmentId);
        nav(url);
    }

    const handleAddQuestion = (assignmentId) => {
        // const url = endpoints['add-question'](assignmentId);
        nav(`/questions/assignments/${assignmentId}`);
    }

    return(
        <>
            {/* <h1>{courseId} 5455</h1> */}
            <div className="container mt-3">
                {assignments.map((assignment) => (
                    <Card style={{marginBottom: "20px"}}>
                        <Card.Header className="d-flex justify-content-between">
                            <div className="d-flex">
                                <div className="div-card-header"> Assignment name: {assignment.name}</div>

                                <div 
                                    onClick={(e) => addNotic(e, assignment)} 
                                    style={{marginLeft: "10px", marginTop: "15px"}}>
                                    <FontAwesomeIcon  
                                        icon={faBell} style={{color: "gold", cursor: "pointer"}} 
                                        className="icon-size" />
                                </div>
                                
                            </div>
                            
                            <Button onClick={() => handleClick(assignment.id)}>List questions</Button>
                            </Card.Header>
                        <Card.Body>
                            <div className="d-flex justify-content-between">
                                <Card.Title className="mb-3">Course: {assignment.courseId?.name}</Card.Title>
                                <Card.Title>{assignment.lessonId?.name}</Card.Title>
                            </div>
                            
                            <div>
                                <p className="text-tag font-weight">Type: {assignment.tagId?.name}</p>
                            </div>

                            <div>
                                <p>Số người hoàn thành: {countUserDone[assignment.id] || 0}</p>
                            </div>
                        </Card.Body>
                        <Card.Footer className="text-muted">
                            <div>
                                <div className="text-tag font-weight" >Created date: {format(assignment.createdDate, 'dd/MM/yyyy')}</div>
                                <div className="text-deadline font-weight">Deadline: {format(assignment.dueDate, 'dd/MM/yyyy')}</div>
                            </div>
                            <Button onClick={() => handleAddQuestion(assignment.id)}>Add question</Button>
                        </Card.Footer>
                        </Card>
                    ))}
            </div>
           
        </>
    );
}

export default Assignments;