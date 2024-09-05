import { useContext, useEffect, useRef, useState } from "react";
import { useNavigate, useParams } from "react-router";
import { authAPIs, endpoints } from "../../configs/APIs";
import { Alert, Button, Card, Col, Form, Nav, ProgressBar, Row } from "react-bootstrap";
import './styleLesson.css';
import ReactPlayer from 'react-player';
import { MyUserContext } from "../../App";
import { ToastContainer, toast } from 'react-toastify';

import 'react-toastify/dist/ReactToastify.css';
import { format, isAfter } from "date-fns";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar, faTriangleExclamation } from "@fortawesome/free-solid-svg-icons";

const Lessons = () => {
    const { courseId } = useParams();
    const user = useContext(MyUserContext);
    const [course, setCourse] = useState({});
    const [lessons, setLessons] = useState([]);
    const [videos, setVideos] = useState([]);  
    const [progress, setProgress] = useState("");
    const [watchedVideos, setWatchedVideos] = useState([]);
    const [activeTab, setActiveTab] = useState("overview");
    const [lessonId, setlessonId] = useState('');
    const [videoSrc, setVideoSrc] = useState("");
    const [assignmentDone, setAssignmentDone] = useState([]);
    const [enrollment, setEnrollment] = useState('');
    const [rating, setRating] = useState('');
    const [avg, setAvg] = useState('');
    const [ratePercent, setRatePercent] = useState('');

    const userId = user.id;
    const nav = useNavigate();

    const [assignments, setAssignment] = useState([]);

    const loadCourse = async () => {
        try {
            let res = await authAPIs().get(endpoints['course'](courseId));
            setCourse(res.data);
        } catch(err) {
            console.error(err);
        }
    }

    const loadRating = async () => {
        try {
            let res = await authAPIs().get(endpoints['check-rating'](user.id, courseId));
            setRating(res.data);
        } catch(err) {
            console.error(err);
        }
    }

    const loadAvgRating = async () => {
        try {
            let res = await authAPIs().get(endpoints['avg-rating'](courseId));
            setAvg(res.data);
        } catch(err) {
            console.error(err);
        }
    }

    const loadRatingIndex = async(start) => {
        try {
            let res = await authAPIs().get(endpoints['rating-percent'](start, courseId));
            setRatePercent(prevState => ({
                ...prevState,
                [start]: res.data
            }));
        } catch (err) {
            console.error(err);
        }
    }

    useEffect(() => {
        for (let i = 1; i <= 5; i++) {
            loadRatingIndex(i);
        }
    }, [rating]);

    const loadCountUserCourse = async () => {
        try {
            let res = await authAPIs().get(endpoints['count-user-course'](courseId));
            setEnrollment(res.data);
        } catch(err) {
            console.error(err);
        }
    }

    const loadLesson = async() => {
        try{
            let res = await authAPIs().get(endpoints['lessons'](courseId));
            setLessons(res.data);
            // console.log(res.data);
        } catch(err) {
            console.error(err);
        }
    }

    const loadVideos = async() => {
        try{
            let res = await authAPIs().get(endpoints['videos']);
            setVideos(res.data);
        } catch(err) {
            console.error(err);
        }
    }

    const loadProgress = async () => {
        try{
            let res = await authAPIs().get(endpoints['progress'](courseId, userId));
            setProgress(res.data);
            // console.log(progress);
        } catch(ex){
            console.log(ex);
        }
    }

    const loadVideosComplete = async() => {
        try {
            let res = await authAPIs().get(endpoints['videosCompleted'](userId));

            setWatchedVideos(res.data);
            // console.log('Watched Videos Map:', watchedVideosMap);
        } catch(ex) {
            console.log(ex);
        }
    }

    const loadAssignment = async(courseId) => {
        let res = await authAPIs().get(endpoints['user-assignments'](courseId));
        setAssignment(res.data);
    }

    const loadAssignmentDone = async (assignmentId) => {
        try {
            let res = await authAPIs().get(endpoints['userDone'](assignmentId, user.id));
            setAssignmentDone(prevState => ({
                ...prevState,
                [assignmentId]: res.data
            }));
        } catch (err) {
            console.log(err);
        }
    }

    useEffect(() => {
        if (assignments.length > 0) {
            assignments.forEach(assignment => {
                loadAssignmentDone(assignment.id);
            });
        }
    }, [assignments]);

    const handleCheckboxChange = async (videoId) => {
        const isWatched = !watchedVideos[videoId];
        
        setWatchedVideos(prevState => ({
            ...prevState,
            [videoId]: isWatched
        }));
    
        try {
            await authAPIs().post(endpoints['addCompleted'], null, {
                params: {
                    userId: userId, 
                    videoId: videoId
                }
            });
            loadProgress();
            toast.success("Video completion status saved successfully.");
        } catch (err) {
            toast.error("Error saving video completion status:", err);
            console.log(err);
        }
    }
    
    const handleVideoChange = (description, lessonId) => {
        setVideoSrc(description);
        setlessonId(lessonId);
        // console.log(lessonId);
    }

    useEffect(() => {
        loadCourse();
        loadCountUserCourse();
        loadRating();
        loadLesson();
        loadVideos();
        loadProgress();
        loadVideosComplete();
        loadAssignment(courseId);
        loadAvgRating();
    }, [courseId, user.id]);

    const ProgressClick = async () => {
        let res = await authAPIs().get(endpoints["progress"](courseId, userId));
        let progressValue = res.data;
        if (progressValue === 100) {
          try {
            let res2 = await authAPIs().post(
              endpoints["create-certificate"](courseId, userId)
            );
    
            const baseUrl = "http://localhost:8080/";
            const pdfUrl = `${baseUrl}${res2.data}`;
            console.log("PDF URL:", pdfUrl);
            window.open(pdfUrl);
          } catch (err) {
            console.log(err);
            toast.error("Có lỗi trong quá trình tải file pdf.");
          }
        } else {
          toast.error("Bạn chưa hoàn thành khóa học.");
        }
      };

    const [start, setStart] = useState(0);
    const [userRate, setUserRate] = useState({ comment: '', rating: null, courseId: null });

    const handleRating = (start) => {
        setStart(start);
        toast.success(`Bạn đang chọn ${start} sao!`);
    };

    const addRating = async(e) => {
        try {
            e.preventDefault();

            const rateData = {
                comment: userRate.comment ,
                rating: start,
                courseId: { id: courseId }
            };

            await authAPIs().post(endpoints['rating-course'](courseId), rateData, {
                headers: {
                    'Content-Type':  "application/json"
                }
            })
            toast.success("successful!");
            // console.log(userRate);
            await loadRating();
            await loadAvgRating();
        } catch(ex){
            toast.error("Error");
            console.error(ex);
        }
    }

    const change = (e, fields) => {
        setUserRate({...userRate, [fields]: e.target.value});
    }

    const renderTabContent = () => {
        switch(activeTab) {
            case "overview":
                return (
                <>
                <div style={{marginLeft: "10px"}}>
                    <div>
                        {/* <p  className="font-size">{course.name}</p> */}
                    </div>
                    <div className="d-flex" style={{justifyContent: "space-around"}}>
                        <div>
                            <p style={{marginLeft: "35%"}} className="font-size">{enrollment}</p>
                            <p>Học viên</p>
                            
                        </div>
                        <div style={{marginLeft: "15%"}}>
                            <div className="d-flex">
                                <p className="font-size">{typeof avg === 'number' && !isNaN(avg) ? avg.toFixed(1) : 0}
                                </p>
                                <FontAwesomeIcon style={{color: "gold", marginLeft: "5px", marginTop: "3px"}} icon={faStar} className="icon-size" />
                            </div>
                            <p>Xếp hạng</p>
                        </div>
                    </div>
                </div>
                </>);
            case "assignments":
                return (
                    <>
                        {assignments === null ? <>
                            <p>Không có bài tập nào được đăng lên!</p>
                        </> : <>
                            {assignments.map(assignment => {

                                const handleAssignmentClick = () => {
                                    if (assignment.tagId?.name === "Quiz") {
                                        nav(`/questions/assignment/${assignment.id}`)
                                    } else if (assignment.tagId?.name === "Essay") {
                                        nav(`/essays/assignment/${assignment.id}`)
                                    }
                                };

                                const isPastDue = isAfter(new Date(), new Date(assignment.dueDate));

                            return (
                                <Card style={{ marginBottom: "20px" }} >
                                    <Card.Header className="d-flex justify-content-between">
                                        <div className="div-card-header">
                                             Assignment name: {assignment.name}
                                        </div>
                                        <div>
                                            {assignmentDone[assignment.id]?.length > 0 ? (
                                                <Button onClick={() => handleAssignmentClick()} className="button-done">Done!</Button>
                                            ) : (
                                                <Button onClick={() => handleAssignmentClick()} disabled={isPastDue} className="button-not-done">Not Done</Button>
                                            )}
                                            </div>
                                    </Card.Header>
                                    <Card.Body>
                                        <div className="d-flex justify-content-between">
                                            <Card.Title>{assignment.lessonId?.name}</Card.Title>
                                        </div>
                                        
                                        <div>
                                            <p className="text-tag font-weight">Type: {assignment.tagId?.name}</p>
                                        </div>
                                    </Card.Body>
                                    <Card.Footer className="text-muted">
                                        <div>
                                            <div className="text-tag font-weight">Created date: {format(assignment.createdDate, 'dd/MM/yyyy')}</div>
                                            <div className="text-deadline font-weight">Deadline: {format(assignment.dueDate, 'dd/MM/yyyy')}</div>
                                        </div>
                                    </Card.Footer>
                                </Card>
                            );
                            }
                        )}
                        </>
                        }
                    </>
                );
                
            case "estimate":
                return <>
                
                    {rating.length > 0 ? (
                        <>
                            <div className="d-flex">
                                <h1 style={{marginTop: "5px"}}>Đánh giá của bạn: </h1>
                                <div style={{marginLeft: "20px"}}>
                                    {[1, 2, 3, 4, 5].map((star) => (
                                        <span
                                            key={star}
                                            style={{
                                                cursor: 'pointer',
                                                color: star <= rating[0].rating ? 'gold' : 'gray',
                                            }}
                                            onClick={() => handleRating(star)}
                                        >
                                            <FontAwesomeIcon icon={faStar} className="icon-size" />
                                        </span>
                                        
                                    ))}
                                     
                                </div>
                                <p style={{marginLeft: "20px"}}>({rating[0].rating} sao)</p>
                            </div>
                            <p>Nhận xét của bạn: </p>
                            <Form.Control 
                                as="textarea" 
                                rows={3} 
                                disabled
                                value={rating[0].comment || ''}
                            />
                            <hr/>
                        </>
                    ) : ( 
                        <>
                            <Form onSubmit={addRating}>
                                <div className="d-flex">
                                    <h1 style={{marginTop: "5px"}}>Đánh giá của bạn: </h1>
                                    <div style={{marginLeft: "20px"}}>
                                        {[1, 2, 3, 4, 5].map((star) => (
                                            <span
                                                key={star}
                                                style={{
                                                    cursor: 'pointer',
                                                    color: star <= rating.rating ? 'gold' : 'gray',
                                                }}
                                                onClick={() => handleRating(star)}
                                            >
                                                <FontAwesomeIcon icon={faStar} className="icon-size" />
                                                
                                            </span>
                                        ))}
                                        
                                    </div>
                                </div>
                                <p>Nhận xét của bạn: </p>
                            
                                <Form.Control 
                                    as="textarea" 
                                    rows={3} 
                                    value={userRate.comment}
                                    onChange={e => change(e, "comment")}
                                />
                                {progress < 100 ?
                                <>
                                <Button disabled type="submit">You'd had completed course before!!</Button>
                                </> :
                                <>
                                <Button type="submit">Gửi đánh giá</Button>
                                </>}
                                {/* <Button type="submit">Gửi đánh giá</Button> */}
                            </Form>
                            <hr/>
                        </>
                    )}
                    
                    <div>
                        <p className="font-size" style={{marginLeft: "35%"}}>Phản hồi của học viên</p>
                        <div className="d-flex" style={{justifyContent: "space-evenly"}}>
                            <div>
                                <p className="font-size" style={{marginLeft: "35%"}}>
                                    {avg !== null && avg !== undefined ? avg.toFixed(1) : 0}
                                </p>
                                <p>
                                {[1, 2, 3, 4, 5].map((star) => (
                                        <span
                                            key={star}
                                            style={{
                                                cursor: 'pointer',
                                                color: star <= Math.round(avg) ? 'gold' : 'gray',
                                            }}
                                            onClick={() => handleRating(star)}
                                        >
                                            <FontAwesomeIcon icon={faStar} className="icon-size" />
                                        </span>
                                    ))}
                                </p>
                                <p>Xếp hạng khóa học</p>
                            </div>
                            <div>
                                <p>Thanh Process cho từng sao</p>
                                {[1, 2, 3, 4, 5].map((star) => (
                                    <div
                                        key={star}
                                        style={{
                                            cursor: 'pointer',
                                            color: star <= Math.round(avg) ? 'gold' : 'gray',
                                            marginBottom: "20px",
                                            marginTop: "3px",
                                        }}
                                    >
                                            <ProgressBar variant="warning" now={ratePercent[star]} />  
                                    </div>
                                ))}
                                
                            </div>
                            <div>
                                <p>Phần trăm process</p>
                                {[1, 2, 3, 4, 5].map((star) => (
                                    <div
                                        key={star}
                                        style={{
                                            cursor: 'pointer',
                                            color: 'gray',
                                            marginBottom: "10px"
                                        }}
                                    >
                                        {star} sao - ({Math.round(ratePercent[star])}%)
                                           
                                    </div>
                                ))}
                                
                            </div>
                        </div>
                        
                    </div>
                    
                </>;
            default:
                return <div>Select a tab to see the content</div>;
        }
    }

    return(
        <>
            <div className="mt-3">
                <Row>
                <ToastContainer />
                    <Col sm={8}>
                    <Alert variant="info" style={{marginLeft: "30px"}}>Click vào video bài học muốn xem nhé!</Alert>
                    <div style={{marginLeft: "50px"}}>
                        <ReactPlayer
                            url={videoSrc}
                            width="900px"
                            height="500px"
                            playing={true}
                            controls={true}
                        />
                    </div>
                    <Nav fill className="mt-5" style={{marginLeft: "5px"}} justify variant="tabs" defaultActiveKey="/home">
                        <Nav.Item>
                            <Nav.Link eventKey="overview" onClick={() => setActiveTab("overview")}>
                            Tổng quan
                            </Nav.Link>
                        </Nav.Item>
                        <Nav.Item>
                        <Nav.Link eventKey="assignments" onClick={() => setActiveTab("assignments")}>
                            Bài tập và tài liệu
                            </Nav.Link>
                        </Nav.Item>
                        <Nav.Item>
                            <Nav.Link eventKey="estimate" onClick={() => setActiveTab("estimate")}>
                            Đánh giá khóa học
                            </Nav.Link>
                        </Nav.Item>
                        </Nav>
                        <div className="mt-4" style={{marginLeft: "5px"}}>
                            {renderTabContent()}
                        </div>
                    </Col>
                    <Col sm={4}>
                        {lessons === null ? 
                        <h1>Bài học hiện chưa có video nào được đăng tải</h1>
                        :(
                        <>
                            <Card>
                            <Card.Header>
                                <div style={{margin: "5px"}}>
                                    Nội dung khóa học
                                </div>
                                <div>
                                    <ProgressBar striped variant="success" now={progress} label={`${Math.round(progress)}%`} />
                                </div>
                            </Card.Header>

                            {lessons.map((lesson) => {
                                    const filteredVideos = videos.filter(video => video.lessonId?.id === lesson.id);
                                    return (
                                        <Card.Body key={lesson.id}>
                                            <Card.Title>{lesson.name}</Card.Title>
                                            {filteredVideos.length > 0 ? (
                                                filteredVideos.map(video => (
                                                    <div key={video.id} 
                                                        className={`d-flex card-border-video 
                                                                    justify-content-between 
                                                                    align-items-center 
                                                                    `
                                                                    }>
                                                        <Card.Text onClick={() => handleVideoChange(video.description, video.id)}>
                                                            {video.name}
                                                        </Card.Text>
                                                        <Form>
                                                            <Form.Check 
                                                                type="checkbox" 
                                                                label="Watched"
                                                                checked={watchedVideos[video.id] || false}
                                                                onChange={() => handleCheckboxChange(video.id)}
                                                            />
                                                        </Form>
                                                    </div>
                                                ))
                                            ) : (
                                                <Card.Text>Không có video nào cho bài học này.</Card.Text>
                                            )}
                                            <br style={{ borderColor: 'black', borderWidth: '1px', margin: "0px" }} />
                                            
                                        </Card.Body>
                                        
                                    );
                                })}
                                
                            <Card.Footer className="text-muted">
                                <Button onClick={ProgressClick}>Nhận chứng chỉ</Button>
                            </Card.Footer>
                        </Card>
                        </>)
                        }
                    </Col>
                </Row>
            </div>
        </>
    );
}

export default Lessons;