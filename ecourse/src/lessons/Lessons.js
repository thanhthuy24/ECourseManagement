import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router";
import { authAPIs, endpoints } from "../configs/APIs";
import { Card, Col, Form, Row } from "react-bootstrap";
import './styleLesson.css';
import ReactPlayer from 'react-player';
import { MyUserContext } from "../App";
import { ToastContainer, toast } from 'react-toastify';
import cookie from "react-cookies";

  import 'react-toastify/dist/ReactToastify.css';

const Lessons = () => {
    const { courseId } = useParams();
    const user = useContext(MyUserContext);
    const [lessons, setLessons] = useState([]);
    const [videos, setVideos] = useState([]);  
    const [watchedVideos, setWatchedVideos] = useState({});
    const [videoSrc, setVideoSrc] = useState("");
    const userId = user.id;

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
            // console.log(res.data);
        } catch(err) {
            console.error(err);
        }
    }

    // const loadVideosCompleted = async() => {
    //     try{
    //         let res = await authAPIs().get(endpoints['videosCompleted'], {
    //             params: {
    //                 userId: userId
    //             }
    //         });
    //         const watchedVideosData = res.data;
    //         const watchedVideosMap = {};
    //         watchedVideosData.forEach(video => {
    //             watchedVideosMap[video.videoId] = true; // Assuming response data has videoId
    //         });
    //         // setWatchedVideos(watchedVideosMap);
    //         console.log(res.data);
    //     } catch(err) {
    //         console.log(err);
    //     }
    // }

    const handleCheckboxChange = async (videoId) => {
        const isWatched = !watchedVideos[videoId];

        // Update the state locally
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
            toast.success("Video completion status saved successfully.");
        } catch (err) {
            toast.error("Error saving video completion status:", err);
            console.log(err);
        }

    }

    const handleVideoChange = (description) => {
        setVideoSrc(description);
    }

    useEffect(() => {
        loadLesson();
        loadVideos();
    }, [courseId, user.id]);

    return(
        <>
            <div>
                <h1>lesson with courseId: {courseId}</h1>
                <Row>
                <ToastContainer />
                    <Col sm={8}>
                    <div style={{marginLeft: "50px"}}>
                        <ReactPlayer
                            url={videoSrc}
                            width="900px"
                            height="500px"
                            playing={true}
                            controls={true}
                        />
                    </div>
                   
                    </Col>
                    <Col sm={4}>
                        {lessons === null ? 
                        <h1>Bài học hiện chưa có video nào được đăng tải</h1>
                        :(
                        <>
                            <Card>
                            <Card.Header>Nội dung khóa học</Card.Header>

                            {lessons.map((lesson) => {
                                    const filteredVideos = videos.filter(video => video.lessonId?.id === lesson.id);
                                    return (
                                        <Card.Body key={lesson.id}>
                                            <Card.Title>{lesson.name}</Card.Title>
                                            {filteredVideos.length > 0 ? (
                                                filteredVideos.map(video => (
                                                    <div key={video.id} className="d-flex card-border-video justify-content-between align-items-center">
                                                        <Card.Text onClick={() => handleVideoChange(video.description)}>
                                                            {video.name}
                                                        </Card.Text>
                                                        <Form.Check 
                                                            type="checkbox" 
                                                            label="Watched"
                                                            checked={watchedVideos[video.id] || false}
                                                            onChange={() => handleCheckboxChange(video.id)}
                                                        />
                                                    </div>
                                                ))
                                            ) : (
                                                <Card.Text>Không có video nào cho bài học này.</Card.Text>
                                            )}
                                            <hr style={{ borderColor: 'black', borderWidth: '1px', margin: "0px" }} />
                                        </Card.Body>
                                    );
                                })}

                            {/* {lessons.map((lesson) => (
                                
                                <Card.Body key={lesson.id}>
                                    <Card.Title> {lesson.name} VND</Card.Title>
                                    {videos && <>
                                        <Card.Text className="d-flex" style={{justifyContent: "space-around"}}>
                                        <div>
                                            Tên video: 
                                        </div>
                                        </Card.Text>
                                    </>}
                                   
                                   <div style={{borderColor: "black"}}></div>
                                 <hr style={{ borderColor: 'black', borderWidth: '1px' }} />

                                </Card.Body>
                            ))} */}
                            <Card.Footer className="text-muted">
                                hhhh
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