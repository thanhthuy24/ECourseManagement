import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import APIs, { authAPIs, endpoints } from "../../configs/APIs";
import { Button, Card, Col, Image, ProgressBar, Row, Spinner } from "react-bootstrap";
import moment from 'moment';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar } from "@fortawesome/free-solid-svg-icons";
const CourseDetail = () => {
    const { id } = useParams();
    const [course, setCourse] = useState([]);
    const nav = useNavigate();

    const [avg, setAvg] = useState('');
    const [ratePercent, setRatePercent] = useState('');

    const loadCourse = async () => {
        let res = await APIs.get(endpoints['course'](id));
        setCourse(res.data);
    }

    useEffect(() => {
        loadCourse();
        loadAvgRating();
    }, [id])

    const handleClick = async (teacherId) => {
        nav(`teachers/${teacherId}`);
    }

    const loadAvgRating = async () => {
        try {
            let res = await authAPIs().get(endpoints['avg-rating'](id));
            setAvg(res.data);
        } catch(err) {
            console.error(err);
        }
    }

    const loadRatingIndex = async(start) => {
        try {
            let res = await authAPIs().get(endpoints['rating-percent'](start, id));
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
    }, []);

    return (
        <>
        <div className="container">
            <Row>
                <Col>
                    {course === null ? 
                        <Spinner animation="border" role="status">
                            <span className="visually-hidden">Loading...</span>
                        </Spinner>
                    : <>
                        {/* <h2>{course.id}</h2> */}
                        <Button className="font-size-bold margin btn-success">{course.tag?.name}</Button>
                        <h3 className="font-size-bold margin font-teacher-username">{course.name}</h3>
                        <h3 className="margin">
                        Price: {(course.price || 0)} VNĐ
                        </h3>

                        <h3 className="margin font-teacher-description border-description">
                            {course.description}
                        </h3>
                        <h3 className="margin">Created date: {moment(course.createdDate).format('DD/MM/YYYY')}</h3>
                        <hr/>
                        <Card>
                            <Card.Header>Thông tin giảng viên</Card.Header>
                            <Card.Body>
                            <Card.Title>Họ tên: {course.teacher?.user?.username}</Card.Title>
                                
                                <Card.Text>
                                Position: {course.teacher?.position}
                                </Card.Text>
                                <Card.Text>
                                Description: {course.teacher?.description}
                                </Card.Text>
                                <Button onClick={() => handleClick(course.teacher?.id)} >Detail</Button>
                            </Card.Body>
                            <Card.Footer></Card.Footer>
                        </Card>
                    </>    
                    }
                </Col>
                <Col className="margin">
                    <Image className="image" src={course.image} />

                </Col>
            </Row>
            <div>
                        <p className="font-size" style={{marginLeft: "15%"}}>Phản hồi của học viên</p>
                        <div className="d-flex" style={{justifyContent: "space-evenly"}}>
                            <div>
                                <p className="font-size" style={{marginLeft: "35%"}}>
                                {avg!== undefined ? avg.toLocaleString('en-US', { 
                                    minimumFractionDigits: 1, 
                                    maximumFractionDigits: 1 
                                }) : "Chưa có đánh giá"}
                                </p>
                                <p>
                                {[1, 2, 3, 4, 5].map((star) => (
                                        <span
                                            key={star}
                                            style={{
                                                cursor: 'pointer',
                                                color: star <= Math.round(avg) ? 'gold' : 'gray',
                                            }}
                                            // onClick={() => handleRating(star)}
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
            </div>
        </>
    );
}

export default CourseDetail;